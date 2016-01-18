package edu.wpi.cs.connectn;

import java.util.function.Function;

/**
 * This class represents configuration values used throughout this project.
 *
 * @author Daniel Beckwith
 */
public final class Config {

    /**
     * The given name to this Connect-N player.
     */
    public static final String PLAYER_NAME = "VIDEO GAME DUNKEYYYYYYYYYYYYYYYYY";

    public static final Function<GameState, Double> HEURISTIC = (state) -> {
        // TODO: heuristic
        return 0d;
    };

    /**
     * Creates a {@link Config} instance.
     */
    private Config() {}
}
