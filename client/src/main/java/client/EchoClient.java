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

/**
 * 
 * @author Andrzej Goławski
 *
 */
@ClientEndpoint
public class EchoClient {

	@OnMessage
	public void onMessage(Person message) {
		System.out.println(message);
	}

	public static void main(String... args) throws DeploymentException, IOException, URISyntaxException, InterruptedException {
		WebSocketContainer container = ContainerProvider.getWebSocketContainer();
		Session session = container.connectToServer(EchoClient.class, new URI("ws://localhost:8080/websocket/echo"));
		session.getBasicRemote().sendText("test message");
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
