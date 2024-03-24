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

import kanban.controller.CategoryItem;

public class TaskItem extends Label
{
	private @FXML MenuItem modify;
	private @FXML MenuItem remove;
	
	private String name;
	private CategoryItem category;
	
	public TaskItem(String name)
	{
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/task.fxml"));
		
		loader.setRoot(this);
		loader.setController(this);
		
		try
		{
			loader.load();
			
			this.setText(name);
			
			this.modify.setOnAction((ActionEvent event) -> { this.modify(); event.consume(); });
			this.remove.setOnAction((ActionEvent event) -> { this.remove(); event.consume(); });
			
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
			
			return;
		}
	}
	
	public void setCategory(CategoryItem category)
	{
		this.category = category;
	}
	
	public CategoryItem getCategory()
	{
		return this.category;
	}
	
	private void modify()
	{
		System.out.println("modify task");
	}
	
	private void remove()
	{
		this.category.removeTask(this);
	}
}