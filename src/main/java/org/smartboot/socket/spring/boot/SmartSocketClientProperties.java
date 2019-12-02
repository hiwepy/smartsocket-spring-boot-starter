package org.smartboot.socket.spring.boot;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(SmartSocketClientProperties.PREFIX)
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

	public int getWriteQueueSize() {
		return writeQueueSize;
	}

	public void setWriteQueueSize(int writeQueueSize) {
		this.writeQueueSize = writeQueueSize;
	}

	public int getReadBufferSize() {
		return readBufferSize;
	}

	public void setReadBufferSize(int readBufferSize) {
		this.readBufferSize = readBufferSize;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public boolean isDirectBuffer() {
		return directBuffer;
	}

	public void setDirectBuffer(boolean directBuffer) {
		this.directBuffer = directBuffer;
	}
	

}