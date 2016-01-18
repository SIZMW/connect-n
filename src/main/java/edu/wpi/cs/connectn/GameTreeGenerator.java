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
    public Iterable<GameState> generateChildren(GameState root) {
        return () -> new GameStateChildrenIterator(root);
    }

    /**
     * This class is used to generate an iterator of all the direct children game states from the parent game state.
     */
    private static class GameStateChildrenIterator implements Iterator<GameState> {

        private final GameState root;
        private int currMoveType;
        private int currColumn;
        private Move currMove;

        /**
         * Creates a {@link GameStateChildrenIterator} instance with the specified root {@link GameState}.
         *
         * @param root The parent {@link GameState} to begin generation from.
         */
        public GameStateChildrenIterator(GameState root) {
            this.root = root;
            currMoveType = 0;
            currColumn = 0;
            currMove = null;
            nextMove();
        }

        /**
         * Generates the next {@link Move} to apply to the children {@link GameState}s.
         */
        private void nextMove() {
            do {
                if (currMoveType >= MoveType.values().length || currColumn >= root.getWidth()) {
                    currMove = null;
                    break;
                }

                currMove = new Move(MoveType.values()[currMoveType], currColumn++);
                if (currColumn >= root.getWidth()) {
                    currColumn = 0;
                    currMoveType++;
                }
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
        public GameState next() {
            if (!hasNext()) {
                throw new IllegalStateException();
            }

            GameState newState = root.clone();
            newState.move(currMove);
            nextMove();
            return newState;
        }
    }
}
