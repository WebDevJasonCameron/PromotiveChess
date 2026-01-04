import core.Board;
import core.Move;
import core.MoveGenerator;

import java.util.List;

import static core.Pieces.EMPTY;

public class Main {
    public static void main(String[] args) {
        Board board = new Board();
        board.setupStartingPosition();

        // Clear pawns to open diagonals for white bishops
        board.set(72, EMPTY); // b2
        board.set(73, EMPTY); // c2
        board.set(74, EMPTY); // d2
        board.set(75, EMPTY); // e2
        board.set(76, EMPTY); // f2
        board.set(77, EMPTY); // g2

        System.out.println(board.pretty());

        MoveGenerator gen = new MoveGenerator();

        int from = 83; // white bishop on c1 in our mapping
        List<Move> moves = gen.generateMovesFrom(board, from);

        System.out.println("Moves from " + from + ":");
        for (Move m : moves) {
            System.out.println("  " + m);
        }
    }
}
