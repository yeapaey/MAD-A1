package com.yeapMAD.assignment1.view;

import java.util.Calendar;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.TimePicker;

public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener
{
	private final Calendar cal = Calendar.getInstance();
	private Calendar callback;
	
	public TimePickerFragment(Calendar callback)
	{
		this.callback = callback;
	}
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState)
	{

		
		int hour = cal.get(Calendar.HOUR_OF_DAY);
		int minute = cal.get(Calendar.MINUTE);

		TimePickerDialog tpd = new TimePickerDialog(getActivity(), this, hour, minute, DateFormat.is24HourFormat(getActivity()));
		return tpd;
	}

	@Override
	public void onTimeSet(TimePicker view, int hourOfDay, int minute)
	{
		callback.set(Calendar.HOUR_OF_DAY, hourOfDay);
		callback.set(Calendar.MINUTE, minute);
	}
}
