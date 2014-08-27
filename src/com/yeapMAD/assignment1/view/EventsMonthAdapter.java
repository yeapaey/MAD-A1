package com.yeapMAD.assignment1.view;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import com.yeapMAD.assignment1.R;
import com.yeapMAD.assignment1.model.PlannedEvent;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.TableLayout.LayoutParams;

public class EventsMonthAdapter extends BaseAdapter {

	private Context context;
	private ArrayList<Integer> dates;
	
	public EventsMonthAdapter(Context context, int[] dates) {
		super();
		this.context = context;
		this.dates = new ArrayList<Integer>();
		
		
		Calendar cal = GregorianCalendar.getInstance();
		int monthLen = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		// Just creating the arrays ahead of time and referencing them when needed may be quicker.
		for (int i = 0; i < monthLen; ++i)
		{
			this.dates.add(i);
		}
	}
	
	@Override
	public int getCount() {
		
		return dates.size();
	}

	@Override
	public Object getItem(int position) {
		
		return dates.get(position);
	}

	@Override
	public long getItemId(int position) {
		 
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		
		if (convertView == null)
		{
			convertView = LayoutInflater.from(context).inflate(R.layout.event_month_square, parent, false);
		}
		
		TextView dayNum = (TextView) convertView.findViewById(R.id.event_month_dayNum);
		TextView eventBool = (TextView) convertView.findViewById(R.id.event_month_eventBool);
		
		dayNum.setText(dates.get(position).toString());
		eventBool.setText("Bool");
		
		return convertView;
	}

}
