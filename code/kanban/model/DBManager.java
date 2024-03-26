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
				
				State state = State.TO_DO;
				
				switch(auxState)
				{
					case "TO_DO":
						state = State.TO_DO;
						break;
					
					case "ON_IT":
						state = State.ON_IT;
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
	
	public static void exportState(File file, List<Task> tasks)
	{
		if(file == null)
			return;
		
		try(PrintWriter writer = new PrintWriter(file))
		{
			for(Task task : tasks)
			{
				String name = task.getName();
				String data = task.getData();
				String color = task.getColor();
				String state = "TO_DO";
				
				switch(task.getState())
				{
					case State.TO_DO:
						state = "TO_DO";
						break;
					
					case State.ON_IT:
						state = "ON_IT";
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