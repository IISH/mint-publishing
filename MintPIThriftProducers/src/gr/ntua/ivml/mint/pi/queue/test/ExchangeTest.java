package gr.ntua.ivml.mint.pi.queue.test;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import gr.ntua.ivml.mint.pi.messages.ExtendedParameter;
import gr.ntua.ivml.mint.pi.messages.ItemMessage;
import gr.ntua.ivml.mint.pi.messages.Namespace;
import gr.ntua.ivml.mint.pi.queue.producers.RecordMessageProducer;

public class ExchangeTest {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		RecordMessageProducer prod = new RecordMessageProducer("localhost","mint");
		String path = "c:\\data\\qtest\\";
		File dir = new File(path);
		String[] children = dir.list();
		FileInputStream fstream;
		DataInputStream in;
		BufferedReader br;
		String val = "";
		Namespace pref = new Namespace("eus", "http://www.euscreen.eu/schemas/euscreen/");
		ExtendedParameter param = new ExtendedParameter();
		param.setParameterName("reportId");
		param.setParameterValue("3e2d1412de123e4wed12443rf23t24");
		ItemMessage msg;
		int counter = 0;
		ArrayList<ExtendedParameter> params = new ArrayList<ExtendedParameter>();
		params.add(param);
		for(int i = 0; i < children.length;i++){
			//System.out.println(path+children[i]);
			fstream = new FileInputStream(path+children[i]);
			in = new DataInputStream(fstream);
			br = new BufferedReader(new InputStreamReader(in));
			String strLine;
			while ((strLine = br.readLine()) != null)   {
				val += strLine;
			}
			in.close();
			br.close();
			fstream.close();
			msg = new ItemMessage(counter, val, 123456, 654321, pref, 1212121212121212l, 123, "euscreen", 3123123151543l, 4321, 1000);
			msg.setParams(params);
			//msg.params.add(param);
			prod.send(msg, "euscreen.oai");
			counter++;
			val = "";
		}
//		Namespace pref = new Namespace("ese", "http://something.com/");
//		ItemMessage msg = new ItemMessage(1212121212, "value", 1567, 12345, pref, 31312321321l, 1350, "euscreen");
//		
//		for(int i = 0;i < 1000;i++){
//			prod.send(msg, "euscreen.oai");
//		}
//		
		prod.close();
	}

}
