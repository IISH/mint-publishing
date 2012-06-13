package gr.ntua.ivml.mint.pi.oai.server;

import gr.ntua.ivml.mint.pi.oai.server.interfaces.OAIServer;
import gr.ntua.ivml.mint.pi.oai.util.Config;

import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.server.TThreadPoolServer.Args;
import org.apache.thrift.transport.TFastFramedTransport;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;
import org.apache.thrift.transport.TTransportException;

public class OAIService {

	/**
	 * @param args
	 * @throws TTransportException 
	 */
	public static void main(String[] args) throws TTransportException {
		final TServerTransport socket = new TServerSocket(Config.getInt("oai.service.port"));
		final OAIServer.Processor<OAIServer.Iface> processor = new OAIServer.Processor<OAIServer.Iface>(new OAIServerHandler());
		Args arguments = new Args(socket);
		arguments.processor(processor);
		arguments.protocolFactory(new TBinaryProtocol.Factory());
		arguments.transportFactory(new TFastFramedTransport.Factory());
		final TServer server = new TThreadPoolServer(arguments);
		server.serve();
	}

}
