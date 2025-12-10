package mainframe;

public class ParkingSpace 
{
	private static boolean visible = false;
	private static boolean vacant = false;
	private static int x_Coordinate;
	private static int y_Coordinate;
	private static int rotation;
	
	public ParkingSpace(int x, int y, int r)
	{
		x_Coordinate = x;
		y_Coordinate = y;
		rotation = r;
	}
	
	public static void setVisibility (boolean v)
	{ visible = v; }
}
