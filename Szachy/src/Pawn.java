public class Pawn extends Piece{
    Boolean isFirstMove;

    public Pawn(int color ,int x, int y) {
        super(color, x, y);
        if (color == 1){
            image = getImage("images/whitePawn.png");
        }
        else {
            image = getImage("images/pawn.png");
        }
        isFirstMove = true;
    }

    public Boolean validateMove(int destinationCol, int destinationRow, ChessBoard board) {
        if (isFirstMove) {
            if (this.color != WHITE){
                if (this.row - destinationRow == 2 && this.col - destinationCol == 0|| this.row - destinationRow == 1 && this.col - destinationCol == 0 && board.isEmpty(destinationCol, destinationRow)){
                    isFirstMove = false;
                    return true;
                }
            }
            else{
                if (destinationRow - this.row == 2 && this.col - destinationCol == 0 || destinationRow - this.row == 1 && this.col - destinationCol == 0 && board.isEmpty(destinationCol, destinationRow)){
                    isFirstMove = false;
                    return true;
                }
            }
        }
        else{
            if (this.color == BLACK){
                if (this.row - destinationRow == 1 && this.col - destinationCol == 0 && board.isEmpty(destinationCol, destinationRow)){
                    isFirstMove = false;
                    return true;
                }
            }
            else{
                if (destinationRow - this.row == 1 && this.col - destinationCol == 0 && board.isEmpty(destinationCol, destinationRow)){
                    isFirstMove = false;
                    return true;
                }
            }
        }

        if (Math.abs(destinationRow - this.row) * Math.abs(destinationCol - this.col) == 1){
            target = board.getPieceAt(destinationCol, destinationRow);
            return target != null;
        }
        return false;
    }
}
