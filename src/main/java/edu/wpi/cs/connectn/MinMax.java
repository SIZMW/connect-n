package edu.wpi.cs.connectn;

import java.util.function.Function;

/**
 * This class test the {@link GameTreeGenerator} class.
 *
 * @author Daniel Beckwith
 */
public class MinMax {

    private static MinMax instance = new MinMax();

    public static MinMax getInstance() {
        return instance;
    }

    /**
     * Gets the next best move based on the given heuristic
     *
     * @param state     the current game state
     * @param depth     must be > 0
     * @param heuristic the heuristic function to score states by
     * @return the best move to take for the current player
     */
    public Move getNextBestMove(GameState state, int depth, Function<GameState, Double> heuristic) {
        if (depth <= 0) throw new IllegalArgumentException("depth must be positive");
        MinMaxNode subNode = new MinMaxNode(state);
        setScore(subNode, depth, heuristic);
        return subNode.getMove();
    }

    private void setScore(MinMaxNode node, int depth, Function<GameState, Double> heuristic) {
        if (depth != 0) {
            boolean max = node.getState().getTurn() == Player.MAX;
            if (max) {
                node.setScore(Double.NEGATIVE_INFINITY);
            }
            else {
                node.setScore(Double.POSITIVE_INFINITY);
            }
            for (Move move : GameTreeGenerator.getInstance().generateValidMoves(node.getState())) {
                GameState newState = node.getState().clone();
                newState.move(move);

                MinMaxNode subNode = new MinMaxNode(newState);
                setScore(subNode, depth - 1, heuristic);

                if ((max && subNode.getScore() > node.getScore()) || (!max && subNode.getScore() < node.getScore())) {
                    node.setScore(subNode.getScore());
                    node.setMove(move);
                }
            }
        }

        if (depth == 0 || node.getMove() == null) {
            // either this is the end of the tree's depth,
            // or there were no valid moves,
            // so just set the score to the heuristic
            node.setScore(heuristic.apply(node.getState()));
        }
    }

    private static class MinMaxNode {

        private final GameState state;
        private double score;
        private Move move;

        public MinMaxNode(GameState state) {
            this.state = state;
        }

        public double getScore() {
            return score;
        }

        public void setScore(double score) {
            this.score = score;
        }

        public Move getMove() {
            return move;
        }

        public void setMove(Move move) {
            this.move = move;
        }

        public GameState getState() {
            return state;
        }
    }
}
