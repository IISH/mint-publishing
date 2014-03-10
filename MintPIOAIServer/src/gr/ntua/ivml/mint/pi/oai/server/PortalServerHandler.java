package gr.ntua.ivml.mint.pi.oai.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.thrift.TException;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import static org.elasticsearch.index.query.FilterBuilders.*;
import static org.elasticsearch.index.query.QueryBuilders.*;

import com.mongodb.BasicDBObject;
import com.mongodb.util.JSON;

import gr.ntua.ivml.mint.pi.oai.server.interfaces.OAIServer.Iface;
import gr.ntua.ivml.mint.pi.oai.server.interfaces.ProgressResponse;
import gr.ntua.ivml.mint.pi.oai.server.interfaces.RecordNotFound;
import gr.ntua.ivml.mint.pi.oai.util.Config;

public class PortalServerHandler implements Iface {

	private DefaultHttpClient httpclient = new DefaultHttpClient();;
	private HttpGet get;
	private String host;
	private String name;
	private String type;
	private String cluster;
	private int port;
	private Client client = null;
	
	private void initValues(){
		this.host = Config.get("fashion.index.host");
		this.name = Config.get("fashion.index.name");
		this.type = Config.get("fashion.index.type");
		this.cluster = Config.get("fashion.index.cluster");
		this.port = Integer.parseInt(Config.get("fashion.index.port"));
	}
	
	public void closeClient(){
		if(this.client != null){
			this.client.close();
		}
	}
	
	private Client getClient() {
		if(client == null) {
			Settings settings = ImmutableSettings.settingsBuilder()
			.put("cluster.name", this.cluster)
			.put("client.transport.sniff", true).build();

			client = new TransportClient(settings)
	        .addTransportAddress(new InetSocketTransportAddress(this.host, this.port));			
		}
		
		return client;
	}
	
	@Override
	public String createReport(String projectName, int userId, int orgId,
			List<Integer> datasets) throws TException {
		String res = null;
		
		
		get = new HttpGet(Config.get("verb.createReport"));

		try {
			
			URI uri = new URIBuilder(get.getURI()).addParameter("datasetId", "" + datasets.get(0))
					.addParameter("orgId", "" +  orgId).build();
			get.setURI(uri);
			HttpResponse response = httpclient.execute(get);
			BufferedReader rd = new BufferedReader
					  (new InputStreamReader(response.getEntity().getContent()));
			String resp = org.apache.commons.io.IOUtils.toString(rd);
			BasicDBObject obj = (BasicDBObject) JSON.parse(resp);
			res = obj.getString("reportId");
			get.releaseConnection();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public String fetchReport(String reportId) throws TException {
		String res = null;
	
		get = new HttpGet(Config.get("verb.fetchReport"));

		try {
			
			URI uri = new URIBuilder(get.getURI()).addParameter("reportId", "" +  reportId).build();
			get.setURI(uri);
			HttpResponse response = httpclient.execute(get);
			BufferedReader rd = new BufferedReader
					  (new InputStreamReader(response.getEntity().getContent()));
			String resp = org.apache.commons.io.IOUtils.toString(rd);
			res = resp;
			get.releaseConnection();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public ProgressResponse getProgress(String reportId) throws TException {
	
		ProgressResponse res = new ProgressResponse();
		String re = this.fetchReport(reportId);
		BasicDBObject obj = (BasicDBObject) JSON.parse(re);
		
		res.setConflictedRecords(obj.getInt("invalid"));
		res.setInsertedRecords(obj.getInt("valid"));
		res.setTotalRecords(obj.getInt("valid")+obj.getInt("invalid"));
		return res;
		
	}
	
	
	@Override
	public void closeReport(String reportId) throws TException {

		
		get = new HttpGet(Config.get("verb.closeReport"));

		try {
			
			URI uri = new URIBuilder(get.getURI()).addParameter("reportId", "" +  reportId).build();
			get.setURI(uri);
			httpclient.execute(get);
			get.releaseConnection();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}		
	}

	@Override
	public void initIndex(String projectName) throws TException {
		// TODO Auto-generated method stub
		
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
		get = new HttpGet(Config.get("verb.deleteDataset"));

		try {
			
			URI uri = new URIBuilder(get.getURI()).addParameter("datasetId", "" +  datasetId).build();
			get.setURI(uri);
			httpclient.execute(get);			
			get.releaseConnection();
			//initValues();
			//this.getClient().prepareDeleteByQuery(this.name).setTypes(this.type).setQuery(termQuery("provenance.datasetId", datasetId)).execute().actionGet();
			//this.closeClient();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public List<String> getReportsByOrgId(int orgId) throws TException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getReportsByUserId(int userId) throws TException {
		// TODO Auto-generated method stub
		return null;
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

}
