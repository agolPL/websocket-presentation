package pl.agol.jee.websocket;

import java.io.IOException;

import javax.websocket.OnMessage;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 * 
 * @author Andrzej Goławski
 *
 */
@ServerEndpoint("/echo")
public class EchoService {

	@OnMessage
	public void onMessage(String message, Session session) throws IOException {
		session.getBasicRemote().sendText(message);
	}
}
