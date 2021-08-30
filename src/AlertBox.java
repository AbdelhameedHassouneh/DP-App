import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AlertBox {
	public static void display(String message) {
		Stage window = new Stage();

		window.initModality(Modality.APPLICATION_MODAL);// block events to other windows
		window.setTitle("Alert");// setting title
		window.setMinWidth(250);// setting width of the stage

		Label label = new Label(); // label for the message to show
		label.setText(message);
		Button closeButton = new Button("Close this window");
		closeButton.setOnAction(e -> window.close());// closing the window after the button pressed

		VBox layout = new VBox(10);
		layout.getChildren().addAll(label, closeButton);
		layout.setAlignment(Pos.CENTER);// set the layout in the center

		// Display window and wait for it to be closed before returning
		Scene scene = new Scene(layout, 300, 300);
		window.setScene(scene);// setting the scene
		window.showAndWait();
	}

	public static void displayInfoMessage(String message, boolean flag) {
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		Label label = new Label(); // label for the message to show
		label.setLayoutX(30);
		label.setLayoutY(50);
		if (flag) {
			label.setTextFill(Color.web("green"));

		} else {
			label.setTextFill(Color.web("red"));
		}

		label.setText(message);
		Button closeButton = new Button("Close");

		closeButton.setOnAction(e -> window.close());// closing the window after the button pressed
		VBox vbox = new VBox();
		vbox.getChildren().addAll(label, closeButton);
		vbox.setSpacing(15);
		vbox.setAlignment(Pos.CENTER);
		
		

		Scene scene = new Scene(vbox, 300, 300);
		window.setScene(scene);
		window.show();

	}

}
