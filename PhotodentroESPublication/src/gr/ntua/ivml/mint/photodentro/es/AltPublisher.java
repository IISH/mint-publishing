package gr.ntua.ivml.mint.photodentro.es;

import java.io.IOException;
import java.util.ArrayList;

import nu.xom.Builder;
import nu.xom.Document;
import nu.xom.Node;
import nu.xom.Nodes;
import nu.xom.ParsingException;
import nu.xom.ValidityException;
import nux.xom.xquery.XQueryUtil;

import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.transport.InetSocketTransportAddress;

import com.mongodb.BasicDBObject;
import com.mongodb.util.JSON;



public class AltPublisher {
	
	private Client client;
	private static class BuildJson {
		BasicDBObject content = new BasicDBObject();
		Document doc;
		
		public static BuildJson create(Document doc )  {
			BuildJson bj = new BuildJson();
			bj.doc = doc;
			return bj;
		}
		
		public BuildJson addNodes( String name, String query ) {
			Nodes nods = XQueryUtil.xquery(doc, query );
			ArrayList<String> values = new ArrayList<String>();
			for(int i = 0; i<nods.size();i++){
				Node nod = nods.get(i);
				values.add(nod.getValue());
			}
			content.put( name, values);
			return this;
		}
	}
	
	
	public AltPublisher(String hostName, int port){
		client = new TransportClient()
        .addTransportAddress(new InetSocketTransportAddress(hostName, port));

	}
	
	
	public void publish(String xml, String orgName, int counter){
		client.prepareIndex(orgName, "lom", Integer.toString(counter)).setSource(extractValues(xml))
		.execute()
		.actionGet();
	}
	
	
	public void delete(String orgName){
		client.admin().indices().delete(new DeleteIndexRequest(orgName)).actionGet();
	}
	public void close(){
		client.close();
	}
	
	
	private String extractValues(String xml){
		BasicDBObject res = new BasicDBObject();

		try {
			Document doc = new Builder().build(xml, null );
			BuildJson general = BuildJson.create(doc )
				.addNodes( "identifier", "/*:lom/*:general/*:identifier/*:entry")
				.addNodes( "title", "/*:lom/*:general/*:title/*:string")
				.addNodes( "description", "/*:lom/*:general/*:description/*:string")
				.addNodes( "keyword", "/*:lom/*:general/*:keyword/*:string" )
				.addNodes( "coverage", "/*:lom/*:general/*:coverage/*:string")
				.addNodes( "structure","/*:lom/*:general/*:structure/*:value" )
				.addNodes( "aggregationLevel", "/*:lom/*:general/*:aggregationLevel/*:value" );
			
			res.put( "general", general.content );

			BuildJson lifeCycle = BuildJson.create( doc )
				.addNodes( "version","/*:lom/*:lifeCycle/*:version/*:string")
				.addNodes("status","/*:lom/*:lifeCycle/*:status/*:value" );
			
			
			res.put("lifeCycle", lifeCycle.content);
			BuildJson educational = BuildJson.create( doc )
				.addNodes( "interactivityType", "/*:lom/*:educational/*:interactivityType/*:value")
				.addNodes( "learningResourceType", "/*:lom/*:educational/*:learningResourceType/*:value")
				.addNodes( "semanticDensity","/*:lom/*:educational/*:semanticDensity/*:value" )
				.addNodes( "intendedEndUserRole", "/*:lom/*:educational/*:intendedEndUserRole/*:value" )
				.addNodes( "context", "/*:lom/*:educational/*:context/*:value")
				.addNodes( "typicalAgeRange", "/*:lom/*:educational/*:typicalAgeRange/*:string")
				.addNodes( "difficulty", "/*:lom/*:educational/*:difficulty/*:value" )
				.addNodes( "learningTimeDuration", "/*:lom/*:educational/*:typicalLearningTime/*:duration")
				.addNodes( "learningTimeDescription", "/*:lom/*:educational/*:typicalLearningTime/*:description/*:string")
				.addNodes( "educationalDescription","/*:lom/*:educational/*:description/*:string" )
				.addNodes( "educationalLanguage", "/*:lom/*:educational/*:language");
			
			res.put("educational", educational.content);
			res.put("xml", xml);
		}  catch (ValidityException e) {
			e.printStackTrace();
		} catch (ParsingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return JSON.serialize(res);
	}
}
