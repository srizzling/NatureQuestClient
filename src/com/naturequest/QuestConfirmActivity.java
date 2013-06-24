package com.naturequest;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class QuestConfirmActivity extends Activity
{
	private List<String> quests = new ArrayList<String>();
	private ListView questListView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.questconfirm);
		this.quests = Game.getGame().getQuests();
		
		this.questListView = (ListView)findViewById(R.id.listView1);
		this.questListView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, this.quests));
		
		this.questListView.setOnItemClickListener(new OnItemClickListener()
		{

			@Override
			public void onItemClick(AdapterView<?> arg0, View view, int position, long id)
			{
				Game.getGame().setCurrentQuest(QuestConfirmActivity.this.quests.get(position));
				
				Intent intent = new Intent (QuestConfirmActivity.this, MainMenuActivity.class);
				startActivity(intent);
			}
		});
	}
}
