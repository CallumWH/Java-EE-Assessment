package com.qa.service;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import com.qa.persistence.Classroom;
import com.qa.utils.JSONUtil;

import static javax.transaction.Transactional.TxType.REQUIRED;
import static javax.transaction.Transactional.TxType.SUPPORTS;

import java.util.List;

@Transactional(SUPPORTS) @Default
public class ClassroomManager implements ClassroomManagerInterface
{
	@PersistenceContext(unitName = "primary")
	private EntityManager entityManager;
	
	@Inject
	private JSONUtil JsonUtil;
	
	@Transactional(REQUIRED)
	public String createClassroom(String classroomJSON) 
	{
		Classroom classroom = JsonUtil.getObjectForJSON(classroomJSON, Classroom.class);
		entityManager.persist(classroom);
		return "{\"message\": \"classroom sucessfully added\"}";
	}

	public String findAllClassrooms() {
        TypedQuery<Classroom> query = entityManager.createQuery("SELECT a FROM Account a ORDER BY a.forename DESC", Classroom.class);
        return JsonUtil.getJSONForObject(query.getResultList());
    }
	
	public String findSpecificClassroom(int classroomNumber)
	{
		TypedQuery<Classroom> query = entityManager.createQuery("SELECT a FROM Account a WHERE accountNumber = " + classroomNumber, Classroom.class);
		return JsonUtil.getJSONForObject(query.getSingleResult());
	}
	
	@Transactional(REQUIRED)
	public String updateClassroom(String classroomJSON)
	{
		Classroom updatedClassroom = JsonUtil.getObjectForJSON(classroomJSON, Classroom.class);		
		entityManager.merge(updatedClassroom);
		
		return new String(updatedClassroom.getRoomNumber() + " has been updated");
	}
	
	@Transactional(REQUIRED)
	public String deleteClassroom(int roomNumber)
	{
		Classroom classroom = JsonUtil.getObjectForJSON(findSpecificClassroom(roomNumber), Classroom.class);
		entityManager.remove(classroom);
		
		return new String(classroom.getRoomNumber() + " has been deleted");
	}

}
