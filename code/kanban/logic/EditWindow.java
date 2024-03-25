package kanban.logic;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;

import javafx.scene.input.MouseEvent;

import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;

import javafx.scene.paint.Color;

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
	
	private static final Color COLORS[] = {
		Color.web("89BAFF"),
		Color.web("81DDB6"),
		Color.web("FEBF91"),
		Color.web("BCB1F4"),
		Color.web("EE9ECF"),
		Color.web("F8DC7E"),
	};
	
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
			
			for(int i = 0; i < this.COLORS.length; i++)
			{
				ToggleButton button = (ToggleButton) this.colors.getToggles().get(i);
				
				button.setUserData(this.COLORS[i]);
			}
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
		Toggle chosenButton = this.colors.getSelectedToggle();
		
		this.task.setName(this.newTaskName.getText());
		
		if(chosenButton != null)
			this.task.setColor((Color) chosenButton.getUserData());
		
		((Stage) this.getScene().getWindow()).close();
	}
}