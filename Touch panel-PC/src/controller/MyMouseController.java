package controller;

import java.awt.Dimension;
import java.awt.Robot;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.MouseInfo;
import java.awt.AWTException;
import java.awt.event.InputEvent;

public class MyMouseController {
	//�洢��Ļ�ߴ�
	private Dimension dim;
	//�Զ�������
	private Robot robot;

	public MyMouseController(){
		dim = Toolkit.getDefaultToolkit().getScreenSize();
//		System.out.println("��Ļ��СΪ��" + dim.getWidth() + " " + dim.getHeight());
		try{
			robot = new Robot();
		}catch(AWTException e){
			e.printStackTrace();
		}
	}
	
	//����ƶ�����
	public void Move(int width,int heigh){		
		System.out.println("enter Move()...");
		Point mousepoint = MouseInfo.getPointerInfo().getLocation();
  		System.out.println("�ƶ�ǰ���꣺" + mousepoint.x + " " + mousepoint.y);
  		width += mousepoint.x;
  		heigh += mousepoint.y;
		try{
//			robot.delay(3000);
			robot.mouseMove(width,heigh);
		}catch(Exception e){
			e.printStackTrace();
		}
		System.out.println("�ƶ������꣺" + width + " " + heigh);
		//robot.mousePress(InputEvent.BUTTON1_MASK);//��굥��
	}
	
	//��������
	public void Click(){
		//robot.mousePress(InputEvent.BUTTON1_MASK);
		robot.mousePress(InputEvent.BUTTON1_MASK);
		robot.mouseRelease(InputEvent.BUTTON1_MASK);
	}
	
	//˫������
//	public void DoubleClick(){
//		robot.mousePress(InputEvent.BUTTON1_MASK);
//		robot.mousePress(InputEvent.BUTTON1_MASK);
//		robot.mouseRelease(InputEvent.BUTTON1_MASK);
//		robot.mouseRelease(InputEvent.BUTTON1_MASK);
//	}
	//�һ�
	public void RightClick(){
		//robot.mousePress(InputEvent.BUTTON1_MASK);
		robot.mousePress(InputEvent.BUTTON3_MASK);
//		robot.mousePress(InputEvent.BUTTON1_MASK);
		robot.mouseRelease(InputEvent.BUTTON3_MASK);
//		robot.mouseRelease(InputEvent.BUTTON1_MASK);
		
	}
	
	//�������
	public void Press(){
		robot.mousePress(InputEvent.BUTTON1_MASK);
	}
	
	//�ͷ����
	public void Release(){
		robot.mouseRelease(InputEvent.BUTTON1_MASK);
	}

}
