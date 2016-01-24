package edu.wpi.cs.connectn;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

/**
 * This class tests the {@link GameTreeGenerator} class.
 *
 * @author Daniel Beckwith
 */
public class GameTreeGeneratorTest {

    private void assertMoves(Collection<GameState> expected, GameState root, Iterable<Move> actual) {
        int i = 0;
        for (Move move : actual) {
            if (i >= expected.size()) {
                Assert.fail("Too many moves were generated");
            }
            GameState newState = root.clone();
            newState.move(move);
            Assert.assertTrue("GameState " + newState + " wasn't expected", expected.contains(newState));
            i++;
        }
        if (i < expected.size()) {
            Assert.fail("Too few moves were generated");
        }
    }

    @Test
    public void testGenerateChildren() throws Exception {
        GameState start = new GameState(7, 6, Player.MAX, 4);
        Collection<GameState> expectedChildren = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            BoardCell[][] board = new BoardCell[start.getWidth()][start.getHeight()];
            for (int j = 0; j < board.length; j++) {
                for (int k = 0; k < board[0].length; k++) {
                    board[j][k] = k == board[0].length - 1 && j == i ? BoardCell.MAX : BoardCell.NONE;
                }
            }
            expectedChildren.add(new GameState(Player.MIN, 4, board, new boolean[] { false, false }));
        }
        assertMoves(expectedChildren, start, GameTreeGenerator.getInstance().generateValidMoves(start));

