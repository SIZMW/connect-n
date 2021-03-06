package edu.wpi.cs.connectn;

import org.junit.Assert;
import org.junit.Test;

public class HeuristicTest {

    @Test
    public void testApply() throws Exception {

//        Assert.assertEquals(Double.POSITIVE_INFINITY, Heuristic.getInstance().apply(new GameState(Player.MAX, 4, TestUtils.fixBoard(new BoardCell[][] {
//                { BoardCell.NONE, BoardCell.MAX, BoardCell.NONE, BoardCell.MIN, BoardCell.NONE },
//                { BoardCell.NONE, BoardCell.MAX, BoardCell.NONE, BoardCell.MIN, BoardCell.MIN },
//                { BoardCell.NONE, BoardCell.MAX, BoardCell.MIN, BoardCell.MAX, BoardCell.MAX },
//                { BoardCell.MAX, BoardCell.MAX, BoardCell.MIN, BoardCell.MIN, BoardCell.MAX },
//                { BoardCell.MAX, BoardCell.MIN, BoardCell.MAX, BoardCell.MIN, BoardCell.MAX }
//        }), new boolean[] { false, false })), 0);
//
//        /*
//        9 9 9 9 9 9 9
//        9 9 9 9 9 9 9
//        9 2 9 9 9 9 9
//        2 2 9 9 9 9 9
//        2 1 1 9 9 9 9
//        2 1 1 9 9 9 9
//         */
//        Assert.assertEquals(Double.POSITIVE_INFINITY, Heuristic.getInstance().apply(new GameState(Player.MAX, 4, TestUtils.fixBoard(new BoardCell[][] {
//                { BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE },
//                { BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE },
//                { BoardCell.MAX, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE },
//                { BoardCell.MAX, BoardCell.MAX, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE },
//                { BoardCell.MAX, BoardCell.MIN, BoardCell.MIN, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE },
//                { BoardCell.MAX, BoardCell.MIN, BoardCell.MIN, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE }
//        }), new boolean[] { false, false })), 0);

        Heuristic.getInstance().setWeightFunction((l, n) -> 1d);
        Assert.assertTrue(Heuristic.getInstance().apply(new GameState(Player.MAX, 4, TestUtils.fixBoard(new BoardCell[][] {
                { BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE },
                { BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE },
                { BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE },
                { BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE },
                { BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE },
                { BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.MAX, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE }
        }), new boolean[] { false, false })) > Heuristic.getInstance().apply(new GameState(Player.MAX, 4, TestUtils.fixBoard(new BoardCell[][] {
                { BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE },
                { BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE },
                { BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE },
                { BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE },
                { BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE },
                { BoardCell.MAX, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE }
        }), new boolean[] { false, false })));

        Heuristic.getInstance().setWeightFunction((l, n) -> (double) l * l * l);
        /*
        . . . . . . .
        . . . . . . .
        . . . . . . .
        . . . - . . .
        . . . - . . .
        . + . + . . .
         */
        Assert.assertEquals((2 * 8 + 5 * 1) - (1 * 8 + 18 * 1), Heuristic.getInstance().apply(new GameState(Player.MAX, 4, TestUtils.fixBoard(new BoardCell[][] {
                { BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE },
                { BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE },
                { BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE },
                { BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.MIN, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE },
                { BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.MIN, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE },
                { BoardCell.NONE, BoardCell.MAX, BoardCell.NONE, BoardCell.MAX, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE }
        }), new boolean[] { false, false })), 0);
    }
}