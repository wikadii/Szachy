public class King extends Piece{
    public King(int color ,int x, int y) {
        super(color, x, y);
        if (color == 1){
            image = getImage("images/white-king.png");
        }
        else {
            image = getImage("images/black-king.png");
        }
    }
    public Boolean validateMove(int destinationCol, int destinationRow, ChessBoard board){
        if (Math.abs(destinationCol - this.col) + Math.abs(destinationRow - this.row) == 1 ||  Math.abs(destinationCol - this.col) * Math.abs(destinationRow - this.row) == 1){
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
