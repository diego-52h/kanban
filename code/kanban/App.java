package kanban;

import javafx.application.Application;

import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.stage.Stage;

import kanban.controller.BoardNode;

public class App extends Application
{
	private final int SIZE_X = 1080;
	private final int SIZE_Y = 720;
	
	@Override
	public void start(Stage stage)
	{
		Parent root = new BoardNode("Kanban Board");
		
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
			exception.printStackTrace();
			
			return;
		}
	}
}