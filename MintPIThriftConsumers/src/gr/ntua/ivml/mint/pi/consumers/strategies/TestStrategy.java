package gr.ntua.ivml.mint.pi.consumers.strategies;

import gr.ntua.ivml.mint.pi.messages.ItemMessage;

public class TestStrategy implements MessageConsumerStrategy {

	@Override
	public void consume(ItemMessage msg, String topic) {
		System.out.println(msg.toString());
	}

}
