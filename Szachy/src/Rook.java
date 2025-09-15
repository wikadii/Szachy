public class Rook extends Piece{
    public Rook(int color ,int x, int y, ChessBoard board) {
        super(color, x, y, board);
        if (color == 1){
            image = getImage("images/white-rook.png");
        } else {
            image = getImage("images/black-rook.png");
        }
    }

    @Override
    public Boolean validateMove(int destinationCol, int destinationRow, ChessBoard board) {
        // Moving horizontally
        if (destinationRow == this.row && destinationCol != this.col) {
            int step = (destinationCol > this.col) ? 1 : -1;
            for (int c = this.col + step; c != destinationCol; c += step) {
                if (board.getPieceAt(c, this.row) != null) {
                    return false;
                }
            }
            canCastle = false;
            return verifyTarget(board, destinationCol, destinationRow);
        }

        // Moving vertically
        if (destinationCol == this.col && destinationRow != this.row) {
            int step = (destinationRow > this.row) ? 1 : -1;
            for (int r = this.row + step; r != destinationRow; r += step) {
                if (board.getPieceAt(this.col, r) != null) {
                    return false; // path blocked
                }
            }
            canCastle = false;
            return verifyTarget(board, destinationCol, destinationRow);
        }

        return false;
    }

}
