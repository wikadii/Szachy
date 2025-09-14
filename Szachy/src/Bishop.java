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
    public Boolean validateMove(int destCol, int destRow, ChessBoard board) {
        if (Math.abs(destCol - this.col) == Math.abs(destRow - this.row)) {
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
