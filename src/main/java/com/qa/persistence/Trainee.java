package com.qa.persistence;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="trainees")
public class Trainee
{
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@Column(length = 20)
	private int traineeID;
	@Column(length = 20)
	private String traineeName;
	@Column(length = 10)
	private int classroomAssigned;
	
	public int getClassroomAssigned()
	{
		return classroomAssigned;
	}

	public void setClassroomAssigned(int classroomAssigned)
	{
		this.classroomAssigned = classroomAssigned;
	}

	public int getTraineeID()
	{
		return traineeID;
	}

	public void setTraineeID(int traineeID)
	{
		this.traineeID = traineeID;
	}

	public String getTraineeName()
	{
		return traineeName;
	}

	public void setTraineeName(String traineeName)
	{
		this.traineeName = traineeName;
	}
	

	public Trainee()
	{
	}
	
	
}
