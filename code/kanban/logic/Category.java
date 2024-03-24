package kanban.logic;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.control.Label;

import javafx.scene.layout.VBox;

import javafx.scene.input.DragEvent;
import javafx.scene.input.TransferMode;

import kanban.logic.Task;

public class Category extends VBox
{
	private @FXML VBox tasks;
	private @FXML Label name;
	
	public Category(String title)
	{
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/category.fxml"));
		
		loader.setRoot(this);
		loader.setController(this);
		
		try
		{
			loader.load();
			
			this.name.setText(title);
			
			this.setOnDragOver((DragEvent event) -> {
				event.acceptTransferModes(TransferMode.MOVE);
				event.consume();
			});
			
			this.setOnDragDropped((DragEvent event) -> {
				try
				{
					Task task = ((Task) event.getGestureSource());
					
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
	
	public void insertTask(Task task)
	{
		task.setCategory(this);
		
		this.tasks.getChildren().add(task);
	}
	
	public void removeTask(Task task)
	{
		task.setCategory(null);
		
		this.tasks.getChildren().remove(task);
	}
}