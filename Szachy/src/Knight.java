public class Knight extends Piece{
    public Knight(int color ,int x, int y) {
        super(color, x, y);
        if (color == 1){
            image = setImage("images/white-knight.png");
        }
        else {
            image = setImage("images/black-knight.png");
        }
    }

    @Override
    public Boolean validateMove(int destCol, int destRow, ChessBoard board) {
        if(Math.abs(destCol - this.col) * Math.abs(destRow - this.row) == 2){
            return true;
        }
        return false;
    }
}

