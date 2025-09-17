public class King extends Piece{
    public King(int color ,int x, int y, ChessBoard board) {
        super(color, x, y, board);
        isKing = true;
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

    public boolean canCastle(ChessBoard board, boolean kingside) {
        if (!this.isFirstMove) return false;

        int rookCol = kingside ? 8 : 1;
        int step = kingside ? 1 : -1;
        Piece rook = board.getPieceAt(rookCol, this.row);

        if (rook == null || rook.isKing || !rook.isFirstMove) return false;

        for (int c = this.col + step; c != rookCol; c += step) {
            if (board.getPieceAt(c, this.row) != null) return false;
        }

        for (int c = this.col; c != this.col + 2 * step; c += step) {
            int oldCol = this.col;
            this.updatePieceLocation(c, this.row);

            boolean inCheck = (this.color == board.WHITE)
                    ? board.isWhiteKingAttacked()
                    : board.isBlackKingAttacked();

            this.updatePieceLocation(oldCol, this.row);
            if (inCheck) return false;
        }
        return true;
    }

    public void performCastle(ChessBoard board, boolean kingside) {
        int step = kingside ? 1 : -1;
        int rookCol = kingside ? 8 : 1;
        Piece rook = board.getPieceAt(rookCol, this.row);

        int newKingCol = this.col + 2 * step;
        int newRookCol = this.col + step;

        this.updatePieceLocation(newKingCol, this.row);
        rook.updatePieceLocation(newRookCol, this.row);

        this.isFirstMove = false;
        rook.isFirstMove = false;
    }
}
