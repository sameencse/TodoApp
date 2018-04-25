package com.sample.todo.api;

import java.util.List;

public interface TodoManager {

	public List<TodoItems> findAll();

	public void saveItem(TodoItems item);

	public void updateItem(TodoItems item);

	public void deleteItem(int id);

}
