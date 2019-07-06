package simulation.cdr.views;

import com.jfoenix.controls.*;
import com.sun.prism.paint.Paint;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import simulation.cdr.business.LoginChecker;



public class FXMLDocumentController{
    
    @FXML
    private JFXTextField loginTextField;
    
    @FXML
    private JFXPasswordField passwordTextField;
    
    @FXML
	private Label loginfailed;
    
	@FXML
	private Label passfailed;
	
	private StringBuffer loginMsg = new StringBuffer();
	private StringBuffer passMsg = new StringBuffer();
	private LoginChecker lc = new LoginChecker();
    
    
	public void checkLoginPass(ActionEvent event) {
		try {
			if(lc.checkLoginPass(loginTextField.getText(), passwordTextField.getText(), loginMsg, passMsg)) {
				System.out.println("OK!!");
				Stage primaryStage = new Stage();
				Parent root  = FXMLLoader.load(getClass().getResource("/simulation/cdr/views/Main.fxml"));
				Scene scene = new Scene(root);
				primaryStage.setTitle("Main page");
				primaryStage.setScene(scene);
				primaryStage.setResizable(false);
				primaryStage.show();
			}
			else{
				loginfailed.setText(loginMsg.toString());
				passfailed.setText(passMsg.toString());
				System.out.println("NOK!!");
			}
		}catch(Exception e) {
			System.out.println("ici");
			System.out.println(e.getMessage());
		}	
	}
	
}
