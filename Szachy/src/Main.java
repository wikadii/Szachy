import javax.imageio.ImageIO;
import javax.swing.JFrame;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args){
        JFrame window = new JFrame("Szachy");
        window.setResizable(false);
        window.setSize(1280, 1000);
        window.setLocationRelativeTo(null);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Board board = new Board();
        window.add(board, BorderLayout.WEST);

        window.setVisible(true);

    }
}