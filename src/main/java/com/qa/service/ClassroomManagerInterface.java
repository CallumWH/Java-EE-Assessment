package com.qa.service;


public interface ClassroomManagerInterface
{
	public String createClassroom(String classroomJSON);

	public String findAllClassrooms();
	
	public String findSpecificClassroom(int roomNumber);
	
	public String updateClassroom(String classroomJSON); 
	
	public String deleteClassroom(int roomNumber);

}
