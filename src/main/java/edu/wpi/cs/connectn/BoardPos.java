package edu.wpi.cs.connectn;

/**
 * Created by djbsn_000 on 1/22/2016.
 */
public class BoardPos {

    private final int x;
    private final int y;
    private final BoardCell cell;

    public BoardPos(int x, int y, BoardCell cell) {
        this.x = x;
        this.y = y;
        this.cell = cell;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public BoardCell getCell() {
        return cell;
    }
}
