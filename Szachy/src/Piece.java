import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;


public class Piece {
    public int x;
    public int y;
    public int row;
    public int col;
    public int color;
    private final int TILE_SIZE = 60;
    public final int BLACK = 0;
    public final int WHITE = 1;
    BufferedImage image;

    public BufferedImage setImage(String filename) {
        try {
            image = ImageIO.read(getClass().getResourceAsStream(filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    public void updatePieceLocation(int col, int row) {
        this.col = col;
        this.row = row;
        this.x = (col - 1) * TILE_SIZE;
        this.y = (8 - row) * TILE_SIZE;
    }
    public Piece(int color ,int col, int row) {
        this.color = color;
        updatePieceLocation(col, row);
    }

    public Boolean validateMove(int destCol, int destRow) {
        return false;
    }
}
