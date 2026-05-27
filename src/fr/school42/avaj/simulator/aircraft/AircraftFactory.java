package fr.school42.avaj.simulator.aircraft;

public class AircraftFactory {
    private static AircraftFactory factory = new AircraftFactory();
    
    private AircraftFactory() {}
    
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
