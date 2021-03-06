package edu.wpi.cs.connectn;

/**
 * This class represents a move in a specified column of the Connect-N board.
 *
 * @author Aditya Nivarthi
 */
public class Move {

    private final MoveType type;
    private final int column;

    /**
     * Creates a {@link Move} instance with the specified type and column.
     *
     * @param type   The type of move.
     * @param column The column where the move is being executed.
     */
    public Move(MoveType type, int column) {
        this.type = type;
        this.column = column;
    }

    /**
     * Returns the move type of this move.
     *
     * @return a {@link MoveType}.
     */
    public MoveType getType() {
        return type;
    }

    /**
     * Returns the column of this move.
     *
     * @return an integer
     */
    public int getColumn() {
        return column;
    }

    /**
     * Returns a string representation of this object.
     *
     * @return a {@link String}
     */
    @Override
    public String toString() {
        return type + " move in column " + column;
    }

    /**
     * Returns whether this object is "equal" to the specified object.
     *
     * @param o The object to compare to this object.
     * @return a boolean
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Move)) return false;

        Move move = (Move) o;

        if (column != move.column) return false;
        return type == move.type;

    }

    /**
     * Returns a hash code value for the object. This method is supported for the benefit of hash tables such as those provided by {@link java.util.HashMap}.
     *
     * @return an integer
     */
    @Override
    public int hashCode() {
        int result = type != null ? type.hashCode() : 0;
        result = 31 * result + column;
        return result;
    }
}
