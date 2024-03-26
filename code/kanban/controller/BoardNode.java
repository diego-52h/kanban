package kanban.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

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

public class BoardNode extends BorderPane
{
	private @FXML Label name;
	
	private @FXML Button createButton;
	private @FXML Button importButton;
	private @FXML Button exportButton;
	
	private @FXML HBox categoryContainer;
	
	private ObservableList<TaskNode> tasks = FXCollections.observableArrayList();
	private Map<State, CategoryNode> categories = new HashMap();
	
	public BoardNode(String name)
	{
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/board.fxml"));
		
		loader.setRoot(this);
		loader.setController(this);
		
		try
		{
			loader.load();
			
			this.name.setText(name);
			
			this.insertCategory(new CategoryNode("To Do", State.TODO));
			this.insertCategory(new CategoryNode("On It", State.ONIT));
			this.insertCategory(new CategoryNode("Done", State.DONE));
			
			this.createButton.setOnAction((ActionEvent event) -> { this.createNew(); event.consume(); });
			this.importButton.setOnAction((ActionEvent event) -> { this.importState(); event.consume(); });
			this.exportButton.setOnAction((ActionEvent event) -> { this.exportState(); event.consume(); });
			
			this.tasks.addListener((ListChangeListener) (change) -> { this.update(); });
		}
		
		catch(Exception exception)
		{
			exception.printStackTrace();
		}
	}
	
	private void update()
	{
		for(CategoryNode category : this.categories.values())
			category.removeTasks();
		
		ArrayList<TaskNode> tasksToRemove = new ArrayList();
		
		for(TaskNode task : this.tasks)
		{
			State taskState = task.getTask().getState();
			
			if(taskState == State.NONE)
				tasksToRemove.add(task);
			
			else
				this.categories.get(taskState).insertTask(task);
		}
		
		for(TaskNode task : tasksToRemove)
			this.tasks.remove(task);
	}
	
	private void createNew()
	{
		ObservableTask task = new ObservableTask("", "", "#000000", State.NONE);
		
		task.addListener((curr) -> { this.update(); });
		task.setState(State.TODO);
		
		this.tasks.add(new TaskNode(task));
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
		this.categories.put(category.getState(), category);
		
		this.categoryContainer.getChildren().add(category);
		this.categoryContainer.setHgrow(category, Priority.ALWAYS);
	}
}