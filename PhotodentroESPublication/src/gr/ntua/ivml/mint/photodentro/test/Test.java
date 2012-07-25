package gr.ntua.ivml.mint.photodentro.test;

import gr.ntua.ivml.mint.photodentro.es.Publisher;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Test {

	private static String readFileAsString(String filePath)
		    throws java.io.IOException{
		        StringBuffer fileData = new StringBuffer(1000);
		        BufferedReader reader = new BufferedReader(
		                new FileReader(filePath));
		        char[] buf = new char[1024];
		        int numRead=0;
		        while((numRead=reader.read(buf)) != -1){
		            fileData.append(buf, 0, numRead);
		        }
		        reader.close();
		        return fileData.toString();
		    }
	
	
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		String xml = readFileAsString("C:\\data\\photodentro\\test.xml");
		Publisher publisher = new Publisher("oreo.image.ntua.gr", 9300);
		//publisher.publish(xml, "ntua", 1);
		publisher.delete("ntua");
		publisher.close();
	}

}
