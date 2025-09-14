import java.awt.*;

public class Pawn extends Piece{
    Boolean isFirstMove;

    public Pawn(int color ,int x, int y) {
        super(color, x, y);
        if (color == 1){
            image = setImage("images/whitePawn.png");
        }
        else {
            image = setImage("images/pawn.png");
        }
        isFirstMove = true;
    }

    public Boolean validateMove(int destCol, int destRow, ChessBoard board) {
        if (isFirstMove) {
            if (this.color == BLACK){
                if (this.row - destRow == 2 && this.col - destCol == 0|| this.row - destRow == 1 && this.col - destCol == 0 && board.isEmpty(destCol, destRow)){
                    isFirstMove = false;
                    return true;
                }
            }
            else{
                if (destRow - this.row == 2 && this.col - destCol == 0 || destRow - this.row == 1 && this.col - destCol == 0 && board.isEmpty(destCol, destRow)){
                    isFirstMove = false;
                    return true;
                }
            }
        }
        else{
            if (this.color == BLACK){
                if (this.row - destRow == 1 && this.col - destCol == 0 && board.isEmpty(destCol, destRow)){
                    isFirstMove = false;
                    return true;
                }
            }
            else{
                if (destRow - this.row == 1 && this.col - destCol == 0 && board.isEmpty(destCol, destRow)){
                    isFirstMove = false;
                    return true;
                }
            }
        }

        if (Math.abs(destRow - this.row) * Math.abs(destCol - this.col) == 1){
            target = board.getPieceAt(destCol, destRow);
            return target != null;
        }
        return false;
    }
}
