package kanban.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.control.Label;

import javafx.scene.layout.VBox;

import javafx.scene.input.DragEvent;
import javafx.scene.input.TransferMode;

import kanban.controller.BoardNode;
import kanban.controller.TaskNode;

import kanban.model.State;
import kanban.model.Task;

public class CategoryNode extends VBox
{
	private @FXML VBox taskContainer;
	private @FXML Label name;
	
	private BoardNode owner;
	private State representedState;
	
	public CategoryNode(String name, State representedState, BoardNode owner)
	{
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/category.fxml"));
		
		loader.setRoot(this);
		loader.setController(this);
		
		try
		{
			loader.load();
			
			this.owner = owner;
			this.representedState = representedState;
			
			this.name.setText(name);
			
			this.setOnDragExited((DragEvent event) -> { this.setFocused(false); event.consume(); });
			this.setOnDragEntered((DragEvent event) -> { this.setFocused(true); event.consume(); });
			
			this.setOnDragOver((DragEvent event) -> {
				Task task = ((TaskNode) event.getGestureSource()).getTask();
				
				if(task.getState() != this.representedState)
					event.acceptTransferModes(TransferMode.MOVE);
				
				event.consume();
			});
			
			this.setOnDragDropped((DragEvent event) -> {
				Task task = ((TaskNode) event.getGestureSource()).getTask();
				
				task.setState(this.representedState);
				this.owner.update();
				
				event.setDropCompleted(true);
				event.consume();
			});
		}
		
		catch(Exception exception)
		{
			exception.printStackTrace();
		}
	}
	
	public State getRepresentedState() { return this.representedState; }
	
	public void eraseTasks() { this.taskContainer.getChildren().clear(); };
	
	public void insertTask(TaskNode task) { this.taskContainer.getChildren().add(task); }
	public void removeTask(TaskNode task) { this.taskContainer.getChildren().remove(task); }
}