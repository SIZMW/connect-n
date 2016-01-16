package edu.wpi.cs.connectn;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by djbsn_000 on 1/15/2016.
 */
public class GameTreeGeneratorTest {

    private BoardCell[][] fixBoard(BoardCell[][] board) {
        BoardCell[][] newBoard = new BoardCell[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                newBoard[j][i] = board[i][j];
            }
        }
        return newBoard;
    }

    private <T> void assertArrayIterableEquals(T[] expected, Iterable<T> actual) {
        int i = 0;
        for (T actualElement : actual) {
            if (i >= expected.length) {
                Assert.fail("Too many elements were generated");
            }
            Assert.assertEquals("Element " + i + " was incorrect", expected[i], actualElement);
            i++;
        }
        if (i < expected.length) {
            Assert.fail("Too few elements were generated");
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
            expectedChildren[i] = new GameState(Player.MIN, 4, board);
        }
        assertArrayIterableEquals(expectedChildren, GameTreeGenerator.getInstance().generateChildren(start));

        assertArrayIterableEquals(new GameState[] {
                        new GameState(Player.MIN, 4, fixBoard(new BoardCell[][] {
                                { BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.MIN, BoardCell.NONE },
                                { BoardCell.NONE, BoardCell.MAX, BoardCell.NONE, BoardCell.MIN, BoardCell.MIN },
                                { BoardCell.MAX, BoardCell.MAX, BoardCell.MIN, BoardCell.MAX, BoardCell.MAX },
                                { BoardCell.MAX, BoardCell.MAX, BoardCell.MIN, BoardCell.MIN, BoardCell.MAX },
                                { BoardCell.MAX, BoardCell.MIN, BoardCell.MAX, BoardCell.MIN, BoardCell.MAX }
                        })),
                        new GameState(Player.MIN, 4, fixBoard(new BoardCell[][] {
                                { BoardCell.NONE, BoardCell.MAX, BoardCell.NONE, BoardCell.MIN, BoardCell.NONE },
                                { BoardCell.NONE, BoardCell.MAX, BoardCell.NONE, BoardCell.MIN, BoardCell.MIN },
                                { BoardCell.NONE, BoardCell.MAX, BoardCell.MIN, BoardCell.MAX, BoardCell.MAX },
                                { BoardCell.MAX, BoardCell.MAX, BoardCell.MIN, BoardCell.MIN, BoardCell.MAX },
                                { BoardCell.MAX, BoardCell.MIN, BoardCell.MAX, BoardCell.MIN, BoardCell.MAX }
                        })),
                        new GameState(Player.MIN, 4, fixBoard(new BoardCell[][] {
                                { BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.MIN, BoardCell.NONE },
                                { BoardCell.NONE, BoardCell.MAX, BoardCell.MAX, BoardCell.MIN, BoardCell.MIN },
                                { BoardCell.NONE, BoardCell.MAX, BoardCell.MIN, BoardCell.MAX, BoardCell.MAX },
                                { BoardCell.MAX, BoardCell.MAX, BoardCell.MIN, BoardCell.MIN, BoardCell.MAX },
                                { BoardCell.MAX, BoardCell.MIN, BoardCell.MAX, BoardCell.MIN, BoardCell.MAX }
                        })),
                        new GameState(Player.MIN, 4, fixBoard(new BoardCell[][] {
                                { BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.MIN, BoardCell.MAX },
                                { BoardCell.NONE, BoardCell.MAX, BoardCell.NONE, BoardCell.MIN, BoardCell.MIN },
                                { BoardCell.NONE, BoardCell.MAX, BoardCell.MIN, BoardCell.MAX, BoardCell.MAX },
                                { BoardCell.MAX, BoardCell.MAX, BoardCell.MIN, BoardCell.MIN, BoardCell.MAX },
                                { BoardCell.MAX, BoardCell.MIN, BoardCell.MAX, BoardCell.MIN, BoardCell.MAX }
                        })),
                        new GameState(Player.MIN, 4, fixBoard(new BoardCell[][] {
                                { BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.MIN, BoardCell.NONE },
                                { BoardCell.NONE, BoardCell.MAX, BoardCell.NONE, BoardCell.MIN, BoardCell.MIN },
                                { BoardCell.NONE, BoardCell.MAX, BoardCell.MIN, BoardCell.MAX, BoardCell.MAX },
                                { BoardCell.NONE, BoardCell.MAX, BoardCell.MIN, BoardCell.MIN, BoardCell.MAX },
                                { BoardCell.MAX, BoardCell.MIN, BoardCell.MAX, BoardCell.MIN, BoardCell.MAX }
                        })),
                        new GameState(Player.MIN, 4, fixBoard(new BoardCell[][] {
                                { BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.MIN, BoardCell.NONE },
                                { BoardCell.NONE, BoardCell.MAX, BoardCell.NONE, BoardCell.MIN, BoardCell.MIN },
                                { BoardCell.NONE, BoardCell.MAX, BoardCell.NONE, BoardCell.MAX, BoardCell.MAX },
                                { BoardCell.MAX, BoardCell.MAX, BoardCell.MIN, BoardCell.MIN, BoardCell.MAX },
                                { BoardCell.MAX, BoardCell.MIN, BoardCell.MIN, BoardCell.MIN, BoardCell.MAX }
                        })),
                        new GameState(Player.MIN, 4, fixBoard(new BoardCell[][] {
                                { BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.MIN, BoardCell.NONE },
                                { BoardCell.NONE, BoardCell.MAX, BoardCell.NONE, BoardCell.MIN, BoardCell.NONE },
                                { BoardCell.NONE, BoardCell.MAX, BoardCell.MIN, BoardCell.MAX, BoardCell.MIN },
                                { BoardCell.MAX, BoardCell.MAX, BoardCell.MIN, BoardCell.MIN, BoardCell.MAX },
                                { BoardCell.MAX, BoardCell.MIN, BoardCell.MAX, BoardCell.MIN, BoardCell.MAX }
                        }))
                },
                GameTreeGenerator.getInstance().generateChildren(new GameState(Player.MAX, 4, fixBoard(new BoardCell[][] {
                        { BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.MIN, BoardCell.NONE },
                        { BoardCell.NONE, BoardCell.MAX, BoardCell.NONE, BoardCell.MIN, BoardCell.MIN },
                        { BoardCell.NONE, BoardCell.MAX, BoardCell.MIN, BoardCell.MAX, BoardCell.MAX },
                        { BoardCell.MAX, BoardCell.MAX, BoardCell.MIN, BoardCell.MIN, BoardCell.MAX },
                        { BoardCell.MAX, BoardCell.MIN, BoardCell.MAX, BoardCell.MIN, BoardCell.MAX }
                }))));
    }
}