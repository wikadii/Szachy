import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

/*
    ischeck mozna zrobic tak
    col , row krola jednego i drugiego - petla przez pieces - jezeli validate(colKrola, rowKrola) == true return ze jest szach
    i ewentualnie cofnij ruch, mata to sie jeszcze wymysli xd
    */

public class ChessBoard extends JPanel {
    public int width = 480;
    public int height = 480;
    public final int WHITE = 1;
    public final int BLACK = 0;
    public final int TILE_SIZE = 60;
    int turn = WHITE;
    public BufferedImage image;
    public JLabel label;
    Piece selectedPiece = null;




    ArrayList<Piece> pieces = new ArrayList<Piece>();

    void move(){
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
                        selectedPiece.updatePieceLocation(col, row);
                        if (selectedPiece.target != null) {
                            pieces.remove(selectedPiece.target);
                        }
                        if (turn == WHITE){
                            turn = BLACK;
                        }
                        else{
                            turn = WHITE;
                        }
                    } else {
                        selectedPiece.updatePieceLocation(pieceCol, pieceRow);
                        System.out.println("Invalid move");
                    }
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

    public ChessBoard() {
        this.setPreferredSize(new Dimension(width, height));
        this.setBackground(Color.decode("#80807e"));
        try {
            image = ImageIO.read(getClass().getResourceAsStream("images/pawn.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        addPieces(pieces);
        move();
    }

    public void addPieces(ArrayList<Piece> pieces){
        for(int i = 1; i <= 8; i++){
            pieces.add(new Pawn(1,i,2));
        }
        for(int i = 1; i <= 8; i++){
            pieces.add(new Pawn(0,i,7));
        }
        pieces.add(new Rook(1,1,1));
        pieces.add(new Rook(1,8,1));
        pieces.add(new Rook(0,1,8));
        pieces.add(new Rook(0,8,8));
        pieces.add(new Knight(1,2,1));
        pieces.add(new Knight(1,7,1));
        pieces.add(new Knight(0,2,8));
        pieces.add(new Knight(0,7,8));

        pieces.add(new Bishop(1,3,1));
        pieces.add(new Bishop(1,6,1));

        pieces.add(new Bishop(0,3,8));
        pieces.add(new Bishop(0,6,8));

        pieces.add(new King(1,5,1));
        pieces.add(new King(0,5,8));

        pieces.add(new Queen(1,4,1));
        pieces.add(new Queen(0,4,8));
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawBoard((Graphics2D) g);
        drawPawn((Graphics2D) g);

    }
    public void drawPawn(Graphics2D g2D) {
        for (Piece p: pieces){
            g2D.drawImage(p.image,p.x,p.y, TILE_SIZE, TILE_SIZE,this);
        }
    }

    public void drawBoard(Graphics2D g2D) {
        boolean isBlack = false;
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
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

    Piece getPieceAt(int col, int row){
        for (Piece p: pieces){
            if (p.col == col && p.row == row) {
                return p;
            }
        }
        return null;
    }
}