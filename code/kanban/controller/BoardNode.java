package kanban.controller;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.scene.layout.BorderPane;

import kanban.controller.EditorWindow;

public class BoardNode extends BorderPane
{
	private @FXML Label name;
	
	private @FXML Button createButton;
	private @FXML Button importButton;
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
			
			this.createButton.setOnAction((ActionEvent event) -> { this.createNew(); event.consume(); });
			this.importButton.setOnAction((ActionEvent event) -> { this.importState(); event.consume(); });
			this.exportButton.setOnAction((ActionEvent event) -> { this.exportState(); event.consume(); });
		}
		
		catch(Exception exception)
		{
			exception.printStackTrace();
		}
	}
	
	private void createNew()
	{
		EditorWindow.launch(this.getScene().getWindow());
	}
	
	private void importState()
	{
		System.out.println("import state");
	}
	
	private void exportState()
	{
		System.out.println("export state");
	}
}