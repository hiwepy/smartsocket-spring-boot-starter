package org.smartboot.socket.spring.boot;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit tests for {@link SmartSocketClientProperties}.
 *
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 * @since 1.0.0
 */
@DisplayName("SmartSocketClientProperties Tests")
class SmartSocketClientPropertiesTest {

    @Test
    @DisplayName("Default values are set correctly")
    void testDefaultValues() {
        SmartSocketClientProperties props = new SmartSocketClientProperties();
        assertThat(props.getHost()).isNull();
        assertThat(props.getPort()).isEqualTo(8888);
        assertThat(props.getWriteQueueSize()).isEqualTo(0);
        assertThat(props.getReadBufferSize()).isEqualTo(512);
        assertThat(props.isDirectBuffer()).isFalse();
    }

    @Test
    @DisplayName("All properties can be set and retrieved")
    void testSettersAndGetters() {
        SmartSocketClientProperties props = new SmartSocketClientProperties();
        props.setHost("192.168.1.1");
        props.setPort(9999);
        props.setWriteQueueSize(100);
        props.setReadBufferSize(1024);
        props.setDirectBuffer(true);

        assertThat(props.getHost()).isEqualTo("192.168.1.1");
        assertThat(props.getPort()).isEqualTo(9999);
        assertThat(props.getWriteQueueSize()).isEqualTo(100);
        assertThat(props.getReadBufferSize()).isEqualTo(1024);
        assertThat(props.isDirectBuffer()).isTrue();
    }

    @Test
    @DisplayName("PREFIX constant has expected value")
    void testPrefix() {
        assertThat(SmartSocketClientProperties.PREFIX).isEqualTo("h2.server");
    }
}
