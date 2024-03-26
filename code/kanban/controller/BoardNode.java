package kanban.controller;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

import kanban.controller.CategoryNode;
import kanban.controller.EditorWindow;

public class BoardNode extends BorderPane
{
	private @FXML Label name;
	
	private @FXML Button createButton;
	private @FXML Button importButton;
	private @FXML Button exportButton;
	
	private @FXML HBox categoryContainer;
	
	public BoardNode(String name)
	{
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/board.fxml"));
		
		loader.setRoot(this);
		loader.setController(this);
		
		try
		{
			loader.load();
			
			this.name.setText(name);
			
			// <==
			
			this.insertCategory(new CategoryNode("C1"));
			this.insertCategory(new CategoryNode("C2"));
			this.insertCategory(new CategoryNode("C3"));
			
			// ==>
			
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
	
	private void insertCategory(CategoryNode category)
	{
		this.categoryContainer.getChildren().add(category);
		this.categoryContainer.setHgrow(category, Priority.ALWAYS);
	}
}