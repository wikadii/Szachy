public class Pawn extends Piece{
    public Pawn(int color ,int x, int y, ChessBoard board) {
        super(color, x, y, board);
        if (color == 1){
            image = getImage("images/whitePawn.png");
        }
        else {
            image = getImage("images/pawn.png");
        }
        isFirstMove = true;
    }

    public boolean canEnPassant(int destinationCol, int destinationRow, ChessBoard board){
        if (board.enPassantPawn == null) return false;

        int direction = (color == WHITE) ? 1 : -1;

        if (Math.abs(destinationCol - this.col) == 1 && destinationRow == this.row + direction) {
            if (board.isEmpty(destinationCol, destinationRow)) {
                if (board.enPassantPawn.row == this.row &&
                        board.enPassantPawn.col == destinationCol) {
                    return true;
                }
            }
        }
        return false;
    }

    public Boolean validateMove(int destinationCol, int destinationRow, ChessBoard board) {
        int direction = (color == WHITE) ? 1 : -1;
        if (destinationCol == this.col &&
                destinationRow == this.row + direction &&
                board.isEmpty(destinationCol, destinationRow)) {
            return true;
        }

        if (isFirstMove &&
                destinationCol == this.col &&
                destinationRow == this.row + 2 * direction &&
                board.isEmpty(destinationCol, destinationRow) &&
                board.isEmpty(destinationCol, this.row + direction)) {
            return true;
        }

        if (Math.abs(destinationCol - this.col) == 1 &&
                destinationRow == this.row + direction) {
            Piece target = board.getPieceAt(destinationCol, destinationRow);
            if (target != null && target.color != this.color) {
                return true;
            }
        }
        return false;
    }
}

