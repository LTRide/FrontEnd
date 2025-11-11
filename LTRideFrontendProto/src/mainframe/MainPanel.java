package mainframe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.util.*;

public class MainPanel extends JPanel implements Runnable
{
	private ArrayList<AbstractShape> scaffolding;
	private AbstractShape ui;
	private static final long serialVersionUID = 1L;
	private double zoom = 1.0;
	private int offsetX = 0, offsetY = 0;
	private int dragStartX = 0, dragStartY = 0;
	
	public MainPanel()
	{
		setVisible(true);
		
		addMouseWheelListener(new MouseAdapter()
			{
				@Override
				public void mouseWheelMoved(MouseWheelEvent e)
				{
					double oldZoom = zoom;
					if (e.getPreciseWheelRotation() < 0)
						zoom *= 1.1;
					else
						zoom /= 1.1;
					
			        // Get mouse position
			        int mouseX = e.getX();
			        int mouseY = e.getY();

			        // Adjust offset so zoom centers on mouse
			        offsetX = (int) (mouseX - ((mouseX - offsetX) * (zoom / oldZoom)));
			        offsetY = (int) (mouseY - ((mouseY - offsetY) * (zoom / oldZoom)));
					
					repaint();
				}
			});
		
        addMouseListener(new MouseAdapter() 
        {
            @Override
            public void mousePressed(MouseEvent e) 
            {
                dragStartX = e.getX() - offsetX;
                dragStartY = e.getY() - offsetY;
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() 
        {
            @Override
            public void mouseDragged(MouseEvent e) 
            {
                offsetX = e.getX() - dragStartX;
                offsetY = e.getY() - dragStartY;
                repaint();
            }
        });
		
		scaffolding = new ArrayList<AbstractShape>();
	}
	
	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		if (Stage.getMap() != null)
		{
			Graphics2D g2d = (Graphics2D) g;
			g2d.clearRect(0, 0, getWidth(), getHeight());
			g2d.translate(offsetX, offsetY);
			g2d.scale(zoom, zoom);
			g.drawImage(Stage.getMap(), 0, 0, this);
		}
	}
	
	@Override
	public void run() 
	{
		// TODO Auto-generated method stub
		
	}
}
