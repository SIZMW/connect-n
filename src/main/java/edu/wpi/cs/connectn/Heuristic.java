package edu.wpi.cs.connectn;

import java.util.function.Function;

/**
 * This class defines the heuristic used to quantify how good a board state is.
 *
 * @author Daniel Beckwith
 */
public class Heuristic implements Function<GameState, Double> {

    private static Heuristic instance = new Heuristic();

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
     * Returns the heuristic value of the specified {@link GameState}.
     *
     * @param gameState
     * @return
     */
    @Override
    public Double apply(GameState gameState) {
        // TODO: heuristic
        return 0d;
    }
}
