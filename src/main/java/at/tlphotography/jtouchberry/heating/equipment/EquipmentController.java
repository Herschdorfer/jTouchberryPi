package at.tlphotography.jtouchberry.heating.equipment;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import at.tlphotography.jtouchberry.heating.equipment.db.Equipment;
import at.tlphotography.jtouchberry.heating.equipment.db.EquipmentDao;
import at.tlphotography.jtouchberry.heating.equipment.db.SwitchDao;
import at.tlphotography.jtouchberry.heating.equipment.db.Switch;
import at.tlphotography.jtouchberry.heating.equipment.db.Sensor;
import at.tlphotography.jtouchberry.heating.equipment.db.SensorDao;

@Controller
public class EquipmentController {

    private static final Logger LOG = LogManager.getLogger(EquipmentController.class);

    @Autowired
    private EquipmentDao equipmentDao;

    @Autowired
    private SwitchDao switchDao;

    @Autowired
    private SensorDao sensorDao;

    private final List<SseEmitter> emitters = new ArrayList<>();

    @RequestMapping(path = "/heating/status", method = RequestMethod.GET)
    public SseEmitter stream() {
	SseEmitter emitter = new SseEmitter();

	emitters.add(emitter);
	emitter.onCompletion(() -> emitters.remove(emitter));

	return emitter;
    }

    @RequestMapping("/heating/equimpent")
    @ResponseBody
    public Iterable<Equipment> listEquipments() {
	return equipmentDao.findAll();
    }

    @RequestMapping("/heating/equimpent/sensors")
    @ResponseBody
    public Iterable<Sensor> listSensors() {
	return sensorDao.findAll();
    }

    @RequestMapping("/heating/equimpent/switches")
    @ResponseBody
    public Iterable<Switch> listSwitches() {
	return switchDao.findAll();
    }

    @RequestMapping("/heating/equimpent/sensors/{id}")
    @ResponseBody
    public Sensor getSensor(@PathVariable Long id) {
	return sensorDao.findOne(id);
    }

    @RequestMapping("/heating/equimpent/switches/{id}")
    @ResponseBody
    public Switch getSwitch(@PathVariable Long id) {
	return switchDao.findOne(id);
    }

    @RequestMapping(value = "/heating/equimpent/{equipmentId}", method = RequestMethod.GET)
    @ResponseBody
    public Equipment listEquimpent(@PathVariable Long equipmentId) {
	return equipmentDao.findOne(equipmentId);
    }

    @RequestMapping(value = "/heating/equimpent/{equipmentId}/addSensor", method = RequestMethod.POST)
    @ResponseBody
    public Equipment addSensorToEquipment(@PathVariable Long equipmentId, @RequestBody Sensor sensor) {
	Equipment eq = equipmentDao.findOne(equipmentId);
	sensor.setEquipment(eq);
	eq.getSensors().add(sensor);
	equipmentDao.save(eq);
	return eq;
    }

    @RequestMapping(value = "/heating/equimpent/{equipmentId}/addSwitch", method = RequestMethod.POST)
    @ResponseBody
    public Equipment addSwitchToEquipment(@PathVariable Long equipmentId, @RequestBody Switch switchs) {
	Equipment eq = equipmentDao.findOne(equipmentId);
	switchs.setEquipment(eq);
	eq.getSwitches().add(switchs);
	equipmentDao.save(eq);
	return eq;
    }

    @RequestMapping(value = "/heating/equimpent/deleteSensor/{sensorId}", method = RequestMethod.GET)
    @ResponseBody
    public void deleteSensor(@PathVariable Long sensorId) {
	sensorDao.delete(sensorId);
    }

    @RequestMapping(value = "/heating/equimpent/deleteSwitch/{switchId}", method = RequestMethod.GET)
    @ResponseBody
    public void deleteSwitch(@PathVariable Long switchId) {
	switchDao.delete(switchId);
    }

    @RequestMapping("/heating/equimpent/add")
    @ResponseBody
    public String add(@RequestParam(value = "name", required = false) String name) {

	Equipment equipment = null;
	try {
	    equipment = new Equipment(name);
	    equipmentDao.save(equipment);
	} catch (Exception ex) {
	    LOG.error("Error creating the equipment", ex);
	    return "Error creating the equipment: " + ex.toString();
	}
	return "Equipment succesfully created! (id = " + equipment.getId() + ")";

    }

}
