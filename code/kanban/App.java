package kanban;

import javafx.application.Application;

import javafx.fxml.FXMLLoader;

import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.stage.Stage;

public class App extends Application
{
	private final int SIZE_X = 1080;
	private final int SIZE_Y = 720;
	
	@Override
	public void start(Stage stage)
	{
		Parent root = null;
		
		try
		{
			root = FXMLLoader.load(App.class.getResource("/view.fxml"));
		}
		
		catch(Exception exception)
		{
			System.out.println(exception.toString());
			
			return;
		}
		
		Scene scene = new Scene(root, this.SIZE_X, this.SIZE_Y);
		
		stage.setScene(scene);
		
		stage.centerOnScreen();
		stage.setResizable(false);
		
		stage.show();
	}
	
	public static void main(String args[])
	{
		launch(args);
	}
}