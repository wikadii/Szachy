public class Bishop extends Piece{
    public Bishop(int color ,int x, int y) {
        super(color, x, y);
        if (color == 1){
            image = setImage("images/white-bishop.png");
        }
        else {
            image = setImage("images/black-bishop.png");
        }
    }

    @Override
    public Boolean validateMove(int destCol, int destRow) {
        if (Math.abs(destCol - this.col) == Math.abs(destRow - this.row)) {
            return true;
        }
        return false;
    }
}
