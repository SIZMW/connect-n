package edu.wpi.cs.connectn;

/**
 * Created by djbsn_000 on 1/22/2016.
 */
public final class TestUtils {

    private TestUtils() {}

    public static BoardCell[][] fixBoard(BoardCell[][] board) {
        BoardCell[][] newBoard = new BoardCell[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                newBoard[j][i] = board[i][j];
            }
        }
        return newBoard;
    }
}
