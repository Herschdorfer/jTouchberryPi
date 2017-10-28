package at.tlphotography.rpi.sht31.rest;

import java.io.IOException;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pi4j.io.i2c.I2CBus;
import com.pi4j.io.i2c.I2CDevice;
import com.pi4j.io.i2c.I2CFactory;
import com.pi4j.io.i2c.I2CFactory.UnsupportedBusNumberException;

import at.tlphotography.rpi.data.Sensor;
import at.tlphotography.rpi.data.SensorData;

@RestController
public class SensorDataController {

    @RequestMapping("/data")
    public Sensor getData() throws UnsupportedBusNumberException, IOException, InterruptedException {

	// Code from
	// https://raw.githubusercontent.com/ControlEverythingCommunity/SHT31/master/Java/SHT31.java

	// Create I2CBus
	I2CBus bus = I2CFactory.getInstance(I2CBus.BUS_1);
	// Get I2C device, SHT31 I2C address is 0x44(68)
	I2CDevice device = bus.getDevice(0x44);

	// Send high repeatability measurement command
	// Command msb, command lsb
	byte[] config = new byte[2];
	config[0] = (byte) 0x2C;
	config[1] = (byte) 0x06;
	device.write(config, 0, 2);
	Thread.sleep(500);

	// Read 6 bytes of data
	// temp msb, temp lsb, temp CRC, humidity msb, humidity lsb, humidity
	// CRC
	byte[] data = new byte[6];
	device.read(data, 0, 6);

	// Convert the data
	double cTemp = ((((data[0] & 0xFF) * 256) + (data[1] & 0xFF)) * 175.0) / 65535.0 - 45.0;
	double humidity = ((((data[3] & 0xFF) * 256) + (data[4] & 0xFF)) * 100.0) / 65535.0;

	SensorData[] dataArray = { new SensorData("Humidity", humidity), new SensorData("Temperature", cTemp) };

	return new Sensor("sht31-d", dataArray);
    }
}