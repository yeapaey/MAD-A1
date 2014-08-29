package com.yeapMAD.assignment1.view;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import android.content.Context;
import android.widget.BaseAdapter;

import com.yeapMAD.assignment1.model.PlannedEvent;

public abstract class AbstractAdapter extends BaseAdapter
{
	private Context context;
	private Collection<PlannedEvent> collection;

	public AbstractAdapter(Context context, Collection<PlannedEvent> collection)
	{
		this.context = context;
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
