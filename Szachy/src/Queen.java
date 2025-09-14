public class Queen extends Piece{
    public Queen(int color ,int x, int y) {
        super(color, x, y);
        if (color == 1){
            image = setImage("images/white-queen.png");
        }
        else {
            image = setImage("images/black-queen.png");
        }
    }

    @Override
    public Boolean validateMove(int destCol, int destRow, ChessBoard board) {
        if (Math.abs(destCol - this.col) == Math.abs(destRow - this.row) || Math.abs(destCol - this.col) < 8 && Math.abs(destRow - this.row) == 0 || Math.abs(destCol - this.col) == 0 && Math.abs(destRow - this.row) < 8) {
            return true;
        }
        return false;
    }
}
