import javax.swing.*;

public class Window extends JFrame {
    public MainPanel mainPanel = new MainPanel();

    public Window(){
        this.setTitle("Szachy");
        this.setSize(1000, 600);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.add(mainPanel);
        this.setVisible(true);
    }
}
