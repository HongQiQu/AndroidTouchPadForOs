package components;

import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

/**
 * 系统托盘类
 * @author royen
 * @since 2010.2.10
 */
public class SysTray {
	
	public void show(){
		try{			 
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel"); 
		}
		catch (Exception ex) { 
			ex.printStackTrace(); 
		} 	

		/** 判断本地系统是否支持托盘 */
		if(SystemTray.isSupported()){ 
			this.tray(); 
		} 
		else{
			JOptionPane.showMessageDialog(null,"操作系统不支持托盘");
		}
	}
	
	/**  托盘相关代码 	 */ 
	private void tray(){
		
		/**  获得本操作系统托盘的实例  */
		tray = SystemTray.getSystemTray(); 
			
		URL imageUrl=SysTray.class.getResource("system.gif");
				
		/** 设置托盘图标 */
		ImageIcon icon = new ImageIcon(imageUrl);

		/** 构造右键弹出式菜单 */
		PopupMenu pop = new PopupMenu(); // 构造一个右键弹出式菜单 

		/** 右键弹出式菜单的选项 */		
		MenuItem exit = new MenuItem("退出"); 
		MenuItem about = new MenuItem("关于"); 

		pop.add(exit);
		pop.add(about);
		
		trayIcon = new TrayIcon(icon.getImage(), "正在监测中", pop); 
		
		try{
			/** 将托盘图标添加到系统的托盘实例中 */
			tray.add(trayIcon); // 
		}
		catch(Exception e){
			e.printStackTrace();
		}

		/** 监听鼠标事件 */ 
		trayIcon.addMouseListener(new MouseAdapter() { 
			
			public void mouseClicked(MouseEvent e) { 
				if(e.getClickCount()==2){  
					JOptionPane.showMessageDialog(null,"Copyright @2010 Royen\nAll Right Reserved\nauthor Royen\nversion 1.0\n","about",JOptionPane.INFORMATION_MESSAGE);
				} 
			} 
		}); 

		/** 退出菜单项事件 */
		exit.addActionListener(new ActionListener() {  
			public void actionPerformed(ActionEvent e) { 
				try{
					//bluetooth.getBluetooth().closeServer();					
					
					tray.remove(trayIcon); 					
					tray=null;					
					
					System.gc();
					
					System.exit(0);
				}
				catch(Exception ex){
					ex.printStackTrace();
				}
			} 
		}); 
		
		/** 关于菜单项事件 */
		about.addActionListener(new ActionListener() {  
			public void actionPerformed(ActionEvent e) {				
				JOptionPane.showMessageDialog(null,"Copyright @2010 www.cnblogs.com/Royenhome\nAll Right Reserved\nauthor Royen\nversion 1.0\n","about",JOptionPane.INFORMATION_MESSAGE);
			} 
		}); 	
	}
	
	/** 托盘图标 */
	private TrayIcon trayIcon = null;
	
	/** 本操作系统托盘的实例  */
	private SystemTray tray = null;

}
