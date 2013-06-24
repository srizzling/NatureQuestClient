package com.naturequest;

import java.util.ArrayList;

import org.json.JSONObject;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.naturequest.serverapi.QuestAPI;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class SimpleActivity extends Activity{

	
	private ArrayList<Button> answerButtons = new ArrayList<Button>();
	private TextView question;
	
		@Override
		protected void onCreate(Bundle savedInstanceState)
		{
			super.onCreate(savedInstanceState);
			setContentView(R.layout.question);
			
			//question
			question = (TextView) findViewById(R.id.questionTextView);
			
			//Add Buttons to list
			Button button1 = (Button) findViewById(R.id.answer1Button);
			Button button2 = (Button) findViewById(R.id.answer2Button);
			Button button3 = (Button) findViewById(R.id.answer3Button);
			Button button4 = (Button) findViewById(R.id.answer4Button);
			answerButtons.add(button1);
			answerButtons.add(button2);
			answerButtons.add(button3);
			answerButtons.add(button4);
			
			checkQRCode();
			
		}

		private void checkQRCode() {
			RequestParams params = new RequestParams();
//			params.put("r", ref);
			QuestAPI.get("find", params, new JsonHttpResponseHandler() {				

				@Override
				public void onSuccess(JSONObject question) {       	               
//					qObj = new Question(question);
//	
//					//Set Question
//					questionText.setText(qObj.getQuestion());
//	
//					//Set Answers
//					int i=0;
//					for(String a:qObj.getAnswers()){
//						Button temp = answerButtons.get(i);
//						temp.setText(a);
//						i++;
//					}

				}



			});
			
		}
	
	
	
}
