package gr.ntua.ivml.mint.pi.consumers.util;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;

import gr.ntua.ivml.mint.pi.messages.ItemMessage;

public class MongoUtils {
	
	ThriftToJSON transformer;
	BasicDBObject obj;
	OAIDCGenerator dcgen;
	BasicDBObject temp;
	
	public MongoUtils(){
		transformer = new ThriftToJSON();
		dcgen = new OAIDCGenerator();
	}
	
	public void insertOAIRecord(ItemMessage msg, String collectionName){
		obj = transformer.toJSON(msg);
		BasicDBObject obj_copy = transformer.toJSON(msg);
		
		if(!recordExists(obj, collectionName)){
			obj.put("isPublished", true);
			MongoDB.getDB().getCollection(collectionName).insert(obj);

			BasicDBObject  lala = (BasicDBObject) obj.get("namespace");
			String prefix  = lala.getString("prefix");
			if (prefix.equals("rdf")){
			temp = dcgen.getOAIDCObject(obj_copy);
			
			if (temp != null){
				MongoDB.getDB().getCollection(collectionName).insert(temp);
				temp = null;
			}
		}
		}else{
			insertOAIDuplicateRecord(obj);
		}
	}
	
	private void insertOAIDuplicateRecord(BasicDBObject document){
		BasicDBObject conflictedRecord = new BasicDBObject();
		conflictedRecord.put("reportId", document.get("reportId"));
		conflictedRecord.put("hash", document.get("hash"));
		conflictedRecord.put("datestamp", document.get("datestamp"));
		conflictedRecord.put("itemId", document.get("itemId"));
		MongoDB.getDB().getCollection("conflicts").insert(conflictedRecord);
	}
	
	private boolean recordExists(BasicDBObject document, String collectionName){
		boolean res = false;
		res = idExist(document.getString("hash"), collectionName);
		return res;
	}
	
	private boolean idExist(String id, String collectionName){
		boolean res = false;
		BasicDBObject q = new BasicDBObject();
		q.put("hash", id);
		DBCursor cur = MongoDB.getDB().getCollection(collectionName).find(q);
		if(cur.length() > 0){
			res = true;
		}
		return res;
	}
	
}
