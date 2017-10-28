package at.tlphotography.rpi.sht31;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class Application {

    public Application() { //NOSONARQUBE spring need this
	
    }

    public static void main(String[] args) {
	SpringApplication.run(Application.class, args);

    }

}