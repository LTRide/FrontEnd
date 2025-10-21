package mainframe;

import java.awt.*;

public abstract class AbstractShape
{
   //instance variables
private int xPos;
private int yPos;

private int width;
private int height;

private Color color;

private int xVelocity;
private int yVelocity;


   public AbstractShape(int x, int y, int wid, int ht)
   {
		xPos = x;
		yPos = y;
		width = wid;
		height = ht;
		color = Color.WHITE;
		xVelocity = 0;
		yVelocity= 0;
   }
   
   public AbstractShape(int x, int y, int wid, int ht, Color col)
   {
		xPos = x;
		yPos = y;
		width = wid;
		height = ht;
		color = col;
		xVelocity = 0;
		yVelocity= 0;
   }  
   
   public AbstractShape(int x, int y, int wid, int ht, Color col, int xSpd, int ySpd)
   {
		xPos = x;
		yPos = y;
		width = wid;
		height = ht;
		color = col;
		xVelocity = xSpd;
		yVelocity= ySpd;
   }  
 
   //add in set and get methods for position and velocity
   public void setXPos( int xp)
   {
	    xPos = xp;
   }
   
   public void setYPos( int yp)
   {
	    yPos = yp;
   }
   
   public void setXVelocity( int xs)
   {
	    xVelocity = xs;
   }
   
   public void setYVelocity(int ys)
   {
	    yVelocity = ys;
   }
   
   public int getYVelocity()
   {
	    return yVelocity;
   }
   
   public int getXVelocity()
   {
	    return xVelocity;
   }
   
   public int getXPos()
   {
	    return xPos;
   }
   
   public int getYPos()
   {
	    return yPos;
   }
   
   public Color getColor()
   {
	    return color;
   }
   
   public int getWidth()
   {
	    return width;
   }
   
   public int getHeight()
   {
	    return height;
   }

   public abstract void draw(Graphics window);

   public abstract void moveAndDraw(Graphics window);

   public String toString()
   {
	    return xPos+" "+yPos+" "+width+" "+height+" "+color+" "+xVelocity+" "+yVelocity;
   }
}

