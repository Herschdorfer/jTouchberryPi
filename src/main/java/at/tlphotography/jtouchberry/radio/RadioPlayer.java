package at.tlphotography.jtouchberry.radio;

import javax.annotation.PreDestroy;

import org.springframework.stereotype.Component;

@Component
public class RadioPlayer {

	PlayerThread thread;

	public void play(String uri) {

		if (thread != null)
			thread.stop();

		try {

			thread = new PlayerThread(uri);
			new Thread(thread).start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void stop() {
		if (thread != null)
			thread.stop();
	}

	@PreDestroy
	public void destroy() {
		thread.stop();
	}
}
