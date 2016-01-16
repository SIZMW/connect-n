package edu.wpi.cs.connectn;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by djbsn_000 on 1/15/2016.
 */
public class GameTreeGeneratorTest {

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
        int i = 0;
        for (GameState child : GameTreeGenerator.getInstance().generateChildren(start)) {
            if (i >= expectedChildren.length) {
                Assert.fail("Too many children were generated");
            }
            Assert.assertEquals("The child was incorrect", expectedChildren[i], child);
            i++;
        }
        if (i < expectedChildren.length) {
            Assert.fail("Too few children were generated");
        }
    }
}