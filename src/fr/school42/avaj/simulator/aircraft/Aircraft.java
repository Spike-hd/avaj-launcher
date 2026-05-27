package fr.school42.avaj.simulator.aircraft;

public abstract class Aircraft extends Flyable {
    protected long id;
    protected String name;
    protected Coordinates coordinates;
    private static long idCounter = 0;

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
}
