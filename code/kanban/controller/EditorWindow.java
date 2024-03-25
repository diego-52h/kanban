package kanban.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.layout.BorderPane;

public class EditorWindow extends BorderPane
{
	public EditorWindow()
	{
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/editor.fxml"));
		
		loader.setRoot(this);
		loader.setController(this);
		
		try
		{
			loader.load();
		}
		
		catch(Exception exception)
		{
			exception.printStackTrace();
		}
	}
}