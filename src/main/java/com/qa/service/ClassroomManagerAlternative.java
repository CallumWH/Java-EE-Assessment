package com.qa.service;

import javax.enterprise.inject.Alternative;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import com.qa.persistence.Classroom;
import com.qa.utils.JSONUtil;

import static javax.transaction.Transactional.TxType.REQUIRED;
import static javax.transaction.Transactional.TxType.SUPPORTS;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Alternative
public class ClassroomManagerAlternative implements ClassroomManagerInterface
{
	private HashMap<Integer, Classroom> hmap = new HashMap<Integer, Classroom>();
	private JSONUtil jsonUtil = new JSONUtil();
	
	public String createClassroom(String classroomJSON) 
	{
		Classroom classroom = jsonUtil.getObjectForJSON(classroomJSON, Classroom.class);
		hmap.put(classroom.getRoomNumber(), classroom);
		return "Account " + classroom.getRoomNumber()+ " added";
	}

	public String findAllClassrooms() {
		
        return jsonUtil.getJSONForObject(new ArrayList<Classroom>(hmap.values()));
    }
	
	public String findSpecificClassroom(int roomNumber)
	{
		Classroom classroom = hmap.get(roomNumber);
		return jsonUtil.getJSONForObject(classroom);
	}
	
	public String updateClassroom(String classroomJSON)
	{
		Classroom classroom = jsonUtil.getObjectForJSON(classroomJSON, Classroom.class);
		
		hmap.put(classroom.getRoomNumber(), classroom);
		
		return new String(classroom.getRoomNumber() + " has been updated");
	}
	
	public String deleteClassroom(int roomNumber)
	{
		hmap.remove(roomNumber);
		
		return new String(roomNumber + " has been deleted");
	}

}
