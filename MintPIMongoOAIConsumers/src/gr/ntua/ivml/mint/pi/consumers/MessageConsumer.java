package gr.ntua.ivml.mint.pi.consumers;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeoutException;

import org.apache.thrift.TDeserializer;
import org.apache.thrift.TException;

import gr.ntua.ivml.mint.pi.consumers.strategies.MessageConsumerStrategy;
import gr.ntua.ivml.mint.pi.consumers.util.Config;
import gr.ntua.ivml.mint.pi.messages.ItemMessage;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.ConsumerCancelledException;
import com.rabbitmq.client.QueueingConsumer;
import com.rabbitmq.client.ShutdownSignalException;

public class MessageConsumer implements Runnable{

	private ConnectionFactory factory;
	private Connection connection;
	private Channel channel;
	private QueueingConsumer consumer;
	private QueueingConsumer.Delivery delivery;
	private ClassLoader classLoader;
	private MessageProcessingContext context;
	private TDeserializer td;
	
	public MessageConsumer(){
		factory = new ConnectionFactory();
		try {
			factory.setUri(Config.get("queue.host"));
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (KeyManagementException e) {
			e.printStackTrace();
		}
		classLoader = MessageConsumer.class.getClassLoader();
		context = new MessageProcessingContext();
		td = new TDeserializer();
		
		try {
			
			String className = Config.get("processing.class");
			Class<MessageConsumerStrategy> claz = (Class<MessageConsumerStrategy>) classLoader.loadClass(className);
			context.setStrategy(claz.newInstance());
			
			connection = factory.newConnection();
			channel = connection.createChannel();
			channel.exchangeDeclare(Config.get("queue.exchangeName"), "topic", true);
			channel.queueBind(Config.get("queue.queueName"), Config.get("queue.exchangeName"), Config.get("queue.bindingTopic"));
			consumer = new QueueingConsumer(channel);
			channel.basicConsume(Config.get("queue.queueName"), true, consumer);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (TimeoutException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		while(true){
			try {
				delivery = consumer.nextDelivery();
				String topic = delivery.getEnvelope().getRoutingKey();
				ItemMessage msg = new ItemMessage();
				td.deserialize(msg, delivery.getBody());
				context.executeStrategy(msg, topic);
			} catch (ShutdownSignalException e) {
				e.printStackTrace();
			} catch (ConsumerCancelledException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (TException e) {
				e.printStackTrace();
			}
		}
	}

}
