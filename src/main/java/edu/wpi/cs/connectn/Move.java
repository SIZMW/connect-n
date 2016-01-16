package edu.wpi.cs.connectn;

public class Move {
    private final MoveType type;
    private final int column;

    public Move(MoveType type, int column) {
        this.type = type;
        this.column = column;
    }

    public MoveType getType() {
        return type;
    }

    public int getColumn() {
        return column;
    }

    @Override
    public String toString() {
        return type + " move in column " + column;
    }
}
