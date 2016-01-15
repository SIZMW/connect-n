package edu.wpi.cs.connectn;


import java.util.Arrays;

public class GameState {
    private final Player[][] boardState;
    private Player turn = Player.NONE;

    public GameState(int w, int h, Player turn) {
        boardState = new Player[w][h];
        this.turn = turn;

        for (int i = 0; i < w; i++) {
            Arrays.fill(boardState[i], Player.NONE);
        }
    }

    private GameState(GameState state) {
        boardState = new Player[state.getWidth()][state.getHeight()];
        this.turn = state.getTurn();

        for (int i = 0; i < state.getWidth(); i++) {
           boardState[i] = state.boardState[i].clone();
        }
    }

    public void switchTurn() {
        turn = (turn == Player.MAX) ? Player.MIN : Player.MAX;
    }

    public int getWidth() {
        return boardState.length;
    }

    public int getHeight() {
        return boardState[0].length;
    }

    public Player get(int x, int y) {
        return boardState[x][y];
    }

    public boolean move(Move move) {
        return false;
    }

    public boolean isMoveValid(Move move) {
        return false;
    }

    public Player getTurn() {
        return turn;
    }

    @Override
    public GameState clone() {
        return new GameState(this);
    }
}
