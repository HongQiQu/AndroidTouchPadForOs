package zhenguo.hanxu;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.UUID;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;

public class Bluetooth extends Thread {
	
	  /**
	 * 
	 */

	BluetoothAdapter _bluetooth = BluetoothAdapter.getDefaultAdapter();
	  final UUID _UUID = UUID.fromString("F0E0D0C0-B0A0-0090-8070-605040302010");                                        
      public  BluetoothSocket mmSocket;
      public  OutputStream mmOutStream;
      public  BluetoothSocket tmp = null;
      

      
      
      public void Connect(String address){
    	    System.out.println("开始连接");
    		BluetoothDevice device = _bluetooth.getRemoteDevice(address);
    		
    		try {
    			tmp = device.createRfcommSocketToServiceRecord(_UUID);
    		} catch (IOException e1) {
    			// TODO Auto-generated catch block
    			e1.printStackTrace();
    		}
    		   mmSocket = tmp;	
//        	try {
//    			mmSocket.connect();
//        } catch (IOException e) {
//    		try {
//    			mmSocket.close();
//    		} catch (IOException e2) {
//    		}
//        }       	
        	       	
      }
      
      public void run() { 
 

   try { 
	        mmSocket.connect(); 
   } catch (IOException connectException) { 
       try { 
    	   mmSocket.close(); 
       } catch (IOException closeException) { } 
       return; 
   } 

 //      manageConnectedSocket(cwjSocket);  //管理一个已经连接的RFCOMM通道在单独的线程。
} 

      public void prewrite(){
    	  OutputStream tmpOut = null;
//          System.out.println("预备发送的数据是"+datas);
          try {
              tmpOut = mmSocket.getOutputStream();
//              System.out.println("第二次预备发送的数据是"+datas);
//              System.out.println(tmpOut);
          } catch (IOException e) {
            e.printStackTrace();
          }

           mmOutStream = tmpOut;
//           System.out.println(mmOutStream);
           System.out.println("预备成功？");
    	  
      }
      public void write(String datas){
//      	try {
//      	    System.out.println("tmp"+tmp);
//  			mmSocket.connect();
//      } catch (IOException e) {
//  		try {
//  			mmSocket.close();
//  		} catch (IOException e2) {
//  		}
//      } 
         
 			try {	
               mmOutStream.write(datas.getBytes());
               System.out.println("发送的数据是"+datas);
           } catch (IOException e) {
         	  e.printStackTrace();
           }
        
// 			try{
// 				mmOutStream.close();
// 			}catch(Exception e){			
// 				e.printStackTrace();
// 			}
 		
     	  
       }

      public void cancel() { 
          try { 
              mmSocket.close(); 
          } catch (IOException e) { } 
      } 
  
}
