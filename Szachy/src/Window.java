import javax.swing.*;

public class Window extends JFrame {
    ChessBoard board = new ChessBoard();
    public Window(){
        this.setTitle("Szachy");
        this.setSize(1000, 600);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.add(board);
        this.setVisible(true);
    }
}
