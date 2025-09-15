public class Bishop extends Piece{
    public Bishop(int color ,int x, int y) {
        super(color, x, y);
        if (color == 1){
            image = getImage("images/white-bishop.png");
        } else {
            image = getImage("images/black-bishop.png");
        }
    }

    @Override
    public Boolean validateMove(int destinationCol, int destinationRow, ChessBoard board) {
        if (Math.abs(destinationCol - this.col) == Math.abs(destinationRow - this.row)) {
            target = board.getPieceAt(destinationCol, destinationRow);
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
