package main;

import javax.swing.JOptionPane;

import bluetooth.BlueService;
import components.SysTray;

/**
 * 程序入口
 * @author royen
 * @since 2010.2.10
 */
public class BlueRemote {
	
	public static void main(String[] args){
		BlueService bs=new BlueService();
		
		if(bs.initialBluetooth()){
			new SysTray().show();
			bs.startService();
		}
		else{
			JOptionPane.showMessageDialog(null,"蓝牙栈初始化失败,可能你机器不支持蓝牙模块！");
		}
	}
}
