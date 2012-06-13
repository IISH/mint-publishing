package gr.ntua.ivml.mint.pi.consumers.strategies;


import gr.ntua.ivml.mint.pi.consumers.util.MongoUtils;
import gr.ntua.ivml.mint.pi.messages.ItemMessage;

public class OAIInsertionConsumerStrategy implements MessageConsumerStrategy {
	
	MongoUtils util;
	
	public OAIInsertionConsumerStrategy(){
		util = new MongoUtils();
	}
	
	@Override
	public void consume(ItemMessage msg, String topic) {
		String[] vals = topic.split("\\.");
		util.insertOAIRecord(msg, vals[0]);
	}

}
