package fr.school42.avaj.simulator.aircraft;

import fr.school42.avaj.simulator.SimulatorLogger;

public class Balloon extends Aircraft {

    public Balloon(long p_id, String p_name, Coordinates p_coordinates) {
        super(0, p_name, p_coordinates);
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
                longitude += 2;
                height += 4;
                message = "Sunny day, let's enjoy the good weather and take some pics.";
            }
            case "RAIN" -> {
                height -= 5;
                message = "Rain is falling. We might get wet. Better watch out for lightings.";
            }
            case "FOG" -> {
                height -= 3;
                message = "The visibility is low, we need to proceed with caution!";
            }
            case "SNOW" -> {
                height -= 15;
                message = "It's snowing. Our balloon is going to crash !! Mayday ! Mayday !";
            }
        }

        if (height > 100) height = 100;
        
        this.coordinates = new Coordinates(longitude, latitude, height);

        SimulatorLogger.log("Balloon#" + this.name + "(" + this.id + "): " + message);

        if (this.coordinates.getHeight() <= 0) {
            SimulatorLogger.log("Balloon#" + this.name + "(" + this.id + ") landing.");
            this.weatherTower.unregister(this);
        }
    }

}
