package kanban.model;

import kanban.model.State;

/**
 * Represents a task
 */
public class Task
{
	private String name;
	private String data;
	private String color;
	
	private State state;
	
	/**
	 * Task constructor
	 * 
	 * @param name The name of this task
	 * @param data Description or other data about this task
	 * @param color A hexadecimal color value for this task
	 * @param state The current state of this task
	 */
	public Task(String name, String data, String color, State state)
	{
		this.name = name;
		this.data = data;
		this.color = color;
		
		this.state = state;
	}
	
	public String getName() { return this.name; }
	public String getData() { return this.data; }
	public String getColor() { return this.color; }
	
	public State getState() { return this.state; }
	
	public void setName(String name) { this.name = name; }
	public void setData(String data) { this.data = data; }
	public void setColor(String color) { this.color = color; }
	
	public void setState(State state) { this.state = state; }
}