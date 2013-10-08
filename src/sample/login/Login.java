package sample.login;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sample.Main;
import sample.main.FileUtil;
import sample.userhome.UserHome;

import java.io.IOException;

public class Login{

	@FXML private PasswordField passwordField;
	@FXML private TextField userNameField;
	@FXML private Text actiontarget;    //A text as defined in Login.fxml

	@FXML        //If not public, must have FXML annotation
	private void handleSubmitLogin(ActionEvent actionEvent){
		if(FileUtil.isValid(userNameField.getText(), passwordField.getText())){
			actiontarget.setText(String.format("Username: %s, Password: %s", userNameField.getText(), passwordField.getText()));
			try{
				Stage stage = Main.getStage();
				Parent root = FXMLLoader.load(getClass().getResource("/sample/userhome/UserHome.fxml"));
				stage.setTitle("Media Watcher Home");
				stage.setScene(new Scene(root, stage.getWidth(), stage.getHeight()));
				stage.show();
			}catch(IOException e){
				e.printStackTrace();
			}
		}else{
			actiontarget.setText("Invalid username/password.");
		}
	}

	@FXML
	private void handleSubmitRegister(ActionEvent actionEvent){
		String user =  userNameField.getText();
		String pass = passwordField.getText();
		if(user == null || user.isEmpty() || pass == null || pass.isEmpty())  return;
		try{
			FileUtil.addUser(user, pass);
		}catch(IOException e){
			e.printStackTrace();
		}
	}
}
