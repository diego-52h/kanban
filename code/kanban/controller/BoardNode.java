package kanban.controller;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.scene.layout.BorderPane;

public class BoardNode extends BorderPane
{
	private @FXML Label name;
	
	private @FXML Button importButton;
	private @FXML Button createButton;
	private @FXML Button exportButton;
	
	public BoardNode()
	{
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/board.fxml"));
		
		loader.setRoot(this);
		loader.setController(this);
		
		try
		{
			loader.load();
			
			this.name.setText("board");
			
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
			exception.printStackTrace();
		}
	}
}