        start = new GameState(Player.MAX, 4, TestUtils.fixBoard(new BoardCell[][] {
                { BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.MIN, BoardCell.NONE },
                { BoardCell.NONE, BoardCell.MAX, BoardCell.NONE, BoardCell.MIN, BoardCell.MIN },
                { BoardCell.NONE, BoardCell.MAX, BoardCell.MIN, BoardCell.MAX, BoardCell.MAX },
                { BoardCell.MAX, BoardCell.MAX, BoardCell.MIN, BoardCell.MIN, BoardCell.MAX },
                { BoardCell.MAX, BoardCell.MIN, BoardCell.MAX, BoardCell.MIN, BoardCell.MAX }
        }), new boolean[] { false, false });
        assertMoves(Arrays.asList(
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
                }), new boolean[] { false, true }),
                new GameState(Player.MIN, 4, TestUtils.fixBoard(new BoardCell[][] {
                        { BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.MIN, BoardCell.NONE },
                        { BoardCell.NONE, BoardCell.MAX, BoardCell.NONE, BoardCell.MIN, BoardCell.MIN },
                        { BoardCell.NONE, BoardCell.MAX, BoardCell.NONE, BoardCell.MAX, BoardCell.MAX },
                        { BoardCell.MAX, BoardCell.MAX, BoardCell.MIN, BoardCell.MIN, BoardCell.MAX },
                        { BoardCell.MAX, BoardCell.MIN, BoardCell.MIN, BoardCell.MIN, BoardCell.MAX }
                }), new boolean[] { false, true }),
                new GameState(Player.MIN, 4, TestUtils.fixBoard(new BoardCell[][] {
                        { BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.MIN, BoardCell.NONE },
                        { BoardCell.NONE, BoardCell.MAX, BoardCell.NONE, BoardCell.MIN, BoardCell.NONE },
                        { BoardCell.NONE, BoardCell.MAX, BoardCell.MIN, BoardCell.MAX, BoardCell.MIN },
                        { BoardCell.MAX, BoardCell.MAX, BoardCell.MIN, BoardCell.MIN, BoardCell.MAX },
                        { BoardCell.MAX, BoardCell.MIN, BoardCell.MAX, BoardCell.MIN, BoardCell.MAX }
                }), new boolean[] { false, true })),
                start,
                GameTreeGenerator.getInstance().generateValidMoves(start));

        /*
        9 9 9 9 9 9 9
        9 9 9 9 9 9 9
        9 9 9 9 9 9 9
        2 2 9 9 9 9 9
        2 1 1 9 9 9 9
        2 1 1 9 9 9 9
         */
        start = new GameState(Player.MAX, 4, TestUtils.fixBoard(new BoardCell[][] {
                { BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE },
                { BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE },
                { BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE },
                { BoardCell.MAX, BoardCell.MAX, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE },
                { BoardCell.MAX, BoardCell.MIN, BoardCell.MIN, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE },
                { BoardCell.MAX, BoardCell.MIN, BoardCell.MIN, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE }
        }), new boolean[] { false, false });
        assertMoves(Arrays.asList(
                new GameState(Player.MIN, 4, TestUtils.fixBoard(new BoardCell[][] {
                        { BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE },
                        { BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE },
                        { BoardCell.MAX, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE },
                        { BoardCell.MAX, BoardCell.MAX, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE },
                        { BoardCell.MAX, BoardCell.MIN, BoardCell.MIN, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE },
                        { BoardCell.MAX, BoardCell.MIN, BoardCell.MIN, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE }
                }), new boolean[] { false, false }),
                new GameState(Player.MIN, 4, TestUtils.fixBoard(new BoardCell[][] {
                        { BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE },
                        { BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE },
                        { BoardCell.NONE, BoardCell.MAX, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE },
                        { BoardCell.MAX, BoardCell.MAX, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE },
                        { BoardCell.MAX, BoardCell.MIN, BoardCell.MIN, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE },
                        { BoardCell.MAX, BoardCell.MIN, BoardCell.MIN, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE }
                }), new boolean[] { false, false }),
                new GameState(Player.MIN, 4, TestUtils.fixBoard(new BoardCell[][] {
                        { BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE },
                        { BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE },
                        { BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE },
                        { BoardCell.MAX, BoardCell.MAX, BoardCell.MAX, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE },
                        { BoardCell.MAX, BoardCell.MIN, BoardCell.MIN, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE },
                        { BoardCell.MAX, BoardCell.MIN, BoardCell.MIN, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE }
                }), new boolean[] { false, false }),
                new GameState(Player.MIN, 4, TestUtils.fixBoard(new BoardCell[][] {
                        { BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE },
                        { BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE },
                        { BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE },
                        { BoardCell.MAX, BoardCell.MAX, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE },
                        { BoardCell.MAX, BoardCell.MIN, BoardCell.MIN, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE },
                        { BoardCell.MAX, BoardCell.MIN, BoardCell.MIN, BoardCell.MAX, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE }
                }), new boolean[] { false, false }),
                new GameState(Player.MIN, 4, TestUtils.fixBoard(new BoardCell[][] {
                        { BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE },
                        { BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE },
                        { BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE },
                        { BoardCell.MAX, BoardCell.MAX, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE },
                        { BoardCell.MAX, BoardCell.MIN, BoardCell.MIN, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE },
                        { BoardCell.MAX, BoardCell.MIN, BoardCell.MIN, BoardCell.NONE, BoardCell.MAX, BoardCell.NONE, BoardCell.NONE }
                }), new boolean[] { false, false }),
                new GameState(Player.MIN, 4, TestUtils.fixBoard(new BoardCell[][] {
                        { BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE },
                        { BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE },
                        { BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE },
                        { BoardCell.MAX, BoardCell.MAX, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE },
                        { BoardCell.MAX, BoardCell.MIN, BoardCell.MIN, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE },
                        { BoardCell.MAX, BoardCell.MIN, BoardCell.MIN, BoardCell.NONE, BoardCell.NONE, BoardCell.MAX, BoardCell.NONE }
                }), new boolean[] { false, false }),
                new GameState(Player.MIN, 4, TestUtils.fixBoard(new BoardCell[][] {
                        { BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE },
                        { BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE },
                        { BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE },
                        { BoardCell.MAX, BoardCell.MAX, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE },
                        { BoardCell.MAX, BoardCell.MIN, BoardCell.MIN, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE },
                        { BoardCell.MAX, BoardCell.MIN, BoardCell.MIN, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.MAX }
                }), new boolean[] { false, false }),
                new GameState(Player.MIN, 4, TestUtils.fixBoard(new BoardCell[][] {
                        { BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE },
                        { BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE },
                        { BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE },
                        { BoardCell.NONE, BoardCell.MAX, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE },
                        { BoardCell.MAX, BoardCell.MIN, BoardCell.MIN, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE },
                        { BoardCell.MAX, BoardCell.MIN, BoardCell.MIN, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE }
                }), new boolean[] { false, true })),
                start,
                GameTreeGenerator.getInstance().generateValidMoves(start));
    }
}