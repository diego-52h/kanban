package kanban.controller;

import javafx.fxml.FXMLLoader;

import javafx.scene.layout.VBox;

public class CategoryItem extends VBox
{
	public CategoryItem()
	{
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/category.fxml"));
		
		loader.setRoot(this);
		loader.setController(this);
		
		try
		{
			loader.load();
		}
		
		catch(Exception exception)
		{
			System.err.println(exception.toString());
			
			return;
		}
	}
}