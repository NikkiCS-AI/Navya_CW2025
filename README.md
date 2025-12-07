# Documentation
## • GitHub: https://github.com/NikkiCS-AI/Navya_CW2025

## • Compilation Instructions:  
1. Requirements: 
    - Java Development Kit (JDK) 17 or higher (preferably JDK 23)
    - An IDE such as IntelliJ IDEA, Eclipse, or NetBeans (IntelliJ IDEA recommended)
2. To Compile and Run the Project:
    - Clone the repository from GitHub using the command git clone.
    - Configure Javafx in your IDE in project structure, libraries section.
    - Run the main class located in the src folder.
    - Use maven  javafx plugin or the command mvn javafx:run to run the project.
    - If JavaFX VM options are needed, add the following to your run configuration:
      --module-path "path/to/javafx/lib" --add-modules javafx.controls,javafx.fxml

  
## • Implemented and Working Properly:
1. Background music and sound effects 
- integrated into the game using Audio Manager class. Handles music loops and sound triggers. 

2. Pause and Resume Menu
- Implemented pause and resume functionality in the GUI controller. Game state is preserved when paused.

3. Score display Fix
- Fixed bug in score display to ensure accurate scoring during gameplay.

4. Preview panel 
- Added a real-time preview the GUI for to allow users to see upcoming the next brick.

5. Hold Functionality
- Implemented hold feature to allow users to store a tetromino for later use during gameplay

6. Refactored Brick Classes
- Combined all individual brick classes into a single TetrominoShapeType class to reduce redundancy and improve maintainability. It is rendered via new BrickRenderer class.

7. GameLoopManager
- Created a GameLoopManager class to handle the main game loop, improving code organization and readability
  
## • Implemented but Not Working Properly: 
  
## • Features Not Implemented:
  
## • New Java Classes: 
1. AudioManager.java - Manages background music and sound effects.
2. GameLoopManager.java - Handles the main game loop.
3. BrickRenderer.java - Responsible for rendering tetromino shapes on the game board.
4. TetrominoShapeType.java - Represents all tetromino shapes in a single class.
5. PauseMenu.java - Manages the pause and resume functionality of the game.
6. PreviewPanel.java - Displays the next tetromino shape in the GUI.
7. HoldBrick.java - Implements the hold feature for tetromino shapes.
8. BrickColour.java - Class for defining colors of tetromino shapes.
9. AudioManagerInterface.java - Interface for audio management functionalities.
10. GameLoopManager.java - Manages the main game loop of the application.
11. GameLoopManagerInterface.java - Interface for game loop management functionalities.
12. GameOverHandler.java - Handles game over scenarios and related functionalities.
13. GameOverUIBuilder.java - Builds the game over user interface components.
14. GameStateManager.java - Manages different states of the game (e.g., playing, paused, game over).
15. GameStateManagerInterface.java - Interface for game state management functionalities.
16. GameOverPanel.java - Displays game over information and options to the player.
17. KeyInputHandler.java - Handles user input for controlling the game.
18. StartMenu.java - Manages the start menu of the game.
19. RowsClearedEffectsHandler.java - Visual and sound effects for row clearing.
20. ClearRow.java - Logic for clearing rows in the game board.
21. GridRenderer.java - Renders the game grid on the GUI.
22. SystemCommand.java - enum for system commands like START, PAUSE, RESUME, EXIT.

  
## • Modified Java Classes: 
1. GuiController.java
- Added methods for pause/resume functionality
- score display fixed
- connected preview panel
- added hold functionality
- Improved event handling and FXML integration

2. SimpleBoard.java
- Fixed row/column handling and creatation logic
- Added validation for height and width parameters to prevent out-of-bounds errors
- Improved internal representation for stability and testing

3. DownData.java
- Added validation in constructor to prevent null values
- Added null checks in getters to avoid returning null values

4. ViewData.java
- Added validation for null and negative values in constructor and methods
- Improved mapping logic for better stability

5. Score
- Fixed score calculation logic to ensure accurate scoring during gameplay
- Ensure score updates correctly reflect game events and binds to GUI display

6. GameController.java
- Improved integration with new GameLoopManager and AudioManager classes
- refactored to reduce complexity, reduce duplication and improve readability

7. MatrixOperations.java
- Fixed invalid matrix operations and added validation checks
- Improved rotation and movement logic for tetromino shapes

8. GameOverPanel.java
- rebuilt to improve user experience and display relevant information
- Integrated with GameStateManager for better state handling
- Integrated with GameOverHandler for improved functionality


  
## • Unexpected Problems: 
1. Some Unit Tests Failed Intially
- Null pointer exceptions and incorrect assumptions about object states caused some unit tests to fail initially. 
- Resolved by adding validation checks and improving test coverage.

2. Game Board Rendering Issues
- Initial issues with rendering the game board correctly after refactoring the rendering logic.
- Incorrect orientation and gameboard logic after refacting guicontroller and gamecontroller classes.
- Resolved by thoroughly reviewing and updating the logic in the affected classes especially simpleboard, grid renderer and brick renderer.


## Design Patterns Used:
1. Singleton Pattern
- Used in AudioManager and GameStateManager to ensure a single instance throughout the application.

2. Factory Pattern
- Used for creating tetromino shapes in TetrominoShapeType class.

3. Observer Pattern
- Used for updating the GUI in response to game state changes and in score updates as well as event notifications.

4. Strategy Pattern
Used in movement handling and input systems (KeyInputHandler, GameMovementInterface) to allow for different input strategies.

5. State Pattern
- Used in GameStateManager and GameStateInterface to manage different game states (playing, paused, game over).

6. Updateable Interface
- Implemented by classes that need to be updated regularly, such as GameLoopManager and GameController.

7. Single Responsibility Principle
- Each class has a single responsibility, improving maintainability and readability.


  


