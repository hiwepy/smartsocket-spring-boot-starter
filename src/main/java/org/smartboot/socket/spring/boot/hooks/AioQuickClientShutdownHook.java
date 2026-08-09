package org.smartboot.socket.spring.boot.hooks;

import org.smartboot.socket.transport.AioQuickClient;

@SuppressWarnings("rawtypes")
/**\n * Auto-configuration for AioQuickClientShutdownHook.\n *\n * @author [@Loong Wan](https://github.com/loong10k)\n * @since 1.0.0\n */
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
