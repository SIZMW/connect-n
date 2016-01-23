package edu.wpi.cs.connectn;


import java.util.Arrays;

public class BoardFeature {

    public static final int[] VALID_DIRS = { 1, -1, 1, 0, 1, 1, 0, 1 };

    private final BoardPos[] positions;
    private final int length;
    private final int dx;
    private final int dy;

    public BoardFeature(BoardPos... positions) {
        this.positions = positions;
        this.length = positions.length;

        int dx = 0, dy = 0;
        if (positions.length > 1) {
            dx = positions[1].getX() - positions[0].getX();
            dy = positions[1].getY() - positions[0].getY();

            if (dx != 0) dx /= dx < 0 ? -dx : dx;
            if (dy != 0) dy /= dy < 0 ? -dy : dy;

            if (dx < 0 || (dx == 0 && dy < 0)) {
                dx = -dx;
                dy = -dy;
            }
        }

        this.dx = dx;
        this.dy = dy;
    }

    public BoardPos[] getPositions() {
        return positions;
    }

    public int getDx() {
        return dx;
    }

    public int getDy() {
        return dy;
    }

    public int getLength() {
        return length;
    }

    @Override
    public String toString() {
        return "BoardFeature{" +
                "positions=" + Arrays.toString(positions) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BoardFeature)) return false;

        BoardFeature that = (BoardFeature) o;

        if (length != that.length) return false;
        if (dx != that.dx) return false;
        if (dy != that.dy) return false;
        return Arrays.deepEquals(positions, that.positions);

    }

    @Override
    public int hashCode() {
        int result = Arrays.hashCode(positions);
        result = 31 * result + length;
        result = 31 * result + dx;
        result = 31 * result + dy;
        return result;
    }
}
