package mainframe;

import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;

public class Stage extends JFrame
{
	private static final int WIDTH = 540;
	private static final int HEIGHT = 960;
	private static BufferedImage map = null;
	
	public static void main(String[] args)
	{
		try
		{
			setMap(ImageIO.read(new File("H:\\FrontEnd\\LTRideFrontendProto\\Assets\\Map Assets\\Zoomable School Map Proto.png")));
		} catch (Exception e) {e.printStackTrace();}
		
		Stage run = new Stage();
	}
	
	public Stage()
	{
		super("LTRide");
		setSize(WIDTH, HEIGHT);
		setResizable(false);
		
		getContentPane().add(new MainPanel());
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Graphics2D g2d = getMap().createGraphics();
		g2d.drawImage(getMap(), 0, 0, null);
		g2d.dispose();
	}

	public static BufferedImage getMap() 
	{
		return map;
	}

	public static void setMap(BufferedImage map) 
	{
		Stage.map = map;
	}
	
	public static int getScreenWidth()
	{
		return WIDTH;
	}
	
	public static int getScreenHeight()
	{
		return HEIGHT;
	}
	
}
