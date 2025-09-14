public class King extends Piece{
    public King(int color ,int x, int y) {
        super(color, x, y);
        if (color == 1){
            image = setImage("images/white-king.png");
        }
        else {
            image = setImage("images/black-king.png");
        }
    }
    public Boolean validateMove(int destCol, int destRow){
        if (Math.abs(destCol - this.col) + Math.abs(destRow - this.row) == 1 ||  Math.abs(destCol - this.col) * Math.abs(destRow - this.row) == 1){
            return true;
        }
        return false;
    }
}
