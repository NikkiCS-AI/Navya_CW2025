package com.comp2042;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    private Main main;

    @BeforeEach
    void setUp() {
        main = new Main();
    }

    @AfterEach
    void tearDown() {
        main = null;
    }

    @Test
    void launch() {
        assertDoesNotThrow(() -> Main.main(new String[]{}), "Main.launch should not throw any exception");
    }

    @Test
    void testLaunch() {
        assertDoesNotThrow(() -> Main.main(new String[]{}), "Main should launch safely with no exception");
    }

    @Test
    void init() {
        assertDoesNotThrow(() -> main.init(),
                "init() should not throw any exception");
    }

    @Test
    void stop() {
        assertDoesNotThrow(() -> main.stop(),
                "stop() should not throw any exception");
    }

    @Test
    void getHostServices() {
        assertNotNull(main.getHostServices(),
                "getHostServices() should not return null");

    }

    @Test
    void getParameters() {
        assertNotNull(main.getParameters(),
                "getParameters() should not return null");
    }

    @Test
    void notifyPreloader() {
        assertDoesNotThrow(() -> main.notifyPreloader(null),
                "notifyPreloader() should not throw any exception");
    }

    @Test
    void getUserAgentStylesheet() {
        assertNotNull(main.getUserAgentStylesheet(),
                "getUserAgentStylesheet() should not return null");
    }

    @Test
    void setUserAgentStylesheet() {
        assertDoesNotThrow(() -> main.setUserAgentStylesheet(Main.STYLESHEET_MODENA),
                "setUserAgentStylesheet() should not throw any exception");
    }

    @Test
    void start() {
        assertDoesNotThrow(() -> {
            // Since start requires a Stage, we create a mock or dummy Stage
            javafx.stage.Stage dummyStage = new javafx.stage.Stage();
            main.start(dummyStage);
        }, "start() should not throw any exception when provided with a valid Stage");
    }

    @Test
    void main() {
        assertDoesNotThrow(() -> Main.main(new String[]{}),
                "Main.main() should not throw any exception");
    }
}
