package edu.wpi.cs.connectn;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by djbsn_000 on 1/22/2016.
 */
public class BoardFeatureTest {

    @Test
    public void testGetDirection() throws Exception {
        BoardFeature f;

        f = new BoardFeature(
                new BoardPos(0, 0, BoardCell.MAX)
        );
        Assert.assertEquals(0, f.getDx());
        Assert.assertEquals(0, f.getDy());


        f = new BoardFeature(
                new BoardPos(0, 0, BoardCell.MAX),
                new BoardPos(0, 1, BoardCell.MAX),
                new BoardPos(0, 2, BoardCell.MAX)
        );
        Assert.assertEquals(0, f.getDx());
        Assert.assertEquals(1, f.getDy());

        f = new BoardFeature(
                new BoardPos(0, 0, BoardCell.MAX),
                new BoardPos(1, 1, BoardCell.MAX),
                new BoardPos(2, 2, BoardCell.MAX)
        );
        Assert.assertEquals(1, f.getDx());
        Assert.assertEquals(1, f.getDy());

        f = new BoardFeature(
                new BoardPos(0, 0, BoardCell.MAX),
                new BoardPos(1, 0, BoardCell.MAX),
                new BoardPos(2, 0, BoardCell.MAX)
        );
        Assert.assertEquals(1, f.getDx());
        Assert.assertEquals(0, f.getDy());

        f = new BoardFeature(
                new BoardPos(0, 2, BoardCell.MAX),
                new BoardPos(1, 1, BoardCell.MAX),
                new BoardPos(2, 0, BoardCell.MAX)
        );
        Assert.assertEquals(1, f.getDx());
        Assert.assertEquals(-1, f.getDy());


        f = new BoardFeature(
                new BoardPos(0, 2, BoardCell.MAX),
                new BoardPos(0, 1, BoardCell.MAX),
                new BoardPos(0, 0, BoardCell.MAX)
        );
        Assert.assertEquals(0, f.getDx());
        Assert.assertEquals(1, f.getDy());

        f = new BoardFeature(
                new BoardPos(2, 2, BoardCell.MAX),
                new BoardPos(1, 1, BoardCell.MAX),
                new BoardPos(0, 0, BoardCell.MAX)
        );
        Assert.assertEquals(1, f.getDx());
        Assert.assertEquals(1, f.getDy());

        f = new BoardFeature(
                new BoardPos(2, 0, BoardCell.MAX),
                new BoardPos(1, 0, BoardCell.MAX),
                new BoardPos(0, 0, BoardCell.MAX)
        );
        Assert.assertEquals(1, f.getDx());
        Assert.assertEquals(0, f.getDy());

        f = new BoardFeature(
                new BoardPos(2, 0, BoardCell.MAX),
                new BoardPos(1, 1, BoardCell.MAX),
                new BoardPos(0, 2, BoardCell.MAX)
        );
        Assert.assertEquals(1, f.getDx());
        Assert.assertEquals(-1, f.getDy());
    }
}