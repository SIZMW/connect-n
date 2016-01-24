package edu.wpi.cs.connectn;

import java.util.Map;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * This class defines the heuristic used to quantify how good a board state is.
 *
 * @author Aditya Nivarthi
 */
public class Heuristic implements Function<GameState, Double> {

    private static Heuristic instance = new Heuristic();
    private BiFunction<Integer, Integer, Double> weightFunction;

    /**
     * Creates a Heuristic instance.
     */
    private Heuristic() {}

    /**
     * Returns the heuristic instance.
     *
     * @return a Heuristic
     */
    public static Heuristic getInstance() {
        return instance;
    }

    /**
     * Sets the weight function to use for the heuristic.
     *
     * @param function The function based on the board feature length and the connect length of the game.
     */
    public void setWeightFunction(BiFunction<Integer, Integer, Double> function) {
        this.weightFunction = function;
    }

    /**
     * Returns the heuristic value of the specified {@link GameState}.
     *
     * @param gameState The game state to evaluate with this heuristic function.
     * @return a Double
     */
    @Override
    public Double apply(GameState gameState) {
        double maxScore = calculateScoreForPlayer(gameState, Player.MAX);
        double minScore = calculateScoreForPlayer(gameState, Player.MIN);
        if (maxScore == Double.POSITIVE_INFINITY && minScore == Double.POSITIVE_INFINITY) {
            return 0d;
        }
        return maxScore - minScore;
    }

    /**
     * Calculates the score for the specified {@link Player} on the specified {@link GameState}.
     *
     * @param gameState The current game state.
     * @param player    The player to get the score for.
     * @return a double
     */
    private double calculateScoreForPlayer(GameState gameState, Player player) {
        Map<Integer, Set<BoardFeature>> featuresMap = GameStateEvaluator.getInstance().getFeatures(gameState, player);

        int connectLength = gameState.getConnectLength();
        double heuristicValue = 0.0;

        for (int featureLength : featuresMap.keySet()) {
            int featureCount = featuresMap.get(featureLength).size();
            if ((featureLength == connectLength) && (featureCount > 0)) {
                return Double.POSITIVE_INFINITY;
            }

            heuristicValue += this.weightFunction.apply(featureLength, connectLength) * featureCount;
        }

        return heuristicValue;
    }
}
