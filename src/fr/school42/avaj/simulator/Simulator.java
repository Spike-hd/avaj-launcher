package fr.school42.avaj.simulator;

import fr.school42.avaj.simulator.aircraft.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Simulator {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java Simulator <scenario.txt>");
            System.exit(1);
        }

        String scenarioFile = args[0];

        try {
            SimulatorLogger.openFile();

            java.io.File file = new java.io.File(scenarioFile);
            if (!file.exists() || !file.canRead()) {
                System.out.println("Error: Cannot read file " + scenarioFile);
                System.exit(1);
            }
            
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                
                String line = reader.readLine();
                if (line == null) {
                    System.out.println("Error: File is empty.");
                    System.exit(1);
                }
                
                int simulations = 0;
                try {
                    simulations = Integer.parseInt(line.trim());
                    if (simulations <= 0) {
                        System.out.println("Error: Number of simulations must be a positive integer.");
                        System.exit(1);
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Error: First line of file must be a positive integer.");
                    System.exit(1);
                }

                WeatherTower weatherTower = new WeatherTower();

                int lineNumber = 1;
                while ((line = reader.readLine()) != null) {
                    lineNumber++;
                    if (line.trim().isEmpty()) {
                        System.out.println("Error: Empty line at line " + lineNumber);
                        System.exit(1);
                    }

                    String[] aircraftAttributes = line.trim().split("\\s+"); // On split par les espaces
                    
                    if (aircraftAttributes.length != 5) {
                        System.out.println("Error: Invalid format at line " + lineNumber + ". Expected: TYPE NAME LONG LAT HEIGHT");
                        System.exit(1);
                    }

                    String type = aircraftAttributes[0];
                    if (!type.equals("JetPlane") && !type.equals("Helicopter") && !type.equals("Balloon")) {
                        System.out.println("Error: Invalid aircraft type at line " + lineNumber + ". Expected: JetPlane, Helicopter, or Balloon.");
                        System.exit(1);
                    }

                    String name = aircraftAttributes[1];

                    try {
                        int longitude = Integer.parseInt(aircraftAttributes[2]);
                        int latitude = Integer.parseInt(aircraftAttributes[3]);
                        int height = Integer.parseInt(aircraftAttributes[4]);

                        if (longitude <= 0 || latitude <= 0) {
                            System.out.println("Error: Longitude and latitude must be positive numbers at line " + lineNumber);
                            System.exit(1);
                        }

                        if (height < 0 || height > 100) {
                            System.out.println("Error: Height must be between 0 and 100 at line " + lineNumber);
                            System.exit(1);
                        }

                        // Utilisation de la Factory pour créer l'appareil
                        Coordinates coordinates = new Coordinates(longitude, latitude, height);
                        Flyable aircraft = AircraftFactory.newAircraft(type, name, coordinates);
                        if (aircraft != null) {
                            aircraft.registerTower(weatherTower);
                        }

                    } catch (NumberFormatException e) {
                        System.out.println("Error: Coordinates must be valid integers at line " + lineNumber);
                        System.exit(1);
                    }
                }

                for (int i = 0; i < simulations; i++) {
                    weatherTower.changeWeather();
                }
            }

        } catch (IOException e) {
            System.out.println("Error: Cannot read file " + scenarioFile);
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid number format in file.");
        } finally {
            SimulatorLogger.closeFile();
        }
    }
}
