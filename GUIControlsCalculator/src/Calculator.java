/* Author : Ben Scherer
 * Class: CIS 2212
 * Application: GUI Memory Calculator
 * Description:  Calculator application.
 * 	     GUI: JavaFX.  Leverages VBox/TextField/GridPane to build scene
 * 					   Uses Lambda event handlers
 * 		Calculator: Uses MemoryCalc class to instantiate calculator object and perform operations
 */


import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class Calculator extends Application {

	private static MemoryCalc calcObj = new MemoryCalc(); // Calculator Object

	private static StringProperty calcDisplay = new SimpleStringProperty("0.0"); // Textfield for calculator output
	
																					// binds to this variable
	private static String operator = "";; // Current operator selected

	String[][] buttonArr = new String[][] { { "7", "8", "9", "/" }, { "4", "5", "6", "*" }, { "1", "2", "3", "-" },
			{ "C", "0", ".", "+" } }; // Template for building out calculator grid. Will be used to draw 4x4 grid.

	@Override
	public void start(Stage primaryStage) throws Exception {

		// Will hold all fields/buttons

		VBox calcGrid = new VBox(); // VBox. Will contain all the elements of calculator
		calcGrid.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		calcGrid.autosize();
		
		// GridPane calcGrid = new GridPane();

		TextField calcOutput = createTextField(); // create text field for display and bind to calcDisplay variable
		GridPane buttonGrid = newButtonGrid(buttonArr); // 4x4 button grid and event handlers
		Button equalButton = createEqualButton(); //Create equal button and event handlers
		calcGrid.getChildren().add(calcOutput); // add calculator display
		calcGrid.getChildren().addAll(buttonGrid); // add button grid
		calcGrid.getChildren().add(equalButton); // add "=" button
		VBox.setVgrow(equalButton, Priority.ALWAYS);
		VBox.setVgrow(buttonGrid, Priority.ALWAYS);
		// Create Scene and show output.
		Scene scene = new Scene(calcGrid, 200, 200);


		primaryStage.setTitle("Calculator");
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	// Creates textfield and all associated properties for Calculator Output
	public static TextField createTextField() {
		// Add Textfield used for all output
		TextField calcOutput = new TextField();
		calcOutput.setEditable(false);
		calcOutput.autosize();
		calcOutput.setStyle("-fx-font-weight: bold;-fx-font-size: 20;-fx-font-family: monospace;");
		calcOutput.setPrefWidth(Double.MAX_VALUE);
		calcOutput.setMaxWidth(Double.MAX_VALUE);
		calcOutput.textProperty().bind(calcDisplay);
		;
		return calcOutput;
	}

	//Display ALert box with error message
	public static void displayError(String errMessage) {
		Alert alert = new Alert(AlertType.ERROR, errMessage);
		alert.showAndWait();
	}
	
	public static GridPane newButtonGrid(String[][] buttonArr) {
		GridPane buttonGrid = buildGrid();
		
		// Enumerate two dimensional array
		for (int i = 0; i < buttonArr.length; i++) {
			for (int j = 0; j < buttonArr[i].length; j++) {
				// Create new button object and set to scale
				Button newButton = new Button(buttonArr[i][j]);
				newButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
				newButton.setPrefHeight(40);
				
				// Set click action
				newButton.setOnAction(e -> { // event handler
					Button btn = (Button) e.getSource(); // Get button source
					String btnVal = btn.getText(); // Get text of button
					// If number is passed, add to display
					if (btnVal.matches("^[0-9]$")) { // Match numbers

						if (!operator.isEmpty())
							calcDisplay.set(calcDisplay.get() + btnVal);

					} else if (btnVal == ".") { // decimal button
						// Only add decimal point if one doesn't already exist
						// if (!calcDisplay.get().contains(".")) { //do not insert erroneous decimal
						// points. Commented out. Will throw an error instead of not allowing erroneous
						// decimal points
						if (!operator.isEmpty())
							calcDisplay.set(calcDisplay.get() + btnVal);
						// }

					}

					else if (btnVal == "C") {
						// Clear values if C is pressed
						calcObj.clear();
						calcDisplay.set(Double.toString(calcObj.getCurrentValue()));
					} else {
						// Set the current operator and clear display for new input
						operator = btnVal;
						calcDisplay.set("");
					}

				});
				buttonGrid.add(newButton, j, i); // Add button to GridPane
			}
		}
		
		return buttonGrid;
	}

	public static double checkDouble(String strToCheck) throws Exception {
		
		
		try {
			return Double.parseDouble(strToCheck);
			
		}
		catch (Exception ex) {
			throw new Exception("Invalid Number");
		}
		
		
	}
	// Create equal button and associated event handler
	public static Button createEqualButton() {
		Button newBtn = new Button("=");
		newBtn.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		newBtn.setPrefHeight(40);
		newBtn.setOnAction(e -> {
			try {
				
				//Double operand = Double.parseDouble(calcDisplay.get());
				Double operand = checkDouble(calcDisplay.get());

				// System.out.println("Operator:" + operator);
				// System.out.println("Operand" + operand);
				// Calls different methods of memory calculator class based on currently
				// selected operator

				switch (operator) {

				case "+":
					calcObj.add(operand);
					break;
				case "*":
					calcObj.multiply(operand);
					break;
				case "/":
					calcObj.divide(operand);
					break;
				case "-":
					calcObj.subtract(operand);
					break;

				}

				calcDisplay.set(Double.toString(calcObj.getCurrentValue()));
				operator = "";
			} catch (Exception ex) {
				//calcDisplay.set(ex.getMessage()); // Display message if error is caught
				displayError(ex.getMessage() + "\nValue will be reset to last known value.");
				calcDisplay.set(Double.toString(calcObj.getCurrentValue()));
				
			} finally {
				operator = "";
			}

		});

		return newBtn;

	}

	// Generates initial gridpane with constrains
	public static GridPane buildGrid() {
		GridPane newGrid = new GridPane();
		
		for (int i = 0; i < 4; i++) { // create 4 columns and set to auto grow to match windo
			ColumnConstraints cConstraint = new ColumnConstraints();
			RowConstraints rConstraint = new RowConstraints();
			rConstraint.setVgrow(Priority.ALWAYS);
			rConstraint.setFillHeight(true);
			cConstraint.setHgrow(Priority.ALWAYS);

			cConstraint.setFillWidth(true);
			newGrid.getColumnConstraints().add(cConstraint);
			newGrid.getRowConstraints().add(rConstraint);
			// System.out.println(i);
		}

		
	
		newGrid.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		newGrid.autosize();

		return newGrid;

	}

	// Main method of class
	public static void main(String[] args) {
		launch(args);
	}
}
