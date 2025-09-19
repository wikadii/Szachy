import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {

    public ChessBoard cp;

    public MainPanel() {
        this.setPreferredSize(new Dimension(1000,600));
        this.setLayout(null);
        cp = new ChessBoard();
        this.setBackground(Color.gray);
        cp.setBounds(90,40,cp.BOARD_WIDTH,cp.BOARD_HEIGHT);
        this.add(cp);
        getPromotionPanel(1);
    }

    public JPanel getPromotionPanel(int color) {
        JPanel promotionPanel = new JPanel();
        promotionPanel.setLayout(new BoxLayout(promotionPanel, BoxLayout.Y_AXIS));
        ImageIcon originalIcon = new ImageIcon(getClass().getResource("/images/pawn.jpg"));
        Image scaledImage = originalIcon.getImage().getScaledInstance(cp.TILE_SIZE, cp.TILE_SIZE, Image.SCALE_SMOOTH);
        ImageIcon queen = new ImageIcon(scaledImage);

        JButton queenButton = new JButton(queen);
        queenButton.setBorderPainted(false);
        queenButton.setContentAreaFilled(false);
        queenButton.setFocusPainted(false);
        promotionPanel.add(queenButton);

        promotionPanel.setBounds(0, 40, cp.TILE_SIZE, cp.TILE_SIZE * 4);

        this.add(promotionPanel);

        return promotionPanel;
    }


}
