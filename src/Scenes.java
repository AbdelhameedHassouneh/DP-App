import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import eu.hansolo.enzo.led.Led;
import eu.hansolo.enzo.led.LedBuilder;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Scenes {
	static File file;

	// this is the first scene in the program
	// contains a file chooser for the file
	public static Scene firstScene(Stage primaryStage) {

		Image image = null;
		try {
			image = new Image(new FileInputStream("bulb2.png"));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		ImageView view = new ImageView(image);

		Group pane = new Group();

		BorderPane borderPane = new BorderPane();

		VBox v = new VBox();

		Button browseButton = new Button("Browse the file");

		browseButton.setOnAction(e -> {

			FileChooser fileChooser = new FileChooser();
			file = fileChooser.showOpenDialog(null);

			if (file != null) {

				if (!(file.toString().endsWith(".txt"))) {
					AlertBox.display("Wrong file entered");

				} else {
					int ar[] = ReadFromFile.readFromFile(file);
					if (ar != null) {
						primaryStage.setScene(Scenes.spliterScene(primaryStage, ar));
					}
				}
			}
		});
		v.setPadding(new Insets(100, 0, 0, 0));
		Button enterButton = new Button("Enter from interface");

		enterButton.setMinWidth(200);
		enterButton.setMinHeight(50);
		browseButton.setMinWidth(200);
		browseButton.setMinHeight(50);
		enterButton.setOnAction(e -> {
			primaryStage.setScene(enterFromInterFaceScene(primaryStage));
			// primaryStage.setScene(tableScene());

		});


		v.setSpacing(5);
		v.setAlignment(Pos.CENTER);
		v.getChildren().addAll(browseButton, enterButton);
		borderPane.setCenter(v);
		pane.getChildren().addAll(view, borderPane);
		borderPane.setMinWidth(690);
		borderPane.setMinHeight(790);

		Scene scene = new Scene(pane, 690, 790);
		scene.getStylesheets().add("myStyles.css");

		return scene;
	}
	
	
	//give the user the ability to enter the leds from the interface
	public static Scene enterFromInterFaceScene(Stage primaryStage) {
		Image image = null;
		try {
			image = new Image(new FileInputStream("q.jpg"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ImageView view = new ImageView(image);
		Group group = new Group();

		BorderPane borderPane = new BorderPane();
		VBox v = new VBox();
		Label numberOfLedLabel = new Label("Please enter the number of leds");
		TextField numberOfLedsField = new TextField();
		Label orderOfLedsLabel = new Label("Please enter the order of leds");
		TextArea area = new TextArea();
		area.setMinHeight(400);
		area.setMinWidth(400);

		v.getChildren().addAll(numberOfLedLabel, numberOfLedsField, orderOfLedsLabel, area);
		Button backButton = new Button("back");
		backButton.setOnAction(e -> {
			primaryStage.setScene(firstScene(primaryStage));
		});
		Button continueButton = new Button("continue");
		continueButton.setOnAction(e -> {
			int nOfValues = 0;
			try {
				nOfValues = Integer.parseInt(numberOfLedsField.getText());

			} catch (Exception ee) {
				
				AlertBox.display("wrong input in textfield");
			}

			String inTextArea = area.getText();
			String StringLights[] = inTextArea.split(" ");
			if (StringLights.length != nOfValues) {
				//
				AlertBox.display("the number u entered above doesn't equal the number of values");
				
			} else {
				boolean booleans[] = new boolean[nOfValues + 1];
				int ar[] = new int[nOfValues];
				boolean in = false;
				for (int i = 0; i < StringLights.length; i++) {
					try {
						ar[i] = Integer.parseInt(StringLights[i]);
						if (ar[i] > nOfValues || ar[i] <= 0) { //if the value was minus or above the range
							
							AlertBox.display("Wrong range in the leds values");
							in = true;
							break;
						}
						if (booleans[ar[i]]) {
							AlertBox.display("Please enter the leds with no repetitions");
							in = true;
							break;
						} else {
							booleans[ar[i]] = true;
						}
					} catch (NumberFormatException nfe) {
						AlertBox.display("Please just enter integer values");
						in = true;
						break;
					
					}
				}
				if (!in) {
					
					primaryStage.setScene(Scenes.spliterScene(primaryStage, ar));
				}
			}
		});
		backButton.setMinWidth(100);
		continueButton.setMinWidth(100);

		HBox h = new HBox();
		h.setSpacing(30);
		h.setAlignment(Pos.CENTER);
		h.getChildren().addAll(backButton, continueButton);
		borderPane.setBottom(h);
		h.setPadding(new Insets(0, 0, 30, 0));
		v.setSpacing(20);
		v.setPadding(new Insets(40, 40, 40, 40));
		borderPane.setCenter(v);
		borderPane.setMinWidth(690);
		borderPane.setMinHeight(790);

		group.getChildren().addAll(view, borderPane);
		Scene scene = new Scene(group, 690, 790);
		scene.getStylesheets().add("myStyles.css");
		return scene;
	}
	
	
	//displaying the longest increasing subsequence table and the indices table
	public static Scene tableScene(int ar[], int sublist[], Stage primaryStage) {
		Image image = null;
		try {
			image = new Image(new FileInputStream("q.jpg"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ImageView view = new ImageView(image);
		Group group = new Group();
		String switches[] = new String[ar.length];

		VBox v = new VBox();

		String values[] = new String[ar.length];
		for (int i = 0; i < ar.length; i++) {
			values[i] = ar[i] + "";
			switches[i] = (i + 1) + "";
		}

		Arbitrary arbitrary = new Arbitrary(ar.length, switches, values);
		ArrayList<Arbitrary> list = new ArrayList<>();
		list.add(arbitrary);
		ObservableList<Arbitrary> oList = FXCollections.observableArrayList(list);
		TableView<Arbitrary> tableView = new TableView<>();
		tableView.setMinWidth(400);
		tableView.setMinHeight(200);
		tableView.setMaxHeight(200);
		for (int i = 0; i < values.length; i++) {
			TableColumn<Arbitrary, String> c = new TableColumn<Arbitrary, String>(arbitrary.arOfLights[i]);
			helper(c, i);
			tableView.getColumns().add(c);
			c.setMinWidth(70);

		}

		tableView.setItems(oList);
		String subListStrings[] = new String[sublist.length];

		for (int i = 0; i < sublist.length; i++) {

			subListStrings[i] = sublist[i] + "";

		}

		Arbitrary arbitrary2 = new Arbitrary(ar.length, switches, subListStrings);
		ArrayList<Arbitrary> list2 = new ArrayList<>();
		list2.add(arbitrary2);
		ObservableList<Arbitrary> oList2 = FXCollections.observableArrayList(list2);
		TableView<Arbitrary> tableView2 = new TableView<>();
		tableView2.setMinWidth(400);
		tableView2.setMinHeight(200);
		tableView2.setMaxHeight(200);
		for (int i = 0; i < values.length; i++) {
			TableColumn<Arbitrary, String> c = new TableColumn<Arbitrary, String>(arbitrary.arOfLights[i]);
			helper(c, i);
			tableView2.getColumns().add(c);
			c.setMinWidth(70);

		}

		tableView2.setItems(oList2);

		Label lisLabel = new Label("Longest increasing subsequence");
		lisLabel.setFont(new Font(27));
		Label subListLabel = new Label("SubList");

		subListLabel.setFont(new Font(27));
		Button backButton = new Button("back");
		backButton.setOnAction(e -> {
			primaryStage.setScene(Scenes.spliterScene(primaryStage, ar));
		});
		v.getChildren().addAll(lisLabel, tableView, subListLabel, tableView2, backButton);
		v.setMinWidth(690);
		v.setMinHeight(790);
		v.setSpacing(20);
		v.setAlignment(Pos.CENTER);
		group.getChildren().addAll(view, v);

		Scene scene = new Scene(group, 690, 790);
		scene.getStylesheets().add("myStyles.css");
		return scene;

	}
	
	
	//helper for the table scene
	public static void helper(TableColumn<Arbitrary, String> c, int i) {
		c.setCellValueFactory((p) -> {
			String[] x = p.getValue().arOfValues;
			// return new SimpleStringProperty(x[i].str);
			return new SimpleStringProperty(x[i]);

		});
	}

	static int index;
	
	
	// spliting between the show led and show tables
	public static Scene spliterScene(Stage primaryStage, int ar[]) {

		int lis[] = new int[ar.length];
		int sublist[] = new int[ar.length];
		String result = Algorithms.DpMethod(ar, lis, sublist);
		
		int max = -1;
		index = -1;
		for (int i = 0; i < lis.length; i++) {
			if (max < lis[i]) {
				max = lis[i];
				index = i;
			}
		}
		Image image = null;
		try {
			image = new Image(new FileInputStream("bulb2.png"));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		ImageView view = new ImageView(image);

		Group pane = new Group();

		BorderPane borderPane = new BorderPane();

		VBox v = new VBox();

		Button showTableButton = new Button("Show table");
		showTableButton.setOnAction(e -> {
			primaryStage.setScene(Scenes.tableScene(lis, sublist, primaryStage));

		});
		Button showLedsButton = new Button("Show Leds");
		showLedsButton.setOnAction(e -> {
			primaryStage.setScene(Scenes.showingLedScene(ar, result, lis, sublist, index, primaryStage));
		});

		v.setPadding(new Insets(100, 0, 0, 0));

		showLedsButton.setMinWidth(200);
		showLedsButton.setMinHeight(50);
		showTableButton.setMinWidth(200);
		showTableButton.setMinHeight(50);

		v.setSpacing(5);
		v.setAlignment(Pos.CENTER);
		v.getChildren().addAll(showTableButton, showLedsButton);
		borderPane.setCenter(v);
		pane.getChildren().addAll(view, borderPane);
		borderPane.setMinWidth(690);
		borderPane.setMinHeight(790);

		Scene scene = new Scene(pane, 690, 790);
		scene.getStylesheets().add("myStyles.css");

		return scene;

	}
	
	
	//showing the turned on leds for the user
	public static Scene showingLedScene(int values[], String result, int lis[], int sublist[], int index,
			Stage primaryStage) {
		int max = -1;
		index = -1;
		for (int i = 0; i < lis.length; i++) {
			if (max < lis[i]) {
				max = lis[i];
				index = i;
			}
		}
		int indecies[] = new int[result.length()];
		int times = Algorithms.recIndecies(indecies, 0, sublist, index);
		System.out.println("times is " + times);
		Image image = null;
		try {
			image = new Image(new FileInputStream("black.jpg"));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		ImageView view = new ImageView(image);
		Group group = new Group();
		VBox vLeds = new VBox();
		Label numberOfOnLeds = new Label("Number of off leds: " + (values.length - times));
		Label numberOfOffLeds = new Label("Number of on leds: " + times);

		numberOfOffLeds.setFont(new Font(22));
		numberOfOnLeds.setFont(new Font(22));

		RadioButton onButton = new RadioButton("on");
		RadioButton offButton = new RadioButton("off");
		ToggleGroup toggleGroup = new ToggleGroup();
		onButton.setToggleGroup(toggleGroup);
		offButton.setToggleGroup(toggleGroup);

		Button backButton = new Button("Back");
		backButton.setPrefWidth(100);
		backButton.setOnAction(e -> {
			primaryStage.setScene(Scenes.spliterScene(primaryStage, values));
		});
		HBox h = new HBox();
		h.setSpacing(20);
		h.getChildren().addAll(offButton, onButton);

		VBox vBox = new VBox();
		vBox.setSpacing(20);
		vBox.getChildren().addAll(numberOfOffLeds, numberOfOnLeds, h, backButton);
		VBox vNumbers = new VBox();

		Led[] leds = new Led[values.length];

		for (int i = 0; i < values.length; i++) {
			Label label = new Label("" + (values[i]));
			label.setPrefWidth(40);
			label.setPrefHeight(40);

			label.setFont(new Font(25));
			vNumbers.getChildren().add(label);
			leds[i] = LedBuilder.create().prefHeight(40.0).prefWidth(40.0).build();

			vLeds.getChildren().addAll(leds[i]);

		}
		for (int i = 0; i < sublist.length; i++) {
			System.out.println("the sublist is of i" + i + " is " + sublist[i]);
		}

		System.out.println("the max index is " + index);

		onButton.setOnMouseClicked(e -> {
			for (int i = indecies.length - 1; i >= 0; i--) {
				leds[indecies[i]].setOn(true);

			}
		});

		offButton.setOnMouseClicked(e -> {
			for (int i = 0; i < leds.length; i++) {
				leds[i].setOn(false);
			}
		});
		Label headerLabel = new Label("Leds");
		headerLabel.setLayoutX(309);
		headerLabel.setLayoutY(30);
		headerLabel.setFont(new Font(32));

		vLeds.setLayoutX(63);
		vLeds.setLayoutY(93);
		vNumbers.setLayoutX(14);
		vNumbers.setLayoutY(93);

		vBox.setLayoutX(400);
		vBox.setLayoutY(93);
		ScrollPane scrollPane = new ScrollPane();
		// Always show vertical scroll bar
		scrollPane.setVbarPolicy(ScrollBarPolicy.ALWAYS);

		// Horizontal scroll bar is only displayed when needed
		scrollPane.setHbarPolicy(ScrollBarPolicy.AS_NEEDED);

		group.getChildren().addAll(vLeds, vNumbers, vBox, headerLabel);
		scrollPane.setContent(group);

		Scene scene = new Scene(scrollPane, 690, 790);
		scene.getStylesheets().add("myStyles.css");
		scrollPane.setStyle("-fx-background: rgb(0,0,0);\n -fx-background-color: rgb(0,0,0)");
		scene.setFill(Color.BLACK);
		return scene;

	}

}
