package kanban.controller;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;

import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;

public class TaskItem extends Label
{
	private @FXML MenuItem modify;
	private @FXML MenuItem remove;
	
	public TaskItem()
	{
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/task.fxml"));
		
		loader.setRoot(this);
		loader.setController(this);
		
		try
		{
			loader.load();
			
			this.setText("task");
			
			this.setOnDragDetected((MouseEvent event) -> {
				Dragboard dragger = this.startDragAndDrop(TransferMode.MOVE);
				{
					ClipboardContent content = new ClipboardContent();
					
					content.putString("");
					dragger.setContent(content);
				}
				
				event.consume();
			});
			
			this.setOnDragDone((DragEvent event) -> {
				switch(event.getTransferMode())
				{
					case TransferMode.MOVE:
					{
						System.out.println("task move done");
					} break;
					
					default:
					{
					}
				}
				
				event.consume();
			});
			
			this.modify.setOnAction((ActionEvent event) -> {
				System.out.println("modify task");
				
				event.consume();
			});
			
			this.remove.setOnAction((ActionEvent event) -> {
				System.out.println("remove task");
				
				event.consume();
			});
		}
		
		catch(Exception exception)
		{
			System.err.println(exception.toString());
			
			return;
		}
	}
}