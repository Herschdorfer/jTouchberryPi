package at.tlphotography.jtouchberry.radio;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class PlayerThread implements Runnable {

	private String uri;
	private Player player;

	public PlayerThread(String uri) {

		this.uri = uri;

	}

	@Override
	public void run() {

		if (player != null)
			player.close();

		URLConnection urlConnection;
		try {
			urlConnection = new URL(uri).openConnection();
			urlConnection.connect();

			player = new Player(urlConnection.getInputStream());

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JavaLayerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			player.play();
		} catch (JavaLayerException e) {
			System.out.println(e.getMessage());
		}

	}

	public void stop() {
		player.close();

	}
}
