package kanban.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.control.Label;

import javafx.scene.layout.VBox;

import javafx.scene.input.DragEvent;
import javafx.scene.input.TransferMode;

public class CategoryItem extends VBox
{
	private @FXML VBox tasks;
	private @FXML Label name;
	
	public CategoryItem()
	{
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/category.fxml"));
		
		loader.setRoot(this);
		loader.setController(this);
		
		try
		{
			loader.load();
			
			this.name.setText("category");
			
			this.setOnDragOver((DragEvent event) -> {
				event.acceptTransferModes(TransferMode.MOVE);
				
				event.consume();
			});
			
			this.setOnDragDropped((DragEvent event) -> {
				System.out.println("dropped item: " + ((TaskItem) event.getGestureSource()).getText());
				
				event.setDropCompleted(true);
				event.consume();
			});
			
			this.setOnDragEntered((DragEvent event) -> {
				this.setFocused(true);
				
				event.consume();
			});
			
			this.setOnDragExited((DragEvent event) -> {
				this.setFocused(false);
				
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