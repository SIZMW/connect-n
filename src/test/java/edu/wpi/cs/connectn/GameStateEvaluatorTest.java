package edu.wpi.cs.connectn;

import org.junit.Test;

/**
 * Created by djbsn_000 on 1/22/2016.
 */
public class GameStateEvaluatorTest {

    @Test
    public void testGetFeatures() throws Exception {
        // TODO: update tests for GameStateEvaluator

        /*Assert.assertEquals(new HashMap<Integer, Set<BoardFeature>>() {{
            put(1, new HashSet<BoardFeature>() {{
                add(new BoardFeature(new BoardPos(0, 3, BoardCell.MAX)));
                add(new BoardFeature(new BoardPos(0, 4, BoardCell.MAX)));
                add(new BoardFeature(new BoardPos(1, 1, BoardCell.MAX)));
                add(new BoardFeature(new BoardPos(1, 2, BoardCell.MAX)));
                add(new BoardFeature(new BoardPos(1, 3, BoardCell.MAX)));
                add(new BoardFeature(new BoardPos(2, 4, BoardCell.MAX)));
                add(new BoardFeature(new BoardPos(3, 2, BoardCell.MAX)));
                add(new BoardFeature(new BoardPos(4, 2, BoardCell.MAX)));
                add(new BoardFeature(new BoardPos(4, 3, BoardCell.MAX)));
                add(new BoardFeature(new BoardPos(4, 4, BoardCell.MAX)));
            }});
            put(2, new HashSet<BoardFeature>() {{
                add(new BoardFeature(
                        new BoardPos(0, 3, BoardCell.MAX),
                        new BoardPos(0, 4, BoardCell.MAX)
                ));
                add(new BoardFeature(
                        new BoardPos(0, 3, BoardCell.MAX),
                        new BoardPos(1, 3, BoardCell.MAX)
                ));
                add(new BoardFeature(
                        new BoardPos(0, 3, BoardCell.MAX),
                        new BoardPos(1, 2, BoardCell.MAX)
                ));
                add(new BoardFeature(
                        new BoardPos(0, 4, BoardCell.MAX),
                        new BoardPos(1, 3, BoardCell.MAX)
                ));
                add(new BoardFeature(
                        new BoardPos(1, 1, BoardCell.MAX),
                        new BoardPos(1, 2, BoardCell.MAX)
                ));
                add(new BoardFeature(
                        new BoardPos(1, 2, BoardCell.MAX),
                        new BoardPos(1, 3, BoardCell.MAX)
                ));
                add(new BoardFeature(
                        new BoardPos(1, 3, BoardCell.MAX),
                        new BoardPos(2, 4, BoardCell.MAX)
                ));
                add(new BoardFeature(
                        new BoardPos(3, 2, BoardCell.MAX),
                        new BoardPos(4, 2, BoardCell.MAX)
                ));
                add(new BoardFeature(
                        new BoardPos(3, 2, BoardCell.MAX),
                        new BoardPos(4, 3, BoardCell.MAX)
                ));
                add(new BoardFeature(
                        new BoardPos(4, 2, BoardCell.MAX),
                        new BoardPos(4, 3, BoardCell.MAX)
                ));
                add(new BoardFeature(
                        new BoardPos(4, 3, BoardCell.MAX),
                        new BoardPos(4, 4, BoardCell.MAX)
                ));
            }});
            put(3, new HashSet<BoardFeature>() {{
                add(new BoardFeature(
                        new BoardPos(1, 1, BoardCell.MAX),
                        new BoardPos(1, 2, BoardCell.MAX),
                        new BoardPos(1, 3, BoardCell.MAX)
                ));
                add(new BoardFeature(
                        new BoardPos(4, 2, BoardCell.MAX),
                        new BoardPos(4, 3, BoardCell.MAX),
                        new BoardPos(4, 4, BoardCell.MAX)
                ));
            }});
            put(4, new HashSet<>());
        }}, GameStateEvaluator.getInstance().getFeatures(new GameState(Player.MAX, 4, TestUtils.fixBoard(new BoardCell[][] {
                { BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.MIN, BoardCell.NONE },
                { BoardCell.NONE, BoardCell.MAX, BoardCell.NONE, BoardCell.MIN, BoardCell.MIN },
                { BoardCell.NONE, BoardCell.MAX, BoardCell.MIN, BoardCell.MAX, BoardCell.MAX },
                { BoardCell.MAX, BoardCell.MAX, BoardCell.MIN, BoardCell.MIN, BoardCell.MAX },
                { BoardCell.MAX, BoardCell.MIN, BoardCell.MAX, BoardCell.MIN, BoardCell.MAX }
        }), new boolean[] { false, false })));

        Assert.assertEquals(new HashMap<Integer, Set<BoardFeature>>() {{
            put(1, new HashSet<BoardFeature>() {{
                add(new BoardFeature(new BoardPos(0, 3, BoardCell.MAX)));
                add(new BoardFeature(new BoardPos(0, 4, BoardCell.MAX)));
                add(new BoardFeature(new BoardPos(1, 0, BoardCell.MAX)));
                add(new BoardFeature(new BoardPos(1, 1, BoardCell.MAX)));
                add(new BoardFeature(new BoardPos(1, 2, BoardCell.MAX)));
                add(new BoardFeature(new BoardPos(1, 3, BoardCell.MAX)));
                add(new BoardFeature(new BoardPos(2, 4, BoardCell.MAX)));
                add(new BoardFeature(new BoardPos(3, 2, BoardCell.MAX)));
                add(new BoardFeature(new BoardPos(4, 2, BoardCell.MAX)));
                add(new BoardFeature(new BoardPos(4, 3, BoardCell.MAX)));
                add(new BoardFeature(new BoardPos(4, 4, BoardCell.MAX)));
            }});
            put(2, new HashSet<BoardFeature>() {{
                add(new BoardFeature(
                        new BoardPos(0, 3, BoardCell.MAX),
                        new BoardPos(0, 4, BoardCell.MAX)
                ));
                add(new BoardFeature(
                        new BoardPos(0, 3, BoardCell.MAX),
                        new BoardPos(1, 3, BoardCell.MAX)
                ));
                add(new BoardFeature(
                        new BoardPos(0, 3, BoardCell.MAX),
                        new BoardPos(1, 2, BoardCell.MAX)
                ));
                add(new BoardFeature(
                        new BoardPos(0, 4, BoardCell.MAX),
                        new BoardPos(1, 3, BoardCell.MAX)
                ));
                add(new BoardFeature(
                        new BoardPos(1, 0, BoardCell.MAX),
                        new BoardPos(1, 1, BoardCell.MAX)
                ));
                add(new BoardFeature(
                        new BoardPos(1, 1, BoardCell.MAX),
                        new BoardPos(1, 2, BoardCell.MAX)
                ));
                add(new BoardFeature(
                        new BoardPos(1, 2, BoardCell.MAX),
                        new BoardPos(1, 3, BoardCell.MAX)
                ));
                add(new BoardFeature(
                        new BoardPos(1, 3, BoardCell.MAX),
                        new BoardPos(2, 4, BoardCell.MAX)
                ));
                add(new BoardFeature(
                        new BoardPos(3, 2, BoardCell.MAX),
                        new BoardPos(4, 2, BoardCell.MAX)
                ));
                add(new BoardFeature(
                        new BoardPos(3, 2, BoardCell.MAX),
                        new BoardPos(4, 3, BoardCell.MAX)
                ));
                add(new BoardFeature(
                        new BoardPos(4, 2, BoardCell.MAX),
                        new BoardPos(4, 3, BoardCell.MAX)
                ));
                add(new BoardFeature(
                        new BoardPos(4, 3, BoardCell.MAX),
                        new BoardPos(4, 4, BoardCell.MAX)
                ));
            }});
            put(3, new HashSet<BoardFeature>() {{
                add(new BoardFeature(
                        new BoardPos(1, 0, BoardCell.MAX),
                        new BoardPos(1, 1, BoardCell.MAX),
                        new BoardPos(1, 2, BoardCell.MAX)
                ));
                add(new BoardFeature(
                        new BoardPos(1, 1, BoardCell.MAX),
                        new BoardPos(1, 2, BoardCell.MAX),
                        new BoardPos(1, 3, BoardCell.MAX)
                ));
                add(new BoardFeature(
                        new BoardPos(4, 2, BoardCell.MAX),
                        new BoardPos(4, 3, BoardCell.MAX),
                        new BoardPos(4, 4, BoardCell.MAX)
                ));
            }});
            put(4, new HashSet<BoardFeature>() {{
                add(new BoardFeature(
                        new BoardPos(1, 0, BoardCell.MAX),
                        new BoardPos(1, 1, BoardCell.MAX),
                        new BoardPos(1, 2, BoardCell.MAX),
                        new BoardPos(1, 3, BoardCell.MAX)
                ));
            }});
        }}, GameStateEvaluator.getInstance().getFeatures(new GameState(Player.MAX, 4, TestUtils.fixBoard(new BoardCell[][] {
                { BoardCell.NONE, BoardCell.MAX, BoardCell.NONE, BoardCell.MIN, BoardCell.NONE },
                { BoardCell.NONE, BoardCell.MAX, BoardCell.NONE, BoardCell.MIN, BoardCell.MIN },
                { BoardCell.NONE, BoardCell.MAX, BoardCell.MIN, BoardCell.MAX, BoardCell.MAX },
                { BoardCell.MAX, BoardCell.MAX, BoardCell.MIN, BoardCell.MIN, BoardCell.MAX },
                { BoardCell.MAX, BoardCell.MIN, BoardCell.MAX, BoardCell.MIN, BoardCell.MAX }
        }), new boolean[] { false, false })));

        *//*
        9 9 9 9 9 9 9
        9 9 9 9 9 9 9
        9 9 9 9 9 9 9
        2 2 9 9 9 9 9
        2 1 1 9 9 9 9
        2 1 1 9 9 9 9
         *//*
        Assert.assertEquals(new HashMap<Integer, Set<BoardFeature>>() {{
            put(1, new HashSet<BoardFeature>() {{
                add(new BoardFeature(new BoardPos(0, 3, BoardCell.MAX)));
                add(new BoardFeature(new BoardPos(0, 4, BoardCell.MAX)));
                add(new BoardFeature(new BoardPos(0, 5, BoardCell.MAX)));
                add(new BoardFeature(new BoardPos(1, 3, BoardCell.MAX)));
            }});
            put(2, new HashSet<BoardFeature>() {{
                add(new BoardFeature(
                        new BoardPos(0, 3, BoardCell.MAX),
                        new BoardPos(0, 4, BoardCell.MAX)
                ));
                add(new BoardFeature(
                        new BoardPos(0, 4, BoardCell.MAX),
                        new BoardPos(0, 5, BoardCell.MAX)
                ));
                add(new BoardFeature(
                        new BoardPos(0, 3, BoardCell.MAX),
                        new BoardPos(1, 3, BoardCell.MAX)
                ));
                add(new BoardFeature(
                        new BoardPos(0, 4, BoardCell.MAX),
                        new BoardPos(1, 3, BoardCell.MAX)
                ));
            }});
            put(3, new HashSet<BoardFeature>() {{
                add(new BoardFeature(
                        new BoardPos(0, 3, BoardCell.MAX),
                        new BoardPos(0, 4, BoardCell.MAX),
                        new BoardPos(0, 5, BoardCell.MAX)
                ));
            }});
            put(4, new HashSet<BoardFeature>() {{
            }});
        }}, GameStateEvaluator.getInstance().getFeatures(new GameState(Player.MAX, 4, TestUtils.fixBoard(new BoardCell[][] {
                { BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE },
                { BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE },
                { BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE },
                { BoardCell.MAX, BoardCell.MAX, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE },
                { BoardCell.MAX, BoardCell.MIN, BoardCell.MIN, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE },
                { BoardCell.MAX, BoardCell.MIN, BoardCell.MIN, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE }
        }), new boolean[] { false, false })));*/
    }
}