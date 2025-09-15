public class King extends Piece{
    public King(int color ,int x, int y, ChessBoard board) {
        super(color, x, y, board);
        if (color == 1){
            image = getImage("images/white-king.png");
        }
        else {
            image = getImage("images/black-king.png");
        }
    }
    public Boolean validateMove(int destinationCol, int destinationRow, ChessBoard board){
        if (Math.abs(destinationCol - this.col) + Math.abs(destinationRow - this.row) == 1
                || Math.abs(destinationCol - this.col) * Math.abs(destinationRow - this.row) == 1){
            return verifyTarget(board,  destinationCol, destinationRow);
        }
        return false;
    }
    public void castle(){

    }
}
