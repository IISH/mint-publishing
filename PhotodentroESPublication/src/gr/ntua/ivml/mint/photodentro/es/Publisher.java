package gr.ntua.ivml.mint.photodentro.es;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
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



public class Publisher {
	
	private Client client;
	
	public Publisher(String hostName, int port){
		client = new TransportClient()
        .addTransportAddress(new InetSocketTransportAddress(hostName, 9300));

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
		Document doc;
		InputStream is;
		BasicDBObject res = new BasicDBObject();

		try {
			is = new ByteArrayInputStream(xml.getBytes("UTF-8"));
			doc = new Builder().build(is);
			
			String xq = "/*:lom/*:general/*:identifier/*:entry";
			Nodes nods = XQueryUtil.xquery(doc, xq);
			//BasicDBObject general = new BasicDBObject();
			BasicDBObject general = new BasicDBObject();
			ArrayList<String> identifier = new ArrayList<String>();
			for(int i = 0; i<nods.size();i++){
				Node nod = nods.get(i);
				identifier.add(nod.getValue());
			}
			general.put("identifier", identifier);
			
			xq = "/*:lom/*:general/*:title/*:string";
			nods = XQueryUtil.xquery(doc, xq);
			ArrayList<String> title = new ArrayList<String>();
			for(int i = 0; i<nods.size();i++){
				Node nod = nods.get(i);
				title.add(nod.getValue());
			}
			general.put("title", title);
			
			
			
			xq = "/*:lom/*:general/*:description/*:string";
			nods = XQueryUtil.xquery(doc, xq);
			ArrayList<String> description = new ArrayList<String>();
			for(int i = 0; i<nods.size();i++){
				Node nod = nods.get(i);
				description.add(nod.getValue());
			}
			general.put("description", description);
			
			
			xq = "/*:lom/*:general/*:keyword/*:string";
			nods = XQueryUtil.xquery(doc, xq);
			ArrayList<String> keyword = new ArrayList<String>();
			for(int i = 0; i<nods.size();i++){
				Node nod = nods.get(i);
				keyword.add(nod.getValue());
			}
			general.put("keyword", keyword);
			
			
			xq = "/*:lom/*:general/*:coverage/*:string";
			nods = XQueryUtil.xquery(doc, xq);
			ArrayList<String> coverage = new ArrayList<String>();
			for(int i = 0; i<nods.size();i++){
				Node nod = nods.get(i);
				coverage.add(nod.getValue());
			}
			general.put("coverage", coverage);
			
			
			xq = "/*:lom/*:general/*:structure/*:value";
			nods = XQueryUtil.xquery(doc, xq);
			ArrayList<String> structure = new ArrayList<String>();
			for(int i = 0; i<nods.size();i++){
				Node nod = nods.get(i);
				structure.add(nod.getValue());
			}
			general.put("structure", structure);
			
			xq = "/*:lom/*:general/*:aggregationLevel/*:value";
			nods = XQueryUtil.xquery(doc, xq);
			ArrayList<String> aggr = new ArrayList<String>();
			for(int i = 0; i<nods.size();i++){
				Node nod = nods.get(i);
				aggr.add(nod.getValue());
			}
			general.put("aggregationLevel", aggr);
			
			res.put("general", general);
			
			BasicDBObject lifeCycle = new BasicDBObject();
			xq = "/*:lom/*:lifeCycle/*:version/*:string";
			nods = XQueryUtil.xquery(doc, xq);
			ArrayList<String> version = new ArrayList<String>();
			for(int i = 0; i<nods.size();i++){
				Node nod = nods.get(i);
				version.add(nod.getValue());
			}
			lifeCycle.put("version", version);
			
			xq = "/*:lom/*:lifeCycle/*:status/*:value";
			nods = XQueryUtil.xquery(doc, xq);
			ArrayList<String> status = new ArrayList<String>();
			for(int i = 0; i<nods.size();i++){
				Node nod = nods.get(i);
				status.add(nod.getValue());
			}
			lifeCycle.put("status", status);
			
			res.put("lifeCycle", lifeCycle);
			BasicDBObject educational = new BasicDBObject();
			
			xq = "/*:lom/*:educational/*:interactivityType/*:value";
			nods = XQueryUtil.xquery(doc, xq);
			ArrayList<String> interactivity = new ArrayList<String>();
			for(int i = 0; i<nods.size();i++){
				Node nod = nods.get(i);
				interactivity.add(nod.getValue());
			}
			educational.put("interactivityType", interactivity);
			
			xq = "/*:lom/*:educational/*:learningResourceType/*:value";
			nods = XQueryUtil.xquery(doc, xq);
			ArrayList<String> learningResourceType = new ArrayList<String>();
			for(int i = 0; i<nods.size();i++){
				Node nod = nods.get(i);
				learningResourceType.add(nod.getValue());
			}
			educational.put("learningResourceType", learningResourceType);
			
			
			xq = "/*:lom/*:educational/*:semanticDensity/*:value";
			nods = XQueryUtil.xquery(doc, xq);
			ArrayList<String> semanticDensity = new ArrayList<String>();
			for(int i = 0; i<nods.size();i++){
				Node nod = nods.get(i);
				semanticDensity.add(nod.getValue());
			}
			educational.put("semanticDensity", semanticDensity);
			
			
			xq = "/*:lom/*:educational/*:intendedEndUserRole/*:value";
			nods = XQueryUtil.xquery(doc, xq);
			ArrayList<String> intendedEndUserRole = new ArrayList<String>();
			for(int i = 0; i<nods.size();i++){
				Node nod = nods.get(i);
				intendedEndUserRole.add(nod.getValue());
			}
			educational.put("intendedEndUserRole", intendedEndUserRole);
			
			
			xq = "/*:lom/*:educational/*:context/*:value";
			nods = XQueryUtil.xquery(doc, xq);
			ArrayList<String> context = new ArrayList<String>();
			for(int i = 0; i<nods.size();i++){
				Node nod = nods.get(i);
				context.add(nod.getValue());
			}
			educational.put("context", context);	
			
			xq = "/*:lom/*:educational/*:typicalAgeRange/*:string";
			nods = XQueryUtil.xquery(doc, xq);
			ArrayList<String> typicalAgeRange = new ArrayList<String>();
			for(int i = 0; i<nods.size();i++){
				Node nod = nods.get(i);
				typicalAgeRange.add(nod.getValue());
			}
			educational.put("typicalAgeRange", typicalAgeRange);
			
			
			xq = "/*:lom/*:educational/*:difficulty/*:value";
			nods = XQueryUtil.xquery(doc, xq);
			ArrayList<String> difficulty = new ArrayList<String>();
			for(int i = 0; i<nods.size();i++){
				Node nod = nods.get(i);
				difficulty.add(nod.getValue());
			}
			educational.put("difficulty", difficulty);
			
			xq = "/*:lom/*:educational/*:typicalLearningTime/*:duration";
			nods = XQueryUtil.xquery(doc, xq);
			ArrayList<String> duration = new ArrayList<String>();
			for(int i = 0; i<nods.size();i++){
				Node nod = nods.get(i);
				duration.add(nod.getValue());
			}
			educational.put("learningTimeDuration", duration);
						
			xq = "/*:lom/*:educational/*:typicalLearningTime/*:description/*:string";
			nods = XQueryUtil.xquery(doc, xq);
			ArrayList<String> timeDescription = new ArrayList<String>();
			for(int i = 0; i<nods.size();i++){
				Node nod = nods.get(i);
				timeDescription.add(nod.getValue());
			}
			educational.put("learningTimeDescription", timeDescription);
			
			
			xq = "/*:lom/*:educational/*:description/*:string";
			nods = XQueryUtil.xquery(doc, xq);
			ArrayList<String> eduDescription = new ArrayList<String>();
			for(int i = 0; i<nods.size();i++){
				Node nod = nods.get(i);
				eduDescription.add(nod.getValue());
			}
			educational.put("educationalDescription", eduDescription);
			
			xq = "/*:lom/*:educational/*:language";
			nods = XQueryUtil.xquery(doc, xq);
			ArrayList<String> eduLanguage = new ArrayList<String>();
			for(int i = 0; i<nods.size();i++){
				Node nod = nods.get(i);
				eduLanguage.add(nod.getValue());
			}
			educational.put("educationalLnaguage", eduLanguage);			
			
			res.put("educational", educational);
			res.put("xml", xml);
			is.close();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ValidityException e) {
			e.printStackTrace();
		} catch (ParsingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return JSON.serialize(res);
	}
}
