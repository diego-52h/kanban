package kanban;

import javafx.application.Application;

import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.stage.Stage;

import kanban.logic.Board;

public class App extends Application
{
	private final int SIZE_X = 1080;
	private final int SIZE_Y = 720;
	
	@Override
	public void start(Stage stage)
	{
		Parent root = new Board("Kanban Board");
		
		root.getStylesheets().add(App.class.getResource("/common.css").toString());
		
		Scene scene = new Scene(root, this.SIZE_X, this.SIZE_Y);
		
		stage.setScene(scene);
		
		stage.centerOnScreen();
		stage.setResizable(false);
		
		stage.show();
	}
	
	public static void main(String args[])
	{
		try
		{
			launch(args);
		}
		
		catch(Exception exception)
		{
			System.err.println(exception);
			
			exception.printStackTrace();
			
			return;
		}
	}
}