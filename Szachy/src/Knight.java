public class Knight extends Piece{
    public Knight(int color ,int x, int y, ChessBoard board) {
        super(color, x, y, board);
        if (color == 1){
            image = getImage("images/white-knight.png");
        }
        else {
            image = getImage("images/black-knight.png");
        }
    }

    @Override
    public Boolean validateMove(int destinationCol, int destinationRow, ChessBoard board) {
        if(Math.abs(destinationCol - this.col) * Math.abs(destinationRow - this.row) == 2){
            return verifyTarget(board,  destinationCol, destinationRow);
        }
        return false;
    }
}

