package org.smartboot.socket.spring.boot;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(SmartSocketClientProperties.PREFIX)
/**
 * <p>Configuration properties for SmartSocketClient.</p>
 *
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 * @since 1.0.0
 */
public class SmartSocketClientProperties {
	
	public static final String PREFIX = "h2.server";

	/**
	 * 消息队列缓存大小
	 */
	private int writeQueueSize = 0;

	/**
	 * 消息体缓存大小,字节
	 */
	private int readBufferSize = 512;

	/**
	 * 远程服务器IP
	 */
	private String host;

	/**
	 * 服务器端口号
	 */
	private int port = 8888;

	private boolean directBuffer;

    /**
     * <p>Returns the write queue size.</p>
     * @return the get write queue size
     */
	public int getWriteQueueSize() {
		return writeQueueSize;
	}

    /**
     * <p>Sets the write queue size.</p>
     * @param writeQueueSize
     */
	public void setWriteQueueSize(int writeQueueSize) {
		this.writeQueueSize = writeQueueSize;
	}

    /**
     * <p>Returns the read buffer size.</p>
     * @return the get read buffer size
     */
	public int getReadBufferSize() {
		return readBufferSize;
	}

    /**
     * <p>Sets the read buffer size.</p>
     * @param readBufferSize
     */
	public void setReadBufferSize(int readBufferSize) {
		this.readBufferSize = readBufferSize;
	}

    /**
     * <p>Returns the host.</p>
     * @return the get host
     */
	public String getHost() {
		return host;
	}

    /**
     * <p>Sets the host.</p>
     * @param host
     */
	public void setHost(String host) {
		this.host = host;
	}

    /**
     * <p>Returns the port.</p>
     * @return the get port
     */
	public int getPort() {
		return port;
	}

    /**
     * <p>Sets the port.</p>
     * @param port
     */
	public void setPort(int port) {
		this.port = port;
	}

    /**
     * <p>Checks if direct buffer.</p>
     * @return the is direct buffer
     */
	public boolean isDirectBuffer() {
		return directBuffer;
	}

    /**
     * <p>Sets the direct buffer.</p>
     * @param directBuffer
     */
	public void setDirectBuffer(boolean directBuffer) {
		this.directBuffer = directBuffer;
	}
	

}