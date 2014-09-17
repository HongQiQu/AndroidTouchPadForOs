package controller;

import java.awt.Dimension;
import java.awt.Robot;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.MouseInfo;
import java.awt.AWTException;
import java.awt.event.InputEvent;

public class MyMouseController {
	//存储屏幕尺寸
	private Dimension dim;
	//自动化对象
	private Robot robot;

	public MyMouseController(){
		dim = Toolkit.getDefaultToolkit().getScreenSize();
//		System.out.println("屏幕大小为：" + dim.getWidth() + " " + dim.getHeight());
		try{
			robot = new Robot();
		}catch(AWTException e){
			e.printStackTrace();
		}
	}
	
	//鼠标移动函数
	public void Move(int width,int heigh){		
		System.out.println("enter Move()...");
		Point mousepoint = MouseInfo.getPointerInfo().getLocation();
  		System.out.println("移动前坐标：" + mousepoint.x + " " + mousepoint.y);
  		width += mousepoint.x;
  		heigh += mousepoint.y;
		try{
//			robot.delay(3000);
			robot.mouseMove(width,heigh);
		}catch(Exception e){
			e.printStackTrace();
		}
		System.out.println("移动后坐标：" + width + " " + heigh);
		//robot.mousePress(InputEvent.BUTTON1_MASK);//鼠标单击
	}
	
	//单击函数
	public void Click(){
		//robot.mousePress(InputEvent.BUTTON1_MASK);
		robot.mousePress(InputEvent.BUTTON1_MASK);
		robot.mouseRelease(InputEvent.BUTTON1_MASK);
	}
	
	//双击函数
//	public void DoubleClick(){
//		robot.mousePress(InputEvent.BUTTON1_MASK);
//		robot.mousePress(InputEvent.BUTTON1_MASK);
//		robot.mouseRelease(InputEvent.BUTTON1_MASK);
//		robot.mouseRelease(InputEvent.BUTTON1_MASK);
//	}
	//右击
	public void RightClick(){
		//robot.mousePress(InputEvent.BUTTON1_MASK);
		robot.mousePress(InputEvent.BUTTON3_MASK);
//		robot.mousePress(InputEvent.BUTTON1_MASK);
		robot.mouseRelease(InputEvent.BUTTON3_MASK);
//		robot.mouseRelease(InputEvent.BUTTON1_MASK);
		
	}
	
	//按下左键
	public void Press(){
		robot.mousePress(InputEvent.BUTTON1_MASK);
	}
	
	//释放左键
	public void Release(){
		robot.mouseRelease(InputEvent.BUTTON1_MASK);
	}

}
