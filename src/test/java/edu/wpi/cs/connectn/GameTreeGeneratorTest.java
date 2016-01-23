package edu.wpi.cs.connectn;

import org.junit.Assert;
import org.junit.Test;

/**
 * This class tests the {@link GameTreeGenerator} class.
 *
 * @author Daniel Beckwith
 */
public class GameTreeGeneratorTest {

    private void assertMoves(GameState[] expected, GameState root, Iterable<Move> actual) {
        int i = 0;
        for (Move move : actual) {
            if (i >= expected.length) {
                Assert.fail("Too many moves were generated");
            }
            GameState newState = root.clone();
            newState.move(move);
            Assert.assertEquals("GameState " + i + " was incorrect", expected[i], newState);
            i++;
        }
        if (i < expected.length) {
            Assert.fail("Too few moves were generated");
        }
    }

    @Test
    public void testGenerateChildren() throws Exception {
        GameState start = new GameState(7, 6, Player.MAX, 4);
        GameState[] expectedChildren = new GameState[7];
        for (int i = 0; i < expectedChildren.length; i++) {
            BoardCell[][] board = new BoardCell[start.getWidth()][start.getHeight()];
            for (int j = 0; j < board.length; j++) {
                for (int k = 0; k < board[0].length; k++) {
                    board[j][k] = k == board[0].length - 1 && j == i ? BoardCell.MAX : BoardCell.NONE;
                }
            }
            expectedChildren[i] = new GameState(Player.MIN, 4, board, new boolean[] { false, false });
        }
        assertMoves(expectedChildren, start, GameTreeGenerator.getInstance().generateValidMoves(start));

        start = new GameState(Player.MAX, 4, TestUtils.fixBoard(new BoardCell[][] {
                { BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.MIN, BoardCell.NONE },
                { BoardCell.NONE, BoardCell.MAX, BoardCell.NONE, BoardCell.MIN, BoardCell.MIN },
                { BoardCell.NONE, BoardCell.MAX, BoardCell.MIN, BoardCell.MAX, BoardCell.MAX },
                { BoardCell.MAX, BoardCell.MAX, BoardCell.MIN, BoardCell.MIN, BoardCell.MAX },
                { BoardCell.MAX, BoardCell.MIN, BoardCell.MAX, BoardCell.MIN, BoardCell.MAX }
        }), new boolean[] { false, false });
        assertMoves(new GameState[] {
                        new GameState(Player.MIN, 4, TestUtils.fixBoard(new BoardCell[][] {
                                { BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.MIN, BoardCell.NONE },
                                { BoardCell.NONE, BoardCell.MAX, BoardCell.NONE, BoardCell.MIN, BoardCell.MIN },
                                { BoardCell.MAX, BoardCell.MAX, BoardCell.MIN, BoardCell.MAX, BoardCell.MAX },
                                { BoardCell.MAX, BoardCell.MAX, BoardCell.MIN, BoardCell.MIN, BoardCell.MAX },
                                { BoardCell.MAX, BoardCell.MIN, BoardCell.MAX, BoardCell.MIN, BoardCell.MAX }
                        }), new boolean[] { false, false }),
                        new GameState(Player.MIN, 4, TestUtils.fixBoard(new BoardCell[][] {
                                { BoardCell.NONE, BoardCell.MAX, BoardCell.NONE, BoardCell.MIN, BoardCell.NONE },
                                { BoardCell.NONE, BoardCell.MAX, BoardCell.NONE, BoardCell.MIN, BoardCell.MIN },
                                { BoardCell.NONE, BoardCell.MAX, BoardCell.MIN, BoardCell.MAX, BoardCell.MAX },
                                { BoardCell.MAX, BoardCell.MAX, BoardCell.MIN, BoardCell.MIN, BoardCell.MAX },
                                { BoardCell.MAX, BoardCell.MIN, BoardCell.MAX, BoardCell.MIN, BoardCell.MAX }
                        }), new boolean[] { false, false }),
                        new GameState(Player.MIN, 4, TestUtils.fixBoard(new BoardCell[][] {
                                { BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.MIN, BoardCell.NONE },
                                { BoardCell.NONE, BoardCell.MAX, BoardCell.MAX, BoardCell.MIN, BoardCell.MIN },
                                { BoardCell.NONE, BoardCell.MAX, BoardCell.MIN, BoardCell.MAX, BoardCell.MAX },
                                { BoardCell.MAX, BoardCell.MAX, BoardCell.MIN, BoardCell.MIN, BoardCell.MAX },
                                { BoardCell.MAX, BoardCell.MIN, BoardCell.MAX, BoardCell.MIN, BoardCell.MAX }
                        }), new boolean[] { false, false }),
                        new GameState(Player.MIN, 4, TestUtils.fixBoard(new BoardCell[][] {
                                { BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.MIN, BoardCell.MAX },
                                { BoardCell.NONE, BoardCell.MAX, BoardCell.NONE, BoardCell.MIN, BoardCell.MIN },
                                { BoardCell.NONE, BoardCell.MAX, BoardCell.MIN, BoardCell.MAX, BoardCell.MAX },
                                { BoardCell.MAX, BoardCell.MAX, BoardCell.MIN, BoardCell.MIN, BoardCell.MAX },
                                { BoardCell.MAX, BoardCell.MIN, BoardCell.MAX, BoardCell.MIN, BoardCell.MAX }
                        }), new boolean[] { false, false }),
                        new GameState(Player.MIN, 4, TestUtils.fixBoard(new BoardCell[][] {
                                { BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.MIN, BoardCell.NONE },
                                { BoardCell.NONE, BoardCell.MAX, BoardCell.NONE, BoardCell.MIN, BoardCell.MIN },
                                { BoardCell.NONE, BoardCell.MAX, BoardCell.MIN, BoardCell.MAX, BoardCell.MAX },
                                { BoardCell.NONE, BoardCell.MAX, BoardCell.MIN, BoardCell.MIN, BoardCell.MAX },
                                { BoardCell.MAX, BoardCell.MIN, BoardCell.MAX, BoardCell.MIN, BoardCell.MAX }
                        }), new boolean[] { false, false }),
                        new GameState(Player.MIN, 4, TestUtils.fixBoard(new BoardCell[][] {
                                { BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.MIN, BoardCell.NONE },
                                { BoardCell.NONE, BoardCell.MAX, BoardCell.NONE, BoardCell.MIN, BoardCell.MIN },
                                { BoardCell.NONE, BoardCell.MAX, BoardCell.NONE, BoardCell.MAX, BoardCell.MAX },
                                { BoardCell.MAX, BoardCell.MAX, BoardCell.MIN, BoardCell.MIN, BoardCell.MAX },
                                { BoardCell.MAX, BoardCell.MIN, BoardCell.MIN, BoardCell.MIN, BoardCell.MAX }
                        }), new boolean[] { false, false }),
                        new GameState(Player.MIN, 4, TestUtils.fixBoard(new BoardCell[][] {
                                { BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.MIN, BoardCell.NONE },
                                { BoardCell.NONE, BoardCell.MAX, BoardCell.NONE, BoardCell.MIN, BoardCell.NONE },
                                { BoardCell.NONE, BoardCell.MAX, BoardCell.MIN, BoardCell.MAX, BoardCell.MIN },
                                { BoardCell.MAX, BoardCell.MAX, BoardCell.MIN, BoardCell.MIN, BoardCell.MAX },
                                { BoardCell.MAX, BoardCell.MIN, BoardCell.MAX, BoardCell.MIN, BoardCell.MAX }
                        }), new boolean[] { false, false })
                },
                start,
                GameTreeGenerator.getInstance().generateValidMoves(start));
    }
}