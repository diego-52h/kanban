package kanban.controller;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.scene.layout.BorderPane;

public class BoardItem extends BorderPane
{
	private @FXML Label title;
	
	private @FXML Button importButton;
	private @FXML Button createButton;
	private @FXML Button exportButton;
	
	public BoardItem()
	{
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/board.fxml"));
		
		loader.setRoot(this);
		loader.setController(this);
		
		try
		{
			loader.load();
			
			this.title.setText("board");
			
			this.importButton.setOnAction((ActionEvent event) -> {
				System.out.println("import button");
			});
			
			this.createButton.setOnAction((ActionEvent event) -> {
				System.out.println("create button");
			});
			
			this.exportButton.setOnAction((ActionEvent event) -> {
				System.out.println("export button");
			});
		}
		
		catch(Exception exception)
		{
			System.err.println(exception.toString());
			
			return;
		}
	}
}