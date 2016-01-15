package edu.wpi.cs.connectn;

public enum Player {
    MIN, MAX;

    public BoardCell getAsBoardCell() {
        return BoardCell.valueOf(this.toString());
    }

    public GameWinner getAsGameWinner() {
        return GameWinner.valueOf(this.toString());
    }
}
