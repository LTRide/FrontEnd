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
	private static BufferedImage searchIcon = null;
	
	public static void main(String[] args)
	{
        // Load images with checks
        File mapFile = new File("H:\\FrontEnd\\LTRideFrontendProto\\Assets\\Map Assets\\Zoomable School Map Proto.png");
        if (!mapFile.exists()) 
        {
            System.err.println("Map file not found: " + mapFile.getAbsolutePath());
        } 
        else 
        {
            try 
            {
                BufferedImage tmp = ImageIO.read(mapFile);
                if (tmp == null) 
                {
                    System.err.println("ImageIO.read returned null for map file: " + mapFile.getAbsolutePath());
                } 
                else 
                {
                    setMap(tmp);
                }
            } 
            catch (Exception e) { e.printStackTrace(); }
        }

        File searchFile = new File("H:/FrontEnd/LTRideFrontendProto/Assets/Wireframe Assets/Search Icon.png");
        if (!searchFile.exists()) 
        {
            System.err.println("Search icon file not found: " + searchFile.getAbsolutePath());
        } 
        else 
        {
            try 
            {
                BufferedImage tmp = ImageIO.read(searchFile);
                if (tmp == null) 
                {
                    System.err.println("ImageIO.read returned null for search icon file: " + searchFile.getAbsolutePath());
                } 
                else 
                {
                    setSearchButton(tmp);
                }
            } 
            catch (Exception e) { e.printStackTrace(); }
        }

        // Create Swing UI on the Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            Stage run = new Stage();
        });
	}
	
	public Stage()
	{
		super("LTRide");
		setSize(WIDTH, HEIGHT);
		setResizable(false);
		
		getContentPane().add(new MainPanel());
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static BufferedImage getMap() 
	{
		return map;
	}

	public static void setMap(BufferedImage map) 
	{
		Stage.map = map;
	}
	
	public static BufferedImage getSearchButton()
	{
		return searchIcon;
	}
	
	public static void setSearchButton(BufferedImage searchIcon)
	{
		Stage.searchIcon = searchIcon;
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
