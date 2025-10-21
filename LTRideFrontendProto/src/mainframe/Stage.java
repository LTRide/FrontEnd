package mainframe;

import javax.swing.*;
import java.awt.*;

public class Stage extends JFrame
{
	private static final int WIDTH = 540;
	private static final int HEIGHT = 960;
	
	public static void main(String[] args)
	{
		Stage run = new Stage();
	}
	
	public Stage()
	{
		super("LTRide");
		setSize(WIDTH, HEIGHT);
		
		getContentPane().add(new MainPanel());
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
