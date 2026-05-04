package fr.school42.avaj.simulator.aicraft;

public class AircraftFactory {
    
    // 1. Instance statique unique
    private static AircraftFactory factory = new AircraftFactory();
    
    // 2. Constructeur privé pour empêcher l'instanciation depuis l'extérieur
    private AircraftFactory() {
    }
    
    // 3. Méthode publique pour récupérer le singleton (même si l'UML ne force pas de pattern particulier, c'est classique)
    private static AircraftFactory getFactory() {
        return factory;
    }

    public static Flyable newAircraft(String p_type, String p_name, Coordinates p_coordinates) {
        return switch (p_type) {
            case "Helicopter" -> new Helicopter(0, p_name, p_coordinates);
            case "JetPlane" -> new JetPlane(0, p_name, p_coordinates);
            case "Balloon" -> new Balloon(0, p_name, p_coordinates);
            default -> null;
        };
    }
}
