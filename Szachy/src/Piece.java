import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Piece {
    public int x;
    public int y;
    public int row;
    public int col;
    public int color;
    BufferedImage image;

    public BufferedImage setImage(String filename) {
        try {
            image = ImageIO.read(getClass().getResourceAsStream(filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    public Piece(int color ,int x, int y) {
        this.color = color;
        this.x = (x - 1) * 60;
        this.y = (8 - y) * 60;
        this.col = x;
        this.row = y;
    }
}
