# Documentation
## • GitHub: https://github.com/NikkiCS-AI/Navya_CW2025

## • Compilation Instructions:  
  
## • Implemented and Working Properly:
  
## • Implemented but Not Working Properly: 
  
## • Features Not Implemented:
  
## • New Java Classes: 
  
## • Modified Java Classes: 
SimpleBoard.java:- Refactored to validate height and width before creating the board array to prevent potential out-of-bounds errors.
DownData.java:- Refactored constructor to validate input parameters and throw IllegalArgumentException for null values. Added validation in getters to prevent returning null values.
ViewData.java:- Added validation in constructor to check for null or negative values and throw IllegalArgumentException.
  
## • Unexpected Problems: 
  Upon intial testing before refactoring, the code failed 23 out of the 82 total tests due to various issues including logic errors and incorrect handling of edge cases. 
  SimpleBoard.java had with a new array being created before the height was validated, leading to potential out-of-bounds errors. 
  DownData.java constructor accepted null values and the getters returned null without validation throwing.
ViewData.java did not check for null or negative values.
  


