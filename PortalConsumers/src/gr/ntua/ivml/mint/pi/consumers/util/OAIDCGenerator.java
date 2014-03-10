package gr.ntua.ivml.mint.pi.consumers.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import nu.xom.Attribute;
import nu.xom.Builder;
import nu.xom.Document;
import nu.xom.Element;
import nu.xom.Nodes;
import nu.xom.ParsingException;
import nu.xom.ValidityException;
import nux.xom.pool.XOMUtil;
import nux.xom.xquery.ResultSequence;
import nux.xom.xquery.XQuery;
import nux.xom.xquery.XQueryException;

import com.mongodb.BasicDBObject;

public class OAIDCGenerator {
	
	public BasicDBObject getOAIDCObject(BasicDBObject obj){
		BasicDBObject ns = createPrefix("oai_dc", "http://www.openarchives.org/OAI/2.0/oai_dc/");
		BasicDBObject rec = copyOriginalObject(obj);
		rec.put("namespace", ns);
		String xml = generateOAIDCXml(obj.getString("xmlRecord"));
		if(xml != null){
			rec.put("xmlRecord", xml);
		}else{
			rec = null;
		}
		return rec;
	}
	
	private String generateOAIDCXml(String originalXML){
		String xq = "for $var in //*:title return $var";
		
		Document doc;
		InputStream is;
		Element root = new Element("dc");
		root.setNamespaceURI("http://www.openarchives.org/OAI/2.0/oai_dc/");
		root.setNamespacePrefix("oai_dc");
		root.setNamespaceURI("http://www.openarchives.org/OAI/2.0/oai_dc/");
		root.addNamespaceDeclaration("dc", "http://purl.org/dc/elements/1.1/");
		root.addNamespaceDeclaration("xsi", "http://www.w3.org/2001/XMLSchema-instance");
		Attribute attr = new Attribute("schemaLocation", "http://www.openarchives.org/OAI/2.0/oai_dc/ http://www.openarchives.org/OAI/2.0/oai_dc.xsd");
		attr.setNamespace("xsi", "http://www.w3.org/2001/XMLSchema-instance");
		root.addAttribute(attr);
		try {
			is = new ByteArrayInputStream(originalXML.getBytes("UTF-8"));
			doc = new Builder().build(is);
			XQuery xquery = new XQuery(xq, null);
			ResultSequence results = xquery.execute(doc);
			Nodes nods = results.toNodes();
			for(int i = 0; i < nods.size();i++){
				String val = nods.get(i).getValue();
				Element elem = new Element("title");
				elem.setNamespaceURI("http://purl.org/dc/elements/1.1/");
				elem.setNamespacePrefix("dc");
				elem.insertChild(val, 0);
				root.appendChild(elem);
			}
			
			xq = "for $var in //*:creator return $var";
			xquery = new XQuery(xq, null);
			results = xquery.execute(doc);
			nods = results.toNodes();
			for(int i = 0; i < nods.size();i++){
				String val = nods.get(i).getValue();
				Element elem = new Element("creator");
				elem.setNamespaceURI("http://purl.org/dc/elements/1.1/");
				elem.setNamespacePrefix("dc");
				elem.insertChild(val, 0);
				root.appendChild(elem);
			}
			
			xq = "for $var in //*:subject return $var";
			xquery = new XQuery(xq, null);
			results = xquery.execute(doc);
			nods = results.toNodes();
			for(int i = 0; i < nods.size();i++){
				String val = nods.get(i).getValue();
				Element elem = new Element("subject");
				elem.setNamespaceURI("http://purl.org/dc/elements/1.1/");
				elem.setNamespacePrefix("dc");
				elem.insertChild(val, 0);
				root.appendChild(elem);
			}
			
			xq = "for $var in //*:description return $var";
			xquery = new XQuery(xq, null);
			results = xquery.execute(doc);
			nods = results.toNodes();
			for(int i = 0; i < nods.size();i++){
				String val = nods.get(i).getValue();
				Element elem = new Element("description");
				elem.setNamespaceURI("http://purl.org/dc/elements/1.1/");
				elem.setNamespacePrefix("dc");
				elem.insertChild(val, 0);
				root.appendChild(elem);
			}
			
			xq = "for $var in //*:publisher return $var";
			xquery = new XQuery(xq, null);
			results = xquery.execute(doc);
			nods = results.toNodes();
			for(int i = 0; i < nods.size();i++){
				String val = nods.get(i).getValue();
				Element elem = new Element("publisher");
				elem.setNamespaceURI("http://purl.org/dc/elements/1.1/");
				elem.setNamespacePrefix("dc");
				elem.insertChild(val, 0);
				root.appendChild(elem);
			}
			
			xq = "for $var in //*:contributor return $var";
			xquery = new XQuery(xq, null);
			results = xquery.execute(doc);
			nods = results.toNodes();
			for(int i = 0; i < nods.size();i++){
				String val = nods.get(i).getValue();
				Element elem = new Element("contributor");
				elem.setNamespaceURI("http://purl.org/dc/elements/1.1/");
				elem.setNamespacePrefix("dc");
				elem.insertChild(val, 0);
				root.appendChild(elem);
			}
			
			xq = "for $var in //*:date return $var";
			xquery = new XQuery(xq, null);
			results = xquery.execute(doc);
			nods = results.toNodes();
			for(int i = 0; i < nods.size();i++){
				String val = nods.get(i).getValue();
				Element elem = new Element("date");
				elem.setNamespaceURI("http://purl.org/dc/elements/1.1/");
				elem.setNamespacePrefix("dc");
				elem.insertChild(val, 0);
				root.appendChild(elem);
			}
			
			xq = "for $var in //*:type return $var";
			xquery = new XQuery(xq, null);
			results = xquery.execute(doc);
			nods = results.toNodes();
			for(int i = 0; i < nods.size();i++){
				String val = nods.get(i).getValue();
				Element elem = new Element("type");
				elem.setNamespaceURI("http://purl.org/dc/elements/1.1/");
				elem.setNamespacePrefix("dc");
				elem.insertChild(val, 0);
				root.appendChild(elem);
			}
			
			xq = "for $var in //*:format return $var";
			xquery = new XQuery(xq, null);
			results = xquery.execute(doc);
			nods = results.toNodes();
			for(int i = 0; i < nods.size();i++){
				String val = nods.get(i).getValue();
				Element elem = new Element("format");
				elem.setNamespaceURI("http://purl.org/dc/elements/1.1/");
				elem.setNamespacePrefix("dc");
				elem.insertChild(val, 0);
				root.appendChild(elem);
			}
			
			xq = "for $var in //*:identifier return $var";
			xquery = new XQuery(xq, null);
			results = xquery.execute(doc);
			nods = results.toNodes();
			for(int i = 0; i < nods.size();i++){
				String val = nods.get(i).getValue();
				Element elem = new Element("identifier");
				elem.setNamespaceURI("http://purl.org/dc/elements/1.1/");
				elem.setNamespacePrefix("dc");
				elem.insertChild(val, 0);
				root.appendChild(elem);
			}
			
			xq = "for $var in //*:source return $var";
			xquery = new XQuery(xq, null);
			results = xquery.execute(doc);
			nods = results.toNodes();
			for(int i = 0; i < nods.size();i++){
				String val = nods.get(i).getValue();
				Element elem = new Element("source");
				elem.setNamespaceURI("http://purl.org/dc/elements/1.1/");
				elem.setNamespacePrefix("dc");
				elem.insertChild(val, 0);
				root.appendChild(elem);
			}
			
			xq = "for $var in //*:language return $var";
			xquery = new XQuery(xq, null);
			results = xquery.execute(doc);
			nods = results.toNodes();
			for(int i = 0; i < nods.size();i++){
				String val = nods.get(i).getValue();
				Element elem = new Element("language");
				elem.setNamespaceURI("http://purl.org/dc/elements/1.1/");
				elem.setNamespacePrefix("dc");
				elem.insertChild(val, 0);
				root.appendChild(elem);
			}
			
			xq = "for $var in //*:relation return $var";
			xquery = new XQuery(xq, null);
			results = xquery.execute(doc);
			nods = results.toNodes();
			for(int i = 0; i < nods.size();i++){
				String val = nods.get(i).getValue();
				Element elem = new Element("relation");
				elem.setNamespaceURI("http://purl.org/dc/elements/1.1/");
				elem.setNamespacePrefix("dc");
				elem.insertChild(val, 0);
				root.appendChild(elem);
			}
			
			xq = "for $var in //*:coverage return $var";
			xquery = new XQuery(xq, null);
			results = xquery.execute(doc);
			nods = results.toNodes();
			for(int i = 0; i < nods.size();i++){
				String val = nods.get(i).getValue();
				Element elem = new Element("coverage");
				elem.setNamespaceURI("http://purl.org/dc/elements/1.1/");
				elem.setNamespacePrefix("dc");
				elem.insertChild(val, 0);
				root.appendChild(elem);
			}
			
			xq = "for $var in //*:rights return $var";
			xquery = new XQuery(xq, null);
			results = xquery.execute(doc);
			nods = results.toNodes();
			for(int i = 0; i < nods.size();i++){
				String val = nods.get(i).getValue();
				Element elem = new Element("rights");
				elem.setNamespaceURI("http://purl.org/dc/elements/1.1/");
				elem.setNamespacePrefix("dc");
				elem.insertChild(val, 0);
				root.appendChild(elem);
			}
			
			//System.out.println(XOMUtil.toPrettyXML(root));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ValidityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParsingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XQueryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String res = null;
		if(root.getChildCount() > 0){
			res = XOMUtil.toPrettyXML(root);
		}
		
		return res;
	}
	
	private BasicDBObject copyOriginalObject(BasicDBObject obj){
		BasicDBObject newObj = new BasicDBObject();
		
		newObj.put("hash", obj.getString("hash"));
		newObj.put("sets", obj.get("sets"));
		newObj.put("datasetId", obj.get("datasetId"));
		newObj.put("orgId", obj.get("orgId"));
		newObj.put("userId", obj.get("userId"));
		newObj.put("itemId", obj.get("itemId"));
		newObj.put("reportId", obj.get("reportId"));
		newObj.put("sourceItemId", obj.get("sourceItemId"));
		newObj.put("sourceDatasetId", obj.get("sourceDatasetId"));
		newObj.put("datestamp", obj.get("datestamp"));
		newObj.put("isPublished", true);
		if(obj.get("isValid") != null){
			newObj.put("isValid", obj.get("isValid"));
		}
		newObj.put("schemaId", obj.get("schemaId"));
		return newObj;
	}
	
	private BasicDBObject createPrefix(String prefix, String namespace){
		BasicDBObject obj = new BasicDBObject();
		obj.put("prefix", prefix);
		obj.put("uri", namespace);
		return obj;
	}
	
	public BasicDBObject copyCompleteDocument(BasicDBObject obj){
		BasicDBObject newObj = new BasicDBObject();
		newObj.put("hash", obj.getString("hash"));
		newObj.put("sets", obj.get("sets"));
		newObj.put("datasetId", obj.get("datasetId"));
		newObj.put("orgId", obj.get("orgId"));
		newObj.put("userId", obj.get("userId"));
		newObj.put("itemId", obj.get("itemId"));
		newObj.put("reportId", obj.get("reportId"));
		newObj.put("sourceItemId", obj.get("sourceItemId"));
		newObj.put("sourceDatasetId", obj.get("sourceDatasetId"));
		newObj.put("datestamp", obj.get("datestamp"));
		newObj.put("isPublished", true);
		if(obj.get("isValid") != null){
			newObj.put("isValid", obj.get("isValid"));
		}
		newObj.put("schemaId", obj.get("schemaId"));
		newObj.put("xmlRecord", obj.get("xmlRecord"));
		newObj.put("namespace", obj.get("namespace"));
		
		return newObj;
	}
}
