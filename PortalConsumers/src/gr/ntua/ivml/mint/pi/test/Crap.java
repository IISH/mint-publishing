package gr.ntua.ivml.mint.pi.test;

import gr.ntua.ivml.mint.pi.consumers.util.MongoDB;

import java.util.ArrayList;

import com.mongodb.BasicDBObject;

public class Crap {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BasicDBObject obj = new BasicDBObject();
		obj.put("id", "projects");
		
		ArrayList<BasicDBObject> projs = new ArrayList<BasicDBObject>();
		BasicDBObject proj = new BasicDBObject();
		proj.put("projectName", "lh");
		proj.put("title", "Linked Heritage");
		proj.put("description", "Coordination of standards and technologies for the enrichment of EUROPEANA");
		proj.put("image", "europeana2.png");
		projs.add(proj);
		proj = new BasicDBObject();
		proj.put("projectName", "linkedheritage");
		proj.put("title", "Linked Heritage");
		proj.put("description", "Coordination of standards and technologies for the enrichment of EUROPEANA");
		proj.put("image", "europeana4.png");
		projs.add(proj);
		proj = new BasicDBObject();
		proj.put("projectName", "linkedathena");
		proj.put("title", "Athena Project");
		proj.put("description", "Access to cultural heritage networks across Europe");
		proj.put("image", "europeana3.png");
		projs.add(proj);
		
		obj.put("projects", projs);
		MongoDB.getDB().getCollection("metadata").insert(obj);
	}

}
