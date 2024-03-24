package kanban.logic;

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

import javafx.beans.property.SimpleObjectProperty;

import kanban.logic.Category;
import kanban.logic.EditWindow;

public class Task extends Label
{
	private @FXML MenuItem modify;
	private @FXML MenuItem remove;
	
	private Category category;
	
	private SimpleObjectProperty<String> name;
	
	public Task(String name)
	{
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/task.fxml"));
		
		loader.setRoot(this);
		loader.setController(this);
		
		try
		{
			loader.load();
			
			this.name = new SimpleObjectProperty();
			
			this.modify.setOnAction((ActionEvent event) -> { this.modify(); event.consume(); });
			this.remove.setOnAction((ActionEvent event) -> { this.remove(); event.consume(); });
			
			this.setOnDragDetected((MouseEvent event) -> {
				Dragboard dragger = this.startDragAndDrop(TransferMode.MOVE);
				ClipboardContent content = new ClipboardContent();
				
				content.putString("");
				dragger.setContent(content);
				
				event.consume();
			});
			
			this.name.addListener((element, prev, curr) -> { this.setText(curr); });
			
			this.name.setValue(name);
		}
		
		catch(Exception exception)
		{
			exception.printStackTrace();
			
			return;
		}
	}
	
	public void setCategory(Category category) { this.category = category; }
	
	public void setName(String name) { this.name.setValue(name); }
	
	public String getName() { return this.name.getValue(); }
	
	private void modify()
	{
		EditWindow.launch(this, this.getScene().getWindow());
	}
	
	private void remove()
	{
		this.category.removeTask(this);
	}
}