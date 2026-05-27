package fr.school42.avaj.simulator;

import fr.school42.avaj.simulator.aircraft.Aircraft;
import fr.school42.avaj.simulator.aircraft.Flyable;
import java.util.ArrayList;
import java.util.List;

public class Tower {
    private List<Flyable> observers = new ArrayList<>();

    public void register(Flyable flyable) {
        if (!observers.contains(flyable)) {
            observers.add(flyable);
            SimulatorLogger.log("Tower says: " + ((Aircraft) flyable).getIdentifier() + " registered to weather tower.");
        }
    }

    public void unregister(Flyable flyable) {
        if (observers.contains(flyable)) {
            observers.remove(flyable);
            SimulatorLogger.log("Tower says: " + ((Aircraft) flyable).getIdentifier() + " unregistered from weather tower.");
        }
    }

    protected void conditionsChanged() {
        List<Flyable> currentObservers = new ArrayList<>(observers);
        for (Flyable flyable : currentObservers) {
            flyable.updateConditions();
        }
    }
}
