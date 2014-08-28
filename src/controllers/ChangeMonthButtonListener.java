package controllers;

import java.util.Calendar;

import android.view.View;
import android.view.View.OnClickListener;

import com.yeapMAD.assignment1.R;
import com.yeapMAD.assignment1.view.EventsMonthAdapter;

public class ChangeMonthButtonListener implements OnClickListener
{
	private EventsMonthAdapter adapter;
	
	public ChangeMonthButtonListener(EventsMonthAdapter adapter)
	{
		this.adapter = adapter;
	}

	@Override
	public void onClick(View v)
	{
		int viewId = v.getId();
		Calendar cal = adapter.getCurrentDate();
		
		switch (viewId)
		{
		case R.id.events_month_next_button:
			cal.roll(Calendar.MONTH, true);
			break;
		case R.id.events_month_previous_button:
			cal.roll(Calendar.MONTH, false);
			break;
			default:
		}
		adapter.setCurrentDate(cal);
	}

}
