// Bishop.java
public class Bishop extends Piece {
    public Bishop(int color, int col, int row, ChessBoard board) {
        super(color, col, row, board);
        if (color == WHITE) {
            image = getImage("images/white-bishop.png");
        } else {
            image = getImage("images/black-bishop.png");
        }
    }

    @Override
    public Boolean validateMove(int destinationCol, int destinationRow, ChessBoard board) {
        int dCol = destinationCol - this.col;
        int dRow = destinationRow - this.row;

        // Musi być ruch po przekątnej
        if (Math.abs(dCol) != Math.abs(dRow)) {
            return false;
        }

        // Kierunek ruchu
        int stepCol = dCol > 0 ? 1 : -1;
        int stepRow = dRow > 0 ? 1 : -1;

        // Sprawdzenie czy droga wolna
        int c = this.col + stepCol;
        int r = this.row + stepRow;
        while (c != destinationCol && r != destinationRow) {
            if (board.getPieceAt(c, r) != null) {
                return false; // coś stoi na drodze
            }
            c += stepCol;
            r += stepRow;
        }

        return verifyTarget(board, destinationCol, destinationRow);
    }
}
