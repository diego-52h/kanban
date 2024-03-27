package kanban.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import java.io.File;
import java.io.PrintWriter;

import kanban.model.State;
import kanban.model.Task;

public class DBManager
{
	/**
	 * Imports a list of tasks from a file produced by using
	 * DBManager.exportState()
	 * 
	 * @param file The file to read tasks from
	 * 
	 * @return A <code> List </code> containing the imported list of tasks
	 */
	public static List<Task> importState(File file)
	{
		List<Task> tasks = new ArrayList();
		
		if(file == null)
			return tasks;
		
		try(Scanner scanner = new Scanner(file))
		{
			scanner.useDelimiter(",|\\n");
			
			while(scanner.hasNext())
			{
				String name = scanner.next();
				String data = scanner.next();
				String color = scanner.next();
				String auxState = scanner.next();
				
				State state = State.NONE;
				
				switch(auxState)
				{
					case "NONE":
						state = State.NONE;
						break;
					
					case "TODO":
						state = State.TODO;
						break;
					
					case "ONIT":
						state = State.ONIT;
						break;
					
					case "DONE":
						state = State.DONE;
						break;
				}
				
				tasks.add(new Task(name, data, color, state));
			}
		}
		
		catch(Exception exception)
		{
			exception.printStackTrace();
		}
		
		return tasks;
	}
	
	/**
	 * Exports a list of tasks into a file in order to be recovered later by using
	 * DBManager.importState()
	 * 
	 * @param file The file to write the task list into
	 * @param tasks The list of tasks to export
	 */
	public static void exportState(File file, List<Task> tasks)
	{
		if(file == null)
			return;
		
		try(PrintWriter writer = new PrintWriter(file))
		{
			writer.print("\n");
			
			for(Task task : tasks)
			{
				String name = task.getName();
				String data = task.getData();
				String color = task.getColor();
				String state = "NONE";
				
				switch(task.getState())
				{
					case State.NONE:
						state = "NONE";
						break;
					
					case State.TODO:
						state = "TODO";
						break;
					
					case State.ONIT:
						state = "ONIT";
						break;
					
					case State.DONE:
						state = "DONE";
						break;
				}
				
				writer.printf("%s,%s,%s,%s\n", name, data, color, state);
			}
			
			writer.flush();
			writer.close();
		}
		
		catch(Exception exception)
		{
			exception.printStackTrace();
		}
	}
}