package com.qa.service;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import com.qa.persistence.Classroom;
import com.qa.persistence.Trainee;
import com.qa.utils.JSONUtil;

import static javax.transaction.Transactional.TxType.REQUIRED;
import static javax.transaction.Transactional.TxType.SUPPORTS;

import java.util.List;

@Transactional(SUPPORTS) @Default
public class ClassroomManager implements ClassroomManagerInterface
{
	@PersistenceContext(unitName = "primary")
	private EntityManager classroomManager;
	
	@Inject
	private JSONUtil JsonUtil;
	
	@Transactional(REQUIRED)
	public String createClassroom(String classroomJSON) 
	{
		Classroom classroom = JsonUtil.getObjectForJSON(classroomJSON, Classroom.class);
		classroomManager.persist(classroom);
		return "{\"message\": \"classroom sucessfully added\"}";
	}
	
	@Transactional(REQUIRED)
	public String createTrainee(String traineeJSON) 
	{
		Trainee trainee = JsonUtil.getObjectForJSON(traineeJSON, Trainee.class);
		Classroom classroom = JsonUtil.getObjectForJSON(findSpecificClassroom(trainee.getClassroomAssigned()), Classroom.class);		
		classroom.addTrainee(trainee);
		
		updateClassroom(JsonUtil.getJSONForObject(classroom));
		
		return "{\"message\": \"" + trainee.getTraineeID() + " sucessfully added to " + classroom.getRoomNumber() + "\"}";
	}

	public String findAllClassrooms() {
        TypedQuery<Classroom> query = classroomManager.createQuery("SELECT a FROM Classroom a ORDER BY a.roomNumber DESC", Classroom.class);
        return JsonUtil.getJSONForObject(query.getResultList());
    }
	
	public String findSpecificClassroom(int classroomNumber)
	{
		TypedQuery<Classroom> query = classroomManager.createQuery("SELECT a FROM Classroom a WHERE a.roomNumber = " + classroomNumber, Classroom.class);
		return JsonUtil.getJSONForObject(query.getSingleResult());
	}
	
	@Transactional(REQUIRED)
	public String updateClassroom(String classroomJSON)
	{
		Classroom updatedClassroom = JsonUtil.getObjectForJSON(classroomJSON, Classroom.class);
		Classroom classroomToAlter = JsonUtil.getObjectForJSON(findSpecificClassroom(updatedClassroom.getRoomNumber()), Classroom.class);
		
		if (classroomToAlter != null) 
		{
			classroomToAlter = updatedClassroom;
			classroomManager.merge(updatedClassroom);
		}
		
		return new String(updatedClassroom.getRoomNumber() + " has been updated");
	}
	
	@Transactional(REQUIRED)
	public String deleteClassroom(int roomNumber)
	{
		Classroom classroom = JsonUtil.getObjectForJSON(findSpecificClassroom(roomNumber), Classroom.class);
		classroomManager.remove(classroom);
		
		return new String(classroom.getRoomNumber() + " has been deleted");
	}

}
