package gr.ntua.ivml.mint.pi.test;

import org.apache.thrift.TException;
import org.apache.thrift.TSerializer;

import gr.ntua.ivml.mint.pi.messages.ItemMessage;
import gr.ntua.ivml.mint.pi.messages.Namespace;

public class ThriftTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Namespace pref = new Namespace("ese", "http://something.com/");
//		ItemMessage msg = new ItemMessage(1212121212, "value", 1567, 12345, pref, 31312321321l, 1350, "euscreen");
//		
//		TSerializer ts = new TSerializer();
//		try {
//			byte[] val = ts.serialize(msg);
//			System.out.println(val.length);
//		} catch (TException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}

}
