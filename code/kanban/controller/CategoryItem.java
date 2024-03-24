package kanban.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.control.Label;

import javafx.scene.layout.VBox;

import javafx.scene.input.DragEvent;
import javafx.scene.input.TransferMode;

import kanban.controller.TaskItem;

public class CategoryItem extends VBox
{
	private @FXML VBox tasks;
	private @FXML Label name;
	
	public CategoryItem(String title)
	{
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/category.fxml"));
		
		loader.setRoot(this);
		loader.setController(this);
		
		try
		{
			loader.load();
			
			this.name.setText(title);
			
			this.insertTask(new TaskItem("task 1"));
			this.insertTask(new TaskItem("task 2"));
			this.insertTask(new TaskItem("task 3"));
			
			this.setOnDragOver((DragEvent event) -> {
				event.acceptTransferModes(TransferMode.MOVE);
				event.consume();
			});
			
			this.setOnDragDropped((DragEvent event) -> {
				try
				{
					TaskItem task = ((TaskItem) event.getGestureSource());
					
					this.insertTask(task);
				}
				
				catch(ClassCastException exception)
				{
					System.err.println(exception);
				}
				
				event.setDropCompleted(true);
				event.consume();
			});
			
			this.setOnDragEntered((DragEvent event) -> { this.setFocused(true); event.consume(); });
			this.setOnDragExited((DragEvent event) -> { this.setFocused(false); event.consume(); });
		}
		
		catch(Exception exception)
		{
			exception.printStackTrace();
			
			return;
		}
	}
	
	public void insertTask(TaskItem task)
	{
		task.setCategory(this);
		
		this.tasks.getChildren().add(task);
	}
	
	public void removeTask(TaskItem task)
	{
		task.setCategory(null);
		
		this.tasks.getChildren().remove(task);
	}
}