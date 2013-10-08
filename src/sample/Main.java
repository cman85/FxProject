package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.main.FileUtil;

import java.io.IOException;

public class Main extends Application{

	private static Stage stage;

	@Override
	public void start(Stage primaryStage) throws Exception{
		Parent root = FXMLLoader.load(getClass().getResource("login/Login.fxml"));
		primaryStage.setTitle("Media Watcher Login");
		primaryStage.setScene(new Scene(root, 750, 600));
		setStage(primaryStage);
		primaryStage.show();
	}

	public static void main(String[] args){
		try{
			FileUtil.init();
		}catch(IOException e){
			e.printStackTrace();
		}
		launch(args);
	}
	public static void setStage(Stage stage){
		Main.stage = stage;
	}

	public static Stage getStage(){
		return Main.stage;
	}


}
