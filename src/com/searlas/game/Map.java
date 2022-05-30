package com.searlas.game;

import java.awt.Color;
import java.awt.Robot;
import java.util.ArrayList;
import java.util.Arrays;

public class Map {
	public int[][] map =new int[15][23];
	//map的x是左侧 y是上侧
	public ArrayList<Color> listColor = new ArrayList<Color>() ;
	int chooseColor;
	Point left=new Point();
	Point right=new Point();
	Point up=new Point();
	Point down=new Point();
	public Map() {
		
	}
	public void showMap() {
		/*for(int i = 0; i<15;i++) {
			for(int j =0;j<23;j++) {
				System.out.print(map[i][j]+" ");
			}
		}*/
		System.out.println(map);
	}
	
	public int equalColor(Color color) {
		if(color.equals(Color_game.color1)) {
			return 1;
		}
		if(color.equals(Color_game.color2)){
			return 2;
		}
		if(color.equals(Color_game.color3)) {
			return 3;
		}
		if(color.equals(Color_game.color4)){
			return 4;
		}
		if(color.equals(Color_game.color5)) {
			return 5;
		}
		if(color.equals(Color_game.color6)) {
			return 6;
		}
		if(color.equals(Color_game.color7)) {
			return 7;
		}
		if(color.equals(Color_game.color8)) {
			return 8;
		}
		if(color.equals(Color_game.color9)) {
			return 9;
		}
		if(color.equals(Color_game.color10)) {
			return 10;
		}
		if(color.equals(Color_game.color11)) {
			return 11;
		}
		if(color.equals(Color_game.color12)) {
			return 12;
		}
		else return 1;
	}
	
	public void recordColor(Robot robot,int x,int y) {
		int startx = x;
		map[5][5]=99;
		robot.mouseMove(x, y);
		System.out.println("recording");
		for(int i = 0; i<15;i++) {
			for(int j =0;j<23;j++) {
				robot.mouseMove(x, y);
				map[i][j]=equalColor(robot.getPixelColor(x, y));
				x+=25;//鼠标向右25
			}
			x=startx;
			y+=25;
		}
		System.out.println("recorded");
	}
	
	
	public void getLeftColor(int x,int y) {
		if(y!=0) {
			for(int Y =y-1;Y>0;Y--){
				if(!isBackground(x,Y)) {
					left.x=x;
					left.y=Y;
					left.color=map[x][Y];
					return;
				}
			}
		}
		left.x=0;
		left.y=0;
		left.color=-1;
		//左边没有颜色方块
	}
	public void getRightColor(int x,int y) {
		if(y!=23) {
			for(int Y =y+1;Y<23;Y++) {
				if(!isBackground(x,Y)) {
					right.x=x;
					right.y=Y;
					right.color=map[x][Y];
					return;
				}
			}
		}
		right.x=-1;
		right.y=-1;
		right.color=-1;
		//右边没有颜色方块
	}
	public void getUpColor(int x,int y) {
		if(x!=0) {			
			for(int X =x-1;X>0;X--) {
				if(!isBackground(X,y)) {
					up.x=X;
					up.y=y;
					up.color=map[X][y];
					return;
				}
			}
		}
		up.x=-1;
		up.y=-1;
		up.color=-1;
		//上边没有颜色方块
	}
	public void getDownColor(int x,int y) {
		if(x!=15) {
			for(int X =x+1;X<15;X++) {
				if(!isBackground(X,y)) {
					down.x=X;
					down.y=y;
					down.color=map[X][y];
					return;
				}
			}
		}
		down.x=-1;
		down.y=-1;
		down.color=-1;
		//下边没有颜色方块
	}
	
	public boolean isBackground(int x,int y) {
		return map[x][y]>0&&map[x][y]<3;
	}
	
	public boolean isNotBackgournd(int color) {
		return !(color>0&&color<3);
	}

	public void updateColor(int x,int y) {
		ArrayList<Point> points= new ArrayList<Point>();
		getLeftColor(x, y);
		getRightColor(x, y);
		getUpColor(x, y);
		getDownColor(x, y);
		points.add(left);
		points.add(right);
		points.add(up);
		points.add(down);
		for (Point point : points) {
			System.out.print(point);
		}
		System.out.println("update");
		for(int i = 0;i<4;i++) {
			for(int j=0;j<4&&j!=i;j++) {
				if(points.get(i).color==points.get(j).color  &&  points.get(i).color!=-1  &&  points.get(j).color!=-1) {
					map[points.get(i).x][points.get(i).y]=1;
					map[points.get(j).x][points.get(j).y]=1;
				}
			}
		}
		for(int i=0;i<map.length;i++)
			System.out.println(Arrays.toString(map[i]));
	}
	public boolean judgeClick(Robot robot,int x,int y) {
		if(!isBackground(x, y)) {
			return false;
		}
		//一定要是背景色
		getLeftColor(x,y);
		getRightColor(x,y);
		getUpColor(x,y);
		getDownColor(x,y);
		int list[]= {left.color,right.color,up.color,down.color};
		int nums =1;
		int  max =1;
		for(int i=0;i<4;i++) {
			if(max<nums) {
				max = nums;//记录最大
			}
			nums = 1;
			
			for(int j=0;j<4;j++)
				if(list[i]==list[j] && list[i]!=-1 && list[j]!=-1 && j!=i)
					nums++;
		}
		if(max == 2 || max==4) {
			System.out.println(Arrays.toString(list));
			return true;
		}
		return false;
	}
}
