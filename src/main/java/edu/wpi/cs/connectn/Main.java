package edu.wpi.cs.connectn;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.BiFunction;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * Main class to run the connect-n AI.
 *
 * @author Aditya Nivarthi
 */
public class Main {

    // Termination constants
    public static String WIN = "win";
    public static String DRAW = "draw";
    public static String LOSE = "lose";
    public static String PLAYER_2 = "player2:";

    // Logger
    private static Logger logger = Logger.getGlobal();

    // Current game information
    private static GameState currentGameState;
    private static int playerNumber = -1;
    private static int height = -1;
    private static int width = -1;
    private static int connectLength = -1;
    private static int turn = -1;
    private static int timelimit = -1;

    /**
     * Defines the potential heuristic weighting functions to use.
     */
    private static List<BiFunction<Integer, Integer, Double>> heuristicFunctions = Arrays.asList(
            (l, n) -> 1d,
            (l, n) -> (double) l,
            (l, n) -> l * l / (double) n,
            (l, n) -> (double) l * l * n
    );

    /**
     * Sets up the file logging for debugging purposes.
     */
    private static void setUpLogger() {
        try {
            LogManager.getLogManager().reset();
            logger.setUseParentHandlers(false);

            // Log file
            FileHandler loggingFileHandler = new FileHandler("./log" + playerNumber + ".log");
            loggingFileHandler.setLevel(Level.ALL);

            // Console logging
            ConsoleHandler loggingConsoleHandler = new ConsoleHandler();
            loggingConsoleHandler.setLevel(Level.OFF);

            // Remove old handlers
            Handler[] handlers = Logger.getGlobal().getHandlers();

            for (Handler handler : handlers) {
                Logger.getGlobal().removeHandler(handler);
            }

            // Add logging handlers
            logger.addHandler(loggingFileHandler);
            logger.addHandler(loggingConsoleHandler);

            SimpleFormatter simpleFormatter = new SimpleFormatter();
            loggingFileHandler.setFormatter(simpleFormatter);

            logger.setLevel(Level.ALL);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sets up the heuristic weighting functions for the min max heuristic.
     *
     * @param args The command line arguments.
     */
    private static void setUpHeuristics(String args[]) {
        int heuristicOption = 0;
        if (args.length > 0) {
            try {
                heuristicOption = Integer.parseInt(args[0]);
            }
            catch (NumberFormatException ignored) {}

            if (heuristicOption >= heuristicFunctions.size()) {
                heuristicOption = 0;
            }
        }

        Heuristic.getInstance().setWeightFunction(heuristicFunctions.get(heuristicOption));
    }

    /**
     * Initializes the program and reads all the input data from the referee.
     */
    public static void init() {
        Random rand = new Random();
        String name = Config.PLAYER_NAME + rand.nextInt();

        // Send this player's name to the referee
        Communicator.getInstance().sendCmd(name);

        // Gets both players' names from the referee
        String playerNames = Communicator.getInstance().getCmd();

        // Gets the configuration of the game from the referee
        String config = Communicator.getInstance().getCmd();
        String[] options = config.split(" ");

        if (options.length < 5) {
            return;
        }

        // Parse confiiguration options
        height = Integer.parseInt(options[0]);
        width = Integer.parseInt(options[1]);
        connectLength = Integer.parseInt(options[2]);
        turn = Integer.parseInt(options[3]);
        timelimit = Integer.parseInt(options[4]);

        // Get player numbers
        String[] playerNumbers = playerNames.split(PLAYER_2);
        if (playerNumbers.length < 2) {
            return;
        }

        // Determine players by referee numbers
        if (playerNumbers[0].contains(name)) {
            playerNumber = 1;
        }
        else {
            playerNumber = 2;
        }

        // Construct game state
        currentGameState = new GameState(width, height, (turn == playerNumber) ? Player.MAX : Player.MIN, connectLength);
    }

    /**
     * Makes this player's moves until the referee says the game is over or one of the players has won.
     */
    public static void makeMoves() {
        Move move;
        while (true) {
            if (currentGameState.getTurn() == Player.MAX) {
                move = MinMax.getInstance().getNextBestMove(currentGameState, 5, Heuristic.getInstance());
                Communicator.getInstance().sendCmd(Main.getMoveAsCommand(move));
                logger.log(Level.INFO, "Player " + playerNumber + " move: " + move.toString());
            }
            else {
                String command = Communicator.getInstance().getCmd();
                if (Main.gameIsOver(command)) {
                    break;
                }

                String args[] = command.split(" ");
                int col = Integer.parseInt(args[0]);
                int moveType = Integer.parseInt(args[1]);

                move = new Move((moveType == 0) ? MoveType.POP : MoveType.DROP, col);
            }


            currentGameState.move(move);
        }
    }

    /**
     * Checks the referee input for the termination strings.
     *
     * @param input The input passed from the referee.
     * @return true if referee terminated the game; false otherwise
     */
    public static boolean gameIsOver(String input) {
        return (input.equals(WIN) || input.equals(LOSE) || input.equals(DRAW));
    }

    /**
     * Converts the {@link Move} object into a string representation for the referee to read from standard input.
     *
     * @param move The {@link Move} object to convert.
     * @return a String
     */
    public static String getMoveAsCommand(Move move) {
        return move.getColumn() + " " + ((move.getType() == MoveType.POP) ? 0 + "" : 1 + "");
    }

    /**
     * Main function.
     */
    public static void main(String[] args) {
        Main.setUpHeuristics(args);
        Main.init();
        Main.setUpLogger();
        Main.makeMoves();
    }
}
