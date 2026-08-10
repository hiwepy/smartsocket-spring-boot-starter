package org.smartboot.socket.spring.boot.ext;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit tests for {@link StringProtocol}.
 *
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 * @since 1.0.0
 */
@DisplayName("StringProtocol Tests")
class StringProtocolTest {

    private final StringProtocol protocol = new StringProtocol();

    @Test
    @DisplayName("encode produces correct ByteBuffer")
    void testEncode() {
        String msg = "Hello";
        ByteBuffer buffer = protocol.encode(msg, null);
        assertThat(buffer).isNotNull();
        assertThat(buffer.remaining()).isEqualTo(msg.length());
    }

    @Test
    @DisplayName("decode reads from ByteBuffer")
    void testDecode() {
        String msg = "Test";
        ByteBuffer buffer = ByteBuffer.allocate(msg.length() * 2);
        buffer.asCharBuffer().put(msg);
        String result = protocol.decode(buffer, null, false);
        assertThat(result).isEqualTo(msg);
    }

    @Test
    @DisplayName("encode and decode are symmetric")
    void testEncodeDecodeSymmetry() {
        String msg = "Symmetric";
        ByteBuffer encoded = protocol.encode(msg, null);
        assertThat(encoded).isNotNull();
        assertThat(encoded.remaining()).isEqualTo(msg.length());
    }
}
