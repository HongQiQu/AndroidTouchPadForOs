package zhenguo.hanxu;

import zhenguo.hanxu.R;
import zhenguo.hanxu.R.id;
import zhenguo.hanxu.R.layout;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
public class TouchpanelActivity extends Activity implements OnGestureListener {
	//触屏坐标获取 与 灵敏度设置
	int i = 0;
	//当前坐标x,y上次坐标x0,y0
	int x,x0;
	int y,y0;
 //   Intent intent = getIntent();
//    String address=intent.getStringExtra("address");
    Bluetooth bt=new Bluetooth();
    String address;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	System.out.println("开始");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Button button1 = (Button)findViewById(R.id.button1);
        Button button2 = (Button)findViewById(R.id.button2);
        button1.setOnClickListener(new Button1Listener());
        button2.setOnClickListener(new Button2Listener());
//        button1.setOnTouchListener(new Button1Listener());  
        final GestureDetector gestureScanner = new GestureDetector(this);
        View myView=(View)findViewById(R.id.myView);
//        ButtonOnTouchListener listener2 = new ButtonOnTouchListener(); 
//        
//        button1.setOnTouchListener(listener2);
//        button2.setOnTouchListener(listener2);


        Intent intent = getIntent();
        String address=intent.getStringExtra("address");
        this.address=address;
        try{
             	bt.Connect(address);
             	bt.start();
             	bt.prewrite();
   //         System.out.println("连接呢");
        }catch(Exception e){
        	e.printStackTrace();
        }
		myView.setOnTouchListener(
        		new OnTouchListener(){  


					public  boolean onTouch(View v, MotionEvent event) {
					//	bt.Connect(address);  
//						bt.Connect(address);
//						System.out.println("hanxu1"); 
						System.out.println("触发了");
	                   
						if(i == 0){
							i++;
							//发送数据
							System.out.println("位移：" + 0 + " " + 0 );
							x0 = (int)event.getX();
							y0 = (int)event.getY();
						}else{
							if(i%1 == 0){
								i++;
								x = (int)event.getX();
								y = (int)event.getY();
								//位移为m,n								
								int m,n;
								m = x - x0;
								n = y - y0;
								//发送数据
								System.out.println("原坐标：" + x0 + " " + y0 + " 当前坐标：" + x + " " + y );
								System.out.println("位移：" + m + " " + n+"," );
								String m0=Arrangement(m);
								String n0=Arrangement(n);
					        	String datas="0"+m0+","+n0;
					        	System.out.println(datas);
					        	bt.write(datas);
								x0 = (int)event.getX();
								y0 = (int)event.getY();
								if(m == 0 && n == 0){
									i=0;
								}
							}else{
								i++;
							}
							
					    	}
	                    	
	                
						return gestureScanner.onTouchEvent(event);
						//return true;
					}
					
					
					public String Arrangement(int m){
						String a="";
						if(m<0){		    	
						a="-";
						String m1=0-m+"";
						for(int i=0;i<3-m1.length();i++){
						a+="0";
						  }
						a+=m1;
			            }else{
			            String m1=m+"";	
			            for(int i=0;i<4-m1.length();i++){
						a+="0";
						  }
						  a+=m1;
			            	}
						return a;
					}
                  
        		}
        );
        
        myView.setOnClickListener(
        		new OnClickListener(){
        			public void onClick(View v){
        				//System.out.println("单击事件");
        			}
        		}
        );
        
