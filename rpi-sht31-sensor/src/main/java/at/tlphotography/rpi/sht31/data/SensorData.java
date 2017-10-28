package at.tlphotography.rpi.sht31.data;

public class SensorData {

    private final double temperature;
    private final double humidity;

    public SensorData(double d, double e) {
	this.humidity = e;
	this.temperature = d;
    }

    public double getHumidity() {
	return humidity;
    }

    public double getTemperature() {
	return temperature;
    }

}