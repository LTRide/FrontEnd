package mainframe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.util.*;

public class MainPanel extends JPanel implements Runnable
{
	private static final long serialVersionUID = 1L;
	private double zoom = 1.0;
	private int offsetX = 0, offsetY = 0;
	private int dragStartX = 0, dragStartY = 0;
    private final int panelWidth = Stage.getScreenWidth();
    private final int panelHeight = Stage.getScreenHeight();

	
	public MainPanel()
	{
		setVisible(true);
		setLayout(null);
		
        // Adding necessary buttons for UI
        BufferedImage iconImgS = Stage.getSearchButton();
        JButton searchButton;
        int btnW = 63, btnH = 63;
        if (iconImgS != null) 
        {
            // Scale to button size so the icon fits
            Image scaled = iconImgS.getScaledInstance(btnW, btnH, Image.SCALE_SMOOTH);
            searchButton = new JButton(new ImageIcon(scaled));
            searchButton.setBorderPainted(false);
            searchButton.setContentAreaFilled(false);
            searchButton.setOpaque(false);
        } 
        else 
        {
            System.err.println("Search icon is null; creating fallback button.");
            searchButton = new JButton("Search"); // visible fallback so you can see the button
        }

        searchButton.setBounds(10, 835, btnW, btnH);
        add(searchButton);  // Search Button
        
        BufferedImage iconImgM = Stage.getMenuButton();
        JButton menuButton;
        if (iconImgM != null)
        {
        	Image scaled = iconImgM.getScaledInstance(btnW, btnH, Image.SCALE_SMOOTH);
        	menuButton = new JButton(new ImageIcon(scaled));
            menuButton.setBorderPainted(false);
            menuButton.setContentAreaFilled(false);
            menuButton.setOpaque(false);
        }
        else 
        {
            System.err.println("Menu icon is null; creating fallback button.");
            menuButton = new JButton("Menu"); // visible fallback so you can see the button
        }
        
        menuButton.setBounds(10, 10, btnW, btnH);
        add(menuButton); // Menu Button
		
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
					
					zoom = Math.max(0.1, Math.min(zoom,  5.0));
					
			        // Get mouse position
			        int mouseX = e.getX();
			        int mouseY = e.getY();

			        // Adjust offset so zoom centers on mouse
			        offsetX = (int) (mouseX - ((mouseX - offsetX) * (zoom / oldZoom)));
			        offsetY = (int) (mouseY - ((mouseY - offsetY) * (zoom / oldZoom)));
					
			        // Clamp offset so map edges stay outside window
			        int mapWidth = (int)(Stage.getMap().getWidth() * zoom);
			        int mapHeight = (int)(Stage.getMap().getHeight() * zoom);

			        // Prevent empty space on left/top
			        offsetX = Math.min(offsetX, 0);
			        offsetY = Math.min(offsetY, 0);

			        // Prevent empty space on right/bottom
			        offsetX = Math.max(offsetX, panelWidth - mapWidth);
			        offsetY = Math.max(offsetY, panelHeight - mapHeight);
			        
			        // Center vertically if map is smaller than panel
			        if (mapHeight < panelHeight) 
			        {
			            offsetY = (panelHeight - mapHeight) / 2;
			        } 
			        else 
			        {
			            // Clamp vertical edges
			            offsetY = Math.min(offsetY, 0);
			            offsetY = Math.max(offsetY, panelHeight - mapHeight);
			        }
			        
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
                int originX = offsetX;
                int originY = offsetY;
                

                int mouseX = e.getX();
                int mouseY = e.getY();

                int newOffsetX = mouseX - dragStartX;
                int newOffsetY = mouseY - dragStartY;
                
                // Convert mouse position to image coordinates
                int imageX = (int) ((mouseX - offsetX) / zoom);
                int imageY = (int) ((mouseY - offsetY) / zoom);

                // Check if mouse is within image bounds
                boolean withinImage = imageX >= 0 && imageX < Stage.getMap().getWidth()
                                   && imageY >= 0 && imageY < Stage.getMap().getHeight();

                if (withinImage) 
                {
                    offsetX = mouseX - dragStartX;
                    offsetY = mouseY - dragStartY;
                } 
                else 
                {
                    offsetX = originX;
                    offsetY = originY;
                }
                
             // Clamp panning
		        int mapWidth = (int)(Stage.getMap().getWidth() * zoom);
		        int mapHeight = (int)(Stage.getMap().getHeight() * zoom);

                newOffsetX = Math.min(newOffsetX, 0);
                newOffsetX = Math.max(newOffsetX, panelWidth - mapWidth);
                offsetX = newOffsetX;
                
                newOffsetY = Math.min(newOffsetY, 0);
                newOffsetY = Math.max(newOffsetY, panelHeight - mapHeight);
                offsetY = newOffsetY;
                
		        // Center vertically if map is smaller than panel
		        if (mapHeight < panelHeight) 
		        {
		            offsetY = (panelHeight - mapHeight) / 2;
		        } 
		        else 
		        {
		            // Clamp vertical edges
		            offsetY = Math.min(offsetY, 0);
		            offsetY = Math.max(offsetY, panelHeight - mapHeight);
		        }

                repaint();            
            }
        });
	}
	
	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		if (Stage.getMap() != null)
		{
			Graphics2D g2d = (Graphics2D) g.create();
			
			g2d.clearRect(0, 0, getWidth(), getHeight());
			g2d.translate(offsetX, offsetY);
			g2d.scale(zoom, zoom);
			g2d.drawImage(Stage.getMap(), 0, 0, this);
			g2d.dispose();
		}
	}
	
	@Override public void run() {}
}
