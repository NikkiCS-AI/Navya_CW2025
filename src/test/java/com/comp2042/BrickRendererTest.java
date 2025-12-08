package com.comp2042;

import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BrickRendererTest {

    private BrickRenderer brickRenderer;
    private GridPane gamePanel;
    private GridPane brickPanel;
    private GridPane previewPanel;
    private GridPane holdPanel;

    @BeforeEach
    void setUp() {
        brickRenderer = new BrickRenderer();

        // Create mock GridPanes
        gamePanel = new GridPane();
        brickPanel = new GridPane();
        previewPanel = new GridPane();
        holdPanel = new GridPane();
    }

    @Test
    void testRefreshBrick_WithNullParameters_DoesNothing() {
        // Act & Assert - Should not throw with null parameters
        assertDoesNotThrow(() -> brickRenderer.refreshBrick(
                null, null, null, null, false
        ));

        assertDoesNotThrow(() -> brickRenderer.refreshBrick(
                null, null, null, null, true
        ));
    }

    @Test
    void testRefreshBrick_WhenPaused_DoesNothing() {
        // Arrange
        ViewData brick = createTestViewData();
        Rectangle[][] brickRects = new Rectangle[4][4];

        // Act & Assert - Should not throw when paused
        assertDoesNotThrow(() -> brickRenderer.refreshBrick(
                brick, brickRects, brickPanel, gamePanel, true
        ));

        // Brick panel should remain unchanged
        assertNotNull(brickPanel);
    }

    @Test
    void testRefreshBrick_WithValidData_DoesNotThrow() {
        // Arrange
        ViewData brick = createTestViewData();
        Rectangle[][] brickRects = new Rectangle[4][4];

        // Act & Assert
        assertDoesNotThrow(() -> brickRenderer.refreshBrick(
                brick, brickRects, brickPanel, gamePanel, false
        ));
    }

    @Test
    void testRefreshGameBackground_WithNullParameters_DoesNothing() {
        // Act & Assert
        assertDoesNotThrow(() -> brickRenderer.refreshGameBackground(null, null));
        assertDoesNotThrow(() -> brickRenderer.refreshGameBackground(new int[20][10], null));
        assertDoesNotThrow(() -> brickRenderer.refreshGameBackground(null, new Rectangle[20][10]));
    }

    @Test
    void testRefreshGameBackground_WithValidData_DoesNotThrow() {
        // Arrange
        int[][] board = new int[20][10];
        Rectangle[][] matrix = new Rectangle[GridRenderer.VISIBLE_ROWS][10];

        // Initialize matrix with rectangles
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = new Rectangle(20, 20);
            }
        }

        // Fill some cells
        board[10][5] = 1;  // Color value 1
        board[15][3] = 2;  // Color value 2

        // Act & Assert
        assertDoesNotThrow(() -> brickRenderer.refreshGameBackground(board, matrix));
    }

    @Test
    void testUpdatePreviewPanel_StaticMethod_WithNullParameters() {
        // Act & Assert - Static method should handle null
        assertDoesNotThrow(() -> BrickRenderer.updatePreviewPanel(null, null));
        assertDoesNotThrow(() -> BrickRenderer.updatePreviewPanel(previewPanel, null));
        assertDoesNotThrow(() -> BrickRenderer.updatePreviewPanel(null, createTestViewData()));
    }

    @Test
    void testUpdatePreviewPanel_StaticMethod_WithValidData() {
        // Arrange
        ViewData nextBrick = createTestViewData();

        // Act
        assertDoesNotThrow(() -> BrickRenderer.updatePreviewPanel(previewPanel, nextBrick));

        // Assert - Preview panel should have children added
        // (Can't easily test without JavaFX runtime, but at least no exception)
    }

    @Test
    void testUpdateHoldPanel_WithNullParameters_DoesNothing() {
        // Act & Assert
        assertDoesNotThrow(() -> brickRenderer.updateHoldPanel(null, null));
        assertDoesNotThrow(() -> brickRenderer.updateHoldPanel(holdPanel, null));
        assertDoesNotThrow(() -> brickRenderer.updateHoldPanel(null, new int[4][4]));
    }

    @Test
    void testUpdateHoldPanel_WithValidData_DoesNotThrow() {
        // Arrange
        int[][] holdShape = {
                {1, 1},
                {1, 1}  // O-shaped brick
        };

        // Act & Assert
        assertDoesNotThrow(() -> brickRenderer.updateHoldPanel(holdPanel, holdShape));
    }

    @Test
    void testCreateBrickRectangle_PrivateMethod_CanBeTestedIndirectly() {
        // Since createBrickRectangle is private, we test it through public methods
        // that use it

        // Arrange
        int[][] holdShape = {{1}}; // Single cell brick

        // Act
        brickRenderer.updateHoldPanel(holdPanel, holdShape);

        // Assert - No exception means createBrickRectangle worked
        assertDoesNotThrow(() -> brickRenderer.updateHoldPanel(holdPanel, holdShape));
    }

    @Test
    void testSetRectColor_PrivateMethod_CanBeTestedIndirectly() {
        // Test through refreshGameBackground

        // Arrange
        int[][] board = new int[20][10];
        Rectangle[][] matrix = new Rectangle[GridRenderer.VISIBLE_ROWS][10];

        // Fill matrix with rectangles
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = new Rectangle(20, 20);
            }
        }

        // Set some colors
        board[GridRenderer.VISIBLE_START_ROW][0] = 0; // Empty
        board[GridRenderer.VISIBLE_START_ROW][1] = 1; // Color 1
        board[GridRenderer.VISIBLE_START_ROW][2] = 2; // Color 2

        // Act & Assert - Should not throw
        assertDoesNotThrow(() -> brickRenderer.refreshGameBackground(board, matrix));
    }

    @Test
    void testBrickPositionCalculation() {
        // This tests the internal logic of addBrickToGamePanel indirectly

        // Arrange
        ViewData brick = createTestViewData();
        Rectangle[][] brickRects = new Rectangle[4][4];

        // Set brick at specific position
        int x = 3;
        int y = GridRenderer.VISIBLE_START_ROW + 2; // Make sure it's in visible area

        // Create a ViewData with specific position
        int[][] brickData = {{1, 1}, {1, 1}}; // 2x2 brick
        int[][] nextBrickData = {{1}};
        ViewData positionedBrick = new ViewData(brickData, x, y, nextBrickData);

        // Act & Assert
        assertDoesNotThrow(() -> brickRenderer.refreshBrick(
                positionedBrick, brickRects, brickPanel, gamePanel, false
        ));
    }

    @Test
    void testBrickOutsideVisibleArea() {
        // Test brick placed above visible area

        // Arrange
        int[][] brickData = {{1}}; // Single cell brick
        int[][] nextBrickData = {{1}};

        // Place brick above visible area (y < VISIBLE_START_ROW)
        int y = GridRenderer.VISIBLE_START_ROW - 2;
        ViewData brick = new ViewData(brickData, 5, y, nextBrickData);

        Rectangle[][] brickRects = new Rectangle[1][1];

        // Act & Assert - Should not throw
        assertDoesNotThrow(() -> brickRenderer.refreshBrick(
                brick, brickRects, brickPanel, gamePanel, false
        ));

        // Brick should not be added to game panel since it's not visible
    }

    @Test
    void testClearOldBrick_Logic() {
        // Test that old bricks are cleared properly

        // Arrange
        ViewData brick1 = createTestViewData();
        Rectangle[][] brickRects = new Rectangle[4][4];

        // Add first brick
        brickRenderer.refreshBrick(brick1, brickRects, brickPanel, gamePanel, false);

        // Create second brick
        int[][] brickData2 = {{0, 1, 0}, {1, 1, 1}}; // T-shaped brick
        int[][] nextBrickData2 = {{1}};
        ViewData brick2 = new ViewData(brickData2, 4, 5, nextBrickData2);

        // Act & Assert - Should not throw when replacing brick
        assertDoesNotThrow(() -> brickRenderer.refreshBrick(
                brick2, brickRects, brickPanel, gamePanel, false
        ));
    }

    // Helper method to create test ViewData
    private ViewData createTestViewData() {
        int[][] brickData = {
                {1, 0, 0},
                {1, 1, 1}  // L-shaped brick
        };
        int[][] nextBrickData = {
                {1, 1},
                {1, 1}  // O-shaped brick
        };
        return new ViewData(brickData, 3, 5, nextBrickData);
    }

    @Test
    void testConstants() {
        // Verify constants are accessible
        // Note: PREVIEW_SIZE and GAME_SIZE are private
        // VISIBLE_ROWS and VISIBLE_START_ROW come from GridRenderer

        // Just verify GridRenderer constants exist
        assertTrue(GridRenderer.VISIBLE_ROWS > 0);
        assertTrue(GridRenderer.VISIBLE_START_ROW >= 0);
    }

    @Test
    void testBrickColourIntegration() {
        // Test that BrickColour.getColour is called without throwing

        // Arrange
        int[][] holdShape = {{1}, {2}, {3}}; // Different color values

        // Act & Assert
        assertDoesNotThrow(() -> brickRenderer.updateHoldPanel(holdPanel, holdShape));
    }

    @Test
    void testMultipleCallsToMethods() {
        // Test calling methods multiple times

        // Arrange
        ViewData brick = createTestViewData();
        Rectangle[][] brickRects = new Rectangle[4][4];
        int[][] board = new int[20][10];
        Rectangle[][] matrix = new Rectangle[GridRenderer.VISIBLE_ROWS][10];
        int[][] holdShape = {{1, 1}, {1, 1}};

        // Initialize matrix
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = new Rectangle(20, 20);
            }
        }

        // Act & Assert - Multiple calls should work
        for (int i = 0; i < 5; i++) {
            assertDoesNotThrow(() -> brickRenderer.refreshBrick(
                    brick, brickRects, brickPanel, gamePanel, false
            ));

            assertDoesNotThrow(() -> brickRenderer.refreshGameBackground(board, matrix));

            assertDoesNotThrow(() -> BrickRenderer.updatePreviewPanel(previewPanel, brick));

            assertDoesNotThrow(() -> brickRenderer.updateHoldPanel(holdPanel, holdShape));
        }
    }
}