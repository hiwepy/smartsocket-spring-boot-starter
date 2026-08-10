package org.smartboot.socket.spring.boot.ext;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.smartboot.socket.StateMachineEnum;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit tests for {@link StringMessageClientProcessor}.
 *
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 * @since 1.0.0
 */
@DisplayName("StringMessageClientProcessor Tests")
class StringMessageClientProcessorTest {

    @Test
    @DisplayName("Processor can be instantiated")
    void testInstantiation() {
        StringMessageClientProcessor processor = new StringMessageClientProcessor();
        assertThat(processor).isNotNull();
    }

    @Test
    @DisplayName("getSession returns null initially")
    void testGetSessionInitiallyNull() {
        StringMessageClientProcessor processor = new StringMessageClientProcessor();
        assertThat(processor.getSession()).isNull();
    }

    @Test
    @DisplayName("stateEvent handles NEW_SESSION case")
    void testStateEventNewSession() {
        StringMessageClientProcessor processor = new StringMessageClientProcessor();
        processor.stateEvent(null, StateMachineEnum.NEW_SESSION, null);
        // session is set from the event (null in this case)
        assertThat(processor.getSession()).isNull();
    }

    @Test
    @DisplayName("stateEvent handles default case")
    void testStateEventDefault() {
        StringMessageClientProcessor processor = new StringMessageClientProcessor();
        processor.stateEvent(null, StateMachineEnum.INPUT_SHUTDOWN, null);
        assertThat(processor.getSession()).isNull();
    }
}
