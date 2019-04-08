package myPackage;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Person extends UserInterface implements Initializable {
	public static int gameType = 0;
	public static int timeScale = 1;
	public static String currentPerson = "Empty Slot";

	public static void main(String[] args) {

		launch(args);
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {

		list1.addAll("choice1", "choice2", "choice3");
		list2.addAll("choice4", "choice5", "choice6");
		
		readToSave();
		fillChoiceBox();
		
		game.setItems(list2);

		profile.getSelectionModel().select(0);
		currentPerson = new String(profile.getValue());

		play.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (currentPerson.equals("Empty Slot")) {
					createPerson();
					fillChoiceBox();
				}
				else {
					
				if(gameType == 0) {
					QuickGame game1 = new QuickGame(timeScale, currentPerson);
					game1.displayResults();
				
				}
				else {
					QuickGame game1 = new DetailedGame(timeScale, currentPerson);
					game1.displayResults();
				}
				
				
				}
			}

		});

		profile.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				currentPerson = new String(profile.getValue());

			}

		});
		
		preset.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				

			}

		});
		game.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				

			}

		});

	}

	public void createPerson() {
		final Pane p = new Pane();
		TextField text = new TextField();

		p.getChildren().add(text);

		Stage newStage = new Stage();
		newStage.setScene(new Scene(p));
		newStage.show();

		/*
		 * https://stackoverflow.com/questions/13880638/
		 * how-do-i-pick-up-the-enter-key-being-pressed- in-javafx2
		 */
		text.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent ke) {
				if (ke.getCode().equals(KeyCode.ENTER)) {
					boolean check = false;
					for(int i = 0; i<9 && check == false; i++) {
						if(people[i] == null || people[i].equals("Empty Slot")) {
							people[i] = text.getText();
							check = true;
						}
					}
					
					list3.removeAll();
					fillChoiceBox();
					newStage.close();
					
				}
			}

		});

	}

	public void fillChoiceBox() {

		for (int i = 0; i < 9; i++) {
				if(people[i] == null) {
					list3.add("Empty Slot");
				}
				else {
				list3.add(people[i]);
				}
		}
		if (list3.size() > 10) {
			list3.remove(0, 9);
		}

		profile.setItems(list3);
		profile.getSelectionModel().select(0);
	}

}
