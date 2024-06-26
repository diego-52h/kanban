package kanban.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.control.Label;

import javafx.scene.layout.VBox;

import javafx.scene.input.DragEvent;
import javafx.scene.input.TransferMode;

import kanban.controller.TaskNode;

import kanban.model.State;
import kanban.model.Task;

public class CategoryNode extends VBox
{
	private @FXML Label name;
	private @FXML VBox taskContainer;
	
	private final State state;
	private final String nameText;
	
	public CategoryNode(String name, State state)
	{
		this.state = state;
		this.nameText = name;
		
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/category.fxml"));
		
		loader.setRoot(this);
		loader.setController(this);
		
		try
		{
			loader.load();
			
			this.setOnDragExited((DragEvent event) -> { this.setFocused(false); event.consume(); });
			this.setOnDragEntered((DragEvent event) -> { this.setFocused(true); event.consume(); });
			
			this.setOnDragOver((DragEvent event) -> {
				Task task = ((TaskNode) event.getGestureSource()).getTask();
				
				if(task.getState() != this.state)
					event.acceptTransferModes(TransferMode.MOVE);
				
				event.consume();
			});
			
			this.setOnDragDropped((DragEvent event) -> {
				Task task = ((TaskNode) event.getGestureSource()).getTask();
				
				task.setState(this.state);
				
				event.setDropCompleted(true);
				event.consume();
			});
			
			this.update();
		}
		
		catch(Exception exception)
		{
			exception.printStackTrace();
		}
	}
	
	public void insertTask(TaskNode task) { this.taskContainer.getChildren().add(task); this.update(); }
	public void removeTask(TaskNode task) { this.taskContainer.getChildren().remove(task); this.update(); }
	
	public void removeTasks() { this.taskContainer.getChildren().clear(); }
	
	/**
	 * Updates the graphical elements of this category
	 */
	private void update()
	{
		this.name.setText(this.nameText + " - " + this.taskContainer.getChildren().size());
	}
}