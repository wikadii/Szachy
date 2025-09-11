import javax.imageio.ImageIO;
import java.io.IOException;

public class Bishop extends Piece{
    public Bishop(int color ,int x, int y) {
        super(color, x, y);
        if (color == 1){
            image = setImage("white-bishop.png");
        }
        else {
            image = setImage("black-bishop.png");
        }
    }
}
