package client;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;

import javax.websocket.ClientEndpoint;
import javax.websocket.ContainerProvider;
import javax.websocket.DeploymentException;
import javax.websocket.OnMessage;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;

import org.apache.commons.lang.SerializationUtils;

import pl.agol.jee.websocket.Person;

/**
 * 
 * @author Andrzej Goławski
 *
 */
@ClientEndpoint
public class PersonClient {

	@OnMessage
	public void onMessage(ByteBuffer message) {
		Person person = getPerson(message);
		System.out.println(person);
	}

	private Person getPerson(ByteBuffer message) {
		byte[] personBytes = new byte[message.remaining()];
		message.get(personBytes);
		return (Person) SerializationUtils.deserialize(personBytes);
	}

	public static void main(String... args) throws DeploymentException, IOException, URISyntaxException, InterruptedException {
		WebSocketContainer container = ContainerProvider.getWebSocketContainer();
		Session session = container.connectToServer(PersonClient.class, new URI("ws://localhost:8080/websocket/person"));
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