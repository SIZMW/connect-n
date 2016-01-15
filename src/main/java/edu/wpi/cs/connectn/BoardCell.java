package edu.wpi.cs.connectn;

public enum BoardCell {
    MIN, MAX, NONE;

    public GameWinner getAsGameWinner() {
        return GameWinner.valueOf(this.toString());
    }
}
