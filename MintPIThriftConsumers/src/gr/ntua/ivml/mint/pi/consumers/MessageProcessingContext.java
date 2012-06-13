package gr.ntua.ivml.mint.pi.consumers;

import gr.ntua.ivml.mint.pi.consumers.strategies.MessageConsumerStrategy;

import gr.ntua.ivml.mint.pi.messages.ItemMessage;

public class MessageProcessingContext {
	private MessageConsumerStrategy strategy;
	
	public MessageProcessingContext(MessageConsumerStrategy strategy){ this.strategy = strategy; }
	public MessageProcessingContext(){}
	
	public void executeStrategy(ItemMessage message, String topic){  this.strategy.consume(message, topic); }
	public void setStrategy(MessageConsumerStrategy strategy){ this.strategy = strategy; }
}
