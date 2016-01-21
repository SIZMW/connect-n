package edu.wpi.cs.connectn;

import java.util.function.Function;

/**
 * Created by Daniel on 1/21/2016.
 */
public class Heuristic implements Function<GameState, Double> {

    private static Heuristic instance = new Heuristic();

    public static Heuristic getInstance() {
        return instance;
    }

    private Heuristic() {}

    @Override
    public Double apply(GameState gameState) {
        // TODO: heuristic
        return 0d;
    }
}
