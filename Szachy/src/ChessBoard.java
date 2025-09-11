import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ChessBoard extends JPanel {
    public int width = 480;
    public int height = 480;
    public BufferedImage image;
    public JLabel label;

    public ChessBoard() {
        this.setPreferredSize(new Dimension(width, height));
        this.setBackground(Color.decode("#80807e"));
        try {
            image = ImageIO.read(getClass().getResourceAsStream("pawn.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        label = new JLabel(new ImageIcon(image));
        this.add(label);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw((Graphics2D) g);
    }

    public void draw(Graphics2D g2D) {
        boolean isBlack = false;
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                if (!isBlack) {
                    g2D.setColor(Color.decode("#EEEED2"));
                    isBlack = true;
                } else {
                    g2D.setColor(Color.decode("#769656"));
                    isBlack = false;
                }
                g2D.fillRect(col * 60, row * 60, 60, 60);
            }
            isBlack = !isBlack;
        }
    }
}