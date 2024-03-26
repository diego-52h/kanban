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

public class TaskNode extends Label
{
	private @FXML MenuItem modifyButton;
	private @FXML MenuItem removeButton;
	
	public TaskNode()
	{
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/task.fxml"));
		
		loader.setRoot(this);
		loader.setController(this);
		
		try
		{
			loader.load();
			
			this.setText("T");
			
			this.modifyButton.setOnAction((ActionEvent event) -> { this.modify(); event.consume(); });
			this.removeButton.setOnAction((ActionEvent event) -> { this.remove(); event.consume(); });
			
			this.setOnDragDetected((MouseEvent event) -> {
				Dragboard dragger = this.startDragAndDrop(TransferMode.MOVE);
				ClipboardContent content = new ClipboardContent();
				
				content.putString("");
				dragger.setContent(content);
				
				event.consume();
			});
		}
		
		catch(Exception exception)
		{
			exception.printStackTrace();
		}
	}
	
	private void modify()
	{
		EditorWindow.launch(this.getScene().getWindow());
	}
	
	private void remove()
	{
		System.out.println("remove");
	}
}