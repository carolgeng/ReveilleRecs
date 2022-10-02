import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.util.Scanner;
import java.io.IOException;
import java.io.FileNotFoundException;
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
import java.util.ArrayList;
import java.io.File;

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
	private int food_page, food_select, ent_select, shop_select, fac_page, dine_select;
	private BufferedImage campus, food_map, fuego, CJ, mojo, sharetea, korean, mess, eugene, aggiepark, escape, gstation, iceskating,lakebryan, jumping, safari;
	private BufferedImage mall, csquare, warehouse, ao, maroonu, boutique, cstation, gym, hh;
	private boolean begin;
	private Font cursive, cursive2, exo_m, exo_r;
	private TreeMap<Integer, BufferedImage> food_m, ent_m, shop_m;
	private TreeMap<Integer, ArrayList<Point>> campus_food;
	private Scanner sc;
	
	public Panel() {
		addKeyListener(this);
		addMouseListener(this);
	//	map = new TreeMap<>();
		food_m = new TreeMap<>();
		ent_m = new TreeMap<>();
		shop_m = new TreeMap<>();
		ent_select = 1;
		shop_select = 1;
		fac_page = 1;
		begin = false;
		campus_food = new TreeMap<>();
		dine_select = 0;
		try {
			cursive = Font.createFont(Font.TRUETYPE_FONT, new File("Autumn in November.ttf")).deriveFont(115f);
			cursive2 = Font.createFont(Font.TRUETYPE_FONT, new File("Anydore.otf")).deriveFont(40f);
			exo_m = Font.createFont(Font.TRUETYPE_FONT, new File("Exo-Bold.otf")).deriveFont(30f);
			exo_r = Font.createFont(Font.TRUETYPE_FONT, new File("Exo-Regular.otf")).deriveFont(20f);
			food_map=ImageIO.read(Panel.class.getResource("food_map.jpg"));
			fuego=ImageIO.read(Panel.class.getResource("fuego.jpg"));
			CJ=ImageIO.read(Panel.class.getResource("C&J.jpg"));
			mojo=ImageIO.read(Panel.class.getResource("mojo.jpg"));
			sharetea=ImageIO.read(Panel.class.getResource("sharetea.jpg"));
			korean=ImageIO.read(Panel.class.getResource("korean.jpg"));
			eugene=ImageIO.read(Panel.class.getResource("eugene.jpg"));
			mess=ImageIO.read(Panel.class.getResource("mess.jpg"));
			aggiepark=ImageIO.read(Panel.class.getResource("aggiepark.jpg"));
			escape=ImageIO.read(Panel.class.getResource("escape.jpg"));
			lakebryan=ImageIO.read(Panel.class.getResource("lakebryan.jpg"));
			gstation=ImageIO.read(Panel.class.getResource("grandstation.jpg"));
			iceskating=ImageIO.read(Panel.class.getResource("iceskating.jpg"));
			safari=ImageIO.read(Panel.class.getResource("safari.jpg"));
			jumping=ImageIO.read(Panel.class.getResource("jumping.jpg"));
			mall=ImageIO.read(Panel.class.getResource("mall.jpg"));
			csquare=ImageIO.read(Panel.class.getResource("square.jpg"));
			warehouse=ImageIO.read(Panel.class.getResource("warehouse.jpg"));
			ao=ImageIO.read(Panel.class.getResource("ao.jpg"));
			maroonu=ImageIO.read(Panel.class.getResource("maroonu.jpg"));
			cstation=ImageIO.read(Panel.class.getResource("cstation.jpg"));
			boutique=ImageIO.read(Panel.class.getResource("boutique.jpg"));
			gym=ImageIO.read(Panel.class.getResource("gym.jpg"));
			campus = ImageIO.read(Panel.class.getResource("campus.jpg"));
			hh = ImageIO.read(Panel.class.getResource("hh.png"));
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			//register the font
			ge.registerFont(cursive);
			ge.registerFont(cursive2);
			ge.registerFont(exo_m);
			ge.registerFont(exo_r);
		}
		catch (IOException e) {
		    e.printStackTrace();
		} catch(FontFormatException e) {
		    e.printStackTrace();
		}
		food_page = 1;
		food_select = 1;
		food_m.put(1, fuego);
		food_m.put(2, sharetea);
		food_m.put(3, mojo);
		food_m.put(4, CJ);
		food_m.put(5, korean);
		food_m.put(6, eugene);
		food_m.put(7, mess);
		ent_m.put(1, aggiepark);
		ent_m.put(2,  lakebryan);
		ent_m.put(3, safari);
		ent_m.put(4, jumping);
		ent_m.put(5, escape);
		ent_m.put(6, iceskating);
		ent_m.put(7, gstation);
		shop_m.put(1, mall);
		shop_m.put(2, csquare);
		shop_m.put(3, warehouse);
		shop_m.put(4, ao);
		shop_m.put(5,  maroonu);
		shop_m.put(6, cstation);
		shop_m.put(7, boutique);
	}
	
	public void putint() {
		try {
			Scanner sc = new Scanner(new File("restaurant.txt"));
			int c = 1;
			while(sc.hasNextLine()) {
				String[] arr = sc.nextLine().split(" ");
				ArrayList<Point> list = new ArrayList<>();
				for(int i = 0; i < arr.length; i+=2) {
					Point p = new Point(Integer.parseInt(arr[i]), Integer.parseInt(arr[i+1]));
					list.add(p);
				}
				campus_food.put(c, list);
				c++;
			}
		}
		catch(IOException e) {}
	}
	
	public void paint(Graphics g) {
		//Color cream=new Color(242, 239, 223);
		Color cream = new Color(251, 246, 237);
		Color pastel_maroon = new Color(242,221,208);
		Color maroon = new Color(170, 82, 92);
		Color c = new Color(193, 133, 133); //maroon ish
		Color gray = new Color(219,215,201);
		Color gray_green = new Color(193, 201, 179);
		Color mmaroon = new Color(128, 0, 0);
		Color yellow = new Color(246, 233, 183);
		Color darkbrown = new Color(81, 69, 21);
		Color umber = new Color(155, 131, 70);
		Color cream_gray = new Color(240, 228, 197);
		Color gr = new Color(32, 182, 42);
		
		
		
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
		g.drawString("R E V E I L L E   R E C S", getWidth()/5*2-30, getHeight()/20);
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
			 
			g.setColor(mmaroon);
			g.fillRect(0, 0, getWidth(), getHeight());
			
			g.setFont(cursive);
			g.setColor(Color.white);
			g.drawString(" F o o d", getWidth()/30*10, getHeight()/8+10);
			g.setFont(exo_m);
			g.drawString("  O N 	      C A M P U S", getWidth()/30*12-5, getHeight()/4);
			
			((Graphics2D) g).setStroke(new BasicStroke(6));
			g.drawLine(40, getHeight()/4-10, getWidth()/30*11+5, getHeight()/4-10);
			g.drawLine(getWidth()/24*14+30, getHeight()/4-10, getWidth()-20, getHeight()/4-10);
			
			g.setColor(Color.white);
			((Graphics2D) g).setStroke(new BasicStroke(2));
			g.drawRect(60, getHeight()/30*9, getWidth()/2, getHeight()/20*13);
			g.drawRect(getWidth()/20*11, getHeight()/30*9, getWidth()/30*12, getHeight()/20*13);
			
			g.drawImage(food_map, getWidth()/20*11, getHeight()/30*9, getWidth()/30*12, getHeight()/20*13, null);
			//if(begin)
				//g.drawImage(campus, getWidth()/20*11, getHeight()/30*9, getWidth()/30*12, getHeight()/20*13, null);
			
			switch(food_page) {
			
			case 1:
			g.setFont(exo_r);
			g.drawString("Dining Halls:", getWidth()/18, getHeight()/40*15-20);
			g.drawLine(getWidth()/18, getHeight()/40*15-13, getWidth()/8+5, getHeight()/40*15-13);
			g.drawString("·  Sbisa", getWidth()/18, getHeight()/80*33);
			g.drawString("·  Commons",getWidth()/18, getHeight()/80*36);
			g.drawString("·  Duncans",getWidth()/18, getHeight()/80*39);
			g.drawString("| Northside", getWidth()/4, getHeight()/80*33);
			g.drawString("| Southside", getWidth()/4, getHeight()/80*36);
			g.drawString("| Southside", getWidth()/4, getHeight()/80*39);
			g.drawString("Retail Swipes:",getWidth()/18, getHeight()/80*43);
			g.drawLine(getWidth()/18, getHeight()/80*44-5, getWidth()/7, getHeight()/80*44-5);
			g.drawString("·  Cabo's Grill ",getWidth()/18, getHeight()/80*46);
			g.drawString("·  Pom & Honey      ",getWidth()/18, getHeight()/80*49);
			g.drawString("·  Rev's Grill      ",getWidth()/18, getHeight()/80*52);
			g.drawString("·  Spin n Stone Pizza   ",getWidth()/18, getHeight()/80*55);
			g.drawString("·  Panda Express        ",getWidth()/18, getHeight()/80*58);
			g.drawString("·  Houston Street Sub",getWidth()/18, getHeight()/80*61);
			g.drawString("·  Chick Fil A		",getWidth()/18, getHeight()/80*64);
			g.drawString("·  Einstein's Bagels    ",getWidth()/18, getHeight()/80*67);
			g.drawString("·  Copperhead Jack's",getWidth()/18, getHeight()/80*70);
			g.drawString("·  The Grill     ",getWidth()/18, getHeight()/80*73);
			g.drawString("| MSC", getWidth()/4, getHeight()/80*46);
			g.drawString("| MSC", getWidth()/4, getHeight()/80*49);
			g.drawString("| MSC", getWidth()/4, getHeight()/80*52);
			g.drawString("| MSC", getWidth()/4, getHeight()/80*55);
			g.drawString("| MSC | Polo Garage", getWidth()/4, getHeight()/80*58);
			g.drawString("| MSC | Polo Garage", getWidth()/4, getHeight()/80*61);
			g.drawString("| MSC	| Sbisa Underground", getWidth()/4, getHeight()/80*64);
			g.drawString("| Sbisa Complex", getWidth()/4, getHeight()/80*67);
			g.drawString("| Sbisa Complex", getWidth()/4, getHeight()/80*70);
			g.drawString("| Pavilion", getWidth()/4, getHeight()/80*73);
			
			g.drawString(" 1 | 2", getWidth()/4, getHeight()/80*77);
			g.drawString("·", getWidth()/4+5, getHeight()/80*76);
			
			g.fillOval(25, getHeight()-80, 75, 70);
			
			g.setColor(mmaroon);
		    Polygon arrowHead = new Polygon();  
		    arrowHead.addPoint( 60,getHeight()-63);
		    arrowHead.addPoint( 40, getHeight()-43);
		    arrowHead.addPoint( 60,getHeight()-23);
		    ((Graphics2D) g).setStroke(new BasicStroke(6));
		    g.drawLine(60, getHeight()-43, 82, getHeight()-43);
		    g.fillPolygon(arrowHead);
			
			break;
			
			case 2:
				g.setFont(exo_r);
				g.drawString("Food Trucks:", getWidth()/18, getHeight()/40*15-20);
				g.drawLine(getWidth()/18, getHeight()/40*15-13, getWidth()/8+15, getHeight()/40*15-13);
				g.drawString("·  Taqueria Puro Potosino", getWidth()/18, getHeight()/80*33);
				g.drawString("·  Abu Omar Halal",getWidth()/18, getHeight()/80*36);
				g.drawString("·  Bad Chx",getWidth()/18, getHeight()/80*39);
				g.drawString("·  Chef Tai", getWidth()/18, getHeight()/80*42);
				g.drawString("·  Taz Indian Cuisine",getWidth()/18, getHeight()/80*45);
				g.drawString("| Zachry | ILCB", getWidth()/4, getHeight()/80*33);
				g.drawString("| Zachry", getWidth()/4, getHeight()/80*36);
				g.drawString("| Zachry", getWidth()/4, getHeight()/80*39);
				g.drawString("| Zachry", getWidth()/4, getHeight()/80*42);
				g.drawString("| Zachry", getWidth()/4, getHeight()/80*45);
			
				g.drawString("Others:", getWidth()/18, getHeight()/80*49);
				g.drawLine(getWidth()/18, getHeight()/80*50-1, getWidth()/9-3, getHeight()/80*50-1);
				g.drawString("·  Starbucks", getWidth()/18, getHeight()/80*52);
				g.drawString("·  Smoothie King",getWidth()/18, getHeight()/80*55);
				g.drawString("·  Shake Smart",getWidth()/18, getHeight()/80*58);
				g.drawString("·  Aggie Express", getWidth()/18, getHeight()/80*61);
				g.drawString("| Zachry | MSC | Evans | Hullabaloo", getWidth()/4, getHeight()/80*52);
				g.drawString("| MSC | SREC", getWidth()/4, getHeight()/80*55);
				g.drawString("| Polo Garage", getWidth()/4, getHeight()/80*58);
				g.drawString("| Commons | Pavilion", getWidth()/4, getHeight()/80*61);
				
				g.drawString(" 1 | 2", getWidth()/4, getHeight()/80*77);
				g.drawString("·", getWidth()/4+30, getHeight()/80*76);
				
				g.fillOval(25, getHeight()-80, 75, 70);
				g.setColor(mmaroon);
			    arrowHead = new Polygon();  
			    arrowHead.addPoint( 60,getHeight()-63);
			    arrowHead.addPoint( 40, getHeight()-43);
			    arrowHead.addPoint( 60,getHeight()-23);
			    ((Graphics2D) g).setStroke(new BasicStroke(6));
			    g.drawLine(60, getHeight()-43, 82, getHeight()-43);
			    g.fillPolygon(arrowHead);
			    break;	
			}
			break;
			
		case 2:
			Color green = new Color(195, 221, 208);


 			g.setColor(green);
 			g.fillRect(0, 0, getWidth(), getHeight());

 // 			try {
 // 				font = Font.createFont(Font.TRUETYPE_FONT, new File("Kitnoms-Regular.ttf")).deriveFont(150f);
 // 				GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
 // 				ge.registerFont(font);
 // 				campus=ImageIO.read(Panel.class.getResource("hh.png"));
 // 			} catch (FontFormatException | IOException e1) {
 // 				// TODO Auto-generated catch block
 // 				e1.printStackTrace();
 // 			}

 			g.setFont(cursive);
 			g.setColor(Color.white);
 			g.drawString("Landmarks", getWidth()*3/9, getHeight()/6);

 			g.setFont(exo_m);
 			g.drawString("o n    c a m p u s", getWidth()*6/15, getHeight()/4);


 			((Graphics2D) g).setStroke(new BasicStroke(6));
 			g.drawLine(30, getHeight()/4, getWidth()/3, getHeight()/4);
 			g.drawLine(getWidth()*2/3, getHeight()/4, getWidth()-30, getHeight()/4);

 			((Graphics2D) g).setStroke(new BasicStroke(2));
 			g.drawRoundRect(getWidth()/20, getHeight()*4/13, getWidth()/3+50, getHeight()*2/3 -10, 10, 10);

 			g.drawRoundRect(getWidth()/3+getWidth()/10+20, getHeight()*4/13, getWidth()/2+20, getHeight()*2/3 -10, 10, 10);

 			g.drawImage(hh, getWidth()/3+getWidth()/10+20, getHeight()*4/13, getWidth()/2+20, getHeight()*2/3 -10, null);
 			//MSC, Rudder, George Bush Library and Museum, Kyle Field, 12th Man, Corps of Cadets, The Gardens

 			Font ft = exo_m.deriveFont(20f);
 			g.drawString("Important Locations", getWidth()/18, getHeight()/40*15-10);
 			g.drawString("Landmarks",getWidth()/18, getHeight()/80*46);
 			g.drawString("Parks",getWidth()/18, getHeight()/80*61);
 			
 			g.setFont(exo_r);

 			g.drawLine(getWidth()/18, getHeight()/40*15-3, getWidth()/80*20, getHeight()/40*15-3);
 			g.drawLine(getWidth()/18, getHeight()/80*47-5, getWidth()/80*13, getHeight()/80*47-5);
 			g.drawLine(getWidth()/18, getHeight()/80*62-5, getWidth()/80*9, getHeight()/80*62-5);
 			
 			g.drawString("·  Memorial Student Center", getWidth()/18, getHeight()/80*33);
 			g.drawString("·  Kyle Field",getWidth()/18, getHeight()/80*36);
 			g.drawString("·  Texas A&M Rosenthal Meat Center",getWidth()/18, getHeight()/80*39);
 			g.drawString("·  Academic Plaza",getWidth()/18, getHeight()/80*42);

 	//		g.drawString("| Northside", getWidth()/4, getHeight()/80*33);
 	//		g.drawString("| Southside", getWidth()/4, getHeight()/80*36);
 	//		g.drawString("| Southside", getWidth()/4, getHeight()/80*39);
 			
 			g.drawString("·  Century Tree ",getWidth()/18, getHeight()/80*49);
 			g.drawString("·  Aggie Ring Statue      ",getWidth()/18, getHeight()/80*52);
 			g.drawString("·  George H.W. Bush Presidential Library      ",getWidth()/18, getHeight()/80*55);
 			g.drawString("·  Ford Hall of Champions   ",getWidth()/18, getHeight()/80*58);


 			g.drawString("·  The Gardens		",getWidth()/18, getHeight()/80*64);
 			g.drawString("·  Aggie Park    ",getWidth()/18, getHeight()/80*67);
 			g.drawString("·  Texas A&M Research Park",getWidth()/18, getHeight()/80*70);
 			g.drawString("·  Eli Whiteley Medal of Honor Park     ",getWidth()/18, getHeight()/80*73);
 	//		g.drawString("| MSC", getWidth()/4, getHeight()/80*46);
 	//		g.drawString("| MSC", getWidth()/4, getHeight()/80*49);
 	//		g.drawString("| MSC", getWidth()/4, getHeight()/80*52);
 	//		g.drawString("| MSC", getWidth()/4, getHeight()/80*55);
 	//		g.drawString("| MSC | Polo Garage", getWidth()/4, getHeight()/80*58);
 	//		g.drawString("| MSC | Polo Garage", getWidth()/4, getHeight()/80*61);
 	//		g.drawString("| MSC	| Sbisa Underground", getWidth()/4, getHeight()/80*64);
 	//		g.drawString("| Sbisa Complex", getWidth()/4, getHeight()/80*67);
 	//		g.drawString("| Sbisa Complex", getWidth()/4, getHeight()/80*70);
 	//		g.drawString("| Pavilion", getWidth()/4, getHeight()/80*73);

 	//		g.drawString(" 1 | 2", getWidth()/4, getHeight()/80*77);
 	//		g.drawString("·", getWidth()/4+5+4, getHeight()/80*76-2);



 			//button
 			g.fillOval(25, getHeight()-80, 75, 70);
 			g.setColor(green);
 		    Polygon arrowHead = new Polygon();  
 		    arrowHead.addPoint( 60,getHeight()-63);
 		    arrowHead.addPoint( 40, getHeight()-43);
 		    arrowHead.addPoint( 60,getHeight()-23);
 		    ((Graphics2D) g).setStroke(new BasicStroke(6));
 		    g.drawLine(60, getHeight()-43, 82, getHeight()-43);
 		    g.fillPolygon(arrowHead);
			
			break;
			
		case 3:
			g.setColor(mmaroon);
			 //		g.fillRect(0, 0, getWidth(), getHeight());
			 //		g.setColor(Color.black);
			 //		
			 //		g.setColor(Color.white);
			 //		g.setFont(new Font("TimesRoman", Font.BOLD, 110));
			 //		g.drawString("Facilities", getWidth()/3, getHeight()/8);
			 //		
			 //		Dimension d = this.getSize();
			 //		((Graphics2D) g).setStroke(new BasicStroke(6));
			 //        g.drawLine(30, d.height/4, d.width-30 , d.height/4);

			 		g.setColor(mmaroon);
			 		g.fillRect(0, 0, getWidth(), getHeight());

			 		g.setFont(cursive); // OG font
			 		g.setColor(Color.white);
			 		g.drawString("Facilities", getWidth()/30*11, getHeight()/8+10); // OG

			 		g.setFont(exo_m); // OG font
			 		g.drawString("  O N 	      C A M P U S", getWidth()/30*12-5, getHeight()/4); // OG
			 		//g.drawString("  O N 	      C A M P U S", getWidth()/30*12+20, getHeight()/4); // ryans

			 		((Graphics2D) g).setStroke(new BasicStroke(6));
			 		g.drawLine(40, getHeight()/4-10, getWidth()/30*11+5, getHeight()/4-10);
			 		g.drawLine(getWidth()/24*14+30, getHeight()/4-10, getWidth()-20, getHeight()/4-10);

			 		g.setColor(Color.white);
			 		((Graphics2D) g).setStroke(new BasicStroke(2));
			 		g.drawRect(60, getHeight()/30*9, getWidth()/2, getHeight()/20*13);
			 		g.setColor(pastel_maroon);
			 		g.fillRect(60, getHeight()/30*9, getWidth()/2, getHeight()/20*13);
			 		g.drawRect(getWidth()/20*11, getHeight()/30*9, getWidth()/30*12, getHeight()/20*13);
			 		g.drawImage(gym, getWidth()/20*11, getHeight()/30*9, getWidth()/30*12, getHeight()/20*13, null);
			 		
			 		g.setFont(exo_r);
			 		g.setColor(mmaroon);
			 		g.drawString(" 1 | 2", getWidth()/4, getHeight()/80*77);
					
			 		((Graphics2D) g).setStroke(new BasicStroke(2));
					g.setColor(Color.white);
					g.fillOval(30, getHeight()/6, 100, 100);
					g.setColor(mmaroon);
					g.drawOval(30, getHeight()/6, 100, 100);
					arrowHead = new Polygon();  
				    arrowHead.addPoint(80, getHeight()/60*11);
				    arrowHead.addPoint(50, getHeight()/120*29);
				    arrowHead.addPoint(80, getHeight()/60*16);
				    ((Graphics2D) g).setStroke(new BasicStroke(6));
				    g.drawLine(80, getHeight()/120*29, 110, getHeight()/120*29);
				    g.fillPolygon(arrowHead);
				    
			 		
			 		// ----- PAGE 1 (Facilities):
					switch(fac_page) {
					case 1:
			 		ft = exo_m.deriveFont(25f);
			 		g.setFont(ft);
			 		g.setColor(mmaroon);
			 		//g.setFont(new Font("Dialog", Font.PLAIN, 20)); // just so it'll run on mine RIP - ryan
			 		g.drawString("Exercise Facilities:", getWidth()/18, getHeight()/40*14);
			 		g.drawString("Libraries:",getWidth()/18, getHeight()/60*32); // ryans
			 		g.drawString("Resource Facilities:",getWidth()/18, getHeight()/80*65); // ryans
			 		g.setFont(exo_r); // OG font
			 		g.drawString("·  PEAP Building", getWidth()/18, getHeight()/80*30+2);
			 		g.drawString("·  Polo Road Rec Center", getWidth()/18, getHeight()/80*33+1);
			 		g.drawString("·  Student Recreation Center",getWidth()/18, getHeight()/80*36);
			 		g.drawString("·  Southside Recreation Center",getWidth()/18, getHeight()/80*39);
			 		//g.drawString("Libraries:",getWidth()/18, getHeight()/2); // OG
			 		g.drawString("·  Evans Library ",getWidth()/18, getHeight()/80*46);
			 		g.drawString("·  Geoerge H.W. Bush Presidential Library and Museum		      ",getWidth()/18, getHeight()/80*49);
			 		g.drawString("·  Medical Sciences Library (MSL)      ",getWidth()/18, getHeight()/80*52);
			 		g.drawString("·  Business Library (BLCC)   ",getWidth()/18, getHeight()/80*55);
			 		g.drawString("·  Cushing Memorial Library and Archives        ",getWidth()/18, getHeight()/80*58);
			 		g.drawString("·  Policy Sciences & Economics Library",getWidth()/18, getHeight()/80*61);
			 		//g.drawString("Resource Facilities:",getWidth()/18, getHeight()/2+245); // ryans
			 		g.drawString("·  YMCA Building ",getWidth()/18, getHeight()/80*68);
			 		g.drawString("·  Beutel Health Center		      ",getWidth()/18, getHeight()/80*71);
			 		g.drawString("·  Alumni Resource Center		      ",getWidth()/18, getHeight()/80*74);

			 		g.drawString("·", getWidth()/4+5, getHeight()/80*76);
			 		
			 		break;
			 		case 2:
			 			ft = exo_m.deriveFont(25f);
				 		g.setFont(ft);
			 		// ----- PAGE 2 (Facilities): page turner feature still needs to be added here :o
			 		g.drawString("Leisure:", getWidth()/18, getHeight()/40*15-28);
			 		g.setFont(exo_r); // OG font
			 		g.setColor(mmaroon);
			 		//g.setFont(new Font("Dialog", Font.PLAIN, 20)); // just so it'll run on mine RIP - ryan
			 		
			 		g.drawString("·  Tennis Courts", getWidth()/18, getHeight()/80*30+2);
			 		g.drawString("·  Sand Volleyball Courts", getWidth()/18, getHeight()/80*33+1);
			 		g.drawString("·  Golf Course",getWidth()/18, getHeight()/80*36);
			 		g.drawString("·  AggiePark",getWidth()/18, getHeight()/80*39);
			 		g.drawString("·", getWidth()/4+30, getHeight()/80*76);
					
				    break;
					}
			break;
			
		case 4:
			g.setColor(yellow);
			g.fillRect(0, 0, getWidth(), getHeight());
			g.setFont(cursive);
			g.setColor(darkbrown);
			g.drawString(" F o o d", getWidth()/30*10, getHeight()/8+10);
			g.setFont(exo_m);
			g.drawString("I N   C O L L E G E   S T A T I O N", getWidth()/30*11-2, getHeight()/4);
			((Graphics2D) g).setStroke(new BasicStroke(6));
			g.drawLine(40, getHeight()/4-10, getWidth()/3, getHeight()/4-10);
			g.drawLine(getWidth()/24*16, getHeight()/4-10, getWidth()-30, getHeight()/4-10);
			
			//g.fillOval(20, getHeight()/3, 10, 10);
			
			g.setColor(umber);
			g.drawRoundRect(40, getHeight()/30*9, getWidth()/30*14, getHeight()/15, 50, 50);
			g.drawRoundRect(40, getHeight()/30*12, getWidth()/30*14, getHeight()/15, 50, 50);
			g.drawRoundRect(40, getHeight()/30*15, getWidth()/30*14, getHeight()/15, 50, 50);
			g.drawRoundRect(40, getHeight()/30*18, getWidth()/30*14, getHeight()/15, 50, 50);
			g.drawRoundRect(40, getHeight()/30*21, getWidth()/30*14, getHeight()/15, 50, 50);
			g.drawRoundRect(40, getHeight()/30*24, getWidth()/30*14, getHeight()/15, 50, 50);
			g.drawRoundRect(40, getHeight()/30*27, getWidth()/30*14, getHeight()/15, 50, 50);
			
			g.setColor(Color.black);
			((Graphics2D) g).setStroke(new BasicStroke(8));
			g.drawRoundRect(40, getHeight()/30*(9+3*(food_select-1)), getWidth()/30*14, getHeight()/15, 50, 50);
			((Graphics2D) g).setStroke(new BasicStroke(6));
			
			g.setColor(cream_gray);
			g.fillRoundRect(40, getHeight()/30*9, getWidth()/30*14, getHeight()/15, 50, 50);
			g.fillRoundRect(40, getHeight()/30*12, getWidth()/30*14, getHeight()/15, 50, 50);
			g.fillRoundRect(40, getHeight()/30*15, getWidth()/30*14, getHeight()/15, 50, 50);
			g.fillRoundRect(40, getHeight()/30*18, getWidth()/30*14, getHeight()/15, 50, 50);
			g.fillRoundRect(40, getHeight()/30*21, getWidth()/30*14, getHeight()/15, 50, 50);
			g.fillRoundRect(40, getHeight()/30*24, getWidth()/30*14, getHeight()/15, 50, 50);
			g.fillRoundRect(40, getHeight()/30*27, getWidth()/30*14, getHeight()/15, 50, 50);
			
			g.setColor(darkbrown);
			g.setFont(exo_r);
			g.drawString("Fuego Tortilla Grill", 80, getHeight()/90*31);
			g.drawString("ShareTea", 80, getHeight()/90*40);
			g.drawString("Burger Mojo", 80, getHeight()/90*49);
			g.drawString("C&J Barbeque", 80, getHeight()/90*58);
			g.drawString("Ohana Korean Grill", 80, getHeight()/90*67);
			g.drawString("Sweet Eugenes", 80, getHeight()/90*76);
			g.drawString("Mess Waffles", 80, getHeight()/90*85);
			
			g.drawRect(getWidth()/30*17, getHeight()/90*31, getWidth()/3, getHeight()/18*11);
			BufferedImage img = food_m.get(food_select);
			g.drawImage(img, getWidth()/30*17, getHeight()/90*31, getWidth()/3, getHeight()/18*11, null);
			try{
				Scanner sc = new Scanner(new File("food.txt"));
				String line = "";
				for(int i = 0; i < food_select; i++) {
					line = sc.nextLine();
				}
				g.drawString(line, getWidth()/30*17, getHeight()/90*28);
			}
			catch(IOException e) {System.out.println("file not read");}
			
			
			((Graphics2D) g).setStroke(new BasicStroke(2));
			g.setColor(cream_gray);
			g.fillOval(30, 30, 100, 100);
			g.setColor(darkbrown);
			g.drawOval(30, 30, 100, 100);
			arrowHead = new Polygon();  
		    arrowHead.addPoint(75, 51);
		    arrowHead.addPoint(45, 81);
		    arrowHead.addPoint(75, 111);
		    ((Graphics2D) g).setStroke(new BasicStroke(6));
		    g.drawLine(75, 81, 100, 81);
		    g.fillPolygon(arrowHead);
			
			break;
			
		case 5:
			g.setColor(gray_green);
			g.fillRect(0, 0, getWidth(), getHeight());
			Font newFont = cursive.deriveFont(82f);
			g.setFont(newFont);
			g.setColor(Color.black);
			g.drawString("ENTERTAINMENT", getWidth()/30*2, getHeight()/8+20);
			g.setFont(exo_m);
			g.drawString("I N   C O L L E G E   S T A T I O N", getWidth()/30*11-3, getHeight()/4-10);
			((Graphics2D) g).setStroke(new BasicStroke(6));
			g.drawLine(40, getHeight()/4-20, getWidth()/3, getHeight()/4-20);
			g.drawLine(getWidth()/24*16, getHeight()/4-20, getWidth()-35, getHeight()/4-20);
			
			g.drawRoundRect(40, getHeight()/30*8, getWidth()/30*12, getHeight()/15, 50, 50);
			g.drawRoundRect(40, getHeight()/30*11, getWidth()/30*12, getHeight()/15, 50, 50);
			g.drawRoundRect(40, getHeight()/30*14, getWidth()/30*12, getHeight()/15, 50, 50);
			g.drawRoundRect(40, getHeight()/30*17, getWidth()/30*12, getHeight()/15, 50, 50);
			g.drawRoundRect(40, getHeight()/30*20, getWidth()/30*12, getHeight()/15, 50, 50);
			g.drawRoundRect(40, getHeight()/30*23, getWidth()/30*12, getHeight()/15, 50, 50);
			g.drawRoundRect(40, getHeight()/30*26, getWidth()/30*12, getHeight()/15, 50, 50);
			
			g.setColor(gr);
			((Graphics2D) g).setStroke(new BasicStroke(8));
			g.drawRoundRect(40, getHeight()/30*(8+3*(ent_select-1)), getWidth()/30*12, getHeight()/15, 50, 50);
			((Graphics2D) g).setStroke(new BasicStroke(6));
			
			g.setColor(cream);
			g.fillRoundRect(40, getHeight()/30*8, getWidth()/30*12, getHeight()/15, 50, 50);
			g.fillRoundRect(40, getHeight()/30*11, getWidth()/30*12, getHeight()/15, 50, 50);
			g.fillRoundRect(40, getHeight()/30*14, getWidth()/30*12, getHeight()/15, 50, 50);
			g.fillRoundRect(40, getHeight()/30*17, getWidth()/30*12, getHeight()/15, 50, 50);
			g.fillRoundRect(40, getHeight()/30*20, getWidth()/30*12, getHeight()/15, 50, 50);
			g.fillRoundRect(40, getHeight()/30*23, getWidth()/30*12, getHeight()/15, 50, 50);
			g.fillRoundRect(40, getHeight()/30*26, getWidth()/30*12, getHeight()/15, 50, 50);
			
			g.setColor(Color.black);
			g.setFont(exo_r);
			g.drawString("Aggie Park", 80, getHeight()/90*28);
			g.drawString("Lake Bryan", 80, getHeight()/90*37);
			g.drawString("Wild Animal Safari", 80, getHeight()/90*46);
			g.drawString("Jumping World", 80, getHeight()/90*55);
			g.drawString("Escape Room ", 80, getHeight()/90*64);
			g.drawString("Ice Skating", 80, getHeight()/90*73);
			g.drawString("Grand Station Entertainment", 80, getHeight()/90*82);
			
			g.drawRect(getWidth()/2, getHeight()/90*27, getWidth()/30*13, getHeight()/18*11);
			img = ent_m.get(ent_select);
			g.drawImage(img, getWidth()/2, getHeight()/90*27, getWidth()/30*13, getHeight()/18*11, null);
			
			((Graphics2D) g).setStroke(new BasicStroke(2));
			g.setColor(cream);
			g.fillOval(getWidth()/32*29, getHeight()/60*52, 100, 100);
			g.setColor(Color.black);
			g.drawOval(getWidth()/32*29, getHeight()/60*52, 100, 100);
			arrowHead = new Polygon();  
		    arrowHead.addPoint(getWidth()/64*62-3, getHeight()/60*53+3);
		    arrowHead.addPoint(getWidth()/64*60-3, getHeight()/60*55+3);
		    arrowHead.addPoint(getWidth()/64*62-3, getHeight()/60*57+3);
		    ((Graphics2D) g).setStroke(new BasicStroke(6));
		    g.drawLine(getWidth()/64*62-3, getHeight()/60*55+3, getWidth()/64*63-3, getHeight()/60*55+3);
		    g.fillPolygon(arrowHead);
		    
		    break;
		    
		case 6:
			g.setColor(pastel_maroon);
			g.fillRect(0, 0, getWidth(), getHeight());
			g.setFont(cursive);
			g.setColor(maroon);
			g.drawString("Shopping", getWidth()/30*10, getHeight()/8+10);
			g.setFont(exo_m);
			g.drawString("I N  C O L L E G E  S T A T I O N", getWidth()/30*11-2, getHeight()/4);
			((Graphics2D) g).setStroke(new BasicStroke(6));
			g.drawLine(40, getHeight()/4-10, getWidth()/3, getHeight()/4-10);
			g.drawLine(getWidth()/24*16, getHeight()/4-10, getWidth()-30, getHeight()/4-10);
			
			//g.fillOval(20, getHeight()/3, 10, 10);
			
			g.setColor(maroon);
			g.drawRoundRect(40, getHeight()/30*9, getWidth()/30*14, getHeight()/15, 50, 50);
			g.drawRoundRect(40, getHeight()/30*12, getWidth()/30*14, getHeight()/15, 50, 50);
			g.drawRoundRect(40, getHeight()/30*15, getWidth()/30*14, getHeight()/15, 50, 50);
			g.drawRoundRect(40, getHeight()/30*18, getWidth()/30*14, getHeight()/15, 50, 50);
			g.drawRoundRect(40, getHeight()/30*21, getWidth()/30*14, getHeight()/15, 50, 50);
			g.drawRoundRect(40, getHeight()/30*24, getWidth()/30*14, getHeight()/15, 50, 50);
			g.drawRoundRect(40, getHeight()/30*27, getWidth()/30*14, getHeight()/15, 50, 50);
			
			g.setColor(mmaroon);
			((Graphics2D) g).setStroke(new BasicStroke(8));
			g.drawRoundRect(40, getHeight()/30*(9+3*(shop_select-1)), getWidth()/30*14, getHeight()/15, 50, 50);
			((Graphics2D) g).setStroke(new BasicStroke(6));
			
			
			g.setColor(cream);
			g.fillRoundRect(40, getHeight()/30*9, getWidth()/30*14, getHeight()/15, 50, 50);
			g.fillRoundRect(40, getHeight()/30*12, getWidth()/30*14, getHeight()/15, 50, 50);
			g.fillRoundRect(40, getHeight()/30*15, getWidth()/30*14, getHeight()/15, 50, 50);
			g.fillRoundRect(40, getHeight()/30*18, getWidth()/30*14, getHeight()/15, 50, 50);
			g.fillRoundRect(40, getHeight()/30*21, getWidth()/30*14, getHeight()/15, 50, 50);
			g.fillRoundRect(40, getHeight()/30*24, getWidth()/30*14, getHeight()/15, 50, 50);
			g.fillRoundRect(40, getHeight()/30*27, getWidth()/30*14, getHeight()/15, 50, 50);
			
			g.setColor(maroon);
			g.setFont(exo_r);
			g.drawString("Post Oak Mall", 80, getHeight()/90*31);
			g.drawString("Century Square", 80, getHeight()/90*40);
			g.drawString("CC Warehouse", 80, getHeight()/90*49);
			g.drawString("Aggie Outfitters", 80, getHeight()/90*58);
			g.drawString("Maroon U", 80, getHeight()/90*67);
			g.drawString("Central Station", 80, getHeight()/90*76);
			g.drawString("Threads Boutique", 80, getHeight()/90*85);
			
			g.drawRect(getWidth()/30*17, getHeight()/3, getWidth()/3, getHeight()/18*11);
			img = shop_m.get(shop_select);
			g.drawImage(img, getWidth()/30*17, getHeight()/3, getWidth()/3, getHeight()/18*11, null);
			
			((Graphics2D) g).setStroke(new BasicStroke(2));
			g.setColor(cream);
			g.fillOval(30, 30, 100, 100);
			g.setColor(maroon);
			g.drawOval(30, 30, 100, 100);
			arrowHead = new Polygon();  
		    arrowHead.addPoint(75, 51);
		    arrowHead.addPoint(45, 81);
		    arrowHead.addPoint(75, 111);
		    ((Graphics2D) g).setStroke(new BasicStroke(6));
		    g.drawLine(75, 81, 100, 81);
		    g.fillPolygon(arrowHead);
			
		}
	}
	
	public void addNotify()
	{
		super.addNotify();
		requestFocus();
	}
	
	public void reset() {
		food_page=1;
		food_select=1;
		ent_select = 1;
		shop_select = 1;
		fac_page = 1;
		begin = false;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		System.out.println("code: "+e.getKeyCode());
		//food on campus 
		if(choice == 1) {
			if(food_page == 1 && e.getKeyCode()==39) {
				food_page = 2;
				begin = true;
			}
			else if(food_page == 2 && e.getKeyCode()==37)
				food_page = 1;
			begin = true;
		}
		else if(choice == 3) {
			if(fac_page == 1 && e.getKeyCode()==39) {
				fac_page = 2;
			}
			else if(fac_page == 2 && e.getKeyCode()==37)
				fac_page = 1;
		}
		else if(choice == 4) {
			if(e.getKeyCode()==40) {
				if(food_select!=7)
					food_select++;}
			//end of page situation not implemented
			else if(e.getKeyCode()==38) {
				if(food_select!=1)
					food_select--;
			}
			System.out.println("food select "+food_select);	
		}
		else if(choice == 5) {
			if(e.getKeyCode()==40) {
				if(ent_select!=7)
					ent_select++;}
			//end of page situation not implemented
			else if(e.getKeyCode()==38) {
				if(ent_select!=1)
					ent_select--;
			}
		}
		else if(choice == 6) {
			if(e.getKeyCode()==40) {
				if(shop_select!=7)
					shop_select++;}
			//end of page situation not implemented
			else if(e.getKeyCode()==38) {
				if(shop_select!=1)
					shop_select--;
			}
		}
		repaint();
		
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
		if(choice == 0 && x>getWidth()/10 && x <getWidth()/10+getWidth()/3-getWidth()/13 && y > getHeight()/3+50 && y < getHeight()/3+50+ getHeight()/8) {
			choice = 1;
		}
		else if(choice == 0 && x>getWidth()/10 && x <getWidth()/10+getWidth()/3-getWidth()/13 && y > getHeight()/2+50 && y < getHeight()/2+50+ getHeight()/8) {
			choice = 2;
		}
		else if(choice == 0 && x>getWidth()/10 && x <getWidth()/10+getWidth()/3-getWidth()/13 && y > getHeight()/3*2+50 && y < getHeight()/3*2+50+ getHeight()/8) {
			choice = 3;
		}
		else if(choice == 0 && x>getWidth()/3+getWidth()/7+10 && x <getWidth()/3+getWidth()/7+10+getWidth()/5*2 && y > getHeight()/5-5 && y < getHeight()/5-5+getHeight()/11) {
			choice = 4;
		}
		else if( choice == 0 && x>getWidth()/3+getWidth()/7+10 && x <getWidth()/3+getWidth()/7+10+getWidth()/5*2 && y > getHeight()/2-getHeight()/16 && y < getHeight()/2-getHeight()/16+getHeight()/11) {
			choice = 5;
		}
		else if(choice == 0 && x>getWidth()/3+getWidth()/7+10 && x <getWidth()/3+getWidth()/7+10+getWidth()/5*2 && y > getHeight()/4*3-getHeight()/16 && y < getHeight()/4*3-getHeight()/16+getHeight()/11) {
			choice = 6;
		}
		//back arrow
		else if(choice == 1 && x>25 && x<100 && y>getHeight()-80 && y<getHeight()-10) {
			choice = 0;
			reset();
		}
		else if(choice == 2 && x > 25 && x < 100 && y > getHeight()-80 && y < getHeight()-10) {
			choice = 0;
			reset();
		}
		else if(choice == 3 && x > 30 && x <130 && y > getHeight()/6 && y < getHeight()/6+100) {
			choice = 0;
			reset();
		}
		else if(choice == 4 && x>30 && x<130 && y>30 && y<130) {
			choice = 0;
			reset();
		}
		else if(choice == 5 && x > getWidth()/32*29 && x < getWidth()/32*29+100 && y > getHeight()/60*52 && y < getHeight()/60*52+100) {
			choice = 0;
			reset();
		}
		else if(choice == 6 && x>30 && x<130 && y>30 && y<130) {
			choice = 0;
			reset();
		}
		
		System.out.println(choice);
		repaint();
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
