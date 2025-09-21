import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;


public class ChessPanel extends JPanel {
    public final int BOARD_WIDTH = 480;
    public final int BOARD_HEIGHT = 480;
    public final int WHITE = 1;
    public final int BLACK = 0;
    public final int TILE_SIZE = 60;
    public final int COL_NUM = 8;
    public final int ROW_NUM = 8;

    public int turn = WHITE;

    public int gameState = 0;
    public int fiftyMovesCounter = 0;

    public boolean isWhiteInCheck = false;
    public boolean isBlackInCheck = false;
    public boolean hasCaptured = false;

    Piece enPassantPawn = null;
    Piece selectedPiece = null;
    ArrayList<Piece> pieces = new ArrayList<>();

    MainPanel mainPanel;

    public ChessPanel(MainPanel mainPanel) {
        this.mainPanel = mainPanel;
        this.setPreferredSize(new Dimension(BOARD_WIDTH, BOARD_HEIGHT));
        this.setBackground(Color.decode("#80807e"));
        setPieces(pieces);
        move();
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawBoard((Graphics2D) g);
        drawPiece((Graphics2D) g);
    }
    public void setPieces(ArrayList<Piece> pieces) {
        //biale
        for (int i = 1; i <= 8; i++) {
            pieces.add(new Pawn(1, i, 2, this));
        }
        pieces.add(new Rook(1, 1, 1, this));
        pieces.add(new Rook(1, 8, 1, this));
        pieces.add(new Knight(1, 2, 1, this));
        pieces.add(new Knight(1, 7, 1, this));
        pieces.add(new Bishop(1, 3, 1, this));
        pieces.add(new Bishop(1, 6, 1, this));
        pieces.add(new King(1, 5, 1, this));
        pieces.add(new Queen(1, 4, 1, this));

        //czarne
        for (int i = 1; i <= 8; i++) {
            pieces.add(new Pawn(0, i, 7, this));
        }
        pieces.add(new Rook(0, 1, 8, this));
        pieces.add(new Rook(0, 8, 8, this));
        pieces.add(new Knight(0, 2, 8, this));
        pieces.add(new Knight(0, 7, 8, this));
        pieces.add(new Bishop(0, 3, 8, this));
        pieces.add(new Bishop(0, 6, 8, this));
        pieces.add(new King(0, 5, 8, this));
        pieces.add(new Queen(0, 4, 8, this));
    }
    public void drawPiece(Graphics2D g2D) {
        for (Piece p : pieces) {
            g2D.drawImage(p.image, p.x, p.y, TILE_SIZE, TILE_SIZE, this);
        }
    }
    public void drawBoard(Graphics2D g2D) {
        boolean isBlack = false;
        for (int row = 0; row < ROW_NUM; row++) {
            for (int col = 0; col < COL_NUM; col++) {
                if (!isBlack) {
                    g2D.setColor(Color.decode("#EEEED2"));
                    isBlack = true;
                } else {
                    g2D.setColor(Color.decode("#769656"));
                    isBlack = false;
                }
                g2D.fillRect(col * TILE_SIZE, row * TILE_SIZE, TILE_SIZE, TILE_SIZE);
            }
            isBlack = !isBlack;
        }
    }
    public boolean simulateMove(int col, int row, Piece piece) {
        int originalCol = piece.col;
        int originalRow = piece.row;
        Piece targetPiece = getPieceAt(col, row);

        piece.updatePieceLocation(col, row);
        if (targetPiece != null) pieces.remove(targetPiece);

        boolean kingInCheck = (piece.color == WHITE) ? isWhiteKingAttacked() : isBlackKingAttacked();

        piece.updatePieceLocation(originalCol, originalRow);
        if (targetPiece != null) pieces.add(targetPiece);

        return !kingInCheck;
    }
    public void move() {
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (gameState == 0) selectPiece(e);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (selectedPiece != null) {
                    int col = (e.getX() / TILE_SIZE) + 1;
                    int row = 8 - (e.getY() / TILE_SIZE);

                    if (handleCastleMove(col, row)) return;
                    if (handleEnPassant(col, row)) return;
                    if (handleRegularMove(col, row)) return;

                    resetSelectedPiece();
                }
            }
        });

        this.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (selectedPiece != null) {
                    selectedPiece.x = e.getX() - TILE_SIZE / 2;
                    selectedPiece.y = e.getY() - TILE_SIZE / 2;
                    repaint();
                }
            }
        });
    }
    private void selectPiece(MouseEvent e) {
        if (gameState != 0) return;

        int col = (e.getX() / TILE_SIZE) + 1;
        int row = 8 - (e.getY() / TILE_SIZE);

        for (Piece piece : pieces) {
            if (piece.col == col && piece.row == row && piece.color == turn) {
                selectedPiece = piece;
                break;
            }
        }
    }
    private void resetSelectedPiece() {
        if (selectedPiece != null) {
            selectedPiece.updatePieceLocation(selectedPiece.col, selectedPiece.row);
            selectedPiece = null;
            repaint();
        }
    }

    private void updateEnPassantFigure(int col, int row){
        if (selectedPiece instanceof Pawn) {
            if (selectedPiece.isFirstMove && Math.abs(selectedPiece.row - row) == 2){
                enPassantPawn = selectedPiece;
                return;
            }
            else enPassantPawn = null;
        }
        return;
    }

    private void updateFiftyMovesRule(){
        if (selectedPiece instanceof Pawn || hasCaptured) fiftyMovesCounter = 0;
        else fiftyMovesCounter++;
    }

    private boolean handleRegularMove(int col, int row) {
        if (!selectedPiece.validateMove(col, row, this) || !simulateMove(col, row, selectedPiece)) {
            return false;
        }

        updateEnPassantFigure(col, row);

        selectedPiece.target = getPieceAt(col, row);
        selectedPiece.updatePieceLocation(col, row);
        if (selectedPiece.target != null){

            pieces.remove(selectedPiece.target);
            hasCaptured = true;
        }
        else hasCaptured = false;

        if (selectedPiece instanceof Pawn) {
            if ((selectedPiece.color == WHITE && row == 8) || (selectedPiece.color == BLACK && row == 1)) {
                selectedPiece.updatePieceLocation(col, row);
                mainPanel.getPromotionPanel((Pawn) selectedPiece);
            }
        }

        selectedPiece.isFirstMove = false;
        changeTurn();
        repaint();
        updateFiftyMovesRule();
        selectedPiece = null;
        gameState = getGameState();
        printGamestate();
        return true;
    }
    private boolean handleEnPassant(int col, int row) {
        if (selectedPiece instanceof Pawn && ((Pawn) selectedPiece).canEnPassant(col, row, this)) {
            pieces.remove(enPassantPawn);
            hasCaptured = true;
            selectedPiece.updatePieceLocation(col, row);
            enPassantPawn = null;
            selectedPiece.isFirstMove = false;
            selectedPiece = null;
            changeTurn();
            repaint();
            gameState = getGameState();
            printGamestate();
            return true;
        }
        return false;
    }
    private boolean handleCastleMove(int col, int row) {
        if (selectedPiece instanceof King && Math.abs(col - selectedPiece.col) == 2 && row == selectedPiece.row) {
            King king = (King) selectedPiece;
            boolean kingside = col > selectedPiece.col;
            if (king.canCastle(this, kingside)) {
                king.castle(this, kingside);
                changeTurn();
                repaint();
                hasCaptured = false;
                updateFiftyMovesRule();
                selectedPiece = null;
                gameState = getGameState();
                printGamestate();
                return true;
            } else {
                resetSelectedPiece();
                System.out.println("Illegal castle move");
            }
        }
        return false;
    }
    public Boolean isSquareEmpty(int col, int row) {
        return getPieceAt(col, row) == null;
    }
    public Piece getPieceAt(int col, int row) {
        for (Piece p : pieces) {
            if (p.col == col && p.row == row) {
                return p;
            }
        }
        return null;
    }
    public void changeTurn(){
        turn = (turn == WHITE) ? BLACK : WHITE;
    }
    Piece getKing(int color){
        for (Piece p : pieces) {
            if (p instanceof King && p.color == color) {
                return p;
            }
        }
        return null;
    }
    public boolean isKingAttacked(int color) {
        Piece king = getKing(color);
        if (king == null) return false;
        for (Piece piece : pieces) {
            if (piece.color != color && !(piece instanceof King)) {
                piece.getMoves();
                for (int[] move : piece.moves) {
                    if (move[0] == king.col && move[1] == king.row) return true;
                }
                piece.moves.clear();
            }
        }
        return false;
    }
    public boolean isWhiteKingAttacked() { return isKingAttacked(WHITE); }
    public boolean isBlackKingAttacked() { return isKingAttacked(BLACK); }
    public void printGamestate(){
        if (gameState == 1) System.out.println(turn == BLACK ? "Białe wygrały" : "Czarne wygrały");
        else if (gameState == 2) System.out.println("Remis (pat)");
    }
    public int getGameState() {
        /*
        0 - gra sie toczy
        1 - checkmate
        2 - remis
        */

        if (fiftyMovesCounter == 50) return 2;
        ArrayList<Piece> piecesCopy = new ArrayList<>(pieces);
        for (Piece piece : piecesCopy) {
            if (piece.color == turn) {
                piece.getMoves();
                for (int[] move : piece.moves) {
                    if (simulateMove(move[0], move[1], piece)) return 0;
                }
            }
        }
        isWhiteInCheck = isWhiteKingAttacked();
        isBlackInCheck = isBlackKingAttacked();
        if ((turn == WHITE && isWhiteInCheck) || (turn == BLACK && isBlackInCheck)) return 1;
        return 2;
    }
    public ArrayList<Piece> getPieces() {
        return pieces;
    }

}
