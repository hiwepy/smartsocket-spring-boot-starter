package org.smartboot.socket.spring.boot;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smartboot.socket.MessageProcessor;
import org.smartboot.socket.spring.boot.ext.StringMessageClientProcessor;
import org.smartboot.socket.spring.boot.ext.StringMessageServerProcessor;
import org.smartboot.socket.spring.boot.ext.StringProtocol;
import org.smartboot.socket.spring.boot.hooks.AioQuickClientShutdownHook;
import org.smartboot.socket.spring.boot.hooks.AioQuickServerShutdownHook;
import org.smartboot.socket.transport.AioQuickClient;
import org.smartboot.socket.transport.AioQuickServer;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 
 */
@Configuration
@ConditionalOnClass(MessageProcessor.class)
@ConditionalOnProperty(prefix = SmartSocketServerProperties.PREFIX, value = "enabled", havingValue = "true")
@EnableConfigurationProperties({ SmartSocketServerProperties.class, SmartSocketClientProperties.class })
@AutoConfigureBefore(DataSourceAutoConfiguration.class)
public class SmartSocketAutoConfiguration {

	public Logger logger = LoggerFactory.getLogger(getClass());
	
	public AioQuickServer<String> aioServer(StringProtocol protocol, 
			StringMessageServerProcessor processor,
			SmartSocketServerProperties properties) throws IOException {
		
		AioQuickServer<String> server = new AioQuickServer<String>(properties.getHost(), properties.getPort()
				, protocol, processor);
		
		server.setBannerEnabled(properties.isBannerEnabled());
		server.setDirectBuffer(properties.isDirectBuffer());
		server.setHost(properties.getHost());
		// 设置AioSession读缓存区长度
		server.setReadBufferSize(properties.getReadBufferSize());
		// Server服务线程数
		server.setThreadNum(properties.getThreadNum());
		// 设置AioSession输出缓存区长度
		server.setWriteQueueSize(properties.getWriteQueueSize());
		
        server.start();
        
        /**
		 * 应用退出时，要调用shutdown来清理资源，关闭网络连接，从RocketMQ服务器上注销自己
		 * 注意：我们建议应用在JBOSS、Tomcat等容器的退出钩子里调用shutdown方法
		 */
		Runtime.getRuntime().addShutdownHook(new AioQuickServerShutdownHook(server));
		
		return server;
	}
	
	public AioQuickClient<String> aioClient(StringProtocol protocol, StringMessageClientProcessor processor,
			 SmartSocketClientProperties properties) throws IOException, ExecutionException, InterruptedException {
		
		
         AioQuickClient<String> client= new AioQuickClient<String>(properties.getHost(), properties.getPort(), protocol, processor);
     
         client.setDirectBuffer(properties.isDirectBuffer());
         // 设置读缓冲区大小
         client.setReadBufferSize(properties.getReadBufferSize());
         // 设置AioSession输出缓存区队列长度
         client.setWriteQueueSize(properties.getWriteQueueSize());
         
         client.start();
         /**
 		 * 应用退出时，要调用shutdown来清理资源，关闭网络连接，从RocketMQ服务器上注销自己
 		 * 注意：我们建议应用在JBOSS、Tomcat等容器的退出钩子里调用shutdown方法
 		 */
 		Runtime.getRuntime().addShutdownHook(new AioQuickClientShutdownHook(client));   
         
		return client;
	}
	
     

}
