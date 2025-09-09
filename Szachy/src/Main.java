import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {
        JFrame window = new JFrame("Szachy");
        window.setResizable(false);
        window.setSize(1280, 720);
        window.setLocationRelativeTo(null);

        window.setVisible(true);
    }
}