package kanban.controller;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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

import kanban.model.State;
import kanban.model.Task;

public class BoardNode extends BorderPane
{
	private @FXML Label name;
	
	private @FXML Button createButton;
	private @FXML Button importButton;
	private @FXML Button exportButton;
	
	private @FXML HBox categoryContainer;
	
	private Set<TaskNode> tasks;
	private Map<State, CategoryNode> categories;
	
	public BoardNode(String name)
	{
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/board.fxml"));
		
		loader.setRoot(this);
		loader.setController(this);
		
		try
		{
			loader.load();
			
			this.name.setText(name);
			
			this.tasks = new HashSet();
			this.categories = new HashMap();
			
			this.insertCategory(new CategoryNode("To Do", State.TO_DO, this));
			this.insertCategory(new CategoryNode("On It", State.ON_IT, this));
			this.insertCategory(new CategoryNode("Done", State.DONE, this));
			
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
		TaskNode task = new TaskNode();
		
		EditorWindow.launch(this.getScene().getWindow());
		
		task.getTask().setState(State.TO_DO);
		
		if(!this.tasks.add(task))
			return;
		
		this.tasks.add(task);
		this.update();
	}
	
	public void update()
	{
		for(CategoryNode category : this.categories.values())
			category.eraseTasks();
		
		for(TaskNode task : this.tasks)
			this.categories.get(task.getTask().getState()).insertTask(task);
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
		this.categories.put(category.getRepresentedState(), category);
		
		this.categoryContainer.getChildren().add(category);
		this.categoryContainer.setHgrow(category, Priority.ALWAYS);
	}
}