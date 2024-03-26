package kanban.controller;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.Observable;
import javafx.beans.InvalidationListener;

import kanban.model.State;
import kanban.model.Task;

public class ObservableTask extends Task implements Observable
{
	private List<InvalidationListener> listeners = new ArrayList();
	
	public ObservableTask(Task task)
	{
		super(task.getName(), task.getData(), task.getColor(), task.getState());
	}
	
	public ObservableTask(String name, String data, String color, State state)
	{
		super(name, data, color, state);
	}
	
	@Override
	public void setName(String name)
	{
		super.setName(name);
		
		this.notifyListeners();
	}
	
	@Override
	public void setData(String data)
	{
		super.setData(data);
		
		this.notifyListeners();
	}
	
	@Override
	public void setColor(String color)
	{
		super.setColor(color);
		
		this.notifyListeners();
	}
	
	@Override
	public void setState(State state)
	{
		super.setState(state);
		
		this.notifyListeners();
	}
	
	@Override public void addListener(InvalidationListener listener) { this.listeners.add(listener); }
	@Override public void removeListener(InvalidationListener listener) { this.listeners.remove(listener); }
	
	private void notifyListeners()
	{
		for(InvalidationListener listener : this.listeners)
			listener.invalidated(this);
	}
}