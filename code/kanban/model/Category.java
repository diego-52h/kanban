package kanban.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.HashSet;

public class Category
{
	private String name;
	private Set<Task> tasks;
	
	public Category(String name)
	{
		this.name = name;
		
		this.tasks = new HashSet<Task>();
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public List<Task> getTasks()
	{
		List<Task> list = new ArrayList<Task>(this.tasks);
		
		return list;
	}
	
	public void insertTask(Task task) { this.tasks.add(task); }
	public void removeTask(Task task) { this.tasks.remove(task); }
}