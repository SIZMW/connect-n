package edu.wpi.cs.connectn;

public enum Player {
    MIN, MAX;

    public BoardCell getBoardCell() {
        return BoardCell.valueOf(this.toString());
    }

    public GameWinner getGameWinner() {
        return GameWinner.valueOf(this.toString());
    }
}
