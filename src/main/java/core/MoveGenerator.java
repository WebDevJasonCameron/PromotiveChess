package core;

import java.util.ArrayList;
import java.util.List;

import static core.Pieces.*;

public class MoveGenerator {

    /**
     * ATTs
     * Moves per piece
     */
    private static final int[] KNIGHT_DELTAS = {-21, -19, -12, -9, -3, 3, 9, 12, 19, 21};
    private static final int[] KING_DELTAS = {-11, -10, -9, -1, 1, 9, 10, 11};
    private static final int[] ROOK_DIRS = {-10, 10, -1, 1};
    private static final int[] BISHOP_DIRS = {-11, -9, 11, 9};

    /**
     * METHs
     */
    public List<Move> generateMovesFrom(Board board, int from) {
        int piece = board.get(from);
        if (piece == OFF_BOARD || piece == EMPTY ) return List.of();

        int type = Pieces.type(piece);

        return switch (type) {
            case 2 -> generateKnightMoves(board, from);
            case 3 -> generateSlidingMoves(board, from, BISHOP_DIRS);
            case 4 -> generateSlidingMoves(board, from, ROOK_DIRS);
            case 5 -> {
                List<Move> q = new ArrayList<>();
                q.addAll(generateSlidingMoves(board, from, ROOK_DIRS));
                q.addAll(generateSlidingMoves(board, from, BISHOP_DIRS));
                yield q;
            }
            case 6 -> generateKingMoves(board, from);
            default -> List.of(); // pawns next
        };
    }

    private List<Move> generateSlidingMoves(Board board, int from, int[] dirs) {
        List<Move> moves = new ArrayList<>();
        int me = board.get(from);

        for (int dir : dirs) {
            int to = from + dir;

            // Sliding: we can safely keep stepping until OFF_BOARD.
            while (board.inBoundsIndex(to) && board.isPlayableSquare(to)) {
                int target = board.get(to);

                if (target == EMPTY) {
                    moves.add(new Move(from, to));
                } else {
                    // occupied: capture if enemy, then stop
                    if (target * me < 0) {
                        moves.add(new Move(from, to));
                    }
                    break;
                }
                to += dir;
            }
        }

        return moves;
    }

    private List<Move> generateKnightMoves(Board board, int from) {
        List<Move> moves = new ArrayList<>();
        int me = board.get(from);

        for (int d : KNIGHT_DELTAS) {
            int to = from + d;

            // bounds safety for 10x10 mailbox
            if (!board.inBoundsIndex(to)) continue;
            if (!board.isPlayableSquare(to)) continue;

            int target = board.get(to);
            if (target == EMPTY || (target * me < 0)) {
                moves.add(new Move(from, to));
            }
        }
        return moves;
    }

    private List<Move> generateKingMoves(Board board, int from) {
        List<Move> moves = new ArrayList<>();
        int me = board.get(from);

        for (int d : KING_DELTAS) {
            int to = from + d;

            // defensive bounds safety (same pattern as knight)
            if (!board.inBoundsIndex(to)) continue;
            if (!board.isPlayableSquare(to)) continue;

            int target = board.get(to);
            if (target == EMPTY || (target * me < 0)) {
                moves.add(new Move(from, to));
            }
        }

        // Castling later (depends on GameState: rights + attacked squares)
        return moves;
    }
}
