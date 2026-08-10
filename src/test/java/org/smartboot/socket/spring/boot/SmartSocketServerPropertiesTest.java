package org.smartboot.socket.spring.boot;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * Unit tests for {@link SmartSocketServerProperties}.
 *
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 * @since 1.0.0
 */
@DisplayName("SmartSocketServerProperties Tests")
class SmartSocketServerPropertiesTest {

    @Test
    @DisplayName("Default values are set correctly")
    void testDefaultValues() {
        SmartSocketServerProperties props = new SmartSocketServerProperties();
        assertThat(props.getHost()).isNull();
        assertThat(props.getPort()).isEqualTo(8888);
        assertThat(props.getWriteQueueSize()).isEqualTo(0);
        assertThat(props.getReadBufferSize()).isEqualTo(512);
        assertThat(props.isDirectBuffer()).isFalse();
        assertThat(props.getThreadNum()).isEqualTo(Runtime.getRuntime().availableProcessors());
        assertThat(props.isBannerEnabled()).isTrue();
    }

    @Test
    @DisplayName("Host and port can be set")
    void testHostAndPort() {
        SmartSocketServerProperties props = new SmartSocketServerProperties();
        props.setHost("localhost");
        props.setPort(9999);
        assertThat(props.getHost()).isEqualTo("localhost");
        assertThat(props.getPort()).isEqualTo(9999);
    }

    @Test
    @DisplayName("WriteQueueSize updates flow limit lines")
    void testWriteQueueSize() {
        SmartSocketServerProperties props = new SmartSocketServerProperties();
        props.setWriteQueueSize(100);
        assertThat(props.getWriteQueueSize()).isEqualTo(100);
        assertThat(props.getFlowLimitLine()).isEqualTo(90);
        assertThat(props.getReleaseLine()).isEqualTo(60);
    }

    @Test
    @DisplayName("ReadBufferSize can be set")
    void testReadBufferSize() {
        SmartSocketServerProperties props = new SmartSocketServerProperties();
        props.setReadBufferSize(1024);
        assertThat(props.getReadBufferSize()).isEqualTo(1024);
    }

    @Test
    @DisplayName("DirectBuffer can be set")
    void testDirectBuffer() {
        SmartSocketServerProperties props = new SmartSocketServerProperties();
        props.setDirectBuffer(true);
        assertThat(props.isDirectBuffer()).isTrue();
    }

    @Test
    @DisplayName("ThreadNum can be set")
    void testThreadNum() {
        SmartSocketServerProperties props = new SmartSocketServerProperties();
        props.setThreadNum(4);
        assertThat(props.getThreadNum()).isEqualTo(4);
    }

    @Test
    @DisplayName("BannerEnabled can be set")
    void testBannerEnabled() {
        SmartSocketServerProperties props = new SmartSocketServerProperties();
        props.setBannerEnabled(false);
        assertThat(props.isBannerEnabled()).isFalse();
    }

    @Test
    @DisplayName("Protocol enum values")
    void testProtocolEnum() {
        assertThat(SmartSocketServerProperties.Protocol.HTTP.get()).isEqualTo(0);
        assertThat(SmartSocketServerProperties.Protocol.TCP.get()).isEqualTo(1);
        assertThat(SmartSocketServerProperties.Protocol.PG.get()).isEqualTo(2);
    }

    @Test
    @DisplayName("Protocol valueOfIgnoreCase finds correct value")
    void testProtocolValueOfIgnoreCase() {
        assertThat(SmartSocketServerProperties.Protocol.valueOfIgnoreCase(0)).isEqualTo(SmartSocketServerProperties.Protocol.HTTP);
        assertThat(SmartSocketServerProperties.Protocol.valueOfIgnoreCase(1)).isEqualTo(SmartSocketServerProperties.Protocol.TCP);
        assertThat(SmartSocketServerProperties.Protocol.valueOfIgnoreCase(2)).isEqualTo(SmartSocketServerProperties.Protocol.PG);
    }

    @Test
    @DisplayName("Protocol valueOfIgnoreCase throws for invalid key")
    void testProtocolValueOfIgnoreCaseInvalid() {
        assertThatThrownBy(() -> SmartSocketServerProperties.Protocol.valueOfIgnoreCase(99))
                .isInstanceOf(NoSuchElementException.class);
    }

    @Test
    @DisplayName("Protocol equals works correctly")
    void testProtocolEquals() {
        assertThat(SmartSocketServerProperties.Protocol.HTTP.equals(SmartSocketServerProperties.Protocol.HTTP)).isTrue();
        assertThat(SmartSocketServerProperties.Protocol.HTTP.equals(0)).isTrue();
    }

    @Test
    @DisplayName("PREFIX constant has expected value")
    void testPrefix() {
        assertThat(SmartSocketServerProperties.PREFIX).isEqualTo("h2.server");
    }
}
