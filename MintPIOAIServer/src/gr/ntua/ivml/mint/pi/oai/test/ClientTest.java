package gr.ntua.ivml.mint.pi.oai.test;

import gr.ntua.ivml.mint.pi.oai.client.OAIServiceClient;

public class ClientTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		OAIServiceClient client = new OAIServiceClient("localhost", 3009);
//		String res = client.createReport("euscreen", 1000, 1001);
//		System.out.println(res);
		//client.initIndex("euscreen");
		client.unpublishRecordsByDatasetId(654321, 123, "euscreen.oai", 123456);
		client.close();
	}

}
