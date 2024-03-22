package kanban.logic;

import java.util.ArrayList;

public class TaskManager
{
	private TaskList tasksToDo;
	private TaskList tasksOnIt;
	private TaskList tasksDone;
	
	public TaskManager()
	{
		this.tasksToDo = new TaskList("To Do");
		this.tasksOnIt = new TaskList("On It");
		this.tasksDone = new TaskList("Done");
	}
	
	public ArrayList<TaskList> getTaskLists()
	{
		ArrayList<TaskList> list = new ArrayList<TaskList>();
		
		list.add(tasksToDo);
		list.add(tasksOnIt);
		list.add(tasksDone);
		
		return list;
	}
	
	public void addTask(Task task)
	{
		tasksToDo.insertTask(task);
	}
	
	public void moveTask(Task task, TaskList listA, TaskList listB)
	{
		listA.removeTask(task);
		listB.insertTask(task);
	}
}