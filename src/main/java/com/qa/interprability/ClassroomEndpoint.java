package com.qa.interprability;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.qa.service.ClassroomManager;

@Path("/classroom")
public class ClassroomEndpoint {

	@Inject
	private ClassroomManager classroomManager;

	@Path("/retrieve")
	@GET
	@Produces({ "application/json" })
	public String getAllAccounts() {
		return classroomManager.findAllClassrooms();
	}

	@Path("/add")
	@POST
	@Produces({ "application/json" })
	@Consumes({ "application/json" })
	public String addAccount(String string) {
		return classroomManager.createClassroom(string);
	}

	@Path("/update/{id}")
	@PUT
	@Produces({ "application/json" })
	public String updateAccount(String classroomJSON) {
		return classroomManager.updateClassroom(classroomJSON);
	}

	@Path("/delete/{id}")
	@DELETE
	@Produces({ "application/json" })
	public String deleteAccount(@PathParam("id") int id) {
		return classroomManager.deleteClassroom(id);

	}

	public void setService(ClassroomManager classroomManager) {
		this.classroomManager = classroomManager;
	}

}
