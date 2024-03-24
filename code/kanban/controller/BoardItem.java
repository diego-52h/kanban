package kanban.controller;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

import kanban.controller.CategoryItem;

public class BoardItem extends BorderPane
{
	private @FXML Label title;
	
	private @FXML HBox categories;
	
	private @FXML Button importButton;
	private @FXML Button createButton;
	private @FXML Button exportButton;
	
	private CategoryItem tasksToDo;
	private CategoryItem tasksOnIt;
	private CategoryItem tasksDone;
	
	public BoardItem(String title)
	{
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/board.fxml"));
		
		loader.setRoot(this);
		loader.setController(this);
		
		try
		{
			loader.load();
			
			this.title.setText(title);
			
			this.tasksToDo = new CategoryItem("To Do");
			this.tasksOnIt = new CategoryItem("On It");
			this.tasksDone = new CategoryItem("Done");
			
			this.addCategory(tasksToDo);
			this.addCategory(tasksOnIt);
			this.addCategory(tasksDone);
			
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
	
	private void addCategory(CategoryItem category)
	{
		this.categories.getChildren().add(category);
		this.categories.setHgrow(category, Priority.ALWAYS);
	}
}