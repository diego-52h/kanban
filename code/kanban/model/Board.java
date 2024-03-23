package kanban.model;

import java.util.ArrayList;
import java.util.List;

public class Board
{
	private Category tasksToDo;
	private Category tasksOnIt;
	private Category tasksDone;
	
	public Board()
	{
		this.tasksToDo = new Category("To Do");
		this.tasksOnIt = new Category("On It");
		this.tasksDone = new Category("Done");
	}
	
	public List<Category> getCategories()
	{
		ArrayList<Category> list = new ArrayList<Category>();
		
		list.add(tasksToDo);
		list.add(tasksOnIt);
		list.add(tasksDone);
		
		return list;
	}
	
	public void addTask(Task task)
	{
		tasksToDo.insertTask(task);
	}
	
	public void moveTask(Task task, Category categoryA, Category categoryB)
	{
		categoryA.removeTask(task);
		categoryB.insertTask(task);
	}
}