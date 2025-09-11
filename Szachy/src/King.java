import javax.imageio.ImageIO;
import java.io.IOException;

public class King extends Piece{
    public King(int color ,int x, int y) {
        super(color, x, y);
        if (color == 1){
            image = setImage("white-king.png");
        }
        else {
            image = setImage("black-king.png");
        }
    }
}
