package edu.wpi.cs.connectn;

import java.util.Arrays;

/**
 * This class represents the Connect-N game state at any point throughout the game.
 *
 * @author Aditya Nivarthi
 */
public class GameState {

    private final BoardCell[][] boardState;
    private final int connectLength;
    private Player turn;
    private boolean[] playerHasPopped;

    /**
     * Creates a GameState instance with a width, height, {@link Player} turn and connect length for winning.
     *
     * @param w             The width of the game board.
     * @param h             The height of the game board.
     * @param turn          The player whose turn it is.
     * @param connectLength The number of consecutive pieces needed to win.
     */
    public GameState(int w, int h, Player turn, int connectLength) {
        boardState = new BoardCell[w][h];
        playerHasPopped = new boolean[2];
        this.turn = turn;
        this.connectLength = connectLength;

        // Reject boards that are not at least the width or height of their connect length
        if (w < connectLength && h < connectLength) {
            throw new IllegalArgumentException("Board is too small");
        }

        for (int i = 0; i < w; i++) {
            Arrays.fill(boardState[i], BoardCell.NONE);
        }
    }

    /**
     * Creates a {@link GameState} instance with a {@link Player} turn, connect length for winning, and a board state.
     *
     * @param turn          The player whose turn it is.
     * @param connectLength The number of consecutive pieces needed to win.
     * @param boardState    The board state to set for this game state.
     */
    GameState(Player turn, int connectLength, BoardCell[][] boardState, boolean[] playerHasPopped) {
        this.turn = turn;
        this.connectLength = connectLength;
        this.boardState = boardState;
        this.playerHasPopped = playerHasPopped;

        // Reject boards that are not at least the width or height of their connect length
        if (getWidth() < connectLength && getHeight() < connectLength) {
            throw new IllegalArgumentException("Board is too small");
        }
    }

    /**
     * Returns the connect length for this board that is needed in order to win.
     *
     * @return an integer
     */
    public int getConnectLength() {
        return connectLength;
    }

    /**
     * Creates a {@link GameState} instance with another {@link GameState}.
     *
     * @param state The other {@link GameState} to clone.
     */
    private GameState(GameState state) {
        boardState = new BoardCell[state.getWidth()][state.getHeight()];
        this.turn = state.getTurn();
        this.connectLength = state.connectLength;
        this.playerHasPopped = state.playerHasPopped.clone();

        for (int i = 0; i < state.getWidth(); i++) {
            boardState[i] = state.boardState[i].clone();
        }
    }

    /**
     * Switches the player whose turn it is to play.
     */
    public void switchTurn() {
        turn = this.getOpponent(this.turn);
    }

    /**
     * Returns the opponent of the player whose turn it currently is.
     *
     * @param p The {@link Player} whose turn it currently is.
     * @return a {@link Player}
     */
    private Player getOpponent(Player p) {
        return (p == Player.MAX) ? Player.MIN : Player.MAX;
    }

    /**
     * Returns the width of the game board.
     *
     * @return an integer
     */
    public int getWidth() {
        return boardState.length;
    }

    /**
     * Returns the height of the game board.
     *
     * @return an integer
     */
    public int getHeight() {
        return boardState[0].length;
    }

    /**
     * Returns a specified position on the game board.
     *
     * @param x The horizontal position on the board.
     * @param y The vertical position on the board.
     * @return a {@link BoardCell}
     */
    public BoardCell get(int x, int y) {
        if (x < 0 || x >= getWidth() || y < 0 || y >= getHeight()) {
            return BoardCell.NONE;
        }
        return boardState[x][y];
    }

    /**
     * Applies the specified {@link Move} to this game state.
     *
     * @param move The {@link Move} to be applied to this game state.
     */
    public void move(Move move) {
        // Get the move type
        switch (move.getType()) {
            case DROP:
                // Calculate the lowest position in the column to place the piece
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
                playerHasPopped[this.turn.ordinal()] = true;
                break;
        }

        this.turn = getOpponent(this.turn);
    }

    /**
     * Returns whether the specified {@link Move} is valid to apply to this game state. Must be called before {@link GameState#move(Move)}.
     *
     * @param move The {@link Move} to be applied to this game state.
     * @return a boolean
     */
    public boolean isMoveValid(Move move) {
        if (move.getColumn() < 0 || move.getColumn() >= getWidth()) {
            return false;
        }

        switch (move.getType()) {
            case DROP:
                return boardState[move.getColumn()][0] == BoardCell.NONE;
            case POP:
                return !playerHasPopped[turn.ordinal()] && boardState[move.getColumn()][getHeight() - 1] == turn.getAsBoardCell();
            default:
                return false;
        }
    }

    /**
     * Returns the winner of the game in this {@link GameState}.
     *
     * @return a {@link GameWinner}
     */
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

        // Both have won
        if (player && other) {
            return GameWinner.TIE;
        }

        // Player has won
        if (player) {
            return this.turn.getAsGameWinner();
        }

        // Opponent has won
        if (other) {
            return otherPlayer.getAsGameWinner();
        }

        // No winner
        return GameWinner.NONE;
    }

    /**
     * Checks for consecutive pieces in the specified direction to see if the specified {@link Player} has won.
     *
     * @param x  The horizontal position to start checking from.
     * @param y  The vertical position to start checking from.
     * @param p  The {@link Player} whose pieces to check for.
     * @param dx The horizontal change for checking consecutive pieces.
     * @param dy The vertical change for checking consecutive pieces.
     * @return a boolean
     */
    private boolean checkDirection(int x, int y, Player p, int dx, int dy) {
        int consecutive = 0;
        while (x >= 0 && x < getWidth() && y >= 0 && y < getHeight() && boardState[x][y] == p.getAsBoardCell()) {
            consecutive++;
            x += dx;
            y += dy;
        }

        return consecutive >= connectLength;
    }

    /**
     * Returns the {@link Player} whose turn it is.
     *
     * @return a {@link Player}
     */
    public Player getTurn() {
        return turn;
    }

    /**
     * Indicates whether some object is "equal" to this one.
     *
     * @param obj The object to compare to this object.
     * @return a boolean
     */
    @Override
    public boolean equals(Object obj) {
        return obj != null && (obj == this || obj instanceof GameState && this.equals((GameState) obj));
    }

    /**
     * Indicates whether some {@link GameState} is "equal" to this one.
     *
     * @param other The {@link GameState} to compare to this {@link GameState}.
     * @return a boolean
     */
    public boolean equals(GameState other) {
        return other != null && (other == this ||
                this.connectLength == other.connectLength &&
                        this.turn == other.turn &&
                        Arrays.deepEquals(this.boardState, other.boardState));
    }

    /**
     * Returns the string representation of this object.
     *
     * @return a {@link String}
     */
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

    /**
     * Clone this object into another object.
     *
     * @return a {@link GameState}
     */
    @Override
    public GameState clone() {
        return new GameState(this);
    }
}
