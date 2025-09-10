import javax.swing.*;
import java.awt.*;

public class Board extends JPanel
{
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        int counter = 1;
        for (int i = 0, x = 0; i < 8; i++, x+=60){
            counter++;
            for (int j = 0, y = 0; j < 8; j++, y+=60){
                if (counter % 2 != 0){
                    g2d.setColor(Color.GREEN);
                }
                else{
                    g2d.setColor(Color.white);
                }
                counter++;
                g2d.fillRect(x,y,60,60);
            }
        }
    }
    public Board()
    {
        this.setPreferredSize(new Dimension(480,480));

    }
}
