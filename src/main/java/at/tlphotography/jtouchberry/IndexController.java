package at.tlphotography.jtouchberry;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexController {

	private static final Logger LOG = LogManager.getLogger(IndexController.class);

	@RequestMapping("/")
	public String greeting(@RequestParam(value = "page", required = false) String content, Model model) {
		if (content != null) {
			switch (content) {
			case "weather":
				model.addAttribute("content", content);
				break;

			case "heating":
				model.addAttribute("content", content);
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
