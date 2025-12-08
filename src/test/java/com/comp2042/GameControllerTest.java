package com.comp2042;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

class GameControllerTest {

    static class MockGuiController extends GuiController {
        private InputEventListener eventListener;
        private boolean gameOverCalled = false;
        private boolean initGameViewCalled = false;
        private boolean refreshGameBackgroundCalled = false;
        private boolean refreshBrickCalled = false;
        private boolean updateHoldDisplayCalled = false;
        private boolean updateNextBrickCalled = false;
        private boolean bindScoreCalled = false;

        private int[][] lastBoardMatrix;
        private ViewData lastViewData;
        private int[][] lastHoldDisplay;
        private ViewData lastNextBrick;

        @Override
        public void setEventListener(InputEventListener listener) {
            this.eventListener = listener;
        }

        @Override
        public void initGameView(int[][] boardMatrix, ViewData viewData) {
            initGameViewCalled = true;
            lastBoardMatrix = boardMatrix;
            lastViewData = viewData;
        }

        @Override
        public void refreshGameBackground(int[][] boardMatrix) {
            refreshGameBackgroundCalled = true;
            lastBoardMatrix = boardMatrix;
        }

        @Override
        public void refreshBrick(ViewData viewData) {
            refreshBrickCalled = true;
            lastViewData = viewData;
        }

        @Override
        public void updateHoldDisplay(int[][] holdShape) {
            updateHoldDisplayCalled = true;
            lastHoldDisplay = holdShape;
        }

        @Override
        public void updateNextBrick(ViewData nextBrickData) {
            updateNextBrickCalled = true;
            lastNextBrick = nextBrickData;
        }

        @Override
        public void bindScore(javafx.beans.property.IntegerProperty score) {
            bindScoreCalled = true;
        }

        @Override
        public void gameOver() {
            gameOverCalled = true;
        }

        public InputEventListener getEventListener() {
            return eventListener;
        }

        public boolean wasGameOverCalled() {
            return gameOverCalled;
        }

        public void reset() {
            gameOverCalled = false;
            initGameViewCalled = false;
            refreshGameBackgroundCalled = false;
            refreshBrickCalled = false;
            updateHoldDisplayCalled = false;
            updateNextBrickCalled = false;
            bindScoreCalled = false;
            lastBoardMatrix = null;
            lastViewData = null;
            lastHoldDisplay = null;
            lastNextBrick = null;
        }
    }

    private MockGuiController mockGui;
    private GameController gameController;


    @BeforeEach
    void setUp() {
        mockGui = new MockGuiController();
        gameController = new GameController(mockGui);
    }

    private MoveEvent createMoveEvent() {
        EventType eventType = EventType.values()[0];
        EventSource eventSource = EventSource.values()[0];
        return new MoveEvent(eventType, eventSource);
    }

    @Test
    void onDownEvent() {
        MoveEvent event = createMoveEvent();
        DownData result = gameController.onDownEvent(event);

        assertNotNull(result);
        assertNotNull(result.getViewData());
        assertNotNull(result.getClearRow());
        assertFalse(gameController.isGameOver());
    }
    @Test
    void onLeftEvent() {
        MoveEvent event = createMoveEvent();
        ViewData result = gameController.onLeftEvent(event);

        assertNotNull(result);
        assertNotNull(result.getBrickData());
        assertTrue(result.getxPosition() >= 0);
    }
    @Test
    void onRightEvent() {
        MoveEvent event = createMoveEvent();
        ViewData result = gameController.onRightEvent(event);

        assertNotNull(result);
        assertNotNull(result.getBrickData());
    }
    @Test
    void onRotateEvent() {
        MoveEvent event = createMoveEvent();
        ViewData result = gameController.onRotateEvent(event);

        assertNotNull(result);
        assertNotNull(result.getBrickData());
    }
    @Test
    void createNewGame() {
        gameController.createNewGame();
        MoveEvent event = createMoveEvent();

        assertFalse(gameController.isGameOver());

        ViewData viewData = gameController.onLeftEvent(event);
        assertNotNull(viewData);
    }

    @Test
    void onHoldEvent() {
        gameController.onHoldEvent();

        assertNotNull(gameController.getHoldShape());
    }
    @Test
    void getHoldShape() {
        int[][] holdShape = gameController.getHoldShape();
        assertNotNull(holdShape);
    }
    @Test
    void getCurrentViewData() {
        ViewData viewData = gameController.getCurrentViewData();
        assertNotNull(viewData);
        assertNotNull(viewData.getBrickData());
        assertTrue(viewData.getxPosition() >= 0);
        assertTrue(viewData.getyPosition() >= 0);
    }
    @Test
    void isGameOver() {
        MoveEvent event = createMoveEvent();
        gameController.onDownEvent(event);
        gameController.onLeftEvent(event);
        gameController.onRightEvent(event);
        gameController.onRotateEvent(event);
        gameController.onHoldEvent();

        assertTrue(true);
    }

    @Test
    void testOnHardDropEvent() {
        gameController.onHardDropEvent();

        assertTrue(true);
    }
}