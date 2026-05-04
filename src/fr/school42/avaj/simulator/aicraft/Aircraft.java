package fr.school42.avaj.simulator.aicraft;

import fr.school42.avaj.simulator.WeatherTower;

public abstract class Aircraft implements Flyable {
    protected long id;
    protected String name;
    protected Coordinates coordinates;
    private static long idCounter = 0;
    protected WeatherTower weatherTower; // peut le mettre en privé dans les classes filles, mais c'est plus simple de le mettre ici

    protected Aircraft(long p_id, String p_name, Coordinates p_coordinates) {
        this.id = this.nextId();
        this.name = p_name;
        this.coordinates = p_coordinates;
    }

    private long nextId() {
        return ++idCounter;
    }

    public String getIdentifier() {
        return this.getClass().getSimpleName() + "#" + this.name + "(" + this.id + ")";
    }

    @Override
    public void registerTower(WeatherTower p_tower) {
        this.weatherTower = p_tower;
        this.weatherTower.register(this);
    }

}
