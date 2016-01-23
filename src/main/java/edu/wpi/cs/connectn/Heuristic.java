package edu.wpi.cs.connectn;

import java.util.Map;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * This class defines the heuristic used to quantify how good a board state is.
 *
 * @author Daniel Beckwith
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
        GameStateEvaluator instance = GameStateEvaluator.getInstance();
        Map<Integer, Set<BoardFeature>> featuresMap = instance.getFeatures(gameState);

        int connectLength = gameState.getConnectLength();
        double heuristicValue = 0.0;

        for (Integer i : featuresMap.keySet()) {
            int featureCount = featuresMap.get(i).size();
            if ((i.equals(connectLength)) && (featureCount > 0)) {
                heuristicValue = Double.POSITIVE_INFINITY;
                break;
            }

            heuristicValue += this.calculateWeightedFeatureValue(i, featureCount, connectLength);
        }

        return (gameState.getTurn().equals(Player.MAX)) ? heuristicValue : heuristicValue * -1;
    }

    /**
     * Calculates the weight of the board feature based on its length, the number of that board feature and the connect length of the board.
     *
     * @param featureLength The number of pieces that make up this feature.
     * @param count The number of the board feature found in the game state.
     * @param connectLength The number of pieces in a row needed to win.
     * @return a Double
     */
    protected Double calculateWeightedFeatureValue(int featureLength, int count, int connectLength) {
        return this.weightFunction.apply(featureLength, connectLength) * count;
    }
}