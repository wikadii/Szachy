import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MainPanel extends JPanel {

    public ChessPanel cp;
    ArrayList<Piece> pieces;

    public MainPanel() {
        cp = new ChessPanel(this);
        pieces = cp.getPieces();

        cp.setBounds(90,40,cp.BOARD_WIDTH,cp.BOARD_HEIGHT);

        this.setPreferredSize(new Dimension(1000,600));
        this.setLayout(null);
        this.setBackground(Color.gray);
        this.add(cp);
    }
    public void getPromotionPanel(Pawn pawn) {
        JPanel promotionPanel = new JPanel();
        promotionPanel.setLayout(new BoxLayout(promotionPanel, BoxLayout.Y_AXIS));
        promotionPanel.setBackground(Color.gray);
        promotionPanel.setBounds(0, 40, cp.TILE_SIZE, (cp.TILE_SIZE * 4) + 40);

        String[] pieceNames = {"queen", "rook", "knight", "bishop"};
        for (String name : pieceNames) {
            JButton promotionButton = createButton(name);
            promotionButton.addActionListener(e -> {
                    cp.pieces.remove(pawn);
                    switch (name) {
                        case "queen": cp.pieces.add(new Queen(pawn.color, pawn.col, pawn.row, cp)); break;
                        case "rook": cp.pieces.add(new Rook(pawn.color, pawn.col, pawn.row, cp)); break;
                        case "knight": cp.pieces.add(new Knight(pawn.color, pawn.col, pawn.row, cp)); break;
                        case "bishop": cp.pieces.add(new Bishop(pawn.color, pawn.col, pawn.row, cp)); break;
                    }
                    remove(promotionPanel);
                    cp.repaint();
                });
            promotionPanel.add(promotionButton);
        }
        this.add(promotionPanel);
        this.revalidate();
        this.repaint();
    }
    public JButton createButton(String pieceName) {
        ImageIcon image = new ImageIcon(getClass().getResource("images/white-" + pieceName + ".jpg"));
        ImageIcon pieceImage = new ImageIcon(image.getImage());

        JButton pieceButton = new JButton(pieceImage);
        pieceButton.setBorderPainted(false);
        pieceButton.setContentAreaFilled(false);
        pieceButton.setFocusPainted(false);
        return pieceButton;
    }
}
