import javax.swing.JFrame;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        JFrame window = new JFrame("Szachy");
        window.setResizable(false);
        window.setSize(1280, 720);
        window.setLocationRelativeTo(null);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
        window.add(new Board(), BorderLayout.EAST);
        window.setVisible(true);

    }
}