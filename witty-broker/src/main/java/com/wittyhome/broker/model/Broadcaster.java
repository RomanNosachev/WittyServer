package com.wittyhome.broker.model;

import java.net.InetAddress;
import java.util.List;

public interface Broadcaster 
{
	void broadcast(String broadcastMessage, int port);
	void broadcast(String broadcastMessage, InetAddress address, int port);
	
	List<InetAddress> listBroadcastAddresses();
}
