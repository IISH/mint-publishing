package gr.ntua.ivml.mint.pi.consumers.util;

import java.util.ArrayList;
import java.util.Iterator;

import gr.ntua.ivml.mint.pi.identifiers.generators.SHA1Generator;
import gr.ntua.ivml.mint.pi.messages.ExtendedParameter;
import gr.ntua.ivml.mint.pi.messages.ItemMessage;
import gr.ntua.ivml.mint.pi.messages.Namespace;
import gr.ntua.ivml.mint.pi.messages.SchemaValidation;

import com.mongodb.BasicDBObject;

public class ThriftToJSON {

	private SHA1Generator gen;
	
	public ThriftToJSON(){
		gen = new SHA1Generator();
	}
	
	public BasicDBObject toJSON(ItemMessage msg){
		
		BasicDBObject document = new BasicDBObject();
		
		document.put("record", msg.getXml());
		document.put("hash", gen.generate(msg.getXml()));
		ArrayList<Integer> sets = new ArrayList<Integer>();
		sets.add(msg.getDataset_id());
		sets.add(msg.getOrg_id());
		sets.add(msg.getUser_id());
		document.put("sets", sets);
		
		document.put("datasetId", msg.getDataset_id());
		document.put("mintOrgId", msg.getOrg_id());
		document.put("userId", msg.getUser_id());
		document.put("recordId", msg.getItem_id());
		
		Iterator<ExtendedParameter> itr = msg.getParamsIterator();
		
		while(itr.hasNext()){
			ExtendedParameter param = itr.next();
			if(param.getParameterName().equals("reportId")){
				document.put("reportId", param.getParameterValue());
			}
		}
		
		document.put("sourceItemId", msg.getSourceItem_id());
		document.put("sourceDatasetId", msg.getSourceDataset_id());
		document.put("datestamp", msg.getDatestamp());
		
		if(msg.getValid() != null){
			SchemaValidation validation = msg.getValid();
			BasicDBObject valid = new BasicDBObject();
			valid.put("schemaId", validation.getSchema_id());
			valid.put("isValid", validation.isValid());
			document.put("isValid", valid);
		}
		
		Namespace namespace = msg.getPrefix();
		BasicDBObject prefix = new BasicDBObject();
		prefix.put("prefix", namespace.getPrefix());
		prefix.put("uri", namespace.getUri());
		document.put("namespace", prefix);
		document.put("schemaId", msg.getSchema_id());
		
		return document;
	}
}
