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

    @Override
    public String toString() {
        return "BoardPos{" +
                "x=" + x +
                ", y=" + y +
                ", cell=" + cell +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BoardPos)) return false;

        BoardPos boardPos = (BoardPos) o;

        if (x != boardPos.x) return false;
        if (y != boardPos.y) return false;
        return cell == boardPos.cell;

    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        result = 31 * result + (cell != null ? cell.hashCode() : 0);
        return result;
    }
}
