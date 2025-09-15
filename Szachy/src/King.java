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
        Piece rook;
        if (destinationRow == this.row && destinationCol != this.col && Math.abs(this.col - destinationCol) == 2) {
            int step = (destinationCol > this.col) ? 1 : -1;

            // Check empty squares between king and destination
            for (int c = this.col + step; c != destinationCol; c += step) {
                if (board.getPieceAt(c, this.row) != null) {
                    return false;
                }
            }

            // Get rook at correct side
            if (step == 1) { // Kingside
                rook = board.getPieceAt(8, this.row);
            } else { // Queenside
                rook = board.getPieceAt(1, this.row);
            }

            if (rook != null && rook.canCastle) {
                // Move the rook to its new position
                int rookDestination = (step == 1) ? this.col + 1 : this.col - 1;
                rook.updatePieceLocation(rookDestination, this.row);
                return verifyTarget(board, destinationCol, destinationRow);
            }

            return false;
        }

        if (Math.abs(destinationCol - this.col) + Math.abs(destinationRow - this.row) == 1
                || Math.abs(destinationCol - this.col) * Math.abs(destinationRow - this.row) == 1){
            return verifyTarget(board,  destinationCol, destinationRow);
        }
        return false;
    }

}
