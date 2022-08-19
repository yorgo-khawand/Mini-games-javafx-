package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
public class Window_controller {
	private Stage stage;
	private Scene scene;
	private Parent pane;
	  public  void loadTicTacToe(ActionEvent e)  throws IOException {
			 pane =FXMLLoader.load(getClass().getResource("Sample.fxml"));
			stage = (Stage)((Node) e.getSource()).getScene().getWindow();
			scene = new Scene(pane,800,600);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		    stage.setScene(scene);
		    stage.setResizable(false);
		    stage.show();
		}
		public void loadHockeyGame(ActionEvent e) throws IOException {
			 pane =(VBox) FXMLLoader.load(getClass().getResource("loadHockeyGame.fxml"));
			stage = (Stage)((Node) e.getSource()).getScene().getWindow();
			scene = new Scene(pane,800,600);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		    stage.setScene(scene);
		    stage.show();
		}
}
