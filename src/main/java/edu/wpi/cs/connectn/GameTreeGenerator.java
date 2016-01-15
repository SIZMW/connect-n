package edu.wpi.cs.connectn;

import java.util.Iterator;

public class GameTreeGenerator {

    private static GameTreeGenerator instance = new GameTreeGenerator();

    public static GameTreeGenerator getInstance() {
        return instance;
    }

    /**
     * Generate all {@link GameState}s that could result from legal {@link Move}s on the given {@link GameState}.
     *
     * @param root the state to generate new states off of
     * @return an {@link Iterator} of all legal resulting {@link GameState}s from the root
     */
    public Iterator<GameState> generateChildren(GameState root) {
        return new GameStateChildrenIterator(root);
    }

    private static class GameStateChildrenIterator implements Iterator<GameState> {

        private final GameState root;
        private int currMoveType;
        private int currColumn;
        private Move currMove;

        public GameStateChildrenIterator(GameState root) {
            this.root = root;
            currMoveType = 0;
            currColumn = 0;
            currMove = null;
        }

        private void nextMove() {
            do {
                if (!hasNext()) break;
                currMove = new Move(MoveType.values()[currMoveType], currColumn++);
                if (currColumn >= root.getWidth()) {
                    currColumn = 0;
                    currMoveType++;
                }
            }
            while (!root.isMoveValid(currMove));
        }

        @Override
        public boolean hasNext() {
            return currMoveType < MoveType.values().length && currColumn < root.getWidth();
        }

        @Override
        public GameState next() {
            nextMove();
            if (!hasNext()) throw new IllegalStateException();
            GameState newState = root.clone();
            newState.move(currMove);
            return newState;
        }
    }
}