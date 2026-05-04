package fr.school42.avaj.simulator;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class SimulatorLogger {
    private static final String FILE_NAME = "simulation.txt";
    private static BufferedWriter writer;

    public static void openFile() {
        try {
            // FileWriter sans 'true' crée un nouveau fichier ou l'écrase
            writer = new BufferedWriter(new FileWriter(FILE_NAME));
        } catch (IOException e) {
            System.out.println("Error: Could not open " + FILE_NAME + " for writing, probably error permissions.");
            System.exit(1); 
        }
    }

    public static void log(String message) {
        try {
            if (writer != null) {
                writer.write(message);
                writer.newLine(); 
            }
        } catch (IOException e) {
            System.out.println("Error: Could not write into " + FILE_NAME);
        }
    }

    public static void closeFile() {
        try {
            if (writer != null) {
                writer.close();
            }
        } catch (IOException e) {
            System.out.println("Error: Could not close " + FILE_NAME);
        }
    }
}
