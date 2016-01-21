package edu.wpi.cs.connectn;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;

/**
 * Class for communicating with the referee.
 */
public class Communicator {

    private static Communicator instance;

    public static Communicator getInstance() {
        return instance;
    }

    private PrintStream out;
    private BufferedReader in;

    private Communicator() {
        setOut(System.out);
        setIn(System.in);
    }

    /**
     * Sets the stream used to send commands to the referee.
     * @param out the {@link OutputStream} to use as the output
     */
    public void setOut(OutputStream out) {
        this.out = new PrintStream( out);
    }

    /**
     * Sets the stream used to get commands from the referee.
     * @param in the {@link InputStream} to use as the input
     */
    public void setIn(InputStream in) {
        this.in = new BufferedReader(new InputStreamReader(in));
    }

    /**
     * Sends a single command to referee.
     * @param cmd the command to send
     */
    public void sendCmd(String cmd) {
        out.println(cmd);
    }

    /**
     * Retrieves a single command from the referee. This method may block while it waits for the referee to send a command.
     * @return the command from the referee, or {@code null} if there was an error
     */
    public String getCmd() {
        try {
            return in.readLine();
        }
        catch (IOException e) {
            System.err.println("Error getting command: " + e);
            return null;
        }
    }
}
