public class King extends Piece{
    public King(int color ,int x, int y, ChessPanel board) {
        super(color, x, y, board);
        value = 1;
        if (color == 1) image = getImage("images/white-king.png");
        else image = getImage("images/black-king.png");
    }
    ChessPanel board;
    @Override
    public Boolean validateMove(int destinationCol, int destinationRow, ChessPanel board) {
        int colChange = Math.abs(destinationCol - col);
        int rowChange = Math.abs(destinationRow - row);

        this.board = board;

        if ((colChange + rowChange == 1) || (colChange * rowChange == 1)) {
            return verifyTarget(board, destinationCol, destinationRow);
        }

        return false;
    }
    public boolean canCastle(ChessPanel board, boolean kingside) {
        if (!isFirstMove) return false;

        int rookCol = kingside ? 8 : 1;
        int step = kingside ? 1 : -1;
        Piece rook = board.getPieceAt(rookCol, row);

        if (rook == null || rook instanceof King || !rook.isFirstMove) return false;

        for (int c = col + step; c != rookCol; c += step) {
            if (board.getPieceAt(c, row) != null) return false;
        }

        for (int c = col; c != col + 2 * step; c += step) {
            int oldCol = col;
            updatePieceLocation(c, row);

            boolean inCheck = (color == WHITE) ? board.isWhiteKingAttacked() : board.isBlackKingAttacked();

            updatePieceLocation(oldCol, row);
            if (inCheck) return false;
        }

        return true;
    }
    public void castle(ChessPanel board, boolean kingside) {
        int step = kingside ? 1 : -1;
        int rookCol = kingside ? 8 : 1;
        int newKingCol = this.col + 2 * step;
        int newRookCol = this.col + step;
        Piece rook = board.getPieceAt(rookCol, this.row);

        this.updatePieceLocation(newKingCol, this.row);
        rook.updatePieceLocation(newRookCol, this.row);

        this.isFirstMove = false;
        rook.isFirstMove = false;
    }

    @Override
    public void getMoves() {
        super.getMoves();

        if (isFirstMove && !board.isKingAttacked(color)) {
            if (canCastle(board, true)) moves.add(new int[]{col + 2, row});
            if (canCastle(board, false)) moves.add(new int[]{col - 2, row});
        }
    }
}
