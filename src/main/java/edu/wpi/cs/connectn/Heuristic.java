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
        return calculateScoreForPlayer(gameState, Player.MAX) - calculateScoreForPlayer(gameState, Player.MIN);
    }

    private double calculateScoreForPlayer(GameState gameState, Player player) {
        Map<Integer, Set<BoardFeature>> featuresMap = GameStateEvaluator.getInstance().getFeatures(gameState, player);

        int connectLength = gameState.getConnectLength();
        double heuristicValue = 0.0;

        for (int i : featuresMap.keySet()) {
            int featureCount = featuresMap.containsKey(i) ? featuresMap.get(i).size() : 0;
            if ((i == connectLength) && (featureCount > 0)) {
                return Double.MAX_VALUE;
            }

            heuristicValue += this.calculateWeightedFeatureValue(i, featureCount, connectLength);
        }

        return heuristicValue;
    }

    /**
     * Calculates the weight of the board feature based on its length, the number of that board feature and the connect length of the board.
     *
     * @param featureLength The number of pieces that make up this feature.
     * @param count         The number of the board feature found in the game state.
     * @param connectLength The number of pieces in a row needed to win.
     * @return a Double
     */
    protected Double calculateWeightedFeatureValue(int featureLength, int count, int connectLength) {
        return this.weightFunction.apply(featureLength, connectLength) * count;
    }
}
