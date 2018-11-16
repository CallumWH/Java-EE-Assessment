package com.qa.persistence;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "classroom")
public class Classroom
{
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@Column(length = 20)
	private int roomNumber;
	@Column(length = 20)
	private String trainerName;
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Trainee> trainees;

	public Classroom()
	{
	}
	
	public int getRoomNumber()
	{
		return roomNumber;
	}

	public void setRoomNumber(int roomNumber)
	{
		this.roomNumber = roomNumber;
	}

	public String getTrainerName()
	{
		return trainerName;
	}

	public void setTrainerName(String trainerName)
	{
		this.trainerName = trainerName;
	}

	public List<Trainee> getTrainees()
	{
		return trainees;
	}
	
	public String addTrainee(Trainee trainee)
	{
		trainees.add(trainee);
		return trainee.getTraineeName() + " added";
	}
}