package gr.ntua.ivml.mint.oai.test;

import java.io.IOException;
import java.net.URISyntaxException;

import org.apache.http.client.ClientProtocolException;

import gr.ntua.ivml.mint.oai.client.Client;

public class testing {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 * @throws URISyntaxException 
	 */
	public static void main(String[] args) throws ClientProtocolException, IOException, URISyntaxException {
		Client client = new Client();
		client.unpublish(1, "http://localhost:9000/photodentro/unpublish");
		//client.publish("value", 1, 1, 1, 1, "3l23k2l3k", 1, 1, "ese", "http://something.la/", 1, "http://localhost:9000/photodentro/add");
	}

}
