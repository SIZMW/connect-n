package edu.wpi.cs.connectn;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class GameStateEvaluator {

    private static GameStateEvaluator instance;

    public static GameStateEvaluator getInstance() {
        return instance;
    }

    private GameStateEvaluator() {}

    public Map<Integer, Collection<BoardFeature>> getFeatures(GameState state) {
        Map<Integer, Collection<BoardFeature>> features = new HashMap<>();
        int n = state.getConnectLength();

        if (n >= 1) {
            // generate all features of length one (individual pieces)
            features.put(1, new ArrayList<>());
            for (int x = 0; x < state.getWidth(); x++) {
                for (int y = 0; y < state.getHeight(); y++) {
                    BoardCell cell = state.get(x, y);
                    if (cell.equals(BoardCell.MAX)) {
                        features.get(1).add(new BoardFeature(new BoardPos(x, y, cell)));
                    }
                }
            }

            if (n >= 2) {
                // generate all features of length two from the individual pieces
                features.put(2, new ArrayList<>());
                for (BoardFeature singleFeature : features.get(1)) {
                    for (int i = 0; i < BoardFeature.VALID_DIRS.length / 2; i++) {
                        BoardPos startPos = singleFeature.getPositions()[0];
                        int x = startPos.getX() + BoardFeature.VALID_DIRS[i];
                        int y = startPos.getY() + BoardFeature.VALID_DIRS[i + 1];
                        BoardCell nextCell = state.get(x, y);
                        if (nextCell.equals(BoardCell.MAX)) {
                            features.get(2).add(new BoardFeature(startPos, new BoardPos(x, y, nextCell)));
                        }
                    }
                }

                for (int currLen = 3; currLen <= n; currLen++) {
                    features.put(3, new ArrayList<>());
                    for (BoardFeature feature : features.get(currLen - 1)) {
                        BoardPos lineEnd = feature.getPositions()[feature.getLength() - 1];
                        int x = lineEnd.getX() + feature.getDx();
                        int y = lineEnd.getY() + feature.getDy();
                        BoardCell nextCell = state.get(x, y);
                        if (nextCell.equals(BoardCell.MAX)) {
                            BoardPos[] positions = new BoardPos[feature.getLength() + 1];
                            System.arraycopy(feature.getPositions(), 0, positions, 0, feature.getLength());
                            positions[positions.length - 1] = new BoardPos(x, y, nextCell);
                            features.get(currLen).add(new BoardFeature(positions));
                        }
                    }
                }
            }
        }

        return features;
    }
}
