package org.graphstream.netlogo.extension.sender;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import org.graphstream.stream.binary.ByteProxy;
//import org.graphstream.stream.netstream.NetStreamSender;
import org.graphstream.stream.netstream.NetStreamUtils;
import org.graphstream.stream.sync.SourceTime;
import org.nlogo.api.ExtensionException;

/**
 * A sender.
 * 
 * A NetStreamSender plus helper methods that do the real job for all the sender
 * primitives.
 * 
 * @author Stefan
 * 
 */
public class GSSender {
	protected String sourceId;
	protected SourceTime sourceTime;
	//protected NetStreamSender nsSender;
    protected ByteProxy client;

	public GSSender(String sourceId, SourceTime sourceTime, String host,int port) throws ExtensionException {
		this.sourceId = sourceId;
		this.sourceTime = sourceTime;
		try {
            client = new ByteProxy(NetStreamUtils.getDefaultNetStreamFactory(), ByteProxy.Mode.CLIENT,InetAddress.getLocalHost(), port);
			//nsSender = new NetStreamSender(host, port);
		} catch (UnknownHostException e) {
			throw new ExtensionException(e.getMessage());
		} catch (IOException e) {
			throw new ExtensionException(e.getMessage());
		}
        //client.start();
		//client.run();
	}

	public void sendNodeAdded(long nodeId) {
		client.nodeAdded(sourceId, newEvent(), nodeId + "");
	}

	public void sendEdgeAdded(long fromId, long toId, boolean directed) {
		client.edgeAdded(sourceId, newEvent(), fromId + "_" + toId, fromId
				+ "", toId + "", directed);
	}

	public void sendNodeRemoved(long nodeId) {
		client.nodeRemoved(sourceId, newEvent(), nodeId + "");
	}

	public void sendEdgeRemoved(long fromId, long toId) {
		client.edgeRemoved(sourceId, newEvent(), fromId + "_" + toId);
	}

	public void sendGraphAttributeAdded(String attribute, Object value) {
		client.graphAttributeAdded(sourceId, newEvent(), attribute, value);
	}

	public void sendNodeAttributeAdded(long nodeId, String attribute,Object value) {
		client.nodeAttributeAdded(sourceId, newEvent(), nodeId + "", attribute, value);
	}

	public void sendEdgeAttributeAdded(long fromId, long toId, String attribute, Object value) {
		client.edgeAttributeAdded(sourceId, newEvent(), fromId + "_" + toId, attribute, value);
	}

	public void sendGraphAttributeRemoved(String attribute) {
		client.graphAttributeRemoved(sourceId, newEvent(), attribute);
	}

	public void sendNodeAttributeRemoved(long nodeId, String attribute) {
		client.nodeAttributeRemoved(sourceId, newEvent(), nodeId + "",
				attribute);
	}

	public void sendEdgeAttributeRemoved(long fromId, long toId,
			String attribute) {
		client.edgeAttributeRemoved(sourceId, newEvent(),
				fromId + "_" + toId, attribute);
	}

	public void sendGraphCleared() {
		client.graphCleared(sourceId, newEvent());
	}

	public void sendStepBegins(double step) {
		client.stepBegins(sourceId, newEvent(), step);
	}

	public void close() throws ExtensionException, InterruptedException, IOException {
		client.stop();
	}

	protected long newEvent() {
		return sourceTime.newEvent();
	}

}