package bluetooth;

import java.io.DataInputStream;
import java.io.InputStream;

import javax.bluetooth.DiscoveryAgent;
import javax.bluetooth.LocalDevice;
import javax.bluetooth.ServiceRecord;
import javax.bluetooth.UUID;
import javax.microedition.io.Connector;
import javax.microedition.io.StreamConnection;
import javax.microedition.io.StreamConnectionNotifier;
import controller.MyMouseController;

/**
 * ����������
 * @author royen
 * @since 2010.2.10
 */
public class BlueService implements Runnable{
	//�����ʾ��
	private final static UUID SERVER_UUID=new UUID("F0E0D0C0B0A000908070605040302010",false);

	//������֪ͨ��
	private StreamConnectionNotifier notifier;

	/** �����豸������ */
	private LocalDevice localDevice=null;

	//�����¼
	ServiceRecord serviceRecord;	

	public BlueService(){

	}

	/**
	 * ��������
	 */
	public void startService(){
		new Thread(this).start();
	}

	public void run() {
		try{
			notifier=(StreamConnectionNotifier)Connector.open(getConnectionStr());

			serviceRecord=localDevice.getRecord(notifier);
		}
		catch(Exception ex){
			System.out.println("occur exception here: "+ex.getMessage());
		}

		while(true){
			StreamConnection conn=null;
			try{
				conn=notifier.acceptAndOpen();				
			}
			catch(Exception ex){
				System.out.println("occur exception when accept connection~");
				continue;
			}	

			//���������ӵĴ����߳�
			new Thread(new ProcessConnection(conn)).start();			
		}
	}

	/**
	 * ��ȡ�����ַ���
	 * @return
	 */
	private String getConnectionStr(){
		StringBuffer sb=new StringBuffer("btspp://");
		sb.append("localhost").append(":");
		sb.append(SERVER_UUID.toString());
		sb.append(";name=BlueMessage");
		sb.append(";authorize=false");
		return sb.toString();
	}	


	/**
	 * ������ʼ��
	 * @return
	 */
	public boolean initialBluetooth(){
		boolean btReady=true;		
		System.out.println("������ʼ��");
		try{
			localDevice=LocalDevice.getLocalDevice();
			if(!localDevice.setDiscoverable(DiscoveryAgent.GIAC)){
				btReady=false;
			}
		}
		catch(Exception e){
			btReady=false;
			e.printStackTrace();
		}		

		return btReady;
	}

	/**
	 * ����ͻ������ӵ��߳�
	 * @author royen
	 * @since 2010.1.25
	 */
	private class ProcessConnection implements Runnable{
		//������
		private StreamConnection conn=null;
		
		//��ȡ����
		private DataInputStream dis=null;
		
		public ProcessConnection(StreamConnection conn){
			this.conn=conn;		
		}
		
		public void run() {	
			System.out.println("client connected...");
			
			try{
//				System.out.println(11111);
				dis=conn.openDataInputStream();
//                System.out.println(222222);
				while(true){
//					System.out.println("true!");
					String code=readInputString();
//					System.out.println(333333);
					System.out.println("receive code: "+code);
					analysis(code);
				}

				
			}
			catch(Exception ex){

				System.out.println("occur exception ,message is "+ex.getMessage());
			}
		}
		
		
		
		private void analysis(String code){
			MyMouseController mmc=new MyMouseController();
			System.out.println("�ж�����"+Integer.parseInt(code.subSequence(0, 1).toString()));
			switch(Integer.parseInt(code.subSequence(0, 1).toString())){
			case 0:
				String Coordinate[]=code.substring(1).split(",");
				int width=Integer.parseInt(Coordinate[0]);
				int heigh=Integer.parseInt(Coordinate[1]);
				mmc.Move(width, heigh);
				break;
			case 1:
				String event[]=code.substring(1).split(",");				
				System.out.println("event��"+event[0]);
				if(event[0].equals("vc")||event[0].equals("leftc"))
					mmc.Click();
				if(event[0].equals("rightc"))
					mmc.RightClick();
				if(event[0].equals("press"))
					mmc.Press();
				if(event[0].equals("release"))
					mmc.Release();				
				break;
			}
//			i=Integer.parseInt(s)
			System.out.println("hanxu"+code);			
		}
		

		/**
		 * ��ȡ���ܵ�����
		 * @param conn
		 * @return
		 */
		private String readInputString(){			
			try{				
//				String msg=dis.readUTF();
				InputStream DataIn; 
				byte[] b = new byte[10];
				int imsg=dis.read(b);
				String msg= new String(b);
//				String msg=msg1.replace("\u0000", "");
	            System.out.println(msg);
//	            String msg1="";
//	            for(int i = 0; i < b.length; i++)
//	            {
//	            	String msg2=""+(char)b[i];
//	            	System.out.println("msg2��"+msg2);
//	            	if(!msg2.equals(""))	            		
//	                msg1 += (char)b[i];
//	            }
//	            System.out.println("1vc"+msg1.equals("1vc"));	            
//                System.out.println("msg1��"+msg1);
//	            if(b.equals("1liftc"))
//	            	System.out.println("��...û����");
//	            if(b.equals("1liftc    "))
//	            	System.out.println("��....");
				return msg;
			}
			catch(Exception ex){
				System.out.println("occur exception when read data~");
				return ex.getMessage();
			}			
		}	
	}

}
