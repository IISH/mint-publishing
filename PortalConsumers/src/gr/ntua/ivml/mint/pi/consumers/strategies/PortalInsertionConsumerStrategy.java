package gr.ntua.ivml.mint.pi.consumers.strategies;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import com.mongodb.BasicDBObject;
import com.mongodb.util.JSON;

import gr.ntua.ivml.mint.pi.consumers.util.Config;
import gr.ntua.ivml.mint.pi.consumers.util.ThriftToJSON;
import gr.ntua.ivml.mint.pi.messages.ItemMessage;

public class PortalInsertionConsumerStrategy implements MessageConsumerStrategy{

	ThriftToJSON toJson = new ThriftToJSON();
	DefaultHttpClient httpclient;
	HttpPost httpost;
	
	public PortalInsertionConsumerStrategy(){
		httpclient = new DefaultHttpClient();
		
	}
	@Override
	public void consume(ItemMessage msg, String topic) {
		String[] vals = topic.split("\\.");
		BasicDBObject obj = toJson.toJSON(msg);
		httpost = new HttpPost(Config.get("play.insertrecord"));
		String payload = JSON.serialize(obj);
		
		StringEntity ent;
		try {
			System.out.println(payload);
			ent = new StringEntity(payload, "UTF-8");
			ent.setContentType("application/json");
			httpost.setEntity(ent);
			httpclient.execute(httpost);
			httpost.releaseConnection();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
