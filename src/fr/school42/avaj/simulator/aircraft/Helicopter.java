package fr.school42.avaj.simulator.aircraft;

import fr.school42.avaj.simulator.SimulatorLogger;

public class Helicopter extends Aircraft {
    public Helicopter(long p_id, String p_name, Coordinates p_coordinates) {
        super(p_id, p_name, p_coordinates);
    }

    @Override
    public void updateConditions() {
        String weather = weatherTower.getWeather(this.coordinates);
        int longitude = this.coordinates.getLongitude();
        int latitude = this.coordinates.getLatitude();
        int height = this.coordinates.getHeight();

        String message = "";

        switch (weather) {
            case "SUN" -> {
                longitude += 10;
                height += 2;
                message = "Sunny day, let's enjoy the good weather and take some pics.";
            }
            case "RAIN" -> {
                longitude += 5;
                message = "Rain is falling. We might get wet. Better watch out for lightings.";
            }
            case "FOG" -> {
                longitude += 1;
                message = "The visibility is low, we need to proceed with caution!";
            }
            case "SNOW" -> {
                height -= 12;
                message = "It's snowing. Our helicopter is going to crash !! Mayday ! Mayday !";
            }
        }

        if (height > 100) height = 100;
        
        this.coordinates = new Coordinates(longitude, latitude, height);

        SimulatorLogger.log("Helicopter#" + this.name + "(" + this.id + "): " + message);

        if (this.coordinates.getHeight() <= 0) {
            SimulatorLogger.log("Helicopter#" + this.name + "(" + this.id + ") landing.");
            this.weatherTower.unregister(this);
        }
    }
}
