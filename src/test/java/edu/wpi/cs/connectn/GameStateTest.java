package edu.wpi.cs.connectn;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by djbsn_000 on 1/16/2016.
 */
public class GameStateTest {

    private BoardCell[][] fixBoard(BoardCell[][] board) {
        BoardCell[][] newBoard = new BoardCell[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                newBoard[j][i] = board[i][j];
            }
        }
        return newBoard;
    }

    @Test
    public void testSwitchTurn() throws Exception {
        GameState state = new GameState(10, 10, Player.MAX, 4);
        Assert.assertEquals(Player.MAX, state.getTurn());
        state.switchTurn();
        Assert.assertEquals(Player.MIN, state.getTurn());
        state.switchTurn();
        Assert.assertEquals(Player.MAX, state.getTurn());
    }

    @Test
    public void testGet() throws Exception {
        GameState state = new GameState(Player.MAX, 4, fixBoard(new BoardCell[][] {
                { BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE },
                { BoardCell.NONE, BoardCell.MAX, BoardCell.NONE, BoardCell.MIN, BoardCell.MIN },
                { BoardCell.NONE, BoardCell.MAX, BoardCell.MIN, BoardCell.MAX, BoardCell.MAX },
                { BoardCell.MAX, BoardCell.MAX, BoardCell.MIN, BoardCell.MIN, BoardCell.MAX },
                { BoardCell.MAX, BoardCell.MIN, BoardCell.MAX, BoardCell.MIN, BoardCell.MAX }
        }));
        Assert.assertEquals(BoardCell.NONE, state.get(0, 0));
        Assert.assertEquals(BoardCell.NONE, state.get(1, 0));
        Assert.assertEquals(BoardCell.MAX, state.get(1, 1));
        Assert.assertEquals(BoardCell.MAX, state.get(1, 2));
        Assert.assertEquals(BoardCell.NONE, state.get(2, 1));
        Assert.assertEquals(BoardCell.MAX, state.get(4, 3));
        Assert.assertEquals(BoardCell.MIN, state.get(3, 4));
    }

    @Test
    public void testMove() throws Exception {
        GameState original = new GameState(Player.MAX, 4, fixBoard(new BoardCell[][] {
                { BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE },
                { BoardCell.NONE, BoardCell.MAX, BoardCell.NONE, BoardCell.MIN, BoardCell.MIN },
                { BoardCell.NONE, BoardCell.MAX, BoardCell.MIN, BoardCell.MAX, BoardCell.MAX },
                { BoardCell.MAX, BoardCell.MAX, BoardCell.MIN, BoardCell.MIN, BoardCell.MAX },
                { BoardCell.MAX, BoardCell.MIN, BoardCell.MAX, BoardCell.MIN, BoardCell.MAX }
        }));
        GameState state;

        state = original.clone();
        state.move(new Move(MoveType.DROP, 0));
        Assert.assertEquals(new GameState(Player.MIN, 4, fixBoard(new BoardCell[][] {
                { BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE },
                { BoardCell.NONE, BoardCell.MAX, BoardCell.NONE, BoardCell.MIN, BoardCell.MIN },
                { BoardCell.MAX, BoardCell.MAX, BoardCell.MIN, BoardCell.MAX, BoardCell.MAX },
                { BoardCell.MAX, BoardCell.MAX, BoardCell.MIN, BoardCell.MIN, BoardCell.MAX },
                { BoardCell.MAX, BoardCell.MIN, BoardCell.MAX, BoardCell.MIN, BoardCell.MAX }
        })), state);

        state = original.clone();
        state.move(new Move(MoveType.DROP, 1));
        Assert.assertEquals(new GameState(Player.MIN, 4, fixBoard(new BoardCell[][] {
                { BoardCell.NONE, BoardCell.MAX, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE },
                { BoardCell.NONE, BoardCell.MAX, BoardCell.NONE, BoardCell.MIN, BoardCell.MIN },
                { BoardCell.NONE, BoardCell.MAX, BoardCell.MIN, BoardCell.MAX, BoardCell.MAX },
                { BoardCell.MAX, BoardCell.MAX, BoardCell.MIN, BoardCell.MIN, BoardCell.MAX },
                { BoardCell.MAX, BoardCell.MIN, BoardCell.MAX, BoardCell.MIN, BoardCell.MAX }
        })), state);

        state = original.clone();
        state.move(new Move(MoveType.POP, 2));
        Assert.assertEquals(new GameState(Player.MIN, 4, fixBoard(new BoardCell[][] {
                { BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.NONE },
                { BoardCell.NONE, BoardCell.MAX, BoardCell.NONE, BoardCell.MIN, BoardCell.MIN },
                { BoardCell.NONE, BoardCell.MAX, BoardCell.NONE, BoardCell.MAX, BoardCell.MAX },
                { BoardCell.MAX, BoardCell.MAX, BoardCell.MIN, BoardCell.MIN, BoardCell.MAX },
                { BoardCell.MAX, BoardCell.MIN, BoardCell.MIN, BoardCell.MIN, BoardCell.MAX }
        })), state);
    }

    @Test
    public void testIsMoveValid() throws Exception {
        GameState state = new GameState(Player.MAX, 4, fixBoard(new BoardCell[][] {
                { BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.MAX, BoardCell.NONE },
                { BoardCell.NONE, BoardCell.MAX, BoardCell.NONE, BoardCell.MIN, BoardCell.MIN },
                { BoardCell.NONE, BoardCell.MAX, BoardCell.MIN, BoardCell.MAX, BoardCell.MAX },
                { BoardCell.MAX, BoardCell.MAX, BoardCell.MIN, BoardCell.MIN, BoardCell.MAX },
                { BoardCell.MAX, BoardCell.MIN, BoardCell.MAX, BoardCell.MIN, BoardCell.MAX }
        }));

        Assert.assertFalse(state.isMoveValid(new Move(MoveType.DROP, -1)));
        Assert.assertTrue(state.isMoveValid(new Move(MoveType.DROP, 0)));
        Assert.assertTrue(state.isMoveValid(new Move(MoveType.DROP, 1)));
        Assert.assertTrue(state.isMoveValid(new Move(MoveType.DROP, 2)));
        Assert.assertFalse(state.isMoveValid(new Move(MoveType.DROP, 3)));
        Assert.assertTrue(state.isMoveValid(new Move(MoveType.DROP, 4)));
        Assert.assertFalse(state.isMoveValid(new Move(MoveType.DROP, 5)));

        Assert.assertFalse(state.isMoveValid(new Move(MoveType.POP, -1)));
        Assert.assertTrue(state.isMoveValid(new Move(MoveType.POP, 0)));
        Assert.assertFalse(state.isMoveValid(new Move(MoveType.POP, 1)));
        Assert.assertTrue(state.isMoveValid(new Move(MoveType.POP, 2)));
        Assert.assertFalse(state.isMoveValid(new Move(MoveType.POP, 3)));
        Assert.assertTrue(state.isMoveValid(new Move(MoveType.POP, 4)));
        Assert.assertFalse(state.isMoveValid(new Move(MoveType.POP, 5)));
    }

    @Test
    public void testGetWinner() throws Exception {
        Assert.assertEquals(GameWinner.NONE, new GameState(Player.MAX, 4, fixBoard(new BoardCell[][] {
                { BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.MIN, BoardCell.NONE },
                { BoardCell.NONE, BoardCell.MAX, BoardCell.NONE, BoardCell.MIN, BoardCell.MIN },
                { BoardCell.NONE, BoardCell.MAX, BoardCell.MIN, BoardCell.MAX, BoardCell.MAX },
                { BoardCell.MAX, BoardCell.MAX, BoardCell.MIN, BoardCell.MIN, BoardCell.MAX },
                { BoardCell.MAX, BoardCell.MIN, BoardCell.MAX, BoardCell.MIN, BoardCell.MAX }
        })).getWinner());
        Assert.assertEquals(GameWinner.MAX, new GameState(Player.MAX, 4, fixBoard(new BoardCell[][] {
                { BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.MAX, BoardCell.NONE },
                { BoardCell.NONE, BoardCell.MAX, BoardCell.NONE, BoardCell.MIN, BoardCell.MAX },
                { BoardCell.NONE, BoardCell.MAX, BoardCell.MIN, BoardCell.MAX, BoardCell.MAX },
                { BoardCell.MAX, BoardCell.MAX, BoardCell.MIN, BoardCell.MIN, BoardCell.MAX },
                { BoardCell.MAX, BoardCell.MIN, BoardCell.MAX, BoardCell.MIN, BoardCell.MAX }
        })).getWinner());
        Assert.assertEquals(GameWinner.MAX, new GameState(Player.MAX, 4, fixBoard(new BoardCell[][] {
                { BoardCell.NONE, BoardCell.MAX, BoardCell.NONE, BoardCell.MAX, BoardCell.NONE },
                { BoardCell.NONE, BoardCell.MAX, BoardCell.MAX, BoardCell.MIN, BoardCell.MIN },
                { BoardCell.NONE, BoardCell.MAX, BoardCell.MIN, BoardCell.MAX, BoardCell.MAX },
                { BoardCell.MAX, BoardCell.MAX, BoardCell.MIN, BoardCell.MIN, BoardCell.MAX },
                { BoardCell.MAX, BoardCell.MIN, BoardCell.MAX, BoardCell.MIN, BoardCell.MAX }
        })).getWinner());
        Assert.assertEquals(GameWinner.MIN, new GameState(Player.MAX, 4, fixBoard(new BoardCell[][] {
                { BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.MAX, BoardCell.NONE },
                { BoardCell.NONE, BoardCell.MAX, BoardCell.NONE, BoardCell.MIN, BoardCell.MIN },
                { BoardCell.NONE, BoardCell.MAX, BoardCell.MIN, BoardCell.MIN, BoardCell.MAX },
                { BoardCell.MAX, BoardCell.MAX, BoardCell.MIN, BoardCell.MIN, BoardCell.MAX },
                { BoardCell.MAX, BoardCell.MIN, BoardCell.MAX, BoardCell.MIN, BoardCell.MAX }
        })).getWinner());
        Assert.assertEquals(GameWinner.TIE, new GameState(Player.MAX, 4, fixBoard(new BoardCell[][] {
                { BoardCell.NONE, BoardCell.NONE, BoardCell.NONE, BoardCell.MAX, BoardCell.NONE },
                { BoardCell.NONE, BoardCell.MAX, BoardCell.NONE, BoardCell.MIN, BoardCell.MAX },
                { BoardCell.NONE, BoardCell.MAX, BoardCell.MIN, BoardCell.MIN, BoardCell.MAX },
                { BoardCell.MAX, BoardCell.MAX, BoardCell.MIN, BoardCell.MIN, BoardCell.MAX },
                { BoardCell.MAX, BoardCell.MIN, BoardCell.MAX, BoardCell.MIN, BoardCell.MAX }
        })).getWinner());
    }
}