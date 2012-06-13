package gr.ntua.ivml.mint.pi.test;

import gr.ntua.ivml.mint.pi.consumers.MessageConsumer;
import gr.ntua.ivml.mint.pi.consumers.util.Config;

public class ConsumerTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//System.out.println(Config.get("queue.host"));
		for(int i = 0; i < 10;i++){
			MessageConsumer con = new MessageConsumer();
			Thread thread = new Thread(con, "consumer #"+i);
			thread.start();
		}
	}

}
