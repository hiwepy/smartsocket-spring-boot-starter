package org.smartboot.socket.spring.boot.ext;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.smartboot.socket.StateMachineEnum;
import org.smartboot.socket.transport.AioSession;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

/**
 * Unit tests for {@link StringMessageServerProcessor}.
 *
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 * @since 1.0.0
 */
@ExtendWith(MockitoExtension.class)
@DisplayName("StringMessageServerProcessor Tests")
class StringMessageServerProcessorTest {

    private final StringMessageServerProcessor processor = new StringMessageServerProcessor();

    @Test
    @DisplayName("Processor can be instantiated")
    void testInstantiation() {
        assertThat(processor).isNotNull();
    }

    @Test
    @DisplayName("process writes response to session")
    void testProcess(@Mock AioSession<String> session) throws IOException {
        doNothing().when(session).write("Hello1");
        processor.process(session, "Hello");
        verify(session).write("Hello1");
    }

    @Test
    @DisplayName("stateEvent can be called with NEW_SESSION")
    void testStateEventNewSession(@Mock AioSession<String> session) {
        processor.stateEvent(session, StateMachineEnum.NEW_SESSION, null);
        assertThat(true).isTrue();
    }

    @Test
    @DisplayName("stateEvent handles null throwable")
    void testStateEventNullThrowable(@Mock AioSession<String> session) {
        processor.stateEvent(session, StateMachineEnum.INPUT_SHUTDOWN, null);
        assertThat(true).isTrue();
    }
}
