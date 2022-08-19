package application;
import javafx.scene.layout.FlowPane;
import java.io.IOException;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.layout.VBox;
public class HockeyGame_Controller {
	private Stage stage;
	private Scene scene;
	private Parent pane;
	@FXML private   Circle ball;
	@FXML private   Rectangle player1,player2,goal1,goal2;
	@FXML private   Text player1_score,player2_score,finishText;
	@FXML private  AnchorPane gameroot;
	@FXML private Button startbtn;
	private static boolean gameStarted;
	public static final int width=800;
	public static final int height=600;
	public static final int player_height=100;
	public static final int player_width=10;
	public  static final int ball_radius=10;
	private static int ballxspeed=1;
	private static int ballyspeed=1;
	private static int scorep1=0;
	private static int scorep2=0;
	private static int player1_ypos=height/2;
	private static  int player1_xpos=width/2-200;
	private static  int player2_ypos=height/2;
	private  static int player2_xpos=width/2+200;
	private static int ball_xpos=width/2;
	private static int ball_ypos=height/2;


	
	
	    public  void switchTogame(ActionEvent e)  throws IOException {
		 pane =(AnchorPane)FXMLLoader.load(getClass().getResource("HockeyGame.fxml"));
		stage = (Stage)((Node) e.getSource()).getScene().getWindow();
		scene = new Scene(pane,800,600);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
	    stage.setScene(scene);
	    stage.setResizable(false);
	    stage.show();
	}
	public void switchToLoading(ActionEvent e) throws IOException {
		 pane =(VBox) FXMLLoader.load(getClass().getResource("loadHockeyGame.fxml"));
		stage = (Stage)((Node) e.getSource()).getScene().getWindow();
		scene = new Scene(pane,800,600);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
	    stage.setScene(scene);
	    stage.show();
	}
	public void switchTowindow(ActionEvent e) throws IOException {
		 pane =(VBox) FXMLLoader.load(getClass().getResource("window.fxml"));
		stage = (Stage)((Node) e.getSource()).getScene().getWindow();
		scene = new Scene(pane,800,600);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
	    stage.setScene(scene);
	    stage.show();
	}
	public void startGame() {
		gameStarted=true;
		startbtn.setDisable(true);
		Timeline t1=new Timeline(new KeyFrame(Duration.millis(5),e->check()    ));
		t1.setCycleCount(Timeline.INDEFINITE);
	    t1.play();
	}
	
	public void check() {

		if(gameStarted) {
			
 			ball_xpos+=ballxspeed;
 			ball_ypos+=ballyspeed;
			this.ball.setCenterX(ball_xpos);
			this.ball.setCenterY(ball_ypos);
		    
 			// check if player1 && player2 and ball outside the scene 
 		       if(player1.getX()<0 ||player1.getY()<0||player1.getX()>width||player1.getY()>height ) {
 		    	   player1.setX(width/2 -200);
 		           player1.setY(height/2);
 		           }
 		       if(player2.getX()<0 ||player2.getY()<0||player2.getX()>width||player2.getY()>height ) {
		    	   player2.setX(width/2 +200);
		           player2.setY(height/2);
		           }
 		      if(ball_ypos>height||ball_ypos<0) ballyspeed*=-1;  
 		      
 		      if(ball_xpos<0||ball_xpos>width)  ballxspeed*=-1;
 		     

 		      //set event handler for player1 and player2
 		
 		player1.setOnMouseDragged(a->{
 		 		player1.setX(player1.getLayoutX() + a.getX());
 		 		player1.setY(player1.getLayoutY() + a.getY());
 		 	});
 		 	
 		 	
 	player2.setOnKeyPressed(c->{
 		switch(c.getCode()) {
 		case DOWN: player2.setY(player2.getY() + 30);
 		break;
 		case UP: player2.setY(player2.getY() - 30) ;break;
 		case LEFT:player2.setX(player2.getX() - 30); break;
 		case RIGHT: player2.setX(player2.getX() + 30); break;
 		}
 	
 	});

 	// check collisions between player1 ,player2 and ball
 	if(ball.getBoundsInLocal().intersects(player1.getBoundsInLocal())){
 		if(ball.getCenterX()-ball.getRadius()==player1.getX()+player_width ||ball.getCenterX()+ball.getRadius()==player1.getX()){
 			 ballxspeed=ballxspeed < 0?ballxspeed-1:ballxspeed+1;
	 	   	     ballxspeed*=-1;
 		}
 	
 	    if(ball.getCenterY()+ball.getRadius()==player1.getY()+player_height || ball.getCenterY()-ball.getRadius()==player1.getY()) {
 	    	 ballyspeed=ballyspeed < 0?ballyspeed-1:ballyspeed+1;
 	   	     ballyspeed*=-1;    
 	}
 	 
 	}
 	
 	     if(ball.getBoundsInLocal().intersects(player2.getBoundsInLocal())){
 	 		if(ball.getCenterX()+ball.getRadius()==player2.getX()|| ball.getCenterX()-ball.getRadius()==player2.getX()+player_width){
 	 			 ballxspeed=ballxspeed < 0&&ballxspeed>-2?ballxspeed-1:ballxspeed+1;
 	 	   	     ballxspeed*=-1;
 	 		}
 	
 	 	    if(ball.getCenterY()+ball.getRadius()==player2.getY()+player_height||ball.getCenterY()-ball.getRadius()==player2.getY()) {
 	 	    	ballyspeed=ballyspeed < 0?ballyspeed-1:ballyspeed+1;
 	 	   	     ballyspeed*=-1; 	    
 	 	}
 	 
 	   }
		if(this.goal1.getBoundsInLocal().intersects(this.ball.getBoundsInLocal())) {
 		    scorep2++;
			this.player2_score.setText("player2: "+ scorep2);
			gameStarted=false;
		}		
		if(this.goal2.getBoundsInLocal().intersects(this.ball.getBoundsInLocal())) {
			scorep1++;
			player1_score.setText("player1: "+scorep1);
			gameStarted=false;
		 }
	if(ballxspeed<-2)ballxspeed+=1;
	if(ballxspeed>2)ballxspeed+=-1;
	if(ballyspeed<-2)ballyspeed+=1;
	if(ballyspeed>2)ballyspeed+=-1;
		
	
		}
		else {
			ball_xpos=width/2;
			ball_ypos=height/2;
			ballxspeed=1;
			ballyspeed=1;
			if(scorep1 ==7) {
				scorep1=0;
				gameStarted=false;
				finishText.setText("PLAYER1 WOOOOONNNNN ");	
				startbtn.setDisable(false);
				
			
			}
			else if(scorep2==7) {
				scorep2=0;
				gameStarted= false;
				finishText.setText("PLAYER2 WOOOOONNNNN ");	
				startbtn.setDisable(false);
				
			}
			else {  gameStarted = true;
			finishText.setText(" ");
			}
		}
		player2.requestFocus();
//		player1.requestFocus();
	}
}
