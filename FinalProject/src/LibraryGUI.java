
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValueBase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Optional;
import java.util.function.Function;

public class LibraryGUI extends Application {

	Library libraryObj = new Library();
	ObservableList<MediaItem> listData;
	ListView<MediaItem> libraryList = new ListView<>();

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		try {
			libraryObj.loadDB("library.txt");
		} catch (Exception ex) {
			displayError(ex.getMessage() + "\nExiting Program");
			return;
			
		}
		listData = FXCollections.observableList(libraryObj.mediaArr);

		libraryList.setItems(listData);
		// Will hold all fields/buttons

		VBox libStage = new VBox(); // VBox. Will contain all elements of library app
		libStage.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		libStage.autosize();

		// Bottom row for holding buttons
		HBox bottomRow = new HBox();
		bottomRow.setPadding(new Insets(15, 12, 15, 12));
		bottomRow.setSpacing(10);

		// List view for media items

		/*
		 * Button add = new Button("Add"); Button delete = new Button("Delete"); Button
		 * checkIn = new Button("Check In"); Button checkOut = new Button("Check Out");
		 */

		bottomRow.getChildren().addAll(createButtons());
		libStage.getChildren().addAll(libraryList, bottomRow);

		// Create Scene and show output.
		Scene scene = new Scene(libStage, 400, 600);

		primaryStage.setTitle("Media Library");
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	ArrayList<Button> createButtons() {

		ArrayList<Button> buttonArr = new ArrayList<>();
		Button add = new Button("Add");
		add.setOnAction(e -> {
			//Add new media to library
			
			try {
				// Prompt for Title. Return if user presses cancel
				String mediaTitle = getStringInput("Add new media", "Title: ");
				if (mediaTitle == null || mediaTitle.isEmpty())
					return;

				// Prompt for date loaned. Return if user presses cancel
				String mediaFormat = getStringInput("Add new media", "Format: ");
				if (mediaFormat == null || mediaFormat.isEmpty())
					return;

				libraryObj.addMedia(mediaTitle, mediaFormat);
				libraryList.refresh();// refresh listview

			} catch (LibraryException e1) {

				displayError(e1.getMessage());
			}

		});
		buttonArr.add(add);

		Button delete = new Button("Delete");
		delete.setOnAction(e -> {
			//Delete media from library
			MediaItem tmpItem = this.libraryList.getSelectionModel().getSelectedItem();
			try {

				libraryObj.deleteMedia(tmpItem.getTitle(), tmpItem.getFormat());

				libraryList.refresh();

			} catch (LibraryException e1) {
				displayError(e1.getMessage());
			}

		});
		buttonArr.add(delete);

		Button checkIn = new Button("Check In");
		checkIn.setOnAction(e -> {
			MediaItem tmpItem = this.libraryList.getSelectionModel().getSelectedItem();
			if (!tmpItem.getOnLoan()) {
				displayError("Item already checked in.");
				return;
			}
			try {
				tmpItem.markReturned();
				libraryList.refresh();
			} catch (LibraryException e1) {
				// TODO Auto-generated catch block
				displayError(e1.getMessage());
			}

		});
		buttonArr.add(checkIn);

		Button checkOut = new Button("Check Out");
		checkOut.setOnAction(e -> {
			MediaItem tmpItem = this.libraryList.getSelectionModel().getSelectedItem();
			
			if (tmpItem == null)//no listview item selected  
				return;
			
			if (tmpItem.getOnLoan()) {
				displayError("Item already checked out.");
				return;
			}
			try {
				// Prompt for name of lendee. Return if user presses cancel
				String nameLoaned = getStringInput("Check out media", "Full name of lendee: ");
				if (nameLoaned == null || nameLoaned.isEmpty())
					return;

				// Prompt for date loaned. Return if user presses cancel
				String dateLoaned = getStringInput("Check out media", "Date Loaned: ");
				if (nameLoaned == null || dateLoaned.isEmpty())
					return;

				tmpItem.markOnLoan(nameLoaned, dateLoaned); // Mark item checked out
				libraryList.refresh();// refresh lisview

			} catch (LibraryException e1) {

				displayError(e1.getMessage());
			}
		});
		buttonArr.add(checkOut);

		return buttonArr;

	}

	String getStringInput(String headerText, String contentText) {
		TextInputDialog dialog = new TextInputDialog();
		dialog.setTitle("Media Library");
		dialog.setHeaderText(headerText);
		dialog.setContentText(contentText);

		Optional<String> result = dialog.showAndWait();

		if (result.isPresent()) {
			return result.get();
		} else {
			return null;
		}

	}

	// Display ALert box with error message
	public static void displayError(String errMessage) {
		Alert alert = new Alert(AlertType.ERROR, errMessage);
		alert.showAndWait();
	}

	// Main method of class
	public static void main(String[] args) {
		launch(args);
	}
}
