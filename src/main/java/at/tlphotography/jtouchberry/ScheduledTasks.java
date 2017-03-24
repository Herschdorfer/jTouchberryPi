package at.tlphotography.jtouchberry;

import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import at.tlphotography.jtouchberry.forecast.db.Forecast;
import at.tlphotography.jtouchberry.forecast.db.ForecastList;
import at.tlphotography.jtouchberry.radio.Audio;

@Component
public class ScheduledTasks {

    private static final Logger LOGGER = LoggerFactory.getLogger(ScheduledTasks.class);

    @PostConstruct
    public void printAudio() {
	LOGGER.info(Audio.getHierarchyInfo());
    }

    //@Scheduled(cron = "0 * * * * *")
    public void reportCurrentTime() {
	HashMap<Integer, String> zamgLinks = new HashMap<>();
	zamgLinks.put(1, "http://www.zamg.ac.at/cms/de/wetter/wetter-oesterreich/wien/heute_vormittag");
	zamgLinks.put(2, "http://www.zamg.ac.at/cms/de/wetter/wetter-oesterreich/wien/heute_nachmittag");
	zamgLinks.put(3, "http://www.zamg.ac.at/cms/de/wetter/wetter-oesterreich/wien/heute_nacht");
	zamgLinks.put(4, "http://www.zamg.ac.at/cms/de/wetter/wetter-oesterreich/wien/morgen_vormittag");
	zamgLinks.put(5, "http://www.zamg.ac.at/cms/de/wetter/wetter-oesterreich/wien/morgen_nachmittag");
	zamgLinks.put(6, "http://www.zamg.ac.at/cms/de/wetter/wetter-oesterreich/wien/uebermorgen");
	zamgLinks.put(7, "http://www.zamg.ac.at/cms/de/wetter/wetter-oesterreich/wien/trend1");
	zamgLinks.put(8, "http://www.zamg.ac.at/cms/de/wetter/wetter-oesterreich/wien/trend2");

	GregorianCalendar cal = (GregorianCalendar) GregorianCalendar.getInstance(TimeZone.getTimeZone("UTC"));

	ForecastList list = new ForecastList(cal);

	zamgLinks.entrySet().parallelStream().forEach(s -> getForecasts(s, list));

    }

    static void getForecasts(Entry<Integer, String> s, ForecastList list) {

	Forecast f = new Forecast();

	LOGGER.info("processing of " + s);

	list.getForecasts().add(f);
    }
}
