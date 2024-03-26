package kanban.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.control.Label;

import javafx.scene.layout.VBox;

import javafx.scene.input.DragEvent;
import javafx.scene.input.TransferMode;

import kanban.controller.TaskNode;

public class CategoryNode extends VBox
{
	private @FXML Label name;
	private @FXML VBox taskContainer;
	
	public CategoryNode(String name)
	{
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/category.fxml"));
		
		loader.setRoot(this);
		loader.setController(this);
		
		try
		{
			loader.load();
			
			this.name.setText(name);
			
			// <==
			
			this.insertTask(new TaskNode());
			this.insertTask(new TaskNode());
			this.insertTask(new TaskNode());
			
			// ==>
			
			this.setOnDragExited((DragEvent event) -> { this.setFocused(false); event.consume(); });
			this.setOnDragEntered((DragEvent event) -> { this.setFocused(true); event.consume(); });
			
			this.setOnDragOver((DragEvent event) -> {
				event.acceptTransferModes(TransferMode.MOVE);
				
				event.consume();
			});
			
			this.setOnDragDropped((DragEvent event) -> {
				System.out.println("dropped item: " + ((TaskNode) event.getGestureSource()).getText());
				
				event.setDropCompleted(true);
				event.consume();
			});
		}
		
		catch(Exception exception)
		{
			exception.printStackTrace();
		}
	}
	
	public void insertTask(TaskNode task) { this.taskContainer.getChildren().add(task); }
	public void removeTask(TaskNode task) { this.taskContainer.getChildren().remove(task); }
}