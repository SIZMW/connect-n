package edu.wpi.cs.connectn;

/**
 * This enumerated type represents the players that can be on the game board at any time in the game.
 *
 * @author Aditya Nivarthi
 */
public enum Player {
    MIN, MAX;

    /**
     * Returns this Player in the form of a {@Link BoardCell}.
     *
     * @return a {@Link BoardCell}
     */
    public BoardCell getAsBoardCell() {
        return BoardCell.valueOf(this.toString());
    }

    /**
     * Returns this Player in the form of a {@Link GameWinner}.
     *
     * @return a {@Link GameWinner}
     */
    public GameWinner getAsGameWinner() {
        return GameWinner.valueOf(this.toString());
    }
}
