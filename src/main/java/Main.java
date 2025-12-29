import core.Board;
import core.Move;
import core.MoveGenerator;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Board board = new Board();
        board.setupStartingPosition();

        System.out.println(board.pretty());

        MoveGenerator gen = new MoveGenerator();

        int from = 82; // white knight on b1 in our mailbox mapping
        List<Move> moves = gen.generateMovesFrom(board, from);

        System.out.println("Moves from " + from + ":");
        for (Move m : moves) {
            System.out.println("  " + m);
        }
    }
}