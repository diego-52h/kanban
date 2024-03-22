package kanban.ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class App extends Application
{
	@Override
	public void start(Stage stage)
	{
		HBox box = new HBox();
		
		Scene scene = new Scene(box, 640, 480);
		
		stage.centerOnScreen();
		stage.setScene(scene);
		
		stage.show();
	}
	
	public static void main(String args[])
	{
		launch(args);
	}
}