package com.naturequest;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import com.loopj.android.http.JsonHttpResponseHandler;
import com.naturequest.R;
import com.naturequest.serverapi.QuestAPI;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class QuestActivity extends Activity{
	private ArrayList <String> qrcodes;
	private ArrayAdapter<String> adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_questlist);
		ListView list = (ListView) findViewById(R.id.listView1);
		qrcodes=new ArrayList<String>();
		
		getCodes();
		
		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, qrcodes);
		list.setAdapter(adapter);
		
		
	}
	
	public void getCodes(){
		
		QuestAPI.get("all.json", null, new JsonHttpResponseHandler() {				
			
            @Override
            public void onSuccess(JSONArray qrs) {       	               
                JSONObject qrnames;                
				try {
					for(int i=0; i<qrs.length(); i++){
						String tempTxt = null;
						qrnames = (JSONObject) qrs.get(i);
						tempTxt = qrnames.getString("name");
						qrcodes.add(tempTxt);
					}
					
				} catch (JSONException e) {
					e.printStackTrace();
				}
                adapter.notifyDataSetChanged();
            }
        });
	}
}
