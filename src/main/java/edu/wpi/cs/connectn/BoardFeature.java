package edu.wpi.cs.connectn;


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

            if (dy < 0 && dx <= 0) dy = -dy;
            if (dx < 0) dx = -dx;
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
}
