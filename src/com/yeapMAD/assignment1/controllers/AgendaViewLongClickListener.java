package com.yeapMAD.assignment1.controllers;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;

import com.yeapMAD.assignment1.model.PlannedEvent;
import com.yeapMAD.assignment1.view.AbstractAdapter;
import com.yeapMAD.assignment1.view.EventEditor;

public class AgendaViewLongClickListener implements AdapterView.OnItemLongClickListener
{
	private AbstractAdapter callback;

	public AgendaViewLongClickListener(AbstractAdapter callback)
	{
		this.callback = callback;
	}
	@Override
	public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id)
	{
		PlannedEvent event = (PlannedEvent) callback.getItem(position);
		Context context = parent.getContext();
		Intent intent = new Intent(context, EventEditor.class);
		intent.putExtra(EventEditor.PASSED_STATE, event);
		context.startActivity(intent);

		return true;
	}

}
