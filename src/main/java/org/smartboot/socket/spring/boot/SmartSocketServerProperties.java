package org.smartboot.socket.spring.boot;

import java.util.NoSuchElementException;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(SmartSocketServerProperties.PREFIX)
public class SmartSocketServerProperties {
	
	public static final String PREFIX = "h2.server";

	// type of server
	public enum Protocol {

		HTTP(0), TCP(1), PG(2);

		private final int protocol;

		Protocol(int protocol) {
			this.protocol = protocol;
		}

		public int get() {
			return protocol;
		}

		public boolean equals(Protocol protocol) {
			return this.compareTo(protocol) == 0;
		}

		public boolean equals(int protocol) {
			return this.compareTo(Protocol.valueOfIgnoreCase(protocol)) == 0;
		}

		public static Protocol valueOfIgnoreCase(int key) {
			for (Protocol protocol : Protocol.values()) {
				if (protocol.get() == key) {
					return protocol;
				}
			}
			throw new NoSuchElementException("Cannot found Protocol with key '" + key + "'.");
		}

	}
	
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
	 * 服务器处理线程数
	 */
	private int threadNum = Runtime.getRuntime().availableProcessors();

	private float limitRate = 0.9f;

	private float releaseRate = 0.6f;
	/**
	 * 流控指标线
	 */
	private int flowLimitLine = (int) (writeQueueSize * limitRate);

	/**
	 * 释放流控指标线
	 */
	private int releaseLine = (int) (writeQueueSize * releaseRate);

	/**
	 * 是否启用控制台banner
	 */
	private boolean bannerEnabled = true;
	
	public final String getHost() {
		return host;
	}

	public final void setHost(String host) {
		this.host = host;
	}

	public final int getPort() {
		return port;
	}

	public final void setPort(int port) {
		this.port = port;
	}

	public final int getThreadNum() {
		return threadNum;
	}

	public final void setThreadNum(int threadNum) {
		this.threadNum = threadNum;
	}

	public int getWriteQueueSize() {
		return writeQueueSize;
	}

	public void setWriteQueueSize(int writeQueueSize) {
		this.writeQueueSize = writeQueueSize;
		flowLimitLine = (int) (writeQueueSize * limitRate);
		releaseLine = (int) (writeQueueSize * releaseRate);
	}

	public int getReadBufferSize() {
		return readBufferSize;
	}

	public void setReadBufferSize(int readBufferSize) {
		this.readBufferSize = readBufferSize;
	}

	int getFlowLimitLine() {
		return flowLimitLine;
	}

	int getReleaseLine() {
		return releaseLine;
	}

	public boolean isBannerEnabled() {
		return bannerEnabled;
	}

	public void setBannerEnabled(boolean bannerEnabled) {
		this.bannerEnabled = bannerEnabled;
	}

	public boolean isDirectBuffer() {
		return directBuffer;
	}

	public void setDirectBuffer(boolean directBuffer) {
		this.directBuffer = directBuffer;
	}
 

}