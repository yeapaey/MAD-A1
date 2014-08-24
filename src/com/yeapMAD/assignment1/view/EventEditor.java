package com.yeapMAD.assignment1.view;

import java.util.GregorianCalendar;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.yeapMAD.assignment1.R;
import com.yeapMAD.assignment1.model.DataEngine;

public class EventEditor extends Activity
{

	private int count = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_event_editor);
		
		// Check if bundle has saved state and load if true, otherwise use defaults
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		getMenuInflater().inflate(R.menu.event_editor, menu);
		return true;
	}


	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		int id = item.getItemId();
		switch (id)
		{
		case R.id.action_event_edit_cancel:
			break;
		case R.id.action_event_edit_done:
			onDoneClick(getCurrentFocus());
			break;
		default:
			return super.onOptionsItemSelected(item);
		}
		
		finish();
		return true;
	}


	public void onDoneClick(View view)
	{
		TextView title = (TextView) findViewById(R.id.event_title);
		TextView note = (TextView) findViewById(R.id.event_note);
		
		DataEngine.addEvent(title.getText().toString(), note.getText().toString(), new GregorianCalendar(2015, 01, 01));
		
		
//		Button button = (Button) findViewById(R.id.test_button);
////		button.setText("Add Guest " + count);
////		++count;
//		
//		EditText title = (EditText) findViewById(R.id.event_title);
//		EditText notes = (EditText) findViewById(R.id.event_notes);
//		
//		
//		
//		DataEngine.addEvent(title.toString(), notes.toString(), new GregorianCalendar());
		
		
		

		// ViewGroup layout = (ViewGroup)
		// findViewById(R.layout.activity_event_editor);
		//
		// if (layout == null)
		// {
		// System.out.println("Layout is null");
		// return;
		// }
		// EditText text = new EditText(this);
		// text.setText("Add a guest");
		// layout.addView(text);
	}

}
