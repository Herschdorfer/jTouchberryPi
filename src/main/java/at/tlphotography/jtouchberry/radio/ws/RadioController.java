package at.tlphotography.jtouchberry.radio.ws;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import at.tlphotography.jtouchberry.radio.Audio;
import at.tlphotography.jtouchberry.radio.RadioPlayer;
import at.tlphotography.jtouchberry.radio.db.Radio;
import at.tlphotography.jtouchberry.radio.db.RadioDao;

@RestController
public class RadioController {

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

	@RequestMapping("/radio/add")
	@ResponseBody
	public String add(String email, String name) {
		Radio radio = null;
		try {
			radio = new Radio(email, name);
			radioDao.save(radio);
		} catch (Exception ex) {
			return "Error creating the user: " + ex.toString();
		}
		return "Radio succesfully created! (id = " + radio.getId() + ")";
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