package kanban.controller;

import javafx.fxml.FXMLLoader;

import javafx.scene.control.Label;

public class TaskItem extends Label
{
	public TaskItem()
	{
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/task.fxml"));
		
		loader.setRoot(this);
		loader.setController(this);
		
		try
		{
			loader.load();
		}
		
		catch(Exception exception)
		{
			System.err.println(exception.toString());
			
			return;
		}
	}
}