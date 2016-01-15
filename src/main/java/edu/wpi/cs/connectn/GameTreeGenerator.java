package edu.wpi.cs.connectn;

import java.util.Iterator;

public class GameTreeGenerator {
    private static GameTreeGenerator instance = new GameTreeGenerator();

    public static GameTreeGenerator getInstance() {
        return instance;
    }

    public Iterator<GameState> generate(GameState state) {
        return null;
    }
}
