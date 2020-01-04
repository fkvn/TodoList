package webtest.models;

import java.util.ArrayList;

public class TodoList {
	private String name;
	private int id;
	private boolean done;
	
	public TodoList() {
		this("", 1, false);
	}
	
	public TodoList(String name, int id, boolean done) {
		this.name = name;
		this.id = id;
		this.done = done;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isDone() {
		return done;
	}

	public void setDone(boolean done) {
		this.done = done;
	}
	
	public static int correctCounts(ArrayList<TodoList> todos) {
		int correct = 0;
		for (int i = 0; i < todos.size(); i++) {
			if (todos.get(i).isDone())
				correct += 1;
		}
		return correct;
	}
	
	public static void removeAllCompleted(ArrayList<TodoList> todos) {
		for (int i = 0; i < todos.size(); i++) {
			if (todos.get(i).isDone())
				todos.remove(i);
		}
	}
	
	public static int getIndex(ArrayList<TodoList> todos, int id) {
		for (int i = 0; i < todos.size(); i++) {
			if (todos.get(i).getId() == id)
				return i;
		}	
		return -1;
	}

}
