package mainframe;

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class MainPanel extends JPanel implements Runnable
{
	private ArrayList<AbstractShape> scaffolding;
	private AbstractShape ui;
	
	public MainPanel()
	{
		setVisible(true);
		scaffolding = new ArrayList<AbstractShape>();
	}
	
	@Override
	public void run() 
	{
		// TODO Auto-generated method stub
		
	}
}
