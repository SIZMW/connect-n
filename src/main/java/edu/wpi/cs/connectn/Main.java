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

public class Main {

    public static String WIN = "win";
    public static String DRAW = "draw";
    public static String LOSE = "lose";

    public static String PLAYER_2 = "player2:";
    private static Logger logger = Logger.getGlobal();
    private static FileHandler loggingFileHandler;
    private static ConsoleHandler loggingConsoleHandler;
    private static SimpleFormatter simpleFormatter;

    private static GameState currentGameState;
    private static int playerNumber = -1;

    private static List<BiFunction<Integer, Integer, Double>> heuristicFunctions = Arrays.asList(
            (l, n) -> 1d,
            (l, n) -> (double) l,
            (l, n) -> l * l / (double) n,
            (l, n) -> (double) l * l * n
    );

    private static void setUpLogger() {
        try {
            LogManager.getLogManager().reset();
            logger.setUseParentHandlers(false);

            loggingFileHandler = new FileHandler("./log.log");
            loggingFileHandler.setLevel(Level.ALL);
            loggingConsoleHandler = new ConsoleHandler();
            loggingConsoleHandler.setLevel(Level.OFF);

            // Remove old handlers
            Handler[] handlers = Logger.getGlobal().getHandlers();

            for (Handler handler : handlers) {
                Logger.getGlobal().removeHandler(handler);
            }

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

    private static void setUpHeuristics(String args[]) {
        int heuristicOption = 0;
        if (args.length > 0) {
            try {
                heuristicOption = Integer.parseInt(args[0]);
            }
            catch (NumberFormatException ignored) {}
            if (heuristicOption >= heuristicFunctions.size()) heuristicOption = 0;
        }

        Heuristic.getInstance().setWeightFunction(heuristicFunctions.get(heuristicOption));
    }

    public static void init() {
        Random rand = new Random();

        String name = Config.PLAYER_NAME + rand.nextInt();
        Communicator.getInstance().sendCmd(name);
        String playerNames = Communicator.getInstance().getCmd();
        String config = Communicator.getInstance().getCmd();

        String[] options = config.split(" ");

        if (options.length < 5) {
            return;
        }

        int height = Integer.parseInt(options[0]);
        int width = Integer.parseInt(options[1]);
        int connectLength = Integer.parseInt(options[2]);
        int turn = Integer.parseInt(options[3]);
        int timelimit = Integer.parseInt(options[4]);

        String[] playerNumbers = playerNames.split(PLAYER_2);
        if (playerNumbers.length < 2) {
            return;
        }

        if (playerNumbers[0].contains(name)) {
            playerNumber = 1;
        }
        else {
            playerNumber = 2;
        }

        if (turn == playerNumber) {
            // make first move
        }
        else {

        }

        // set up current game state
    }

    public static void makeMoves() {
        String command = Communicator.getInstance().getCmd();
        while (!Main.gameIsOver(command)) {
            String args[] = command.split(" ");
            int col = Integer.parseInt(args[0]);
            int moveType = Integer.parseInt(args[1]);

            Move opponentMove = new Move((moveType == 0) ? MoveType.POP : MoveType.DROP, col);
            currentGameState.move(opponentMove);

            // do my move and send it
        }
    }

    public static boolean gameIsOver(String input) {
        if (input.equals(WIN) || input.equals(LOSE) || input.equals(DRAW)) {
            return true;
        }

        return false;
    }

    public static void main(String[] args) {
        Main.setUpLogger();
        Main.setUpHeuristics(args);
        Main.init();
        Main.makeMoves();

        Communicator.getInstance().sendCmd("1 1");
        String nextMove = Communicator.getInstance().getCmd();
        logger.log(Level.INFO, nextMove);
    }
}
