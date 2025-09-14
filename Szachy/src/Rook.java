public class Rook extends Piece{
    public Rook(int color ,int x, int y) {
        super(color, x, y);
        if (color == 1){
            image = setImage("images/white-rook.png");
        }
        else {
            image = setImage("images/black-rook.png");
        }
    }

    @Override
    public Boolean validateMove(int destCol, int destRow,  ChessBoard board) {
        int direction;
        if (Math.abs(destCol - this.col) < 8 && Math.abs(destRow - this.row) == 0 || Math.abs(destCol - this.col) == 0 && Math.abs(destRow - this.row) < 8) {
            target = board.getPieceAt(destCol, destRow);
            if (target != null){
                return !(target.color == color);
            }
            else{
                return true;
            }
        }
        return false;
    }
}
