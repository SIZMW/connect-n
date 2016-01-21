package edu.wpi.cs.connectn;

public class Main {

    public static void main(String[] args) {
        Communicator.getInstance().sendCmd(Config.PLAYER_NAME);
    }
}
