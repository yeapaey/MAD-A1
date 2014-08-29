package com.yeapMAD.assignment1.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Locale;
import java.util.UUID;

import android.location.Address;

public class PlannedEvent implements Serializable
{
	private UUID id;
	private String title;
	private String venue;
	private Calendar startTime;
	private Calendar endTime;
	private String note;
	private Address address;

	public PlannedEvent(String title, String venue, Calendar startTime)
	{
		id = UUID.randomUUID();
		this.title = title;
		this.venue = venue;
		this.startTime = startTime;
		endTime = (Calendar) startTime.clone();
		note = new String();
		address = new Address(Locale.getDefault());
	}

	public PlannedEvent(String title, String venue, Calendar startTime, Calendar endTime, String note, Address address)
	{
		this.title = title;
		this.venue = venue;
		this.startTime = startTime;
		this.endTime = endTime;
		this.note = note;
		this.address = address;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getVenue()
	{
		return venue;
	}

	public void setVenue(String venue)
	{
		this.venue = venue;
	}

	public Calendar getDate()
	{
		return startTime;
	}

	public Calendar getStartTime()
	{
		return startTime;
	}

	public void setStartTime(Calendar startTime)
	{
		this.startTime = startTime;
	}

	public Calendar getEndTime()
	{
		return endTime;
	}

	public void setEndTime(Calendar endTime)
	{
		this.endTime = endTime;
	}

	public String getNote()
	{
		return note;
	}

	public void setNote(String note)
	{
		this.note = note;
	}

	public Address getAddress()
	{
		return address;
	}

	public void setAddress(Address address)
	{
		this.address = address;
	}

	public UUID getId()
	{
		return id;
	}

}
