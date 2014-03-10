package gr.ntua.ivml.mint.pi.consumers.strategies;

import gr.ntua.ivml.mint.pi.messages.ItemMessage;

public interface MessageConsumerStrategy {
	void consume(ItemMessage msg, String topic);
}
