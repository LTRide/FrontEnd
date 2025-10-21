import javax.swing.*;
import java.awt.*;

public class StagePanel
{
    private JFrame frame;
    private static int WIDTH = 1080;
    private static int HEIGHT = 1920;

    public StagePanel(int width, int height)
    {
        system.out.println("running");
        frame = new JFrame("LTRide");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(width, height);
        frame.setLocationRelativeTo(null); //Centering the window
        frame.setVisible(true);
    }

    public static void main (String [] args)
    {
        SwingUtilities.invokeLater(() -> new StagePanel(WIDTH, HEIGHT));

        // @Override
        // protected void paintComponent(Graphics g)
        // {
        //     super.paintComponent(g);


            
        // }
    }
}