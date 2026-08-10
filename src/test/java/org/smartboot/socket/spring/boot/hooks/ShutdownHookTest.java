package org.smartboot.socket.spring.boot.hooks;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.smartboot.socket.transport.AioQuickClient;
import org.smartboot.socket.transport.AioQuickServer;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit tests for shutdown hooks.
 *
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 * @since 1.0.0
 */
@ExtendWith(MockitoExtension.class)
@DisplayName("Shutdown Hook Tests")
class ShutdownHookTest {

    @Test
    @DisplayName("AioQuickServerShutdownHook can be created")
    void testServerShutdownHook(@Mock AioQuickServer server) {
        AioQuickServerShutdownHook hook = new AioQuickServerShutdownHook(server);
        assertThat(hook).isNotNull();
        assertThat(hook).isInstanceOf(Thread.class);
    }

    @Test
    @DisplayName("AioQuickClientShutdownHook can be created")
    void testClientShutdownHook(@Mock AioQuickClient client) {
        AioQuickClientShutdownHook hook = new AioQuickClientShutdownHook(client);
        assertThat(hook).isNotNull();
        assertThat(hook).isInstanceOf(Thread.class);
    }
}
