package kanban.controller;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;

import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;

import kanban.controller.EditorWindow;
import kanban.controller.ObservableTask;

import kanban.model.State;

public class TaskNode extends Label
{
	private @FXML MenuItem modifyButton;
	private @FXML MenuItem removeButton;
	
	private final ObservableTask task;
	
	public TaskNode(ObservableTask task)
	{
		this.task = task;
		
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/task.fxml"));
		
		loader.setRoot(this);
		loader.setController(this);
		
		try
		{
			loader.load();
			
			this.task.addListener((curr) -> { this.update(); });
			
			this.modifyButton.setOnAction((ActionEvent event) -> { this.modify(); event.consume(); });
			this.removeButton.setOnAction((ActionEvent event) -> { this.remove(); event.consume(); });
			
			this.setOnDragDetected((MouseEvent event) -> {
				Dragboard dragger = this.startDragAndDrop(TransferMode.MOVE);
				ClipboardContent content = new ClipboardContent();
				
				content.putString("");
				dragger.setContent(content);
				
				event.consume();
			});
			
			this.setOnMouseExited((MouseEvent event) -> { this.setColor(this.task.getColor(), 0); event.consume(); });
			this.setOnMouseEntered((MouseEvent event) -> { this.setColor(this.task.getColor(), 20); event.consume(); });
			
			this.update();
		}
		
		catch(Exception exception)
		{
			exception.printStackTrace();
		}
	}
	
	public ObservableTask getTask()
	{
		return this.task;
	}
	
	/**
	 * Updates the graphical elements of this task to match the state
	 * held by the internal <code> task </code> object
	 */
	private void update()
	{
		this.setText(task.getName());
		this.setColor(task.getColor(), 0);
	}
	
	/**
	 * Launches a new editor instance for this task
	 */
	private void modify()
	{
		EditorWindow.launch(this.getTask(), this.getScene().getWindow());
	}
	
	private void remove()
	{
		this.task.setState(State.NONE);
	}
	
	private void setColor(String color, int percentage)
	{
		this.setStyle("-fx-background-color: derive(" + color + ", " + percentage + "%);");
	}
}