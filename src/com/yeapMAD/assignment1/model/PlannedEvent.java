package com.yeapMAD.assignment1.model;

import java.io.Serializable;
import java.util.Calendar;

import android.location.Address;

public class PlannedEvent implements Serializable
{
	private String title;
	private String note;
	private Calendar calendar;
	private Calendar startTime;
	private Calendar endTime;
	private String strAddress;
	private Address address;

	public PlannedEvent(String title, String note, Calendar calendar)
	{
		this.title = title;
		this.note = note;
		this.calendar = calendar;
		startTime = calendar;
				
		if (startTime.get(Calendar.MINUTE) <= 30)
		{
			startTime.set(Calendar.MINUTE, 30);
		}
		else
		{
			startTime.set(Calendar.MINUTE, 0);
			startTime.roll(Calendar.HOUR_OF_DAY, 1);
		}
		
		endTime = startTime;
		offsetTime(endTime, 60);
				
				
	}

	public PlannedEvent(String title, String note, Calendar calendar, String strAddress, Address address)
	{
		this.title = title;
		this.note = note;
		this.calendar = calendar;
		this.strAddress = strAddress;
		this.address = address;
	}
	
	public void offsetTime(Calendar cal, int mins)
	{
		int result = cal.get(Calendar.MINUTE) + mins;
		int hourRoll = (result) / 60;
		if (result < 0) // I think this works
		{
			--hourRoll;
		}
		cal.roll(Calendar.MINUTE, mins);
		cal.roll(Calendar.HOUR_OF_DAY, hourRoll);
		
		// Some code to update the view
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getNote()
	{
		return note;
	}

	public void setNote(String notes)
	{
		this.note = notes;
	}

	public Calendar getCalendar()
	{
		return calendar;
	}

	public void setCalendar(Calendar calendar)
	{
		this.calendar = calendar;
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

	public String getStrAddress()
	{
		return strAddress;
	}

	public void setStrAddress(String strAddress)
	{
		this.strAddress = strAddress;
	}

	public Address getAddress()
	{
		return address;
	}

	public void setAddress(Address address)
	{
		this.address = address;
	}

}
