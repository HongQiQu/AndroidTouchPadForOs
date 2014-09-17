package zhenguo.hanxu;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import zhenguo.hanxu.R;

import android.app.ListActivity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class BluetoothActivity extends ListActivity {
	private	BluetoothAdapter bta;
   	Bluetooth bt=new Bluetooth();
	private List<BluetoothDevice> Adevices = new ArrayList<BluetoothDevice>();
	private Handler _handler = new Handler();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.discovery);
        
        BluetoothAdapter _bluetooth = BluetoothAdapter.getDefaultAdapter();
    	System.out.println(_bluetooth.getAddress()+_bluetooth.getName());
    	_bluetooth.startDiscovery();
        Set<BluetoothDevice> devices=_bluetooth .getBondedDevices();
        if(devices.size()>0){
        	Adevices.addAll(devices);
        	showDevices();
        }       
    }
    
	protected void showDevices()
	{
		List<String> list = new ArrayList<String>();
		for (int i = 0, size = Adevices.size(); i < size; ++i)
		{
			StringBuilder b = new StringBuilder();
			BluetoothDevice d = Adevices.get(i);
			b.append(d.getAddress());
			b.append('\n');
			b.append(d.getName());
			String s = b.toString();
			list.add(s);
		}

		final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
		_handler.post(new Runnable() {
			public void run()
			{

				setListAdapter(adapter);
			}
		});
	}
	protected void onListItemClick(ListView l, View v, int position, long id)
	{

		Bluetooth bt=new Bluetooth();
		System.out.println(123321);
		System.out.println(Adevices.get(position).getAddress());
//		if(bt.Connect(Adevices.get(position).getAddress())){
		Intent enabler = new Intent(this,TouchpanelActivity.class);
//		Bundle bundle = new Bundle();
//		bundle.putString("address",Adevices.get(position).getAddress());
//		bundle.putSerializable("Buletooth", bt);
		enabler.putExtra("address",Adevices.get(position).getAddress());
		startActivity(enabler);
		finish();
		}
  
} 
