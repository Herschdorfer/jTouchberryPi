package at.tlphotography.jtouchberry.radio.ws;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import at.tlphotography.jtouchberry.radio.Audio;
import at.tlphotography.jtouchberry.radio.RadioPlayer;
import at.tlphotography.jtouchberry.radio.db.Radio;
import at.tlphotography.jtouchberry.radio.db.RadioDao;

@RestController
public class RadioController {

    Logger LOGGER = LogManager.getLogger(RadioController.class);

    @Autowired
    private RadioDao radioDao;

    @Autowired
    private RadioPlayer player;

    @Autowired
    private Audio audio;

    private Float volume;

    @RequestMapping("/radio/")
    @ResponseBody
    public Iterable<Radio> list() {
	return radioDao.findAll();
    }

    @RequestMapping("/radio/play")
    @ResponseBody
    public String play(Long id) {
	player.play(radioDao.findOne(id).getUri());
	return "OK";
    }

    @RequestMapping("/radio/stop")
    @ResponseBody
    public String stop() {
	player.stop();
	return "OK";
    }

    @RequestMapping("/radio/delete")
    @ResponseBody
    public String delete(Long id) {
	radioDao.delete(id);
	return "OK";
    }

    @RequestMapping("/radio/add")
    @ResponseBody
    public ResponseEntity<String> add(String uri, String name) {

	LOGGER.info("got invoked with " + uri + name);

	if (uri.isEmpty() || name.isEmpty()) {
	    return ResponseEntity.badRequest().body("name and Uri have to be set");
	}

	Radio radio = null;
	try {
	    radio = new Radio(uri, name);
	    radioDao.save(radio);
	} catch (Exception ex) {
	    LOGGER.error(ex);
	    return ResponseEntity.badRequest().body(ex.getLocalizedMessage());
	}
	return ResponseEntity.ok("Radio succesfully created! (id = " + radio.getId() + ")");
    }

    @RequestMapping("/radio/setVolume")
    @ResponseBody
    public String setVolume(Float volume) {
	this.volume = volume;
	audio.setMasterOutputVolume(volume);
	return "OK";
    }

    @RequestMapping("/radio/getVolume")
    @ResponseBody
    public Float getVolume() {
	return volume;
    }

    @RequestMapping("/radio/getMixer")
    @ResponseBody
    public String setMixer() {

	return audio.getHierarchyInfo();
    }
}