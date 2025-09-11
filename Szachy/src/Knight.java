import javax.imageio.ImageIO;
import java.io.IOException;

public class Knight extends Piece{
    public Knight(int color ,int x, int y) {
        super(color, x, y);
        if (color == 1){
            image = setImage("white-knight.png");
        }
        else {
            image = setImage("black-knight.png");
        }
    }
}

