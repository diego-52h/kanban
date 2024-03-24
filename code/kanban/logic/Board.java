package kanban.logic;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

import kanban.logic.Category;

public class Board extends BorderPane
{
	private @FXML Label name;
	
	private @FXML HBox categories;
	
	private @FXML Button importButton;
	private @FXML Button createButton;
	private @FXML Button exportButton;
	
	private Category tasksToDo;
	private Category tasksOnIt;
	private Category tasksDone;
	
	public Board(String title)
	{
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/board.fxml"));
		
		loader.setRoot(this);
		loader.setController(this);
		
		try
		{
			loader.load();
			
			this.name.setText(title);
			
			this.tasksToDo = new Category("To Do");
			this.tasksOnIt = new Category("On It");
			this.tasksDone = new Category("Done");
			
			this.addCategory(tasksToDo);
			this.addCategory(tasksOnIt);
			this.addCategory(tasksDone);
			
			this.createButton.setOnAction((ActionEvent event) -> { this.createTask(); event.consume(); });
			this.importButton.setOnAction((ActionEvent event) -> { this.importState(); event.consume(); });
			this.exportButton.setOnAction((ActionEvent event) -> { this.exportState(); event.consume(); });
		}
		
		catch(Exception exception)
		{
			exception.printStackTrace();
			
			return;
		}
	}
	
	private void addCategory(Category category)
	{
		this.categories.getChildren().add(category);
		this.categories.setHgrow(category, Priority.ALWAYS);
	}
	
	private void createTask()
	{
		System.out.println("create button");
	}
	
	private void importState()
	{
		System.out.println("import button");
	}
	
	private void exportState()
	{
		System.out.println("export button");
	}
}