package com.searlas.game;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.util.Arrays;

public class Main {
	final static int startx = 283;   //上侧
	final static int starty = 305;   //左侧
	public static void main(String[] args) {
		Map mymap = new Map();
		
		try {
			Thread.sleep(1000);
			Robot robot = new Robot();
		//	ArrayList<Color> listColor = new ArrayList<Color>();
//			for (Color color : mymap.listColor) {
//				System.out.println(color);
//			}
			//System.out.println(Arrays.toString(mymap.listColor));
			mymap.recordColor(robot, startx, starty);
			for(int i=0;i<mymap.map.length;i++)
				System.out.println(Arrays.toString(mymap.map[i]));
	
		
		int x;
		int y;
		int number=1;
		for(int q=0;q<7;q++) {
			x=startx;
			y=starty;
			for(int i = 0; i<15;i++) {
				for(int j =0;j<23;j++) {
					if(mymap.judgeClick(robot, i, j)) {
						System.out.println(number+"------------click "+i+" "+j);
						number++;
						mouseClick(robot,x,y);
						mymap.updateColor(i, j);
					}
					x+=25;
			//		robot.mouseMove(x, y);//鼠标向右25
				}
				x=startx;
				y+=25;
			//	robot.mouseMove(x, y);//鼠标向下25
			}
			System.out.print(1);
		}
		
		} catch (AWTException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	
	public static void mouseClick(Robot robot,int x,int y) {
		robot.mouseMove(x, y);
		robot.mousePress(InputEvent.BUTTON1_MASK);
		
		robot.delay(50);
		
		robot.mouseRelease(InputEvent.BUTTON1_MASK);
		
		robot.delay(500);
	}
	
	
	
}
