package kanban.controller;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;

import javafx.stage.Stage;
import javafx.stage.Modality;
import javafx.stage.Window;

import javafx.scene.layout.BorderPane;

import kanban.controller.ObservableTask;

import kanban.model.State;

public class EditorWindow extends BorderPane
{
	private @FXML Button cancelButton;
	private @FXML Button acceptButton;
	
	private @FXML TextField newName;
	private @FXML ToggleGroup colors;
	
	private final ObservableTask task;
	
	private static final String COLORS[] = {
		"#89BAFF",
		"#81DDB6",
		"#FEBF91",
		"#BCB1F4",
		"#EE9ECF",
		"#F8DC7E",
	};
	
	public EditorWindow(ObservableTask task)
	{
		this.task = task;
		
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/editor.fxml"));
		
		loader.setRoot(this);
		loader.setController(this);
		
		try
		{
			loader.load();
			
			this.newName.setText(task.getName());
			
			for(int i = 0; i < COLORS.length; i++)
				this.colors.getToggles().get(i).setUserData(COLORS[i]); // https://i.imgflip.com/1hro6t.jpg
			
			this.cancelButton.setOnAction((ActionEvent event) -> { this.cancelChanges(); event.consume(); });
			this.acceptButton.setOnAction((ActionEvent event) -> { this.acceptChanges(); event.consume(); });
		}
		
		catch(Exception exception)
		{
			exception.printStackTrace();
		}
	}
	
	/**
	 * Launch a new <code> Window </code> that contains an
	 * <code> EditorWindow </code> on it.
	 * 
	 * @param task Task to be edited
	 * @param parentWindow Window from which this new instance was launched
	 */
	public static void launch(ObservableTask task, Window parentWindow)
	{
		Parent root = new EditorWindow(task);
		
		Stage stage = new Stage();
		Scene scene = new Scene(root, 280, 240);
		
		stage.setScene(scene);
		
		stage.initOwner(parentWindow);
		stage.initModality(Modality.WINDOW_MODAL);
		
		stage.centerOnScreen();
		stage.setResizable(false);
		
		stage.showAndWait();
	}
	
	private void cancelChanges()
	{
		this.finish();
	}
	
	private void acceptChanges()
	{
		Toggle selection = this.colors.getSelectedToggle();
		
		if(selection == null)
			return;
		
		String name = this.newName.getText();
		String color = (String) selection.getUserData();
		
		this.task.setName(name);
		this.task.setColor(color);
		
		if(this.task.getState() == State.NONE)
			this.task.setState(State.TODO);
		
		this.finish();
	}
	
	/**
	 * Close the window where this editor is being displayed
	 */
	private void finish()
	{
		((Stage) this.getScene().getWindow()).close();
	}
}