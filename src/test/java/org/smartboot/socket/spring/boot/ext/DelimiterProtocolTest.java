package org.smartboot.socket.spring.boot.ext;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.smartboot.socket.transport.AioSession;

import java.nio.ByteBuffer;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit tests for {@link DelimiterProtocol}.
 *
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 * @since 1.0.0
 */
@ExtendWith(MockitoExtension.class)
@DisplayName("DelimiterProtocol Tests")
class DelimiterProtocolTest {

    private final DelimiterProtocol protocol = new DelimiterProtocol();

    @Test
    @DisplayName("encode produces ByteBuffer with delimiter")
    void testEncode() {
        String msg = "Hello";
        ByteBuffer buffer = protocol.encode(msg, null);
        assertThat(buffer).isNotNull();
        assertThat(buffer.remaining()).isEqualTo(msg.length() + 2);
    }

    @Test
    @DisplayName("encode includes delimiter bytes")
    void testEncodeIncludesDelimiter() {
        String msg = "Test";
        ByteBuffer buffer = protocol.encode(msg, null);
        byte[] bytes = new byte[buffer.remaining()];
        buffer.get(bytes);
        assertThat(bytes[bytes.length - 2]).isEqualTo((byte) '\r');
        assertThat(bytes[bytes.length - 1]).isEqualTo((byte) '\n');
    }

    @Test
    @DisplayName("decode returns null when no delimiter found")
    void testDecodeNoDelimiter(@Mock AioSession<String> session) {
        ByteBuffer buffer = ByteBuffer.wrap("Hello".getBytes());
        String result = protocol.decode(buffer, session, false);
        assertThat(result).isNull();
    }

    @Test
    @DisplayName("decode returns message when delimiter found")
    void testDecodeWithDelimiter(@Mock(lenient = true) AioSession<String> session) {
        // First call: no attachment, creates decoder
        ByteBuffer buffer = ByteBuffer.wrap("Hello\r\n".getBytes());
        String result = protocol.decode(buffer, session, false);
        assertThat(result).startsWith("Hello");
    }
}
