
public class Queen extends Piece {
    public Queen(int color, int col, int row, ChessBoard board) {
        super(color, col, row, board);
        if (color == WHITE) {
            image = getImage("images/white-queen.png");
        } else {
            image = getImage("images/black-queen.png");
        }
    }

    @Override
    public Boolean validateMove(int destinationCol, int destinationRow, ChessBoard board) {
        int dCol = destinationCol - this.col;
        int dRow = destinationRow - this.row;

        if (dCol == 0 && dRow == 0) {
            return false;
        }

        if (Math.abs(dCol) == Math.abs(dRow)) {
            int stepCol = dCol > 0 ? 1 : -1;
            int stepRow = dRow > 0 ? 1 : -1;

            int c = this.col + stepCol;
            int r = this.row + stepRow;
            while (c != destinationCol || r != destinationRow) {
                if (board.getPieceAt(c, r) != null) return false;
                c += stepCol;
                r += stepRow;
            }
            return verifyTarget(board, destinationCol, destinationRow);
        }

        if (destinationRow == this.row && destinationCol != this.col) {
            int step = dCol > 0 ? 1 : -1;
            for (int c = this.col + step; c != destinationCol; c += step) {
                if (board.getPieceAt(c, this.row) != null) return false;
            }
            return verifyTarget(board, destinationCol, destinationRow);
        }

        if (destinationCol == this.col && destinationRow != this.row) {
            int step = dRow > 0 ? 1 : -1;
            for (int r = this.row + step; r != destinationRow; r += step) {
                if (board.getPieceAt(this.col, r) != null) return false;
            }
            return verifyTarget(board, destinationCol, destinationRow);
        }

        return false;
    }
}