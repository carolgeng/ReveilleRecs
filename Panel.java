import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.util.Scanner;
import java.io.IOException;
import java.awt.image.*;
import javax.imageio.ImageIO;
import java.awt.event.KeyEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.QuadCurve2D;
import java.awt.event.MouseEvent;
import java.awt.event.KeyListener;
import java.util.TreeMap;
import java.util.Map;
import java.util.Arrays;

public class Panel extends JPanel implements  MouseListener, KeyListener{
	
	//records the button name and the button locations
	//the value is a size 4 array that has the (x, y) starting location and the length & height
	//private TreeMap<Integer, int[]> map;
	//Dining = 1
	//Landmarks = 2
	//Facilities = 3
	//Food = 4
	//Entertainment = 5
	//Shopping = 6
	
	
	private int choice = 0;
	/*1 = dining
	 *2 = landmarks
	 *3 = facilities 
	 * */
	
	
	public Panel() {
		addKeyListener(this);
		addMouseListener(this);
	//	map = new TreeMap<>();
	}
	
	
	public void paint(Graphics g) {
		//Color cream=new Color(242, 239, 223);
		Color cream = new Color(251, 246, 237);
		Color pastel_maroon = new Color(242,221,208);
		Color maroon = new Color(170, 82, 92);
		Color c = new Color(193, 133, 133); //maroon ish
		Color gray = new Color(219,215,201);
		Color gray_green = new Color(193, 201, 179);
		
		switch(choice) {
		
		case 0:
		
		//g.setColor(cream);
		g.setColor(maroon);
		g.fillRect(0, 0, getWidth(), getHeight());
		g.setColor(Color.black);
		
		/*g.setFont(new Font("Dialog", Font.BOLD, 30));
		g.drawString("BCS Recommendations", getWidth()/3, getHeight()/20);
		g.drawLine(getWidth()/3-10, getHeight()/20+10, getWidth()/3*2, getHeight()/20+10);*/
		
		//Color pastel_maroon = new Color(224, 191, 195);
		//aggie land
		
		((Graphics2D)g).setStroke(new BasicStroke(3));
		//g.setColor(maroon);
		g.setColor(Color.white);
		g.drawRoundRect(getWidth()/20, getHeight()/13, getWidth()/3+50, getHeight()-2*getHeight()/15, 10, 10);
		g.setColor(pastel_maroon);
		g.fillRoundRect(getWidth()/20, getHeight()/13, getWidth()/3+50, getHeight()-2*getHeight()/15, 10, 10);
		
		//cstat rectangle
		g.setColor(Color.black);
		g.drawRoundRect(getWidth()/3+getWidth()/10, getHeight()/13, getWidth()/2, getHeight()-2*getHeight()/15, 10, 10);
		
		//_---------------
		g.setColor(cream);
		g.fillRoundRect(getWidth()/3+getWidth()/10, getHeight()/13, getWidth()/2, getHeight()-2*getHeight()/15, 10, 10);
		//----------------
		
		//inner gray rectangle for cstat
		g.drawRoundRect(getWidth()/3+getWidth()/8, getHeight()/13+60, getWidth()/2-getWidth()/19, getHeight()-2*getHeight()/15-90, 10, 10);
		
		g.setColor(gray_green);
		g.fillRoundRect(getWidth()/3+getWidth()/8, getHeight()/13+60, getWidth()/2-getWidth()/19, getHeight()-2*getHeight()/15-90, 10, 10);
		
		g.setColor(Color.black);
		g.setFont(new Font("Dialog", Font.BOLD, 40));
		g.drawString("CSTAT", getWidth()/30*19, getHeight()/13+50);
		
		g.setColor(Color.white);
		g.setFont(new Font("Dialog", Font.BOLD, 100));
		g.drawString("Aggie", getWidth()/8, getHeight()/4-30);
		g.drawString("Land", getWidth()/7, getHeight()/3);
		
		
		//g.setColor(maroon);
		g.setColor(Color.white);
		g.setFont(new Font("Dialog", Font.BOLD, 30));
		g.drawString("R E V I E L L E   R E C S", getWidth()/5*2-30, getHeight()/20);
		g.drawLine(getWidth()/3-10, getHeight()/20+10, getWidth()/3*2-30, getHeight()/20+10);
		
		g.setColor(maroon);
		//for dining, landmarks, facilities
		g.drawRoundRect(getWidth()/10, getHeight()/3+50, getWidth()/3-getWidth()/13, getHeight()/8, 30, 30);
		g.drawRoundRect(getWidth()/10, getHeight()/2+50, getWidth()/3-getWidth()/13, getHeight()/8, 30, 30);
		g.drawRoundRect(getWidth()/10, getHeight()/3*2+50, getWidth()/3-getWidth()/13, getHeight()/8, 30, 30);
		g.setColor(c);
		//g.setColor(cream);
		g.fillRoundRect(getWidth()/10, getHeight()/3+50, getWidth()/3-getWidth()/13, getHeight()/8, 30, 30);
		g.fillRoundRect(getWidth()/10, getHeight()/2+50, getWidth()/3-getWidth()/13, getHeight()/8, 30, 30);
		g.fillRoundRect(getWidth()/10, getHeight()/3*2+50, getWidth()/3-getWidth()/13, getHeight()/8, 30, 30);
		
		/*int[] temp = {getWidth()/10, getHeight()/3+50, getWidth()/3-getWidth()/13, getHeight()/8};
		map.put(1, temp);
		temp[1]=getHeight()/2+50;
		map.put(2, temp);
		temp[1]=getHeight()/3*2+50;
		map.put(3, temp);*/
		
		g.setColor(Color.white);
		g.drawString("Dining", getWidth()/8, getHeight()/3+60+getHeight()/16);
		g.drawString("Landmarks", getWidth()/8, getHeight()/2+60+getHeight()/16);
		g.drawString("Facilities", getWidth()/8, getHeight()/3*2+60+getHeight()/16);
		
		//for food, entertainment, shopping
		g.drawRect(getWidth()/3+getWidth()/7+10, getHeight()/5-5, getWidth()/5*2, getHeight()/11);
		g.drawRect(getWidth()/3+getWidth()/7+10, getHeight()/2-getHeight()/16, getWidth()/5*2, getHeight()/11);
		g.drawRect(getWidth()/3+getWidth()/7+10, getHeight()/4*3-getHeight()/16, getWidth()/5*2, getHeight()/11);
		g.setColor(gray);
		g.fillRect(getWidth()/3+getWidth()/7+10, getHeight()/5-5, getWidth()/5*2, getHeight()/11);
		g.fillRect(getWidth()/3+getWidth()/7+10, getHeight()/2-getHeight()/16, getWidth()/5*2, getHeight()/11);
		g.fillRect(getWidth()/3+getWidth()/7+10, getHeight()/4*3-getHeight()/16, getWidth()/5*2, getHeight()/11);
		/*temp[0] = getWidth()/3+getWidth()/7+10;
		temp[1] = getHeight()/5-5;
		temp[2] = getWidth()/5*2;
		temp[3] = getHeight()/11;
		map.put(4, temp);
		temp[1] = getHeight()/2-getHeight()/16;
		map.put(5, temp);
		temp[1] = getHeight()/4*3-getHeight()/16;
		map.put(6, temp);*/
		
		g.setColor(Color.black);
		g.drawString("Food", getWidth()/2,getHeight()/4);
		g.drawString("Entertainment", getWidth()/2,getHeight()/2);
		g.drawString("Shopping ", getWidth()/2,getHeight()/4*3);
		
		g.setFont(new Font("Dialog", Font.PLAIN, 20));
		g.setColor(cream);
		g.drawString("-- Fuego Tortilla Grill", getWidth()/3+getWidth()/7+10, getHeight()/3);
		g.drawString("-- Aggie Park", getWidth()/3+getWidth()/7+10, getHeight()/5*3);
		g.drawString("-- Post Oak Mall", getWidth()/3+getWidth()/7+10, getHeight()/6*5);
		g.drawString("|   	most popular", getWidth()/4*3, getHeight()/3);
		g.drawString("| 	most popular", getWidth()/4*3, getHeight()/5*3);
		g.drawString("| 	most popular", getWidth()/4*3, getHeight()/6*5);
		
		break;
		
		case 1:
			g.setColor(Color.MAGENTA);
			g.fillRect(0, 0, getWidth(), getHeight());
		
		}
	}
	
