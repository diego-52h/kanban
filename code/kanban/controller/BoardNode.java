package kanban.controller;

import java.io.File;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

import javafx.stage.FileChooser;

import kanban.controller.CategoryNode;
import kanban.controller.EditorWindow;

import kanban.model.DBManager;
import kanban.model.State;
import kanban.model.Task;

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
			
			this.insertCategory("To Do", State.TODO);
			this.insertCategory("On It", State.ONIT);
			this.insertCategory("Done", State.DONE);
			
			this.createButton.setOnAction((ActionEvent event) -> { this.createNew(); event.consume(); });
			this.importButton.setOnAction((ActionEvent event) -> { this.importState(); event.consume(); });
			this.exportButton.setOnAction((ActionEvent event) -> { this.exportState(); event.consume(); });
			
			this.tasks.addListener((ListChangeListener) (change) -> { this.update(); });
			
			this.update();
		}
		
		catch(Exception exception)
		{
			exception.printStackTrace();
		}
	}
	
	/**
	 * Updates the graphical elements of this board
	 */
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
	
	/**
	 * Registers <code> task </code> on this board instance and attatch
	 * a listener to it
	 * 
	 * @param task The task to register
	 */
	private void addTask(ObservableTask task)
	{
		task.addListener((curr) -> { this.update(); });
		
		this.tasks.add(new TaskNode(task));
	}
	
	/**
	 * Creates a new task, launches an editor window for it and registers it
	 * onto this board
	 */
	private void createNew()
	{
		ObservableTask task = new ObservableTask("", "", "#000000", State.NONE);
		
		EditorWindow.launch(task, this.getScene().getWindow());
		
		this.addTask(task);
	}
	
	/**
	 * Loads a list of tasks from a file
	 */
	private void importState()
	{
		FileChooser chooser = new FileChooser();
		File file = chooser.showOpenDialog(this.getScene().getWindow());
		
		List<Task> importTasks = DBManager.importState(file);
		
		this.tasks.clear();
		
		for(Task task : importTasks)
			this.addTask(new ObservableTask(task));
		
		this.update();
	}
	
	/**
	 * Saves the current tasks to a file
	 */
	private void exportState()
	{
		FileChooser chooser = new FileChooser();
		File file = chooser.showOpenDialog(this.getScene().getWindow());
		
		ArrayList<Task> tasks = new ArrayList();
		
		for(TaskNode taskNode : this.tasks)
			tasks.add(taskNode.getTask());
		
		DBManager.exportState(file, tasks);
	}
	
	private void insertCategory(String name, State state)
	{
		CategoryNode category = new CategoryNode(name, state);
		
		this.categories.put(state, category);
		
		this.categoryContainer.getChildren().add(category);
		this.categoryContainer.setHgrow(category, Priority.ALWAYS);
	}
}