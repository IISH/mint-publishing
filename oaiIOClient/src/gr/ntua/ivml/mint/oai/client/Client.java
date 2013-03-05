package gr.ntua.ivml.mint.oai.client;

import gr.ntua.ivml.mint.pi.identifiers.generators.SHA1Generator;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import com.mongodb.BasicDBObject;
import com.mongodb.util.JSON;

public class Client {
	private HttpClient httpClient;
	private SHA1Generator gen;
	public Client(){
		httpClient = new DefaultHttpClient();
		gen = new SHA1Generator();
	}
	
	public void publish(String xmlRecord, int datasetId, int orgId, int userId, long itemId, String reportId, long sourceItemId, int sourceDatasetId
						, String prefix, String namespace, int schemaId, String postUrl) throws ClientProtocolException, IOException{
		
		BasicDBObject obj = new BasicDBObject();
		obj.put("xmlRecord", xmlRecord);
		obj.put("datasetId", datasetId);
		obj.put("orgId", orgId);
		obj.put("userId", userId);
		obj.put("itemId", itemId);
		obj.put("reportId", reportId);
		obj.put("sourceItemId", sourceItemId);
		obj.put("sourceDatasetId", sourceDatasetId);
		BasicDBObject xm = new BasicDBObject();
		xm.put("prefix", prefix);
		xm.put("namespace", namespace);
		obj.put("namespace", xm);
		obj.put("isPublished", true);
		Date dat = new Date();
		obj.put("datestamp", dat.getTime());
		obj.put("hash", gen.generate(xmlRecord));
		ArrayList<Integer> sets = new ArrayList<Integer>();
		sets.add(orgId);
		sets.add(userId);
		sets.add(datasetId);
		obj.put("sets", sets);
		
		String str = JSON.serialize(obj);
		HttpPost method = new HttpPost(postUrl);
		
		StringEntity requestEntity = new StringEntity(
			    str,
			    "application/json",
			    "UTF-8");
		
		method.setEntity(requestEntity);
		httpClient.execute(method);
	}
	
	public void unpublish(int orgId, String getUrl) throws ClientProtocolException, IOException, URISyntaxException{
		URIBuilder builder = new URIBuilder(getUrl)
        .addParameter("orgId", ""+orgId);
		
		HttpGet post = new HttpGet(builder.build());
	      httpClient.execute(post);
	}
}
