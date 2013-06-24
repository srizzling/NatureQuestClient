package com.naturequest.question;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.naturequest.R;
import com.naturequest.R.id;
import com.naturequest.R.layout;
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
import android.widget.TextView;

public class QuestionActivity extends Activity {

	private TextView questionText;
	private ArrayList<Button> answerButtons;
	private TextView correctText;
	private Question qObj;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_question);


		//Setting up Views
		questionText = (TextView) findViewById(R.id.question);
		correctText = (TextView) findViewById(R.id.correct);

		//List of Buttons for Answers
		final Button answerOne = (Button) findViewById(R.id.answer1);
		Button answerTwo = (Button) findViewById(R.id.answer2);
		Button answerThree = (Button) findViewById(R.id.answer3);
		Button answerFour = (Button) findViewById(R.id.answer4);
		answerButtons = new ArrayList <Button>();
		answerButtons.add(answerOne);
		answerButtons.add(answerTwo);
		answerButtons.add(answerThree);
		answerButtons.add(answerFour);		

		//Get the code from previous activity
		Intent prevIntent=getIntent();
		Bundle prevArgs=prevIntent.getExtras();
		String ref = prevArgs.getString("ref");

//		//Add onClickMethods to all buttons
//		for(final Button b:answerButtons){
//			b.setOnClickListener(new OnClickListener() {			
//				@Override
//				public void onClick(View v) {
//					if(qObj.checkCorrect((String) b.getText())){
//						correctText.setText("Correct!");
//					}
//					else{
//						b.setEnabled(false);
//						correctText.setText("Wrong!");
//					}
//				}
//			});
//		}
//
//		//Get Question Object from server
//		getQuestion(ref);









	}

	private void getQuestion(String ref) {

		RequestParams params = new RequestParams();
		params.put("r", ref);
		QuestAPI.get("find", params, new JsonHttpResponseHandler() {				

			@Override
			public void onSuccess(JSONObject question) {       	               
//				qObj = new Question(question);
//
//				//Set Question
//				questionText.setText(qObj.getQuestion());
//
//				//Set Answers
//				int i=0;
//				for(String a:qObj.getAnswers()){
//					Button temp = answerButtons.get(i);
//					temp.setText(a);
//					i++;
//				}

			}



		});


	}
}
