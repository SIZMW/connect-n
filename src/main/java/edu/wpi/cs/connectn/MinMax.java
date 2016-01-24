package edu.wpi.cs.connectn;

import java.util.function.Function;
import java.util.logging.Logger;

/**
 * This class test the {@link GameTreeGenerator} class.
 *
 * @author Daniel Beckwith
 */
public class MinMax {

    private static Logger logger = Logger.getGlobal();

    private static MinMax instance = new MinMax();
    private int moveNumber = 0;

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
     * Returns the next best move for the player to do in the specified {@link GameState}. Specifies the time for how long the iterative deepening can run before a move must be made.
     *
     * @param state     The current game state.
     * @param timeLimit The number of seconds before a move must be made.
     * @param heuristic The heuristic function to use for scoring states.
     * @return The best move to take for the current player.
     */
    public Move getNextBestMove(GameState state, int timeLimit, Function<GameState, Double> heuristic) {
        logger.info("Move " + moveNumber++);
        long moveEndTime = System.currentTimeMillis() + timeLimit * 1000;
        Move bestMove = null;
        // TODO: multithread different depths
        depthLoop:
        for (int currDepth = 1; ; currDepth++) {
            logger.info("minmax to depth " + currDepth);
            FindMove moveFinder = new FindMove(state.clone(), currDepth, heuristic);
            moveFinder.start();
            while (true) {
                try {
                    Thread.sleep(1);
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (System.currentTimeMillis() > moveEndTime - 100) {
                    logger.info("out of time!");
                    moveFinder.stop();
                    break depthLoop;
                }
                if (!moveFinder.isAlive()) {
                    logger.info("finished depth " + currDepth);
                    bestMove = moveFinder.getBestMove();
                    break;
                }
            }
        }
        return bestMove;
    }

    /**
     * This class is for running a thread to find the best move. It defines to what depth to search for a move for iterative deepening.
     */
    private class FindMove extends Thread {

        private final GameState state;
        private final int depth;
        private final Function<GameState, Double> heuristic;
        private Move bestMove;

        /**
         * Creates a FindMove instance with a game state, depth and heuristic function.
         *
         * @param state     The current game state.
         * @param depth     The depth to recurse to to find a move.
         * @param heuristic The heuristic function to use for scoring states.
         */
        public FindMove(GameState state, int depth, Function<GameState, Double> heuristic) {
            this.state = state;
            this.depth = depth;
            this.heuristic = heuristic;
            bestMove = null;
        }

        /**
         * Returns the next best move to perform.
         *
         * @return a {@link Move}
         */
        public Move getBestMove() {
            return bestMove;
        }

        /**
         * Runs this thread.
         */
        @Override
        public void run() {
            bestMove = findMoveAtDepth(state, depth, heuristic);
        }
    }

    /**
     * Gets the next best move based on the given heuristic
     *
     * @param state     The current game state
     * @param depth     Depth of the game state tree to search down to. This must be > 0.
     * @param heuristic The heuristic function to use for scoring states.
     * @return The best move to take for the current player.
     */
    private Move findMoveAtDepth(GameState state, int depth, Function<GameState, Double> heuristic) {
        if (depth <= 0) throw new IllegalArgumentException("depth must be positive");
        MinMaxNode subNode = new MinMaxNode(state);
        setScore(subNode, depth, heuristic, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);
        return subNode.getMove();
    }

    /**
     * Sets the score using the specified heuristic value, starting with the specified {@link MinMaxNode}. Uses Alpha-Beta pruning to simplify search for best move.
     *
     * @param node      The node to start generating future game states from.
     * @param depth     The depth to search down the game state tree.
     * @param heuristic The {@link Function} used to determine the states' scores.
     * @param alpha     The alpha value for alpha-beta pruning.
     * @param beta      The beta value for alpha-beta pruning.
     */
    private void setScore(MinMaxNode node, int depth, Function<GameState, Double> heuristic, double alpha, double beta) {
//        logger.info("[" + depth + "] Finding score for " + node.getState());
        if (depth != 0) {
            // Determine which player we are ranking states by
            boolean max = node.getState().getTurn() == Player.MAX;

            node.setScore((max) ? Double.NEGATIVE_INFINITY : Double.POSITIVE_INFINITY);

//            logger.info("[" + depth + "] " + (max ? "max" : "min"));

            // For each move we can do on this state, find the children game states that can be reached
            for (Move move : GameTreeGenerator.getInstance().generateValidMoves(node.getState())) {
                GameState newState = node.getState().clone();
                newState.move(move);
                
                MinMaxNode subNode = new MinMaxNode(newState);
                setScore(subNode, depth - 1, heuristic, alpha, beta);

                // Get the child node's score and compare it to this node's score
                if (max ? subNode.getScore() > node.getScore() : subNode.getScore() < node.getScore()) {
//                    logger.info("[" + depth + "] found a better move: " + move + " (" + subNode.getScore() + ")");
                    node.setScore(subNode.getScore());
                    node.setMove(move);

                    if (max ? node.getScore() <= alpha : node.getScore() >= beta) {
                        break;
                    }

                    if (max) {
                        alpha = (node.getScore() >= alpha) ? node.getScore() : alpha;
                    }
                    else {
                        beta = (node.getScore() <= beta) ? node.getScore() : beta;
                    }
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
         * Returns the move that will result in a state with this node's score.
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
