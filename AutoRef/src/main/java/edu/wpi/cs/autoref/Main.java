package edu.wpi.cs.autoref;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

/**
 * Created by djbsn_000 on 1/22/2016.
 */
public class Main {

    public static void main(String[] args) {
        String refPath = args[0];
        String p1 = args[1];
        String p2 = args[2];
        int n = Integer.parseInt(args[3]);
        String refConfig = args[4];

        System.out.println(System.getProperty("user.dir"));

        int p1wins = 0;
        int p2wins = 0;
        int ties = 0;
        try {
            for (int i = 0; i < n; i++) {
                System.out.println("=========== GAME " + (i + 1) + "/" + n + " ===========");
                Process ref = Runtime.getRuntime().exec("java -jar \"" + refPath + "\" \"" + p1 + "\" \"" + p2 + "\" " + refConfig);
                InputStream err = ref.getErrorStream();
                InputStream in = ref.getInputStream();
                Scanner scanner = new Scanner(in);
                while (scanner.hasNextLine() && ref.isAlive()) {
                    // TODO: look for a win or a tie
                    System.out.println(scanner.nextLine());
                }
                int exit = ref.waitFor();
                if (exit != 0) {
                    System.out.println("The ref errored:");
                    // TODO: print error from ref
//                    int c;
//                    while ((c = err.read()) != -1) {
//                        System.out.write(c);
//                    }
                    break;
                }
            }
        }
        catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("=================================");
        System.out.format("Player 1 won %,d/%,d games (%03.2f%%)%n", p1wins, n, 100d * p1wins / n);
        System.out.format("Player 2 won %,d/%,d games (%03.2f%%)%n", p2wins, n, 100d * p2wins / n);
        System.out.format("%,d/%,d games were ties (%03.2f%%)%n", ties, n, 100d * ties / n);
    }
}
