package edu.wpi.cs.connectn;

import java.util.Collection;
import java.util.Map;

public class GameStateEvaluator {
    private static GameStateEvaluator instance;

    public GameStateEvaluator getInstance() {
        return instance;
    }

    private GameStateEvaluator() {}

    public Map<Integer, Collection<BoardFeature>> getFeatuures(GameState state) {

    }
}
