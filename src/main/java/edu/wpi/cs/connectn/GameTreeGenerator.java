package edu.wpi.cs.connectn;

import java.util.Iterator;

/**
 * This class generated the game tree and all of the game states succeeding the beginning game state.
 *
 * @author Daniel Beckwith
 */
public class GameTreeGenerator {

    private static GameTreeGenerator instance = new GameTreeGenerator();

    /**
     * Creates a GameTreeGenerator instance.
     */
    private GameTreeGenerator() {}

    /**
     * Returns a GameTreeGenerator instance.
     *
     * @return a GameTreeGenerator
     */
    public static GameTreeGenerator getInstance() {
        return instance;
    }

    /**
     * Generates all {@link GameState}s that could result from legal {@link Move}s on the given {@link GameState}.
     *
     * @param root The state to generate new states off of
     * @return an {@link Iterator} of all legal resulting {@link GameState}s from the root
     */
    public Iterable<Move> generateValidMoves(GameState root) {
        return () -> new ValidMoveIterator(root);
    }

    /**
     * This class is used to generate an iterator of all the direct children game states from the parent game state.
     */
    private static class ValidMoveIterator implements Iterator<Move> {

        private final GameState root;
        private int currMoveType;
        private int currColumn;
        private Move currMove;

        // Move ordering
        private int[] columnOrder;
        private int addVal = 0;
        private int addSign = -1;

        /**
         * Creates a {@link ValidMoveIterator} instance with the specified root {@link GameState}.
         *
         * @param root The parent {@link GameState} to begin generation from.
         */
        public ValidMoveIterator(GameState root) {
            this.root = root;
            currMoveType = 0;
            currColumn = 0;
            currMove = null;

            columnOrder = new int[root.getWidth()];
            int mid = columnOrder.length / 2;

            for (int i = 0; i < columnOrder.length; i++) {
                mid = this.getNextColum(mid);
                columnOrder[i] = mid;
            }

            nextMove();
        }

        /**
         * Generates the next column from the middle column.
         *
         * @param currCol The column to use to generate the next outer column from the middle.
         * @return an integer
         */
        private int getNextColum(int currCol) {
            int next = currCol + addVal;
            addVal *= -1;
            addVal += addSign;
            addSign *= -1;
            return next;
        }

        /**
         * Generates the next {@link Move} to apply to the children {@link GameState}s.
         */
        private void nextMove() {
            do {
//                if (currMoveType >= MoveType.values().length || currColumn >= root.getWidth()) {
//                    currMove = null;
//                    break;
//                }

                // Move ordering to center on middle
                if (currMoveType >= MoveType.values().length || currColumn >= columnOrder.length) {
                    currMove = null;
                    break;
                }

                currMove = new Move(MoveType.values()[currMoveType], columnOrder[currColumn++]);
                if (currColumn >= columnOrder.length) {
                    currColumn = 0;
                    currMoveType++;
                }

//                currMove = new Move(MoveType.values()[currMoveType], currColumn++);
//                if (currColumn >= root.getWidth()) {
//                    currColumn = 0;
//                    currMoveType++;
//                }
            }
            while (!root.isMoveValid(currMove));
        }

        /**
         * Returns whether the iterator has a next {@link GameState}.
         *
         * @return a boolean
         */
        @Override
        public boolean hasNext() {
            return currMove != null;
        }

        /**
         * Returns the next {@link GameState} in the iterator.
         *
         * @return a {@link GameState}
         */
        @Override
        public Move next() {
            if (!hasNext()) {
                throw new IllegalStateException();
            }

            Move move = currMove;
            nextMove();
            return move;
        }
    }
}
