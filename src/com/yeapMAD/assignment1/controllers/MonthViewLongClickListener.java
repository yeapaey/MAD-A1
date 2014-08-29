package com.yeapMAD.assignment1.controllers;

import java.util.GregorianCalendar;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;

import com.yeapMAD.assignment1.model.PlannedEvent;
import com.yeapMAD.assignment1.view.EventEditor;
import com.yeapMAD.assignment1.view.EventsMonthAdapter;

public class MonthViewLongClickListener implements AdapterView.OnItemLongClickListener
{
	private EventsMonthAdapter callback;

	public MonthViewLongClickListener(EventsMonthAdapter callback)
	{
		this.callback = callback;
	}
	@Override
	public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id)
	{
		Context context = parent.getContext();
		Intent intent = new Intent(context, EventEditor.class);
		intent.putExtra(EventEditor.PASSED_STATE,
				new PlannedEvent(new GregorianCalendar(callback.getYear(),
				callback.getMonth(), (position + 1), 12, 0)));
		context.startActivity(intent);

		return true;
	}

}
