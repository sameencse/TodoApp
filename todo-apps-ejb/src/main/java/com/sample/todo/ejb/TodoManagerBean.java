/**
 * 
 */
package com.sample.todo.ejb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.sample.todo.api.TodoItems;
import com.sample.todo.api.TodoManager;
import com.sample.todo.jpa.TodoItemsEntity;

@Stateless
public class TodoManagerBean implements TodoManager {

	@PersistenceContext(name = "ngp")
	EntityManager em;

	@Override
	public List<TodoItems> findAll() {

		String sql = "select t from TodoItemsEntity t ";
		Query query = em.createQuery(sql);
		List<TodoItemsEntity> list = query.getResultList();
		List<TodoItems> lstTodo = new ArrayList<>();
		list.forEach((todo) -> {
			TodoItems item = new TodoItems();
			item.setId(todo.getId());
			item.setName(todo.getName());
			item.setDescription(todo.getDescription());
			item.setDate(todo.getDate());
			item.setStatus(todo.getStatus());
			lstTodo.add(item);
		});

		return lstTodo;
	}

	@Override
	public void saveItem(TodoItems item) {
		TodoItemsEntity todoEntity = new TodoItemsEntity();

		todoEntity.setId(generateSeqId());
		todoEntity.setName(item.getName());
		todoEntity.setDescription(item.getDescription());
		todoEntity.setDate(item.getDate());
		todoEntity.setStatus(item.getStatus());

		em.persist(todoEntity);
	}

	@Override
	public void updateItem(TodoItems item) {

		TodoItemsEntity todoEntity = em.find(TodoItemsEntity.class, item.getId());
		if (todoEntity != null) {

			todoEntity.setName(item.getName());
			todoEntity.setDescription(item.getDescription());
			todoEntity.setDate(item.getDate());
			todoEntity.setStatus(item.getStatus());

		}

	}

	@Override
	public void deleteItem(int id) {
		TodoItemsEntity todoEntity = em.find(TodoItemsEntity.class, id);
		if (todoEntity != null) {
			em.remove(todoEntity);
		}
	}

	private int generateSeqId() {
		String sql = "Select count(c) from TodoItemsEntity c";
		Query query = em.createQuery(sql);
		long count = (long) query.getSingleResult();
		return (int) count + 1;
	}
}
