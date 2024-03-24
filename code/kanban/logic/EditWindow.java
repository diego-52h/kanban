package kanban.logic;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.layout.BorderPane;

import kanban.logic.Task;

public class EditWindow extends BorderPane
{
	public EditWindow(Task task)
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