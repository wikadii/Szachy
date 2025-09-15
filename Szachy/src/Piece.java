import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class Piece {
    public final int BLACK = 0;
    public final int WHITE = 1;

    Piece target;
    BufferedImage image;

    public int x, y;
    public int row, col;
    public int color;

    public boolean canCastle = true;

    private ChessBoard board;

    public ArrayList<int[]> moves;

    public Piece(int color ,int col, int row, ChessBoard board) {
        this.color = color;
        this.board = board;
        updatePieceLocation(col, row);
    }
    public BufferedImage getImage(String filename) {
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(filename)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }
    public void updatePieceLocation(int col, int row) {
        int tile_size = 60;
        this.col = col;
        this.row = row;
        this.x = (col - 1) * tile_size;
        this.y = (8 - row) * tile_size;
    }
    public Boolean validateMove(int destinationCol, int destinationRow, ChessBoard board) {
        return false;
    }
    public Boolean verifyTarget(ChessBoard board, int destinationCol, int destinationRow) {
        target = board.getPieceAt(destinationCol, destinationRow);
        if (target != null){
            return !(target.color == color);
        }
        else{
            return true;
        }
    }
    public boolean canCastle(int color, boolean canCastle){
        if(canCastle){

        }
    }
}
