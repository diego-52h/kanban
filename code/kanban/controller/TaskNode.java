package kanban.controller;

import javafx.beans.property.SimpleObjectProperty;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;

import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;

import javafx.scene.paint.Color;

import kanban.controller.EditorWindow;

import kanban.model.Task;

public class TaskNode extends Label
{
	private @FXML MenuItem modifyButton;
	private @FXML MenuItem removeButton;
	
	private SimpleObjectProperty<Task> task;
	
	public TaskNode()
	{
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/task.fxml"));
		
		loader.setRoot(this);
		loader.setController(this);
		
		try
		{
			loader.load();
			
			this.task = new SimpleObjectProperty(new Task("", "", "#FEBF91"));
			this.task.addListener((elem, prev, curr) -> { this.update(); });
			
			this.modifyButton.setOnAction((ActionEvent event) -> { this.modify(); event.consume(); });
			this.removeButton.setOnAction((ActionEvent event) -> { this.remove(); event.consume(); });
			
			this.setOnDragDetected((MouseEvent event) -> {
				Dragboard dragger = this.startDragAndDrop(TransferMode.MOVE);
				ClipboardContent content = new ClipboardContent();
				
				content.putString("");
				dragger.setContent(content);
				
				event.consume();
			});
			
			this.setOnMouseExited((MouseEvent event) -> { this.setColor(Color.web(this.getTask().getColor())); event.consume(); });
			this.setOnMouseEntered((MouseEvent event) -> { this.setColor(Color.web(this.getTask().getColor()).deriveColor(1, 0.8, 1, 1)); event.consume(); });
			
			this.update();
		}
		
		catch(Exception exception)
		{
			exception.printStackTrace();
		}
	}
	
	public Task getTask()
	{
		return this.task.getValue();
	}
	
	private void update()
	{
		this.setText(this.getTask().getName());
		this.setColor(Color.web(this.getTask().getColor()));
	}
	
	private void modify()
	{
		EditorWindow.launch(this.getScene().getWindow());
	}
	
	private void remove()
	{
		System.out.println("remove");
	}
	
	private void setColor(Color color)
	{
		int r = (int) (255 * color.getRed());
		int g = (int) (255 * color.getGreen());
		int b = (int) (255 * color.getBlue());
		
		String cssSetter = String.format("-fx-background-color: rgb(%d, %d, %d);", r, g, b);
		
		this.setStyle(cssSetter);
	}
}