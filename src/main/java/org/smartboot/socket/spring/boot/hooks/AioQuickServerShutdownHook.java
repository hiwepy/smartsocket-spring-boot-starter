package org.smartboot.socket.spring.boot.hooks;

import org.smartboot.socket.transport.AioQuickServer;

@SuppressWarnings("rawtypes")
public class AioQuickServerShutdownHook extends Thread {

	private AioQuickServer server;
	
	public AioQuickServerShutdownHook(AioQuickServer server) {
		this.server = server;
	}
	
	@Override
	public void run() {
		server.shutdown();
	}

}
