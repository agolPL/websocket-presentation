package pl.agol.jee.websocket;

import java.io.IOException;

import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

import com.cedarsoftware.util.io.JsonReader;
import com.cedarsoftware.util.io.JsonWriter;

/**
 * 
 * @author Andrzej Goławski
 *
 */
public class PersonCoder implements Encoder.Text<Person>, Decoder.Text<Person> {

	public String encode(Person person) throws EncodeException {
		try {
			return JsonWriter.objectToJson(person);
		} catch (IOException e) {
			throw new EncodeException(person, e.getMessage(), e);
		}
	}

	public Person decode(String person) throws DecodeException {
		try {
			return (Person) JsonReader.jsonToJava(person);
		} catch (IOException e) {
			throw new DecodeException(person, e.getMessage(), e);
		}
	}

	public void init(EndpointConfig config) {
	}

	public void destroy() {
	}

	public boolean willDecode(String s) {
		return true;
	}

}
