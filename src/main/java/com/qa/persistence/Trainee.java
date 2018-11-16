package com.qa.persistence;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
	
	@ManyToOne
	@JoinColumn(name="classroom_fk")
	private Classroom assigned_trainees;
	
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
