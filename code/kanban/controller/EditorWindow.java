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

public class EditorWindow extends BorderPane
{
	private @FXML Button cancelButton;
	private @FXML Button acceptButton;
	
	private @FXML TextField newName;
	private @FXML ToggleGroup colors;
	
	public EditorWindow()
	{
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/editor.fxml"));
		
		loader.setRoot(this);
		loader.setController(this);
		
		try
		{
			loader.load();
			
			this.cancelButton.setOnAction((ActionEvent event) -> { this.cancelChanges(); event.consume(); });
			this.acceptButton.setOnAction((ActionEvent event) -> { this.acceptChanges(); event.consume(); });
		}
		
		catch(Exception exception)
		{
			exception.printStackTrace();
		}
	}
	
	public static void launch(Window parentWindow)
	{
		Parent root = new EditorWindow();
		
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
		
		this.finish();
	}
	
	private void finish()
	{
		((Stage) this.getScene().getWindow()).close();
	}
}