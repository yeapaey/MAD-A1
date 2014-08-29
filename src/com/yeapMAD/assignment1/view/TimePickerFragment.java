package com.yeapMAD.assignment1.view;

import java.util.Calendar;

import controllers.TimePickerButtonListener;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.TimePicker;

public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener
{
	private Calendar calendar;
	private TimePickerButtonListener callback;
	
	public TimePickerFragment(TimePickerButtonListener callback)
	{
		this.callback = callback;
		calendar = callback.getCal();
	}
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState)
	{
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		int minute = calendar.get(Calendar.MINUTE);

		return new TimePickerDialog(getActivity(), this, hour, minute, DateFormat.is24HourFormat(getActivity()));
	}

	@Override
	public void onTimeSet(TimePicker view, int hourOfDay, int minute)
	{
		callback.updateTime(hourOfDay, minute);
	}
}
