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

        if (w < connectLength && h < connectLength) throw new IllegalArgumentException("Board is too small");

        for (int i = 0; i < w; i++) {
            Arrays.fill(boardState[i], BoardCell.NONE);
        }
    }

    GameState(Player turn, int connectLength, BoardCell[][] boardState) {
        this.turn = turn;
        this.connectLength = connectLength;
        this.boardState = boardState;

        if (getWidth() < connectLength && getHeight() < connectLength) {
            throw new IllegalArgumentException("Board is too small");
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
        switch (move.getType()) {
            case DROP:
                int spot = 0;
                for (int i = getHeight() - 1; i >= 0; i--) {
                    if (boardState[move.getColumn()][i] == BoardCell.NONE) {
                        spot = i;
                        break;
                    }
                }
                boardState[move.getColumn()][spot] = this.turn.getAsBoardCell();
                break;
            case POP:
                System.arraycopy(boardState[move.getColumn()], 0, boardState[move.getColumn()], 1, getHeight() - 1);
                boardState[move.getColumn()][0] = BoardCell.NONE;
                break;
        }

        this.turn = getOpponent(this.turn);
    }

    public boolean isMoveValid(Move move) {
        if (move.getColumn() < 0 || move.getColumn() >= getWidth()) return false;

        switch (move.getType()) {
            case DROP:
                return boardState[move.getColumn()][0] == BoardCell.NONE;
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
                if (this.checkDirection(i, j, this.turn, 1, 0) ||
                        this.checkDirection(i, j, this.turn, 0, 1) ||
                        this.checkDirection(i, j, this.turn, 1, 1) ||
                        this.checkDirection(i, j, this.turn, 1, -1)) {
                    player = true;
                }

                if (this.checkDirection(i, j, otherPlayer, 1, 0) ||
                        this.checkDirection(i, j, otherPlayer, 0, 1) ||
                        this.checkDirection(i, j, otherPlayer, 1, 1) ||
                        this.checkDirection(i, j, otherPlayer, 1, -1)) {
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

    private boolean checkDirection(int x, int y, Player p, int dx, int dy) {
        int consecutive = 0;
        while (x >= 0 && x < getWidth() && y >= 0 && y < getHeight() && boardState[x][y] == p.getAsBoardCell()) {
            consecutive++;
            x += dx;
            y += dy;
        }
        return consecutive >= connectLength;
    }

    public Player getTurn() {
        return turn;
    }

    @Override
    public boolean equals(Object obj) {
        return obj != null && (obj == this || obj instanceof GameState && this.equals((GameState) obj));
    }

    public boolean equals(GameState other) {
        return other != null && (other == this ||
                this.connectLength == other.connectLength &&
                        this.turn == other.turn &&
                        Arrays.deepEquals(this.boardState, other.boardState));
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append("Turn: ").append(this.turn).append(" ");
        builder.append("Connect Length: ").append(this.connectLength).append("\n");
        for (int j = 0; j < this.getHeight(); j++) {
            for (int i = 0; i < this.getWidth(); i++) {
                builder.append(boardState[i][j].getSymbol()).append(" ");
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
