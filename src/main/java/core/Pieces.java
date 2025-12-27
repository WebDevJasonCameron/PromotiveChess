package core;

public class Pieces {

    /**
     * ATTs
     */
    public static final int OFF_BOARD = -9;
    public static final int EMPTY = 0;

    // White pieces
    public static final int W_PAWN = 1;
    public static final int W_KNIGHT = 2;
    public static final int W_BISHOP = 3;
    public static final int W_ROOK = 4;
    public static final int W_QUEEN = 5;
    public static final int W_KING = 6;

    // Black pieces
    public static final int B_PAWN = -1;
    public static final int B_KNIGHT = -2;
    public static final int B_BISHOP = -3;
    public static final int B_ROOK = -4;
    public static final int B_QUEEN = -5;
    public static final int B_KING = -6;

    /**
     * CONs
     */
    private Pieces() {}

    /**
     * METHs
     */
    public static boolean isWhite(int p) { return p > 0; }
    public static boolean isBlack(int p) { return p < 0; }
    public static boolean isEmpty(int p) { return p == EMPTY; }
    public static boolean isOffBoard(int p) { return p == OFF_BOARD; }

    public static int type (int p) { return Math.abs(p); } // 1..6
}