	public void addNotify()
	{
		super.addNotify();
		requestFocus();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int x=e.getX();
		int y=e.getY();
		System.out.println("x: "+x+"y : "+y);
		
		/*
		 * g.drawRoundRect(getWidth()/10, getHeight()/3+50, getWidth()/3-getWidth()/13, getHeight()/8, 30, 30);
		g.drawRoundRect(getWidth()/10, getHeight()/2+50, getWidth()/3-getWidth()/13, getHeight()/8, 30, 30);
		g.drawRoundRect(getWidth()/10, getHeight()/3*2+50, getWidth()/3-getWidth()/13, getHeight()/8, 30, 30);
		
		g.drawRect(getWidth()/3+getWidth()/7+10, getHeight()/5-5, getWidth()/5*2, getHeight()/11);
		g.drawRect(getWidth()/3+getWidth()/7+10, getHeight()/2-getHeight()/16, getWidth()/5*2, getHeight()/11);
		g.drawRect(getWidth()/3+getWidth()/7+10, getHeight()/4*3-getHeight()/16, getWidth()/5*2, getHeight()/11);
		 */
		
		if(x>getWidth()/10 && x <getWidth()/10+getWidth()/3-getWidth()/13 && y > getHeight()/3+50 && y < getHeight()/3+50+ getHeight()/8) {
			choice = 1;
		}
		else if(x>getWidth()/10 && x <getWidth()/10+getWidth()/3-getWidth()/13 && y > getHeight()/2+50 && y < getHeight()/3+50+ getHeight()/8) {
			choice = 2;
		}
		else if(x>getWidth()/10 && x <getWidth()/10+getWidth()/3-getWidth()/13 && y > getHeight()/3*2+50 && y < getHeight()/3+50+ getHeight()/8) {
			choice = 3;
		}
		else if(x>getWidth()/3+getWidth()/7+10 && x <getWidth()/3+getWidth()/7+10+getWidth()/5*2 && y > getHeight()/5-5 && y < getHeight()/5-5+getHeight()/11) {
			choice = 4;
		}
		else if(x>getWidth()/3+getWidth()/7+10 && x <getWidth()/3+getWidth()/7+10+getWidth()/5*2 && y > getHeight()/2-getHeight()/16 && y < getHeight()/2-getHeight()/16+getHeight()/11) {
			choice = 5;
		}
		else if(x>getWidth()/3+getWidth()/7+10 && x <getWidth()/3+getWidth()/7+10+getWidth()/5*2 && y > getHeight()/4*3-getHeight()/16 && y < getHeight()/4*3-getHeight()/16+getHeight()/11) {
			choice = 6;
		}
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
	

}
