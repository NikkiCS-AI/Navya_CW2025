# Documentation
## GitHub:
  https://github.com/NikkiCS-AI/Navya_CW2025
## Compilation Instructions: 
  This project was developed and tested using IntelliJ IDEA Community Edition 2025.1, Amazon Corretto JDK 23, and Maven.
  1. Clone the repository or extract the submitted ZIP.
  2. Open the project in IntelliJ as a Maven project.
  3. Ensure the Project SDK is set to Amazon Corretto 23.
  4. If using IntelliJ’s Run button, add the following to the VM options:
  --module-path "<user home>/.m2/repository/org/openjfx/javafx-base/21.0.6/javafx-base-21.0.6-win.jar;... (other JavaFX jars) ..." --add-modules javafx.controls,javafx.fxml
  or run using Maven: `mvn clean javafx:run.
  5. Build and run the main class: `com.comp2042.Main`.
The game should launch in a JavaFX window.
## Implemented and Working Properly:
## Implemented but Not Working Properly: 
## Features Not Implemented: 
## New Java Classes: 
## Modified Java Classes:
  • Used IntelliJ’s Inspect Code and Code Cleanup tools to identify unused variables, simplify expressions, and standardize formatting for better readability.
  
  • Used IntelliJ’s code inspection to identify and fix redundancy, unused imports, and naming inconsistencies. Only non-functional structural changes were applied (verified by all JUnit5 tests).
## Unexpected Problems:
  •JavaFX runtime error fixed by adding module path in IntelliJ VM options.
  •Missing dependencies resolved by reloading Maven project.

