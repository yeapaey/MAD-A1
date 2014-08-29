package com.yeapMAD.assignment1.controllers;

import java.text.DateFormat;
import java.util.Calendar;

import com.yeapMAD.assignment1.view.TimePickerFragment;

import android.app.Activity;
import android.app.DialogFragment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class TimePickerButtonListener implements OnClickListener
{
	private Activity callback;
	private Calendar cal;
	private DateFormat formatter;
	private Button button;

	public TimePickerButtonListener(Activity callback, Calendar cal, DateFormat formatter)
	{
		this.callback = callback;
		this.cal = cal;
		this.formatter = formatter;
	}

	@Override
	public void onClick(View view)
	{
		button = (Button) view;
		DialogFragment newFragment = new TimePickerFragment(this);
		newFragment.show(callback.getFragmentManager(), "timePicker");
	}

	public void updateTime(int hourOfDay, int minute)
	{
		cal.set(Calendar.HOUR_OF_DAY, hourOfDay);
		cal.set(Calendar.MINUTE, minute);
		button.setText(formatter.format(cal.getTime()));
	}

	public Calendar getCal()
	{
		return cal;
	}

}
