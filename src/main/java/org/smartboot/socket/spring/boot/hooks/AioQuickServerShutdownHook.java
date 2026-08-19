package org.smartboot.socket.spring.boot.hooks;

import org.smartboot.socket.transport.AioQuickServer;

@SuppressWarnings("rawtypes")
/**\n * Auto-configuration for AioQuickServerShutdownHook.\n *\n * @author <a href="https://github.com/loong10k">Loong Wan</a>\n * @since 1.0.0\n */
public class AioQuickServerShutdownHook extends Thread {

	private AioQuickServer server;
	
	public AioQuickServerShutdownHook(AioQuickServer server) {
		this.server = server;
	}
	
	@Override
    /**
     * <p>Run.</p>
     */
	public void run() {
		server.shutdown();
	}

}
