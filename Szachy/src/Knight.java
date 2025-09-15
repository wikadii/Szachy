public class Knight extends Piece{
    public Knight(int color ,int x, int y) {
        super(color, x, y);
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

