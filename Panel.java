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
	private TreeMap<String, int[]> map;
	//Dining
	//Landmarks
	//Facilities
	
	public Panel() {
		addKeyListener(this);
		addMouseListener(this);
		map = new TreeMap<>();
	}
	
	
	public void paint(Graphics g) {
		//Color cream=new Color(242, 239, 223);
		Color cream = new Color(251, 246, 237);
		g.setColor(cream);
		g.fillRect(0, 0, getWidth(), getHeight());
		g.setColor(Color.black);
		
		/*g.setFont(new Font("Dialog", Font.BOLD, 30));
		g.drawString("BCS Recommendations", getWidth()/3, getHeight()/20);
		g.drawLine(getWidth()/3-10, getHeight()/20+10, getWidth()/3*2, getHeight()/20+10);*/
		
		//Color pastel_maroon = new Color(224, 191, 195);
		//aggie land
		Color pastel_maroon = new Color(242,221,208);
		Color maroon = new Color(170, 82, 92);
		((Graphics2D)g).setStroke(new BasicStroke(3));
		g.setColor(maroon);
		g.drawRoundRect(getWidth()/20, getHeight()/13, getWidth()/3+50, getHeight()-2*getHeight()/15, 10, 10);
		g.setColor(pastel_maroon);
		g.fillRoundRect(getWidth()/20, getHeight()/13, getWidth()/3+50, getHeight()-2*getHeight()/15, 10, 10);
		
		//cstat rectangle
		g.setColor(Color.black);
		g.drawRoundRect(getWidth()/3+getWidth()/10, getHeight()/13, getWidth()/2, getHeight()-2*getHeight()/15, 10, 10);
		//inner gray rectangle for cstat
		g.drawRoundRect(getWidth()/3+getWidth()/8, getHeight()/13+60, getWidth()/2-getWidth()/19, getHeight()-2*getHeight()/15-90, 10, 10);
		Color gray = new Color(219,215,201);
		g.setColor(gray);
		g.fillRoundRect(getWidth()/3+getWidth()/8, getHeight()/13+60, getWidth()/2-getWidth()/19, getHeight()-2*getHeight()/15-90, 10, 10);
		
		g.setColor(Color.black);
		g.setFont(new Font("Dialog", Font.BOLD, 40));
		g.drawString("CSTAT", getWidth()/30*19, getHeight()/13+50);
		
		g.setColor(Color.white);
		g.setFont(new Font("Dialog", Font.BOLD, 100));
		g.drawString("Aggie", getWidth()/8, getHeight()/4-30);
		g.drawString("Land", getWidth()/7, getHeight()/3);
		
		Color c = new Color(193, 133, 133);
		g.setColor(maroon);
		g.setFont(new Font("Dialog", Font.BOLD, 30));
		g.drawString("Revielle Recs", getWidth()/5*2, getHeight()/20);
		g.drawLine(getWidth()/3-10, getHeight()/20+10, getWidth()/3*2-20, getHeight()/20+10);
		
		g.drawRoundRect(getWidth()/10, getHeight()/3+50, getWidth()/3-getWidth()/13, getHeight()/8, 30, 30);
		g.drawRoundRect(getWidth()/10, getHeight()/2+50, getWidth()/3-getWidth()/13, getHeight()/8, 30, 30);
		g.drawRoundRect(getWidth()/10, getHeight()/3*2+50, getWidth()/3-getWidth()/13, getHeight()/8, 30, 30);
		g.setColor(c);
		//g.setColor(cream);
		g.fillRoundRect(getWidth()/10, getHeight()/3+50, getWidth()/3-getWidth()/13, getHeight()/8, 30, 30);
		g.fillRoundRect(getWidth()/10, getHeight()/2+50, getWidth()/3-getWidth()/13, getHeight()/8, 30, 30);
		g.fillRoundRect(getWidth()/10, getHeight()/3*2+50, getWidth()/3-getWidth()/13, getHeight()/8, 30, 30);
		
		int[] temp = {getWidth()/10, getHeight()/3+50, getWidth()/3-getWidth()/13, getHeight()/8};
		map.put("Dining", temp);
		temp[1]=getHeight()/2+50;
		map.put("Landmarks", temp);
		temp[1]=getHeight()/3*2+50;
		map.put("Facilities", temp);
		
		g.setColor(Color.white);
		g.drawString("Dining", getWidth()/8, getHeight()/3+60+getHeight()/16);
		g.drawString("Landmarks", getWidth()/8, getHeight()/2+60+getHeight()/16);
		g.drawString("Facilities", getWidth()/8, getHeight()/3*2+60+getHeight()/16);
		
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
		// TODO Auto-generated method stub
		
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
