package edu.wpi.cs.connectn;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * This class evaluates the specified {@link GameState}s by the {@link BoardFeature}s they have.
 *
 * @author Daniel Beckwith
 */
public class GameStateEvaluator {

    private static GameStateEvaluator instance = new GameStateEvaluator();

    /**
     * Creates a GameStateEvaluator instance.
     */
    private GameStateEvaluator() {}

    /**
     * Returns the GameStateEvaluator instance.
     *
     * @return
     */
    public static GameStateEvaluator getInstance() {
        return instance;
    }

    /**
     * Returns all the {@link BoardFeature}s of the specified {@link GameState}.
     *
     * @param state The game state to evaluate for features.
     * @return a Map<Integer, Set<BoardFeature>> of feature length mapped to a collection of all the features of that lnegth.
     */
    public Map<Integer, Set<BoardFeature>> getFeatures(GameState state, Player player) {
        Map<Integer, Set<BoardFeature>> features = new HashMap<>();
        int n = state.getConnectLength();

        for (int dir = 0; dir < BoardFeature.VALID_DIRS.length; dir += 2) {
            int dx = BoardFeature.VALID_DIRS[dir];
            int dy = BoardFeature.VALID_DIRS[dir + 1];

            for (int x = 0; x < state.getWidth() - (dx == 0 ? 0 : dx * n - 1); x++) {
                yLoop:
                for (int y = (dy < 0 ? n - 1 : 0); y < state.getHeight() - (dy <= 0 ? 0 : dy * n - 1); y++) {
                    BoardPos[] positions = new BoardPos[n];
                    for (int i = 0; i < n; i++) {
                        int xx = x + dx * i;
                        int yy = y + dy * i;
                        BoardCell cell = state.get(xx, yy);
                        if (cell == player.getOpponent().getAsBoardCell()) {
                            continue yLoop;
                        }
                        positions[i] = new BoardPos(xx, yy, cell);
                    }
                    BoardFeature feature = new BoardFeature(positions);
                    if (feature.getLength() > 0) {
                        features.computeIfAbsent(feature.getLength(), integer -> new HashSet<>()).add(feature);
                    }
                }
            }
        }

//        if (n >= 1) {
//            // generate all features of length one (individual pieces)
//            features.put(1, new HashSet<>());
//            for (int x = 0; x < state.getWidth(); x++) {
//                for (int y = 0; y < state.getHeight(); y++) {
//                    BoardCell cell = state.get(x, y);
//                    if (cell.equals(BoardCell.MAX)) {
//                        features.get(1).add(new BoardFeature(new BoardPos(x, y, cell)));
//                    }
//                }
//            }
//
//            if (n >= 2) {
//                // generate all features of length two from the individual pieces
//                features.put(2, new HashSet<>());
//                for (BoardFeature singleFeature : features.get(1)) {
//                    for (int i = 0; i < BoardFeature.VALID_DIRS.length; i += 2) {
//                        BoardPos startPos = singleFeature.getPositions()[0];
//                        int x = startPos.getX() + BoardFeature.VALID_DIRS[i];
//                        int y = startPos.getY() + BoardFeature.VALID_DIRS[i + 1];
//                        BoardCell nextCell = state.get(x, y);
//                        if (nextCell.equals(BoardCell.MAX)) {
//                            features.get(2).add(new BoardFeature(startPos, new BoardPos(x, y, nextCell)));
//                        }
//                    }
//                }
//
//                for (int currLen = 3; currLen <= n; currLen++) {
//                    features.put(currLen, new HashSet<>());
//                    for (BoardFeature feature : features.get(currLen - 1)) {
//                        BoardPos lineEnd = feature.getPositions()[feature.getLength() - 1];
//                        int x = lineEnd.getX() + feature.getDx();
//                        int y = lineEnd.getY() + feature.getDy();
//                        BoardCell nextCell = state.get(x, y);
//                        if (nextCell.equals(BoardCell.MAX)) {
//                            BoardPos[] positions = new BoardPos[feature.getLength() + 1];
//                            System.arraycopy(feature.getPositions(), 0, positions, 0, feature.getLength());
//                            positions[positions.length - 1] = new BoardPos(x, y, nextCell);
//                            features.get(currLen).add(new BoardFeature(positions));
//                        }
//                    }
//                }
//            }
//        }

        return features;
    }
}
