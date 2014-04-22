package pl.agol.jee.websocket;

import java.io.IOException;
import java.nio.ByteBuffer;

import javax.websocket.EncodeException;
import javax.websocket.OnMessage;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.apache.commons.lang.SerializationUtils;

/**
 * 
 * @author Andrzej Goławski
 *
 */
@ServerEndpoint("/person")
public class PersonService {

	@OnMessage
	public void onMessage(String message, Session session) throws IOException, EncodeException {
		Person person = new Person("name", "lastname");
		ByteBuffer bufferedPerson = ByteBuffer.wrap(SerializationUtils.serialize(person));
		session.getBasicRemote().sendBinary(bufferedPerson);
	}
}
