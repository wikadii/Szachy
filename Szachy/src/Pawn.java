import javax.imageio.ImageIO;
import java.io.IOException;

public class Pawn extends Piece{
    public Pawn(int color ,int x, int y) {
        super(color, x, y);
        if (color == 1){
            image = setImage("whitePawn.png");
        }
        else {
            image = setImage("pawn.png");
        }
    }
}
