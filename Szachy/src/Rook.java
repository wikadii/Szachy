import javax.imageio.ImageIO;
import java.io.IOException;

public class Rook extends Piece{
    public Rook(int color ,int x, int y) {
        super(color, x, y);
        if (color == 1){
            image = setImage("white-rook.png");
        }
        else {
            image = setImage("black-rook.png");
        }
    }
}
