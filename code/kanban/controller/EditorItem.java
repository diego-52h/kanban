package kanban.controller;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.layout.BorderPane;

public class EditorItem extends BorderPane
{
	public EditorItem()
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
			
			return;
		}
	}
}