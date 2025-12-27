package core;

import java.util.Arrays;

import static core.Pieces.*;

public class Board {

    /**
     * ATTs
     */
    private final int[] squares = new int[100];

    /**
     * CONs
     */
    public Board() {
        initMailbox();
    }

    /**
     * METHs
     */
    public int[] squares() {
        return squares;
    }

    public int get(int sq) {
        return squares[sq];
    }

    public void set(int sq, int value) {
        squares[sq] = value;
    }

    public boolean inBoundsIndex(int sq) {
        return sq >= 0 && sq < 100;
    }

    public boolean isPlayableSquares(int sq) {
        if (!inBoundsIndex(sq)) return false;
        return squares[sq] != OFF_BOARD;
    }

    public void initMailbox() {
        Arrays.fill(squares, OFF_BOARD);

        // Mark playable 8x8 as EMPTY: 11-18, 21-28, ..., 81-88
        for (int rank = 1; rank <= 8; rank++) {
            int rowBase = rank * 10;
            for (int file = 1; file <= 8; file++) {
                squares[rowBase + file] = EMPTY;
            }
        }
    }

    public void setupStartingPosition() {
        // Clear playable squares
        for (int sq = 0; sq < 100; sq++) {
            if (s*quares[sq] != OFF_BOARD) squares[sq] = EMPTY;
        }

        // Black back rank at 11..18, pawns at 21..28
        set(11, B_ROOK);   set(12, B_KNIGHT); set(13, B_BISHOP); set(14, B_QUEEN);
        set(15, B_KING);   set(16, B_BISHOP); set(17, B_KNIGHT); set(18, B_ROOK);
        for (int sq = 21; sq <= 28; sq++) set(sq, B_PAWN);

        // White pawns at 71..78, back rank at 81..88
        for (int sq = 71; sq <= 78; sq++) set(sq, W_PAWN);
        set(81, W_ROOK);   set(82, W_KNIGHT); set(83, W_BISHOP); set(84, W_QUEEN);
        set(85, W_KING);   set(86, W_BISHOP); set(87, W_KNIGHT); set(88, W_ROOK);
    }


    public String pretty() {
        // Print ranks 8..1 (top playable row is 11..18)
        StringBuilder sb = new StringBuilder();
        sb.append("   a b c d e f g h\n");
        for (int rank = 1; rank <= 8; rank++) {
            int displayRank = 9 - rank; // show 8..1
            sb.append(displayRank).append("  ");
            int rowBase = rank * 10;
            for (int file = 1; file <= 8; file++) {
                int sq = rowBase + file;
                sb.append(pieceChar(squares[sq])).append(' ');
            }
            sb.append('\n');
        }
        return sb.toString();
    }

    private char pieceChar(int p) {
        return switch (p) {
            case EMPTY -> '.';
            case W_PAWN -> 'P';
            case W_KNIGHT -> 'N';
            case W_BISHOP -> 'B';
            case W_ROOK -> 'R';
            case W_QUEEN -> 'Q';
            case W_KING -> 'K';
            case B_PAWN -> 'p';
            case B_KNIGHT -> 'n';
            case B_BISHOP -> 'b';
            case B_ROOK -> 'r';
            case B_QUEEN -> 'q';
            case B_KING -> 'k';
            default -> '?';
        };
    }


}
