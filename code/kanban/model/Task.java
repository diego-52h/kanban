package kanban.model;

import kanban.model.State;

public class Task
{
	private String name;
	private String data;
	
	private String color; // Hex code
	
	private State state;
	
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