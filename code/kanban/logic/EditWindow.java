package kanban.logic;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

import javafx.scene.layout.BorderPane;

import javafx.stage.Stage;
import javafx.stage.Modality;
import javafx.stage.Window;

import kanban.logic.Task;

public class EditWindow extends BorderPane
{
	private @FXML Button cancelButton;
	private @FXML Button acceptButton;
	
	private @FXML TextField newTaskName;
	
	private @FXML ToggleGroup colors;
	
	private Task task;
	
	public EditWindow(Task task)
	{
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/editor.fxml"));
		
		loader.setRoot(this);
		loader.setController(this);
		
		try
		{
			loader.load();
			
			this.task = task;
			
			this.newTaskName.setText(this.task.getName());
			
			this.cancelButton.setOnAction((ActionEvent event) -> { this.cancelChanges(); event.consume(); });
			this.acceptButton.setOnAction((ActionEvent event) -> { this.acceptChanges(); event.consume(); });
		}
		
		catch(Exception exception)
		{
			exception.printStackTrace();
			
			return;
		}
	}
	
	public static void launch(Task task, Window parentWindow)
	{
		Parent root = new EditWindow(task);
		
		root.getStylesheets().add(EditWindow.class.getResource("/common.css").toString());
		
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
		((Stage) this.getScene().getWindow()).close();
	}
	
	private void acceptChanges()
	{
		this.task.setName(this.newTaskName.getText());
		
		((Stage) this.getScene().getWindow()).close();
	}
}