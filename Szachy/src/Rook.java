public class Rook extends Piece{
    public Rook(int color ,int x, int y) {
        super(color, x, y);
        if (color == 1){
            image = getImage("images/white-rook.png");
        } else {
            image = getImage("images/black-rook.png");
        }
    }

    @Override
    public Boolean validateMove(int destinationCol, int destinationRow, ChessBoard board) {
        if (Math.abs(destinationCol - this.col) < 8 && Math.abs(destinationRow - this.row) == 0 || Math.abs(destinationCol - this.col) == 0 && Math.abs(destinationRow - this.row) < 8) {
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
