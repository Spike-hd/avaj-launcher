package fr.school42.avaj.simulator;

import fr.school42.avaj.simulator.aircraft.Coordinates;

public class WeatherTower extends Tower {
    
    public String getWeather(Coordinates p_coordinates) {
        return WeatherProvider.getProvider().getCurrentWeather(p_coordinates);
    }

    public void changeWeather() {
        this.conditionsChanged();
    }
}
