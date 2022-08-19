package application;


import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;


public class SampleController  implements Initializable  {
Stage stage;
Parent pane;
Scene scene;
    @FXML
    private Button button1;

    @FXML
    private Button button2;

    @FXML
    private Button button3;

    @FXML
    private Button button4;

    @FXML
    private Button button5;

    @FXML
    private Button button6;

    @FXML
    private Button button7;

    @FXML
    private Button button8;

    @FXML
    private Button button9;

    @FXML
    private Text winnerText;
    
   @FXML private Timeline animation;

    private int playerTurn = 0;

    ArrayList<Button> buttons;
    
    public void exitTowindow(ActionEvent e) throws IOException {
		 pane =(VBox) FXMLLoader.load(getClass().getResource("window.fxml"));
		stage = (Stage)((Node) e.getSource()).getScene().getWindow();
		scene = new Scene(pane,800,600);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
	    stage.setScene(scene);
	    stage.show();}
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        buttons = new ArrayList<>(Arrays.asList(button1,button2,button3,button4,button5,button6,button7,button8,button9));

        buttons.forEach(button ->{
            setupButton(button);
            button.setFocusTraversable(false);
        });
    }
    
    
     
    @FXML
    void restartGame(ActionEvent event) {
        buttons.forEach(this::resetButton);
        winnerText.setText("Tic-Tac-Toe");
    }

    public void resetButton(Button button){
        button.setDisable(false);
        button.setText("");
    }

    private void setupButton(Button button) {
        button.setOnMouseClicked(mouseEvent -> {
            setPlayerSymbol(button);
            button.setDisable(true);
            checkIfGameIsOver();
        });
    }

    public void setPlayerSymbol(Button button){
        if(playerTurn % 2 == 0){
            button.setText("X");
            playerTurn = 1;
        } else{
            button.setText("O");
            playerTurn = 0;
        }
    }

    public void checkIfGameIsOver(){
        for (int a = 0; a < 8; a++) {
            String line;
            if(a==0) {
            	line = button1.getText() + button2.getText() + button3.getText();
            }else if(a==1){
            	line = button4.getText() + button5.getText() + button6.getText();
            }else if(a==2) {
            	line = button7.getText() + button8.getText() + button9.getText();
            }else if(a==3) {
            	line = button1.getText() + button5.getText() + button9.getText();
            }else if(a==4) {
            	line = button3.getText() + button5.getText() + button7.getText();
            }else if(a==5) {
            	line = button1.getText() + button4.getText() + button7.getText();
            }else if(a==6) {
            	line = button2.getText() + button5.getText() + button8.getText();
            }else if(a==7){
            	line = button3.getText() + button6.getText() + button9.getText();
            }else {
            	continue;}
           
            //X winner
            if (line.equals("XXX")) {
                winnerText.setText("X won!");
            }

            //O winner
            else if (line.equals("OOO")) {
                winnerText.setText("O won!");
            }
        }
    }
}
