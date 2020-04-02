package com.wittyhome.broker.model;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ClientHandshakeBroadcaster 
implements Broadcaster
{
	private static Logger LOG = LoggerFactory.getLogger(ClientHandshakeBroadcaster.class);
	
	private DatagramSocket socket;
	private List<InetAddress> broadcastAddresses;
	
	@Override
	public void broadcast(String broadcastMessage, InetAddress address, int port) 
	{
		try {
			socket = new DatagramSocket();
			socket.setBroadcast(true);
		} catch (SocketException e) {
			LOG.error("Broadcast failed", e);
		}
		
		byte[] buffer = broadcastMessage.getBytes();
		
		DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, port);
		
		try {
			socket.send(packet);
	        socket.close();
		} catch (IOException e) {
			LOG.error("Unable to send datagram to {}:{}", address, port, e);
		}	
	}
	
	@Override
	public void broadcast(String broadcastMessage, int port) 
	{
		if (Objects.isNull(broadcastAddresses))
			listBroadcastAddresses();
			
		broadcastAddresses.forEach(address -> {
			broadcast(broadcastMessage, address, port);
		});
	}

	@Override
	public List<InetAddress> listBroadcastAddresses() 
	{
		List<InetAddress> broadcastList = new ArrayList<>();
		Enumeration<NetworkInterface> interfaces;
		
		try {
			interfaces = NetworkInterface.getNetworkInterfaces();
			
			while (interfaces.hasMoreElements())
			{
				NetworkInterface networkInterface = interfaces.nextElement();
				
				if (networkInterface.isLoopback() || !networkInterface.isUp())
					continue;
				
				networkInterface.getInterfaceAddresses().stream()
					.map(a -> a.getBroadcast())
					.filter(Objects::nonNull)
					.forEach(broadcastList::add);
			}
		} catch (SocketException e) {
			LOG.error(e.getLocalizedMessage());
		}

		this.broadcastAddresses = broadcastList;
		
		return broadcastList;
	}
}