        gestureScanner.setOnDoubleTapListener(new GestureDetector.OnDoubleTapListener() {
        	public boolean onDoubleTap(MotionEvent e) {
        		
        		// 双击时产生一次
//        		String datas="1vdc,";
//        		bt.write(datas);
//        		System.out.println("view双击事件");
        		return false;
         }

			@Override
			public boolean onDoubleTapEvent(MotionEvent e) {
				System.out.println("onDoubleTapEvent");
				return false;
			}

			@Override
			public boolean onSingleTapConfirmed(MotionEvent e) {
				System.out.println("onSingleTapConfirmed");
				return false;
			}
			
        
        });
        /*
        button1.setOnClickListener(
        		new OnClickListener(){

					@Override
					public void onClick(View v) {
						System.out.println("左键单击事件");
					}
        			
        		}
        		
        );
        
        button2.setOnClickListener(
        		new OnClickListener(){

					@Override
					public void onClick(View v) {
						System.out.println("右键单击事件");
					}
        			
        		}
        );*/
    }
    
    
 
    
    
    class Button1Listener implements OnClickListener,OnGestureListener{
    	final GestureDetector gestureScanner = new GestureDetector(this);
		@Override
		public void onClick(View v) {	
        String datas="1leftc,000";
		System.out.println(datas);
			bt.write(datas);
		}
		@Override
		public boolean onDown(MotionEvent e) {
			String datas="1press,000";
			bt.write(datas);
			System.out.println("按下了哦");
			return false;
		}
		@Override
		public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
				float velocityY) {
			// TODO Auto-generated method stub
			return false;
		}
		@Override
		public void onLongPress(MotionEvent e) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public boolean onScroll(MotionEvent e1, MotionEvent e2,
				float distanceX, float distanceY) {
			// TODO Auto-generated method stub
			return false;
		}
		@Override
		public void onShowPress(MotionEvent e) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public boolean onSingleTapUp(MotionEvent e) {
			// TODO Auto-generated method stub
			return false;
		}
    }
    class Button2Listener  implements OnClickListener{
    	  
		@Override
		public void onClick(View v) {
        String datas="1rightc,00";
			bt.write(datas);
		} 	
    }
    
	@Override
	public boolean onDown(MotionEvent e) {
		
//		String datas="1press,000";
//		bt.write(datas);
//		System.out.println("按下了哦");
		return true;
	}
	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		System.out.println("onFling");
		return false;
	}
	@Override
	public void onLongPress(MotionEvent e) {
		System.out.println("onLongPress");
		
	}
	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
		System.out.println("onScroll");
		return false;
	}
	@Override
	public void onShowPress(MotionEvent e) {
//		String datas="1release,0";
//		bt.write(datas);
//		System.out.println("松开了哦");
		System.out.println("onShowPress");	
	}
	@Override
	public boolean onSingleTapUp(MotionEvent e) {
		String datas="1vc,000000";
		bt.write(datas);
		System.out.println("view单击事件");
		return false;
	}
	
	/* button事件监听开始   */
	
	//计算点击的次数 
//    private int count; 
    //第一次点击的时间 long型 
//    private long firstClick; 
    //最后一次点击的时间 
//    private long lastClick; 
  //第一次点击的button的id 
 //   private int firstId;
//    private class ButtonOnTouchListener implements OnTouchListener{ 
    	 
//        @Override 
//        public boolean onTouch(View v, MotionEvent event) {  
//        	if(event.getAction()==MotionEvent.ACTION_DOWN){
//        		String data1="1liftc,";
//        		String data2="1liftdc,";
//        		String data3="1rightc,";
//        		String data4="1rightdc,";
//        		
//        		//如果第二次点击 距离第一次点击时间过长   那么将第二次点击看为第一次点击
//        		if(firstClick!=0 && System.currentTimeMillis()-firstClick>300){ 
//                    count = 0; 
//                    firstId = 0; 
//                } 
//                count++; 
//                if(count==1){ 
//                    firstClick = System.currentTimeMillis(); 
//                    //记录第一次点得按钮的id 
//                    firstId = v.getId(); 
//                    switch(firstId){ 
//                    case R.id.button1:
//                    	bt.write(data1);
//                    	System.out.println("button1单击");
//                        break; 
//                     
//                    case R.id.button2:
//                    	bt.write(data3);
//                    	System.out.println("button2单击");
//                        break;
//                } 
//                }else if(count==2){ 
//                    lastClick = System.currentTimeMillis(); 
//                    //两次点击小于300ms 也就是连续点击 
//                    if(lastClick-firstClick<300){ 
//                        //第二次点击的button的id 
//                        int id = v.getId(); 
//                        //判断两次点击的button是否是同一个button 
//                        if(id == firstId){ 
//                            switch(id){ 
//                                case R.id.button1:
//                                	bt.write(data2);
//                                	System.out.println("button1双击");
//                                    break; 
//                                 
//                                case R.id.button2: 
//                                	bt.write(data4);
//                                	System.out.println("button2双击");
//                                    break;
//                            } 
//                        } 
//                    } 
//                     
//                    clear(); 
//                }
//        	}
//        	return false; 
//        } 
        //清空状态 
//        private void clear(){ 
//            count = 0; 
//            firstClick = 0; 
//            lastClick = 0; 
//            firstId = 0; 
//        } 
         
    
     
	
	/* button事件监听结束   */

}
        




