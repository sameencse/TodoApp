/**
 * 
 */
package com.sample.todo.jaxrs.rest;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
/**
 * 
 * @author Mohammed.Sameen
 *
 */

import com.sample.todo.api.TodoItems;
import com.sample.todo.api.TodoManager;

@Path("/todo")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TodoResource {

	@EJB(name = "ejb/TodoManager")
	private TodoManager todoManager;

	@GET
	@Path("findAll")
	public List<TodoItems> findAll() {
		return todoManager.findAll();
	}

	@POST
	@Path("persist")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public void persist(@FormParam("name") String name, @FormParam("description") String description,
			@FormParam("date") String date, @FormParam("status") String status) throws ParseException {

		TodoItems item = new TodoItems();
		item.setName(name);
		item.setDescription(description);
		DateFormat inputFormat = new SimpleDateFormat("E MMM dd yyyy HH:mm:ss 'GMT'z", Locale.ENGLISH);
		Date dateParse = inputFormat.parse(date);

		DateFormat outputFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
		outputFormat.setTimeZone(TimeZone.getTimeZone("UTC"));

		String output = outputFormat.format(dateParse);
		item.setDate(new SimpleDateFormat("yyyy-MM-dd").parse(output));

		item.setStatus(status);

		todoManager.saveItem(item);
	}

	@PUT
	@Path("update")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public void update(@FormParam("id") int id, @FormParam("name") String name,
			@FormParam("description") String description, @FormParam("date") String date,
			@FormParam("status") String status) throws ParseException {

		TodoItems item = new TodoItems();
		item.setId(id);
		item.setName(name);
		item.setDescription(description);
		DateFormat inputFormat = new SimpleDateFormat("E MMM dd yyyy HH:mm:ss 'GMT'z", Locale.ENGLISH);
		Date dateParse = inputFormat.parse(date);

		DateFormat outputFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
		outputFormat.setTimeZone(TimeZone.getTimeZone("UTC"));

		String output = outputFormat.format(dateParse);
		item.setDate(new SimpleDateFormat("yyyy-MM-dd").parse(output));

		item.setStatus(status);

		todoManager.updateItem(item);
	}

	@DELETE
	@Path("delete/{id}")
	public void deleteItem(@PathParam("id") int id) {

		todoManager.deleteItem(id);
	}
}
