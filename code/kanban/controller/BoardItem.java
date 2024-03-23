package kanban.controller;

import javafx.fxml.FXMLLoader;

import javafx.scene.layout.BorderPane;

public class BoardItem extends BorderPane
{
	public BoardItem()
	{
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/board.fxml"));
		
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