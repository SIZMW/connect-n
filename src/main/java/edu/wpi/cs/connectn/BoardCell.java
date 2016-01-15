package edu.wpi.cs.connectn;

public enum BoardCell {
    MIN, MAX, NONE;

    public GameWinner getGameWinner() {
        return GameWinner.valueOf(this.toString());
    }
}
