import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Board extends JPanel
{
    public BufferedImage image;
    public JLabel label;
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        int counter = 1;
        for (int i = 0, x = 0; i < 8; i++, x+=90) {
            counter++;
            for (int j = 0, y = 0; j < 8; j++, y += 90) {
                if (counter % 2 != 0) {
                    g2d.setColor(Color.GREEN);
                } else {
                    g2d.setColor(Color.white);
                }
                counter++;
                g2d.fillRect(x, y, 90, 90);
            }
        }
    }
    public Board() {
        this.setPreferredSize(new Dimension(720,720));
        try{
            image = ImageIO.read(getClass().getResourceAsStream("pawn.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
        label = new JLabel(new ImageIcon(image));
        label.setSize(image.getWidth(), image.getHeight());
        label.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                System.out.println("chuj");
            }
        });
        this.add(label);
    }
}
