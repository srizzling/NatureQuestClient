package com.naturequest.question;

import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.naturequest.CustomDialog;
import com.naturequest.MainMenuActivity;
import com.naturequest.R;
import com.naturequest.R.id;
import com.naturequest.R.layout;
import com.naturequest.serverapi.QuestAPI;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class QuestionActivity extends Activity{


	private ArrayList<Button> answerButtons = new ArrayList<Button>();
	private TextView question;
	private Question qObj;

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
		final Intent intent = new Intent(this, MainMenuActivity.class);
		//Add onClickMethods to all buttons
		final Context baseContext = this;
		for(final Button b:answerButtons){
			b.setOnClickListener(new OnClickListener() {			
				@Override
				public void onClick(View v) {
					if(qObj.checkAnswer(((String) b.getText()))){
						final CustomDialog customDialog = new CustomDialog(baseContext, "Wohoo!");

						customDialog.showTextView("Awesome Work! You got the " +
								"question correct!");
						customDialog.setPrimaryButton("Ok", new OnClickListener() {

							@Override
							public void onClick(View v) {
								finish();
								customDialog.dismiss();
								startActivity(intent);
							}
						});

						customDialog.show();
					}
					else{
						final CustomDialog customDialog = new CustomDialog(baseContext, "Awww! :(");
						customDialog.showTextView("Try Again! Thats not the correct answer!");
						customDialog.setPrimaryButton("Ok", null);
						customDialog.show();
						b.setEnabled(false);

					}
				}
			});
		}

	}

	private void checkQRCode() {
		RequestParams params = new RequestParams();
		Intent prevIntent = getIntent();
		Bundle b=prevIntent.getExtras();			
		params.put("r", b.getString("ref"));
		QuestAPI.get("find", params, new JsonHttpResponseHandler() {				

			@Override
			public void onSuccess(JSONObject questionJSON) {       	               
				qObj = new Question(questionJSON);

				//Set Question
				question.setText(qObj.getQuestion());

				//Set Answers
				int i=0;
				for(String a:qObj.getAnswers()){
					Button temp = answerButtons.get(i);
					temp.setText(a);
					i++;
				}

			}

			@Override
			protected Object parseResponse(String arg0)
					throws JSONException {

				return super.parseResponse(arg0);
			}



		});

	}



}
