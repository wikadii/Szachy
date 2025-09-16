import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.Arrays;

/*
    ischeck mozna zrobic tak
    col , row krola jednego i drugiego - petla przez pieces - jezeli validate(colKrola, rowKrola) == true return ze jest szach
    i ewentualnie cofnij ruch, mata to sie jeszcze wymysli xd
    */

public class ChessBoard extends JPanel {
    public final int BOARD_WIDTH = 480;
    public final int BOARD_HEIGHT = 480;
    public final int WHITE = 1;
    public final int BLACK = 0;
    public final int TILE_SIZE = 60;
    public final int COL_NUM = 8;
    public final int ROW_NUM = 8;

    private int turn = WHITE;

    public boolean isWhiteInCheck = false;
    public boolean isBlackInCheck = false;


    Piece selectedPiece = null;
    ArrayList<Piece> pieces = new ArrayList<Piece>();

    public ChessBoard() {
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
    public void setPieces(ArrayList<Piece> pieces){
        //biale
        for(int i = 1; i <= 8; i++){
            pieces.add(new Pawn(1,i,2, this));
        }
        pieces.add(new Rook(1,1,1, this));
        pieces.add(new Rook(1,8,1, this));
        pieces.add(new Knight(1,2,1, this));
        pieces.add(new Knight(1,7,1, this));
        pieces.add(new Bishop(1,3,1, this));
        pieces.add(new Bishop(1,6,1, this));
        pieces.add(new King(1,5,1, this));
        pieces.add(new Queen(1,4,1, this));

        //czarne
        for(int i = 1; i <= 8; i++){
            pieces.add(new Pawn(0,i,7, this));
        }
        pieces.add(new Rook(0,1,8, this));
        pieces.add(new Rook(0,8,8, this));
        pieces.add(new Knight(0,2,8, this));
        pieces.add(new Knight(0,7,8, this));
        pieces.add(new Bishop(0,3,8, this));
        pieces.add(new Bishop(0,6,8, this));
        pieces.add(new King(0,5,8, this));
        pieces.add(new Queen(0,4,8, this));
    }
    public void drawPiece(Graphics2D g2D) {
        for (Piece p: pieces){
            g2D.drawImage(p.image,p.x,p.y, TILE_SIZE, TILE_SIZE,this);
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
    public Boolean isEmpty(int col, int row){
        return getPieceAt(col, row) == null;
    }
    public Piece getPieceAt(int col, int row){
        for (Piece p: pieces){
            if (p.col == col && p.row == row) {
                return p;
            }
        }
        return null;
    }
    public void move(){
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int col = (e.getX() / TILE_SIZE) + 1;
                int row = 8 - (e.getY() / TILE_SIZE);

                for (Piece p : pieces) {
                    if (p.col == col && p.row == row && turn == p.color) {
                        selectedPiece = p;
                        System.out.println(selectedPiece);
                        break;
                    }
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (selectedPiece != null) {
                    int col = (e.getX() / TILE_SIZE) + 1;
                    int row = 8 - (e.getY() / TILE_SIZE);
                    int pieceRow = selectedPiece.row;
                    int pieceCol = selectedPiece.col;
                    if (selectedPiece.validateMove(col, row, ChessBoard.this)) {
                        selectedPiece.target = getPieceAt(col, row);
                        selectedPiece.updatePieceLocation(col, row);
                        selectedPiece.isFirstMove = false;
                        if (selectedPiece.target != null) {
                            pieces.remove(selectedPiece.target);
                        }
                        isWhiteInCheck = isWhiteKingAttacked();
                        isBlackInCheck = isBlackKingAttacked();
                        if(isWhiteInCheck ||  isBlackInCheck){
                            System.out.println("check");
                        }
                        if (turn == WHITE){
                            turn = BLACK;
                        }else{
                            turn = WHITE;
                        }
                    } else {
                        selectedPiece.updatePieceLocation(pieceCol, pieceRow);
                        System.out.println("Invalid move");
                    }
                    selectedPiece.moves.clear();
                    selectedPiece = null;
                    repaint();
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

    public boolean isWhiteKingAttacked(){
        Piece king = null;
        for (Piece p: pieces){
            if (p.isKing && p.color == 1){
                king = p;
            }
        }
        for (Piece p: pieces){
            if (p.color == 0 && !p.isKing){
                p.getMoves();
                for (int[] a : p.moves){
                    if (a[0] == king.col && a[1] == king.row) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    public boolean isBlackKingAttacked(){
        Piece king = null;
        for (Piece p: pieces){
            if (p.isKing && p.color == 0){
                king = p;
            }
        }
        for (Piece p: pieces){
            if (p.color == 1 && !p.isKing){
                p.getMoves();
                for (int[] a : p.moves){
                    if (a[0] == king.col && a[1] == king.row) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}