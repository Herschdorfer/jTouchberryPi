package at.tlphotography.jtouchberry;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import at.tlphotography.jtouchberry.heating.equipment.db.Equipment;
import at.tlphotography.jtouchberry.heating.equipment.db.EquipmentDao;
import at.tlphotography.jtouchberry.radio.db.Radio;
import at.tlphotography.jtouchberry.radio.db.RadioDao;

@Controller
public class IndexController {

    @SuppressWarnings("unused")
    private static final Logger LOG = LogManager.getLogger(IndexController.class);

    @Autowired
    private EquipmentDao equipmentDao;

    @Autowired
    private RadioDao radioDao;

    @RequestMapping("/")
    public String index(@RequestParam(value = "page", required = false) String content, Model model) {
	if (content != null) {
	    switch (content) {
	    case "radio":
		Iterable<Radio> radios = radioDao.findAll();
		model.addAttribute("content", content);
		model.addAttribute("radioList", radios);
		break;

	    case "weather":
		model.addAttribute("content", content);
		break;

	    case "heating":
		Iterable<Equipment> eqs = equipmentDao.findAll();
		model.addAttribute("content", content);
		model.addAttribute("equipment", eqs);
		break;

	    case "home":
	    default:
		model.addAttribute("content", "home");
		break;
	    }

	} else {
	    model.addAttribute("content", "home");
	}
	return "index";
    }
}