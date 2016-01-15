package edu.wpi.cs.connectn;


import java.util.Arrays;

public class GameState {

    private final BoardCell[][] boardState;
    private final int connectLength;
    private Player turn;

    public GameState(int w, int h, Player turn, int connectLength) {
        boardState = new BoardCell[w][h];
        this.turn = turn;
        this.connectLength = connectLength;

        for (int i = 0; i < w; i++) {
            Arrays.fill(boardState[i], BoardCell.NONE);
        }
    }

    private GameState(GameState state) {
        boardState = new BoardCell[state.getWidth()][state.getHeight()];
        this.turn = state.getTurn();
        this.connectLength = state.connectLength;

        for (int i = 0; i < state.getWidth(); i++) {
            boardState[i] = state.boardState[i].clone();
        }
    }

    public void switchTurn() {
        turn = this.getOpponent(this.turn);
    }

    private Player getOpponent(Player p) {
        return (p == Player.MAX) ? Player.MIN : Player.MAX;
    }

    public int getWidth() {
        return boardState.length;
    }

    public int getHeight() {
        return boardState[0].length;
    }

    public BoardCell get(int x, int y) {
        return boardState[x][y];
    }

    public void move(Move move) {
        int col = move.getColumn();
        int spot = 0;

        for (int i = 0; i < this.getHeight(); i++) {
            if (boardState[col][i] != BoardCell.NONE) {
                spot = i - 1;
                break;
            }
        }

        boardState[col][spot] = this.turn.getAsBoardCell();
    }

    public boolean isMoveValid(Move move) {
        switch (move.getType()) {
            case DROP:
                return boardState[move.getColumn()][0] != BoardCell.NONE;
            case POP:
                return boardState[move.getColumn()][getHeight() - 1] == turn.getAsBoardCell();
            default:
                return false;
        }
    }

    public GameWinner getWinner() {
        Player otherPlayer = this.getOpponent(this.turn);
        boolean player = false;
        boolean other = false;

        for (int i = 0; i < this.getWidth(); i++) {
            for (int j = 0; j < this.getHeight(); j++) {
                if (this.checkVertical(i, j, this.turn) || this.checkHorizontal(i, j, this.turn) || this.checkDiagonals(i, j, this.turn)) {
                    player = true;
                }

                if (this.checkVertical(i, j, otherPlayer) || this.checkHorizontal(i, j, otherPlayer) || this.checkDiagonals(i, j, otherPlayer)) {
                    other = true;
                }
            }
        }

        if (player && other) {
            return GameWinner.TIE;
        }

        if (player) {
            return this.turn.getAsGameWinner();
        }

        if (other) {
            return otherPlayer.getAsGameWinner();
        }

        return GameWinner.NONE;
    }

    private boolean checkVertical(int x, int y, Player p) {
        int consecutive = 0;
        for (int k = 0; k < this.getHeight(); k++) {
            if (this.boardState[x + k][y] == p.getAsBoardCell()) {
                consecutive++;
            }
        }

        return (consecutive >= this.connectLength);
    }

    private boolean checkHorizontal(int x, int y, Player p) {
        int consecutive = 0;
        for (int k = 0; k < this.getWidth(); k++) {
            if (this.boardState[x][y + k] == p.getAsBoardCell()) {
                consecutive++;
            }
        }

        return (consecutive >= this.connectLength);
    }

    private boolean checkDiagonals(int x, int y, Player p) {
        int consecutive = 0;
        int smallerDimension = (this.getHeight() < this.getWidth()) ? this.getHeight() : this.getWidth();
        for (int k = 0; k < smallerDimension; k++) {
            if (this.boardState[x + k][y + k] == p.getAsBoardCell()) {
                consecutive++;
            }
        }

        if (consecutive >= this.connectLength) {
            return true;
        }

        consecutive = 0;
        for (int k = 0; k < smallerDimension; k++) {
            if (this.boardState[x - k][y - k] == p.getAsBoardCell()) {
                consecutive++;
            }
        }

        return (consecutive >= this.connectLength);
    }

    public Player getTurn() {
        return turn;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append("Turn: ").append(this.turn);
        builder.append("Connect Length: ").append(this.connectLength);
        for (int i = 0; i < this.getWidth(); i++) {
            for (int j = 0; j < this.getHeight(); j++) {
                builder.append(boardState[i][j]).append(" ");
            }
            builder.append("\n");
        }
        builder.append("\n");
        return builder.toString();
    }

    @Override
    public GameState clone() {
        return new GameState(this);
    }
}
