import java.io.IOException;

import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import at.tlphotography.rpi.data.Sensor;
import at.tlphotography.rpi.data.SensorData;

public class DataSerilisationTest {

    @Test
    public void whenDeserializingPolymorphic_thenCorrect() throws IOException {

	SensorData[] dataArray = { new SensorData("Humidity", 1.0), new SensorData("Temperature", 2.0) };

	Sensor data = new Sensor("sht31-d", dataArray);

	String result = new ObjectMapper().writeValueAsString(data);
	
	System.out.println(result);
    }

}
