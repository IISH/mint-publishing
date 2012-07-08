package gr.ntua.ivml.mint.pi.oai.server;

import java.util.ArrayList;
import java.util.List;

import org.apache.thrift.TException;
import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.util.JSON;

import gr.ntua.ivml.mint.pi.oai.server.interfaces.ProgressResponse;
import gr.ntua.ivml.mint.pi.oai.server.interfaces.OAIServer.Iface;
import gr.ntua.ivml.mint.pi.oai.server.interfaces.RecordNotFound;
import gr.ntua.ivml.mint.pi.oai.util.MongoDB;

public class OAIServerHandler implements Iface {
	
	private String extractProjectFromTopic(String topic){
		String[] vals = topic.split("\\.");
		return vals[0];
	}
	
	
	@Override
	public String createReport(String projectName, int userId, int orgId, List<Integer> datasets)
			throws TException {
		BasicDBObject report = new BasicDBObject();
		report.put("projectName", extractProjectFromTopic(projectName));
		report.put("type", "publication");
		report.put("userId", userId);
		report.put("orgId", orgId);
		report.put("datasetsId", datasets);
		report.put("datestamp", System.currentTimeMillis());
		report.put("isClosed", false);
		report.put("totalInsertedRecords", 0);
		report.put("insertedRecords", 0);
		report.put("conflictedRecords", 0);
		MongoDB.getDB().getCollection("reports").insert(report);
		return report.getString("_id");
	}

	@Override
	public String fetchReport(String reportId) throws TException {
		String res = "";
		ObjectId oId = new ObjectId(reportId);
		BasicDBObject report = (BasicDBObject) MongoDB.getDB().getCollection("reports").findOne(oId);
		res = JSON.serialize(report);
		return res;
	}

	@Override
	public void closeReport(String reportId) throws TException {
		ObjectId oId = new ObjectId(reportId);
		BasicDBObject report = (BasicDBObject) MongoDB.getDB().getCollection("reports").findOne(oId);
		report.put("isClosed", true);
		ProgressResponse resp = getProgress(reportId);
		report.put("insertedRecords", resp.getInsertedRecords());
		report.put("conflictedRecords", resp.getConflictedRecords());
		report.put("totalInsertedRecords", resp.getTotalRecords());
		MongoDB.getDB().getCollection("reports").save(report);
	}

	@Override
	public ProgressResponse getProgress(String reportId) throws TException {
		ProgressResponse res = new ProgressResponse();
		ObjectId oId = new ObjectId(reportId);
		BasicDBObject report = (BasicDBObject) MongoDB.getDB().getCollection("reports").findOne(oId);
		BasicDBObject query = new BasicDBObject("reportId", reportId);
		int inserted = MongoDB.getDB().getCollection(report.getString("projectName")).find(query).count();
		int conflicted = MongoDB.getDB().getCollection("conflicts").find(query).count();
		res.setConflictedRecords(conflicted);
		res.setInsertedRecords(inserted);
		res.setTotalRecords(inserted+conflicted);
		return res;
	}

	@Override
	public void initIndex(String projectName) throws TException {
		BasicDBObject itemIndex = new BasicDBObject();
		itemIndex.put("itemId", 1);
		itemIndex.put("isPublished", 1);
		MongoDB.getDB().getCollection(extractProjectFromTopic(projectName)).ensureIndex(itemIndex);
		BasicDBObject datasetIndex = new BasicDBObject();
		datasetIndex.put("datasetId", 1);
		datasetIndex.put("isPublished", 1);
		MongoDB.getDB().getCollection(extractProjectFromTopic(projectName)).ensureIndex(datasetIndex);
		BasicDBObject reportIndex = new BasicDBObject();
		reportIndex.put("reportId", 1);
		MongoDB.getDB().getCollection(extractProjectFromTopic(projectName)).ensureIndex(reportIndex);
		BasicDBObject setIndex = new BasicDBObject();
		setIndex.put("sets", 1);
		setIndex.put("isPublished", 1);
		setIndex.put("namespace.prefix", 1);
		MongoDB.getDB().getCollection(extractProjectFromTopic(projectName)).ensureIndex(setIndex);
	}


	@Override
	public List<String> getReportsByOrgId(int orgId) throws TException {
		ArrayList<String> res = new ArrayList<String>();
		BasicDBObject query = new BasicDBObject();
		query.put("orgId", orgId);
		DBCursor cur = MongoDB.getDB().getCollection("reports").find(query);
		while(cur.hasNext()){
			BasicDBObject obj = (BasicDBObject) cur.next();
			String str = JSON.serialize(obj);
			res.add(str);
		}
		return res;
	}

	@Override
	public List<String> getReportsByUserId(int userId) throws TException {
		ArrayList<String> res = new ArrayList<String>();
		BasicDBObject query = new BasicDBObject();
		query.put("userId", userId);
		DBCursor cur = MongoDB.getDB().getCollection("reports").find(query);
		while(cur.hasNext()){
			BasicDBObject obj = (BasicDBObject) cur.next();
			String str = JSON.serialize(obj);
			res.add(str);
		}
		return res;
	}

	@Override
	public List<String> getReportsByDatasetId(int datasetId) throws TException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isRecordPublished(long recordId, String projectName)
			throws TException {
		
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isDatasetPublished(int datasetId, String projectName)
			throws TException {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public void publishRecordByRecordId(long recordId, String projectName,
			int userId, int orgId) throws RecordNotFound, TException {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void unpublishRecordByRecordId(long recordId, String projectName,
			int userId, int orgId) throws RecordNotFound, TException {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void unpublishRecordsByOrgId(int orgId, int userId,
			String projectName) throws TException {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void unpublishRecordsByDatasetId(int orgId, int userId,
			String projectName, int datasetId) throws TException {
		
		BasicDBObject query = new BasicDBObject();
		query.put("sourceDatasetId", datasetId);
		query.put("isPublished", true);
		int number = MongoDB.getDB().getCollection(extractProjectFromTopic(projectName)).find(query).count();
		if(number > 0){
			BasicDBObject updateQuery = new BasicDBObject();
			updateQuery.put("$set", new BasicDBObject("isPublished",false));
			MongoDB.getDB().getCollection(extractProjectFromTopic(projectName)).update(query, updateQuery, false, true);
		}
		BasicDBObject report = new BasicDBObject();
		report.put("userId", userId);
		report.put("orgId", orgId);
		report.put("datasetId",datasetId);
		report.put("projectName", extractProjectFromTopic(projectName));
		report.put("datestamp", System.currentTimeMillis());
		report.put("type", "UnpublishDataset");
		report.put("NumberofRecords", number);
		MongoDB.getDB().getCollection("reports").insert(report);
	}
}
