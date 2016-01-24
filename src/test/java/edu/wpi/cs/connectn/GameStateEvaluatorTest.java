package edu.wpi.cs.connectn;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class GameStateEvaluatorTest {

    @Test
    public void testGetFeatures() throws Exception {
        /*
        . . . - .
        . + . - -
        . + - + +
        + + - - +
        + - + - +
         */
        Assert.assertEquals(new HashMap<Integer, Set<BoardFeature>>() {{
            put(1, new HashSet<BoardFeature>() {{
                add(new BoardFeature(
                        new BoardPos(0, 0, BoardCell.NONE),
                        new BoardPos(0, 1, BoardCell.NONE),
                        new BoardPos(0, 2, BoardCell.NONE),
                        new BoardPos(0, 3, BoardCell.MAX)
                ));
            }});
            put(2, new HashSet<BoardFeature>() {{
                add(new BoardFeature(
                        new BoardPos(0, 1, BoardCell.NONE),
                        new BoardPos(0, 2, BoardCell.NONE),
                        new BoardPos(0, 3, BoardCell.MAX),
                        new BoardPos(0, 4, BoardCell.MAX)
                ));
                add(new BoardFeature(
                        new BoardPos(1, 0, BoardCell.NONE),
                        new BoardPos(2, 1, BoardCell.NONE),
                        new BoardPos(3, 2, BoardCell.MAX),
                        new BoardPos(4, 3, BoardCell.MAX)
                ));
            }});
            put(3, new HashSet<BoardFeature>() {{
                add(new BoardFeature(
                        new BoardPos(1, 0, BoardCell.NONE),
                        new BoardPos(1, 1, BoardCell.MAX),
                        new BoardPos(1, 2, BoardCell.MAX),
                        new BoardPos(1, 3, BoardCell.MAX)
                ));
            }});
        }}, GameStateEvaluator.getInstance().getFeatures(new GameState(Player.MAX, 4, TestUtils.fixBoard(new BoardCell[][] {
                { BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.MIN, BoardCell.NONE },
                { BoardCell.NONE, BoardCell.MAX, BoardCell.NONE, BoardCell.MIN, BoardCell.MIN },
                { BoardCell.NONE, BoardCell.MAX, BoardCell.MIN, BoardCell.MAX, BoardCell.MAX },
                { BoardCell.MAX, BoardCell.MAX, BoardCell.MIN, BoardCell.MIN, BoardCell.MAX },
                { BoardCell.MAX, BoardCell.MIN, BoardCell.MAX, BoardCell.MIN, BoardCell.MAX }
        }), new boolean[] { false, false }), Player.MAX));

        /*
        . . . . .
        . . . . .
        . + . . .
        + + - - +
        + - + - +
         */
        Assert.assertEquals(new HashMap<Integer, Set<BoardFeature>>() {{
            put(1, new HashSet<BoardFeature>() {{
                add(new BoardFeature(
                        new BoardPos(0, 0, BoardCell.NONE),
                        new BoardPos(0, 1, BoardCell.NONE),
                        new BoardPos(0, 2, BoardCell.NONE),
                        new BoardPos(0, 3, BoardCell.MAX)
                ));
                add(new BoardFeature(
                        new BoardPos(0, 2, BoardCell.NONE),
                        new BoardPos(1, 2, BoardCell.MAX),
                        new BoardPos(2, 2, BoardCell.NONE),
                        new BoardPos(3, 2, BoardCell.NONE)
                ));
                add(new BoardFeature(
                        new BoardPos(1, 2, BoardCell.MAX),
                        new BoardPos(2, 2, BoardCell.NONE),
                        new BoardPos(3, 2, BoardCell.NONE),
                        new BoardPos(4, 2, BoardCell.NONE)
                ));
                add(new BoardFeature(
                        new BoardPos(1, 3, BoardCell.MAX),
                        new BoardPos(2, 2, BoardCell.NONE),
                        new BoardPos(3, 1, BoardCell.NONE),
                        new BoardPos(4, 0, BoardCell.NONE)
                ));
                add(new BoardFeature(
                        new BoardPos(1, 0, BoardCell.NONE),
                        new BoardPos(2, 1, BoardCell.NONE),
                        new BoardPos(3, 2, BoardCell.NONE),
                        new BoardPos(4, 3, BoardCell.MAX)
                ));
                add(new BoardFeature(
                        new BoardPos(4, 0, BoardCell.NONE),
                        new BoardPos(4, 1, BoardCell.NONE),
                        new BoardPos(4, 2, BoardCell.NONE),
                        new BoardPos(4, 3, BoardCell.MAX)
                ));
            }});
            put(2, new HashSet<BoardFeature>() {{
                add(new BoardFeature(
                        new BoardPos(0, 1, BoardCell.NONE),
                        new BoardPos(0, 2, BoardCell.NONE),
                        new BoardPos(0, 3, BoardCell.MAX),
                        new BoardPos(0, 4, BoardCell.MAX)
                ));
                add(new BoardFeature(
                        new BoardPos(1, 0, BoardCell.NONE),
                        new BoardPos(1, 1, BoardCell.NONE),
                        new BoardPos(1, 2, BoardCell.MAX),
                        new BoardPos(1, 3, BoardCell.MAX)
                ));
                add(new BoardFeature(
                        new BoardPos(4, 1, BoardCell.NONE),
                        new BoardPos(4, 2, BoardCell.NONE),
                        new BoardPos(4, 3, BoardCell.MAX),
                        new BoardPos(4, 4, BoardCell.MAX)
                ));
                add(new BoardFeature(
                        new BoardPos(0, 3, BoardCell.MAX),
                        new BoardPos(1, 2, BoardCell.MAX),
                        new BoardPos(2, 1, BoardCell.NONE),
                        new BoardPos(3, 0, BoardCell.NONE)
                ));
                add(new BoardFeature(
                        new BoardPos(0, 4, BoardCell.MAX),
                        new BoardPos(1, 3, BoardCell.MAX),
                        new BoardPos(2, 2, BoardCell.NONE),
                        new BoardPos(3, 1, BoardCell.NONE)
                ));
            }});
        }}, GameStateEvaluator.getInstance().getFeatures(new GameState(Player.MAX, 4, TestUtils.fixBoard(new BoardCell[][] {
                { BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE },
                { BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE },
                { BoardCell.NONE, BoardCell.MAX, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE },
                { BoardCell.MAX, BoardCell.MAX, BoardCell.MIN, BoardCell.MIN, BoardCell.MAX },
                { BoardCell.MAX, BoardCell.MIN, BoardCell.MAX, BoardCell.MIN, BoardCell.MAX }
        }), new boolean[] { false, false }), Player.MAX));

        /*
        . . . . . . .
        . . . . . . .
        . . . . . . .
        + + . . . . .
        + - - . . . .
        + - - . . . .
         */
        Assert.assertEquals(new HashMap<Integer, Set<BoardFeature>>() {{
            put(1, new HashSet<BoardFeature>() {{
                add(new BoardFeature(
                        new BoardPos(0, 0, BoardCell.NONE),
                        new BoardPos(0, 1, BoardCell.NONE),
                        new BoardPos(0, 2, BoardCell.NONE),
                        new BoardPos(0, 3, BoardCell.MAX)
                ));
                add(new BoardFeature(
                        new BoardPos(1, 0, BoardCell.NONE),
                        new BoardPos(1, 1, BoardCell.NONE),
                        new BoardPos(1, 2, BoardCell.NONE),
                        new BoardPos(1, 3, BoardCell.MAX)
                ));
                add(new BoardFeature(
                        new BoardPos(1, 3, BoardCell.MAX),
                        new BoardPos(2, 3, BoardCell.NONE),
                        new BoardPos(3, 3, BoardCell.NONE),
                        new BoardPos(4, 3, BoardCell.NONE)
                ));
                add(new BoardFeature(
                        new BoardPos(0, 3, BoardCell.MAX),
                        new BoardPos(1, 2, BoardCell.NONE),
                        new BoardPos(2, 1, BoardCell.NONE),
                        new BoardPos(3, 0, BoardCell.NONE)
                ));
                add(new BoardFeature(
                        new BoardPos(1, 3, BoardCell.MAX),
                        new BoardPos(2, 2, BoardCell.NONE),
                        new BoardPos(3, 1, BoardCell.NONE),
                        new BoardPos(4, 0, BoardCell.NONE)
                ));
            }});
            put(2, new HashSet<BoardFeature>() {{
                add(new BoardFeature(
                        new BoardPos(0, 1, BoardCell.NONE),
                        new BoardPos(0, 2, BoardCell.NONE),
                        new BoardPos(0, 3, BoardCell.MAX),
                        new BoardPos(0, 4, BoardCell.MAX)
                ));
                add(new BoardFeature(
                        new BoardPos(0, 4, BoardCell.MAX),
                        new BoardPos(1, 3, BoardCell.MAX),
                        new BoardPos(2, 2, BoardCell.NONE),
                        new BoardPos(3, 1, BoardCell.NONE)
                ));
                add(new BoardFeature(
                        new BoardPos(0, 3, BoardCell.MAX),
                        new BoardPos(1, 3, BoardCell.MAX),
                        new BoardPos(2, 3, BoardCell.NONE),
                        new BoardPos(3, 3, BoardCell.NONE)
                ));
            }});
            put(3, new HashSet<BoardFeature>() {{
                add(new BoardFeature(
                        new BoardPos(0, 2, BoardCell.NONE),
                        new BoardPos(0, 3, BoardCell.MAX),
                        new BoardPos(0, 4, BoardCell.MAX),
                        new BoardPos(0, 5, BoardCell.MAX)
                ));
            }});
        }}, GameStateEvaluator.getInstance().getFeatures(new GameState(Player.MAX, 4, TestUtils.fixBoard(new BoardCell[][] {
                { BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE },
                { BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE },
                { BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE },
                { BoardCell.MAX, BoardCell.MAX, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE },
                { BoardCell.MAX, BoardCell.MIN, BoardCell.MIN, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE },
                { BoardCell.MAX, BoardCell.MIN, BoardCell.MIN, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE }
        }), new boolean[] { false, false }), Player.MAX));

        /*
        . . . . . . .
        . . . . . . .
        . . . . . . .
        . . . - . . .
        . . . - . . .
        . + . + . . .
         */
        Assert.assertEquals(new HashMap<Integer, Set<BoardFeature>>() {{
            put(1, new HashSet<BoardFeature>() {{
                add(new BoardFeature(
                        new BoardPos(1, 2, BoardCell.NONE),
                        new BoardPos(1, 3, BoardCell.NONE),
                        new BoardPos(1, 4, BoardCell.NONE),
                        new BoardPos(1, 5, BoardCell.MAX)
                ));
                add(new BoardFeature(
                        new BoardPos(0, 2, BoardCell.NONE),
                        new BoardPos(1, 3, BoardCell.NONE),
                        new BoardPos(2, 4, BoardCell.NONE),
                        new BoardPos(3, 5, BoardCell.MAX)
                ));
                add(new BoardFeature(
                        new BoardPos(2, 5, BoardCell.NONE),
                        new BoardPos(3, 5, BoardCell.MAX),
                        new BoardPos(4, 5, BoardCell.NONE),
                        new BoardPos(5, 5, BoardCell.NONE)
                ));
                add(new BoardFeature(
                        new BoardPos(3, 5, BoardCell.MAX),
                        new BoardPos(4, 5, BoardCell.NONE),
                        new BoardPos(5, 5, BoardCell.NONE),
                        new BoardPos(6, 5, BoardCell.NONE)
                ));
                add(new BoardFeature(
                        new BoardPos(3, 5, BoardCell.MAX),
                        new BoardPos(4, 4, BoardCell.NONE),
                        new BoardPos(5, 3, BoardCell.NONE),
                        new BoardPos(6, 2, BoardCell.NONE)
                ));
            }});
            put(2, new HashSet<BoardFeature>() {{
                add(new BoardFeature(
                        new BoardPos(0, 5, BoardCell.NONE),
                        new BoardPos(1, 5, BoardCell.MAX),
                        new BoardPos(2, 5, BoardCell.NONE),
                        new BoardPos(3, 5, BoardCell.MAX)
                ));
                add(new BoardFeature(
                        new BoardPos(1, 5, BoardCell.MAX),
                        new BoardPos(2, 5, BoardCell.NONE),
                        new BoardPos(3, 5, BoardCell.MAX),
                        new BoardPos(4, 5, BoardCell.NONE)
                ));
            }});
        }}, GameStateEvaluator.getInstance().getFeatures(new GameState(Player.MAX, 4, TestUtils.fixBoard(new BoardCell[][] {
                { BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE },
                { BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE },
                { BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE },
                { BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.MIN, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE },
                { BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.MIN, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE },
                { BoardCell.NONE, BoardCell.MAX, BoardCell.NONE, BoardCell.MAX, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE }
        }), new boolean[] { false, false }), Player.MAX));
        Assert.assertEquals(new HashMap<Integer, Set<BoardFeature>>() {{
            put(1, new HashSet<BoardFeature>() {{
                add(new BoardFeature(
                        new BoardPos(3, 0, BoardCell.NONE),
                        new BoardPos(3, 1, BoardCell.NONE),
                        new BoardPos(3, 2, BoardCell.NONE),
                        new BoardPos(3, 3, BoardCell.MIN)
                ));
                add(new BoardFeature(
                        new BoardPos(0, 0, BoardCell.NONE),
                        new BoardPos(1, 1, BoardCell.NONE),
                        new BoardPos(2, 2, BoardCell.NONE),
                        new BoardPos(3, 3, BoardCell.MIN)
                ));
                add(new BoardFeature(
                        new BoardPos(1, 1, BoardCell.NONE),
                        new BoardPos(2, 2, BoardCell.NONE),
                        new BoardPos(3, 3, BoardCell.MIN),
                        new BoardPos(4, 4, BoardCell.NONE)
                ));
                add(new BoardFeature(
                        new BoardPos(2, 2, BoardCell.NONE),
                        new BoardPos(3, 3, BoardCell.MIN),
                        new BoardPos(4, 4, BoardCell.NONE),
                        new BoardPos(5, 5, BoardCell.NONE)
                ));
                add(new BoardFeature(
                        new BoardPos(3, 3, BoardCell.MIN),
                        new BoardPos(4, 2, BoardCell.NONE),
                        new BoardPos(5, 1, BoardCell.NONE),
                        new BoardPos(6, 0, BoardCell.NONE)
                ));
                add(new BoardFeature(
                        new BoardPos(2, 4, BoardCell.NONE),
                        new BoardPos(3, 3, BoardCell.MIN),
                        new BoardPos(4, 2, BoardCell.NONE),
                        new BoardPos(5, 1, BoardCell.NONE)
                ));
                add(new BoardFeature(
                        new BoardPos(0, 3, BoardCell.NONE),
                        new BoardPos(1, 3, BoardCell.NONE),
                        new BoardPos(2, 3, BoardCell.NONE),
                        new BoardPos(3, 3, BoardCell.MIN)
                ));
                add(new BoardFeature(
                        new BoardPos(1, 3, BoardCell.NONE),
                        new BoardPos(2, 3, BoardCell.NONE),
                        new BoardPos(3, 3, BoardCell.MIN),
                        new BoardPos(4, 3, BoardCell.NONE)
                ));
                add(new BoardFeature(
                        new BoardPos(2, 3, BoardCell.NONE),
                        new BoardPos(3, 3, BoardCell.MIN),
                        new BoardPos(4, 3, BoardCell.NONE),
                        new BoardPos(5, 3, BoardCell.NONE)
                ));
                add(new BoardFeature(
                        new BoardPos(3, 3, BoardCell.MIN),
                        new BoardPos(4, 3, BoardCell.NONE),
                        new BoardPos(5, 3, BoardCell.NONE),
                        new BoardPos(6, 3, BoardCell.NONE)
                ));

                add(new BoardFeature(
                        new BoardPos(0, 1, BoardCell.NONE),
                        new BoardPos(1, 2, BoardCell.NONE),
                        new BoardPos(2, 3, BoardCell.NONE),
                        new BoardPos(3, 4, BoardCell.MIN)
                ));
                add(new BoardFeature(
                        new BoardPos(1, 2, BoardCell.NONE),
                        new BoardPos(2, 3, BoardCell.NONE),
                        new BoardPos(3, 4, BoardCell.MIN),
                        new BoardPos(4, 5, BoardCell.NONE)
                ));
                add(new BoardFeature(
                        new BoardPos(3, 4, BoardCell.MIN),
                        new BoardPos(4, 3, BoardCell.NONE),
                        new BoardPos(5, 2, BoardCell.NONE),
                        new BoardPos(6, 1, BoardCell.NONE)
                ));
                add(new BoardFeature(
                        new BoardPos(2, 5, BoardCell.NONE),
                        new BoardPos(3, 4, BoardCell.MIN),
                        new BoardPos(4, 3, BoardCell.NONE),
                        new BoardPos(5, 2, BoardCell.NONE)
                ));
                add(new BoardFeature(
                        new BoardPos(0, 4, BoardCell.NONE),
                        new BoardPos(1, 4, BoardCell.NONE),
                        new BoardPos(2, 4, BoardCell.NONE),
                        new BoardPos(3, 4, BoardCell.MIN)
                ));
                add(new BoardFeature(
                        new BoardPos(1, 4, BoardCell.NONE),
                        new BoardPos(2, 4, BoardCell.NONE),
                        new BoardPos(3, 4, BoardCell.MIN),
                        new BoardPos(4, 4, BoardCell.NONE)
                ));
                add(new BoardFeature(
                        new BoardPos(2, 4, BoardCell.NONE),
                        new BoardPos(3, 4, BoardCell.MIN),
                        new BoardPos(4, 4, BoardCell.NONE),
                        new BoardPos(5, 4, BoardCell.NONE)
                ));
                add(new BoardFeature(
                        new BoardPos(3, 4, BoardCell.MIN),
                        new BoardPos(4, 4, BoardCell.NONE),
                        new BoardPos(5, 4, BoardCell.NONE),
                        new BoardPos(6, 4, BoardCell.NONE)
                ));
            }});
            put(2, new HashSet<BoardFeature>() {{
                add(new BoardFeature(
                        new BoardPos(3, 1, BoardCell.NONE),
                        new BoardPos(3, 2, BoardCell.NONE),
                        new BoardPos(3, 3, BoardCell.MIN),
                        new BoardPos(3, 4, BoardCell.MIN)
                ));
            }});
        }}, GameStateEvaluator.getInstance().getFeatures(new GameState(Player.MAX, 4, TestUtils.fixBoard(new BoardCell[][] {
                { BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE },
                { BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE },
                { BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE },
                { BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.MIN, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE },
                { BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.MIN, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE },
                { BoardCell.NONE, BoardCell.MAX, BoardCell.NONE, BoardCell.MAX, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE }
        }), new boolean[] { false, false }), Player.MIN));
    }
}