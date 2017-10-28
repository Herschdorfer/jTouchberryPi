package at.tlphotography.rpi.data;

public class Sensor {

    private final String name;
    private final SensorData[] data;

    public Sensor(String name, SensorData... data) {
	this.name = name;
	
	this.data = data;
    }

    public String getName() {
	return name;
    }

    public SensorData[] getData() {
	return data;
    }

}
