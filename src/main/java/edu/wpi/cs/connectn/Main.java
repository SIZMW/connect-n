package edu.wpi.cs.connectn;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;

public class Main {

    private static List<BiFunction<Integer, Integer, Double>> heuristicFunctions = Arrays.asList(
            (l, n) -> 1d,
            (l, n) -> (double) l,
            (l, n) -> l * l / (double) n,
            (l, n) -> (double) l * l * n
    );

    public static void main(String[] args) {
        int heuristicOption = 0;
        if (args.length > 0) {
            try {
                heuristicOption = Integer.parseInt(args[0]);
            }
            catch (NumberFormatException ignored) {}
            if (heuristicOption >= heuristicFunctions.size()) heuristicOption = 0;
        }

        Heuristic.getInstance().setWeightFunction(heuristicFunctions.get(heuristicOption));

        Communicator.getInstance().sendCmd(Config.PLAYER_NAME);
    }
}
