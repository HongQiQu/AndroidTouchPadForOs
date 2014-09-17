package zhenguo.hanxu;

import java.io.IOException;
//import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.Set;
import java.util.UUID;

import zhenguo.hanxu.R;




import android.app.Activity;
import android.bluetooth.*;
import android.os.Bundle;

public class AndroidBluetooth extends Activity {
//	private	BluetoothAdapter bta;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        /* 取得默认的蓝牙适配器 */
    	BluetoothAdapter _bluetooth = BluetoothAdapter.getDefaultAdapter();
    	System.out.println(_bluetooth.getAddress()+_bluetooth.getName());
    	// Unique UUID for this application
        final UUID _UUID = UUID.fromString("F0E0D0C0-B0A0-0090-8070-605040302010");                                        
    	final BluetoothSocket mmSocket;
        String address = null;
    	_bluetooth.startDiscovery();
        Set<BluetoothDevice> devices=_bluetooth .getBondedDevices();
        if(devices.size()>0){
        	for(Iterator<BluetoothDevice> iterator=devices.iterator();
        			iterator.hasNext();)
        	{
        		BluetoothDevice btd=(BluetoothDevice)iterator.next();
        		address=btd.getAddress();
        		System.out.println(btd.getAddress());
        	}
    	BluetoothDevice device = _bluetooth.getRemoteDevice(address);
    	System.out.println(111);
     	BluetoothSocket tmp = null;
		try {
			tmp = device.createRfcommSocketToServiceRecord(_UUID);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        mmSocket = tmp;
    	try {
    			// This is a blocking call and will only return on a
    			// successful connection or an exception
    			mmSocket.connect();
        } catch (IOException e) {
//    		connectionFailed();
    		// Close the socket
    		try {
    			mmSocket.close();
    		} catch (IOException e2) {
//  			Log.e(TAG, "unable to close() socket during connection failure", e2);
    		}
//            Start the service over to restart listening mode
        }
        
//	     final InputStream mmInStream;
         final OutputStream mmOutStream;

//            InputStream tmpIn = null;
             OutputStream tmpOut = null;

             // Get the BluetoothSocket input and output streams
             try {
//                tmpIn = mmSocket.getInputStream();
                 tmpOut = mmSocket.getOutputStream();
             } catch (IOException e) {
//               Log.e(TAG, "temp sockets not created", e);
             }

//            mmInStream = tmpIn;
              mmOutStream = tmpOut;
              
            System.out.println(22222);  
            String buffer="abc";  
//              byte[] buffer = new byte[1024];
//              int bytes;
  			try {
  				  System.out.println(33333);
                  mmOutStream.write(buffer.getBytes());
                  System.out.println(444444);
                  System.out.println(buffer);
                  // Share the sent message back to the UI Activity
              } catch (IOException e) {
//                 Log.e(TAG, "Exception during write", e);
            	  e.printStackTrace();
              }

    	
    }
   }
} 