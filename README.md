# Documentation
## •GitHub: 
## •Compilation Instructions:  
  1. Clone the repository or extract all the ZIP folder.
  2. Open the project in IntelliJ as a Maven project.
  3. Check that the Project SDK is set to Amazon Corretto 23.
  4. For using IntelliJ’s Run button, add the following to the VM options: --module-path "<user home>/.m2/repository/org/openjfx/javafx-base/21.0.6/javafx-base-21.0.6-win.jar;... (other JavaFX jars) ..." --add-modules javafx.controls,javafx.fxml ; else, run using Maven: "mvn clean install javafx:run".

  5. Build and run the main class: "com.comp2042.Main".

The game should launch in a JavaFX window.


## •Implemented and Working Properly:
## •Implemented but Not Working Properly: 
## •Features Not Implemented: 
## •New Java Classes: 
## •Modified Java Classes:
  1. Refactoring:
     a) Used intellij's Inspect code and Code clean up tools to identify issues such as unused variables, imports, or methods, code style violations, duplicated code. possible bugs or missing and visibility problems such as missing @override.
  2. 
## •Unexpected Problems:
