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
 * Unit tests for {@link FixedLengthProtocol}.
 *
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 * @since 1.0.0
 */
@ExtendWith(MockitoExtension.class)
@DisplayName("FixedLengthProtocol Tests")
class FixedLengthProtocolTest {

    private final FixedLengthProtocol protocol = new FixedLengthProtocol();

    @Test
    @DisplayName("encode produces ByteBuffer with length header")
    void testEncode() {
        String msg = "Hello";
        ByteBuffer buffer = protocol.encode(msg, null);
        assertThat(buffer).isNotNull();
        assertThat(buffer.remaining()).isEqualTo(4 + msg.length());
    }

    @Test
    @DisplayName("encode includes correct length header")
    void testEncodeLengthHeader() {
        String msg = "Test";
        ByteBuffer buffer = protocol.encode(msg, null);
        int length = buffer.getInt();
        assertThat(length).isEqualTo(msg.length());
    }

    @Test
    @DisplayName("decode returns null when buffer too small for header")
    void testDecodeBufferTooSmall(@Mock AioSession<String> session) {
        ByteBuffer buffer = ByteBuffer.allocate(2);
        buffer.put(new byte[]{1, 2});
        buffer.flip();
        String result = protocol.decode(buffer, session, false);
        assertThat(result).isNull();
    }

    @Test
    @DisplayName("decode processes complete message")
    void testDecodeCompleteMessage(@Mock(lenient = true) AioSession<String> session) {
        String msg = "Hello";
        byte[] msgBytes = msg.getBytes();
        ByteBuffer buffer = ByteBuffer.allocate(4 + msgBytes.length);
        buffer.putInt(msgBytes.length);
        buffer.put(msgBytes);
        buffer.flip();
        String result = protocol.decode(buffer, session, false);
        assertThat(result).isEqualTo(msg);
    }
}
