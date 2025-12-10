package mainframe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.util.*;

public class SearchPanel extends JPanel implements Runnable
{
	private static final long serialVersionUID = 2L;
	
	public SearchPanel()
	{
		setVisible(false);
		
	}
	
	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
	}
	
	@Override public void run() {}
}
