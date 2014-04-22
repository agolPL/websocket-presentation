package pl.agol.jee.websocket;

import java.io.IOException;

import javax.websocket.EncodeException;
import javax.websocket.OnMessage;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 * 
 * @author Andrzej Goławski
 *
 */
@ServerEndpoint(
		value = "/advancedPerson", 
		encoders = PersonCoder.class
)
public class AdvancedPersonService {

	@OnMessage
	public void onMessage(String message, Session session) throws IOException, EncodeException {
		Person person = new Person("name", "lastname");
		session.getBasicRemote().sendObject(person);
	}	
}
