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

import javafx.scene.paint.Color;

import javafx.beans.property.SimpleObjectProperty;

import kanban.logic.Category;
import kanban.logic.EditWindow;

public class Task extends Label
{
	private @FXML MenuItem modify;
	private @FXML MenuItem remove;
	
	private Category category;
	
	private SimpleObjectProperty<String> name;
	private SimpleObjectProperty<Color> color;
	
	public Task(String name, Color color)
	{
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/task.fxml"));
		
		loader.setRoot(this);
		loader.setController(this);
		
		try
		{
			loader.load();
			
			this.name = new SimpleObjectProperty();
			this.color = new SimpleObjectProperty();
			
			this.name.addListener((element, prev, curr) -> { this.setText(curr); });
			this.color.addListener((element, prev, curr) -> { this.changeColor(curr); });
			
			this.name.setValue(name);
			this.color.setValue(color);
			
			this.modify.setOnAction((ActionEvent event) -> { this.modify(); event.consume(); });
			this.remove.setOnAction((ActionEvent event) -> { this.remove(); event.consume(); });
			
			this.setOnDragDetected((MouseEvent event) -> {
				Dragboard dragger = this.startDragAndDrop(TransferMode.MOVE);
				ClipboardContent content = new ClipboardContent();
				
				content.putString("");
				dragger.setContent(content);
				
				event.consume();
			});
			
			this.setOnMouseExited((MouseEvent event) -> { this.changeColor(this.color.getValue()); event.consume(); });
			this.setOnMouseEntered((MouseEvent event) -> { this.changeColor(this.color.getValue().deriveColor(1, 0.8, 1, 1)); event.consume(); });
		}
		
		catch(Exception exception)
		{
			exception.printStackTrace();
			
			return;
		}
	}
	
	public void setName(String name) { this.name.setValue(name); }
	public void setColor(Color color) { this.color.setValue(color); }
	
	public void setCategory(Category category) { this.category = category; }
	
	public String getName() { return this.name.getValue(); }
	public Color getColor() { return this.color.getValue(); }
	
	public Category getCategory() { return this.category; }
	
	private void modify()
	{
		EditWindow.launch(this, this.getScene().getWindow());
	}
	
	private void remove()
	{
		this.category.removeTask(this);
	}
	
	private void changeColor(Color color)
	{
		int r = (int) (255 * color.getRed());
		int g = (int) (255 * color.getGreen());
		int b = (int) (255 * color.getBlue());
		
		String cssSetter = String.format("-fx-background-color: rgb(%d, %d, %d);", r, g, b);
		
		this.setStyle(cssSetter);
	}
}