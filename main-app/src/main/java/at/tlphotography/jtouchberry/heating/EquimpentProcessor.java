package at.tlphotography.jtouchberry.heating;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import at.tlphotography.jtouchberry.heating.equipment.db.Equipment;
import at.tlphotography.jtouchberry.heating.equipment.db.EquipmentDao;

@Component
public class EquimpentProcessor {

    private static final Logger LOG = LogManager.getLogger(EquimpentProcessor.class);

    @Autowired
    private EquipmentDao equipmentDao;

    @Scheduled(fixedRate = 10000)
    public void getStatusMessage() {

	for (Equipment equipment : equipmentDao.findAll()) {
	    
	}
    }
}
