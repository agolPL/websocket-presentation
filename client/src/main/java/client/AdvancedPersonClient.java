package client;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.websocket.ClientEndpoint;
import javax.websocket.ContainerProvider;
import javax.websocket.DeploymentException;
import javax.websocket.OnMessage;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;

import pl.agol.jee.websocket.Person;
import pl.agol.jee.websocket.PersonCoder;

/**
 * 
 * @author Andrzej Goławski
 *
 */
@ClientEndpoint(decoders = PersonCoder.class)
public class AdvancedPersonClient {

	@OnMessage
	public void onMessage(Person person) {
		System.out.println(person);
	}

	public static void main(String... args) throws DeploymentException, IOException, URISyntaxException, InterruptedException {
		WebSocketContainer container = ContainerProvider.getWebSocketContainer();
		Session session = container.connectToServer(AdvancedPersonClient.class, new URI("ws://localhost:8080/websocket/advancedPerson"));
		session.getBasicRemote().sendText("any message");
		waitGivenMillis(1000);
	}

	private static void waitGivenMillis(long time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}