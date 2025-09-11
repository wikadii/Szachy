public class Queen extends Piece{
    public Queen(int color ,int x, int y) {
        super(color, x, y);
        if (color == 1){
            image = setImage("white-queen.png");
        }
        else {
            image = setImage("black-queen.png");
        }
    }
}
