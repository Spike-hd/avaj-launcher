package fr.school42.avaj.simulator;

import fr.school42.avaj.simulator.aicraft.Coordinates;

public class WeatherProvider {
    // 2. L'instance unique ("static" veut dire lié à la classe entière)
    private static final WeatherProvider weatherProvider = new WeatherProvider();
    
    // Le tableau des 4 météos possibles
    private static final String[] weather = {"RAIN", "FOG", "SUN", "SNOW"};

    // 1. Constructeur privé (personne d'autre ne peut l'appeler)
    private WeatherProvider() {
    }

    // 3. L'unique point d'accès pour récupérer le gestionnaire de météo
    public static WeatherProvider getProvider() {
        return weatherProvider;
    }

    public String getCurrentWeather(Coordinates p_coordinates) {
        int seed = p_coordinates.getLongitude() + p_coordinates.getLatitude() + p_coordinates.getHeight();
        return weather[seed % 4];
    }
}
