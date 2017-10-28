package at.tlphotography.rpi.data;

public class SensorData {

    private final String name;
    private final double data;

    public SensorData(String name, double data) {
	this.name = name;
	this.data = data;
    }

    public double getData() {
	return data;
    }

    public String getName() {
	return name;
    }

}