package org.smartboot.socket.spring.boot.hooks;

import org.smartboot.socket.transport.AioQuickServer;

@SuppressWarnings("rawtypes")
/**\n * Auto-configuration for AioQuickServerShutdownHook.\n *\n * @author [@Loong Wan](https://github.com/loong10k)\n * @since 1.0.0\n */
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
