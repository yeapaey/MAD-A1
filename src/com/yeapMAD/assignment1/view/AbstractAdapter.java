package com.yeapMAD.assignment1.view;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import android.app.Activity;
import android.widget.BaseAdapter;

import com.yeapMAD.assignment1.model.PlannedEvent;

public abstract class AbstractAdapter extends BaseAdapter
{
	@SuppressWarnings("unused")
	private Activity activity;
	private Collection<PlannedEvent> collection;

	public AbstractAdapter(Activity activity, Collection<PlannedEvent> collection)
	{
		this.activity = activity;
		this.collection = collection;
	}

	public void updateEvents(Collection<PlannedEvent> collection)
	{
		if (collection == null)
		{
			collection = new ArrayList<PlannedEvent>();
		}
		else
		{
			this.collection = collection;
		}

		notifyDataSetChanged();
	}

	@Override
	public int getCount()
	{
		return collection.size();
	}

	@Override
	public Object getItem(int position)
	{
		Iterator<PlannedEvent> iter = collection.iterator();
		PlannedEvent event = null;

		for (int i = 0; i <= position; ++i)
		{
			event = iter.next();
		}

		return event;
	}

	@Override
	public long getItemId(int position)
	{
		return position;
	}
}
