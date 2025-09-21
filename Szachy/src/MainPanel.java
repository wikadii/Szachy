import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MainPanel extends JPanel {

    public ChessPanel cp;
    ArrayList<Piece> pieces;
    public int whiteTime = 60 * 10;
    public int blackTime = 60 * 10;
    public Timer gameTimer;
    JLabel whiteLabel;
    JLabel blackLabel;

    public MainPanel() {
        cp = new ChessPanel(this);
        gameTimer = new Timer(1000, e -> tick());
        pieces = cp.getPieces();
        cp.setBounds(90,40,cp.BOARD_WIDTH,cp.BOARD_HEIGHT);
        gameTimer.start();
        this.setPreferredSize(new Dimension(1000,600));
        this.setLayout(null);
        this.setBackground(Color.gray);
        this.add(cp);
        getTimerPanels();

    }

    public void tick(){
        if (cp.turn == 1) whiteTime--;
        else blackTime--;

        whiteLabel.setText(formatTime(whiteTime));
        blackLabel.setText(formatTime(blackTime));
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
                    this.remove(promotionPanel);
                    this.repaint();
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

    public void getTimerPanels(){
        JPanel whiteTimePanel = createTimePanel(1);
        JPanel blackTimePanel = createTimePanel(0);
        this.add(whiteTimePanel);
        this.add(blackTimePanel);

    }

    public JPanel createTimePanel(int color){
        JPanel timePanel = new JPanel();
        if (color == 0){
            timePanel.setBounds(600, 75, 150,40);
            timePanel.setBackground(Color.pink);
            blackLabel = new JLabel(formatTime(blackTime));
            blackLabel.setFont(new Font("SansSerif", Font.BOLD, 30));
            blackLabel.setBorder(BorderFactory.createEmptyBorder(-5,0,0,0));
            timePanel.add(blackLabel);
        }
        else{
            timePanel.setBounds(600, 445, 150,40);
            timePanel.setBackground(Color.pink);
            whiteLabel = new JLabel(formatTime(whiteTime));
            whiteLabel.setFont(new Font("SansSerif", Font.BOLD, 30));
            whiteLabel.setBorder(BorderFactory.createEmptyBorder(-5,0,0,0));
            timePanel.add(whiteLabel);
        }
        return timePanel;
    }

    private String formatTime(int seconds) {
        int min = seconds / 60;
        int sec = seconds % 60;
        return String.format("%02d:%02d", min, sec);
    }

}


