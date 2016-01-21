package edu.wpi.cs.connectn;

import java.util.function.Function;

/**
 * This class test the {@link GameTreeGenerator} class.
 *
 * @author Daniel Beckwith
 */
public class MinMax {

    private static MinMax instance = new MinMax();

    /**
     * Creates a MinMax instance.
     */
    private MinMax() {}

    /**
     * Returns the MinMax instance.
     *
     * @return a MinMax
     */
    public static MinMax getInstance() {
        return instance;
    }

    /**
     * Gets the next best move based on the given heuristic
     *
     * @param state     The current game state
     * @param depth     Depth of the game state tree to search down to. This must be > 0.
     * @param heuristic The heuristic function to use for scoring states.
     * @return The best move to take for the current player.
     */
    public Move getNextBestMove(GameState state, int depth, Function<GameState, Double> heuristic) {
        if (depth <= 0) throw new IllegalArgumentException("depth must be positive");
        MinMaxNode subNode = new MinMaxNode(state);
        setScore(subNode, depth, heuristic);
        return subNode.getMove();
    }

    /**
     * Sets the score using the specified heuristic value, starting with the specified {@link MinMaxNode}.
     *
     * @param node      The node to start generating future game states from.
     * @param depth     The depth to search down the game state tree.
     * @param heuristic The {@link Function} used to determine the states' scores.
     */
    private void setScore(MinMaxNode node, int depth, Function<GameState, Double> heuristic) {
        if (depth != 0) {
            // Determine which player we are ranking states by
            boolean max = node.getState().getTurn() == Player.MAX;
            if (max) {
                node.setScore(Double.NEGATIVE_INFINITY);
            }
            else {
                node.setScore(Double.POSITIVE_INFINITY);
            }

            // For each move we can do on this state, find the children game states that can be reached
            for (Move move : GameTreeGenerator.getInstance().generateValidMoves(node.getState())) {
                GameState newState = node.getState().clone();
                newState.move(move);

                MinMaxNode subNode = new MinMaxNode(newState);
                setScore(subNode, depth - 1, heuristic);

                // Get the child node's score and compare it to this node's score
                if ((max && subNode.getScore() > node.getScore()) || (!max && subNode.getScore() < node.getScore())) {
                    node.setScore(subNode.getScore());
                    node.setMove(move);
                }
            }
        }

        // At the bottom of our generation tree, or there is not next move that can be made.
        if (depth == 0 || node.getMove() == null) {
            // either this is the end of the tree's depth,
            // or there were no valid moves,
            // so just set the score to the heuristic
            node.setScore(heuristic.apply(node.getState()));
        }
    }

    /**
     * This class defines the MinMaxNode used within the game state generation tree.
     */
    private static class MinMaxNode {

        private final GameState state;
        private double score;
        private Move move;

        /**
         * Creates a MinMaxNode instance.
         *
         * @param state The game state generated at this node.
         */
        public MinMaxNode(GameState state) {
            this.state = state;
        }

        /**
         * Returns the score of this game state.
         *
         * @return a double
         */
        public double getScore() {
            return score;
        }

        /**
         * Sets the score of this game state.
         *
         * @param score The score to set on this game state.
         */
        public void setScore(double score) {
            this.score = score;
        }

        /**
         * Returns the move to get to this game state.
         *
         * @return a {@link Move}
         */
        public Move getMove() {
            return move;
        }

        /**
         * Sets the move for this game state to the specified {@link Move}.
         *
         * @param move The move to set for this state.
         */
        public void setMove(Move move) {
            this.move = move;
        }

        /**
         * Returns the game state for this node.
         *
         * @return a {2link GameState}
         */
        public GameState getState() {
            return state;
        }
    }
}
