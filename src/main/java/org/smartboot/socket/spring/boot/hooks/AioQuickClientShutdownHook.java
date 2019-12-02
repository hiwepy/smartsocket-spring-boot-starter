package org.smartboot.socket.spring.boot.hooks;

import org.smartboot.socket.transport.AioQuickClient;

@SuppressWarnings("rawtypes")
public class AioQuickClientShutdownHook extends Thread{
	

	private AioQuickClient client;

	public AioQuickClientShutdownHook(AioQuickClient client) {
		this.client = client;
	}

	@Override
	public void run() {
		client.shutdown();
	}
	
	
	
}
