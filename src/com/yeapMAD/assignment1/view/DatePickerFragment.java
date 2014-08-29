package com.yeapMAD.assignment1.view;

import java.util.Calendar;

import com.yeapMAD.assignment1.controllers.DatePickerButtonListener;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.widget.DatePicker;

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener
{
	private DatePickerButtonListener callback;
	private Calendar calendar;

	public DatePickerFragment(DatePickerButtonListener callback)
	{
		this.callback = callback;
		calendar = callback.getCalendar();
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState)
	{
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		int day = calendar.get(Calendar.DAY_OF_MONTH);

		DatePickerDialog dialog = new DatePickerDialog(getActivity(), this, year, month, day);
		dialog.getDatePicker().setSpinnersShown(false);
		return dialog;
	}

	@Override
	public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth)
	{
		callback.updateDate(year, monthOfYear, dayOfMonth);
	}
}
