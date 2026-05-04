package fr.school42.avaj.simulator.aicraft;

import fr.school42.avaj.simulator.WeatherTower;

public interface Flyable {
    abstract void updateConditions();
    void registerTower(WeatherTower p_tower);
}
