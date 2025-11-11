# Documentation
## • GitHub: https://github.com/NikkiCS-AI/Navya_CW2025

## • Compilation Instructions:  
  
## • Implemented and Working Properly:
Backgrpund music and sound effects integrated into the game using Audio Manager class.
Pause and resume functionality added to the game.
Score display bug fixed to accurately reflect the player's score.
Preview panel added to the GUI for to allow users to see upcoming the next brick.
  
## • Implemented but Not Working Properly: 
  
## • Features Not Implemented:
  
## • New Java Classes: 
Added class Audio Manager to handle background music and sound effects for the game.  
  
## • Modified Java Classes: 
GuiController.java - Added functionality to pause and resume game.
GuiController.java - Fixed bug in score display.
GuiController.java - Added functionality to integrate Audio Manager for sound effects and background music.

  
## • Unexpected Problems: 
  Upon intial testing before refactoring, the code failed 23 out of the 82 total tests due to various issues including logic errors and incorrect handling of edge cases. 
  SimpleBoard.java had with a new array being created before the height was validated, leading to potential out-of-bounds errors. 
  DownData.java constructor accepted null values and the getters returned null without validation throwing.
  ViewData.java did not check for null or negative values. 
  GuiController.java had multiple issues including incorrect score calculation, failure to pause and resume the game properly, and lack of sound integration.

  


