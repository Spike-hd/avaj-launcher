package fr.school42.avaj.simulator;

import fr.school42.avaj.simulator.aircraft.Coordinates;

public class WeatherProvider {
    private static final String[] weather = {"RAIN", "FOG", "SUN", "SNOW"};
    private static final WeatherProvider weatherProvider = new WeatherProvider();

    private WeatherProvider() {}

    public static WeatherProvider getProvider() {
        return weatherProvider;
    }

    public String getCurrentWeather(Coordinates p_coordinates) {
        int seed = p_coordinates.getLongitude() + p_coordinates.getLatitude() + p_coordinates.getHeight();
        return weather[seed % 4];
    }
}
