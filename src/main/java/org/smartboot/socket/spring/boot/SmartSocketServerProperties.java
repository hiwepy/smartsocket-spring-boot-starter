package org.smartboot.socket.spring.boot;

import java.util.NoSuchElementException;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(SmartSocketServerProperties.PREFIX)
/**
 * <p>Configuration properties for SmartSocketServer.</p>
 *
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 * @since 1.0.0
 */
public class SmartSocketServerProperties {
	
	public static final String PREFIX = "h2.server";

	// type of server
	public enum Protocol {

		HTTP(0), TCP(1), PG(2);

		private final int protocol;

		Protocol(int protocol) {
			this.protocol = protocol;
		}

    /**
     * <p>Returns the get.</p>
     * @return the get
     */
		public int get() {
			return protocol;
		}

    /**
     * <p>Equals.</p>
     * @param protocol
     * @return the equals
     */
		public boolean equals(Protocol protocol) {
			return this.compareTo(protocol) == 0;
		}

    /**
     * <p>Equals.</p>
     * @param protocol
     * @return the equals
     */
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
		flowLimitLine = (int) (writeQueueSize * limitRate);
		releaseLine = (int) (writeQueueSize * releaseRate);
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

	int getFlowLimitLine() {
		return flowLimitLine;
	}

	int getReleaseLine() {
		return releaseLine;
	}

    /**
     * <p>Checks if banner enabled.</p>
     * @return the is banner enabled
     */
	public boolean isBannerEnabled() {
		return bannerEnabled;
	}

    /**
     * <p>Sets the banner enabled.</p>
     * @param bannerEnabled
     */
	public void setBannerEnabled(boolean bannerEnabled) {
		this.bannerEnabled = bannerEnabled;
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