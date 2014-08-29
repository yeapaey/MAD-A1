package com.yeapMAD.assignment1.controllers;

import java.text.DateFormat;
import java.util.Calendar;

import android.app.Activity;
import android.app.DialogFragment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.yeapMAD.assignment1.view.DatePickerFragment;

public class DatePickerButtonListener implements OnClickListener
{
	private Activity callback;
	private Calendar calendar;
	private DateFormat formatter;
	private Button button;

	public DatePickerButtonListener(Activity callback, Calendar calendar, DateFormat formatter)
	{
		this.callback = callback;
		this.calendar = calendar;
		this.formatter = formatter;
	}

	@Override
	public void onClick(View view)
	{
		button = (Button) view;
		DialogFragment newFragment = new DatePickerFragment(this);
		newFragment.show(callback.getFragmentManager(), "datePicker");
	}

	public void updateDate(int year, int monthOfYear, int dayOfMonth)
	{
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, monthOfYear);
		calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
		button.setText(formatter.format(calendar.getTime()));
	}

	public Calendar getCalendar()
	{
		return calendar;
	}

}
