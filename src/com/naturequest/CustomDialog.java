package com.naturequest;


import com.naturequest.R;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.text.Html;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class CustomDialog extends Dialog
{
	private TextView _titleTextView;
	
	private TextView _textView;
	
	private Button _button1;
	private Button _button2;
	
	private Button.OnClickListener _button1OnClickListener;
	private Button.OnClickListener _button2OnClickListener;
	
	public CustomDialog(Context context, String title) 
	{
		super(context);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.custom_dialog);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

		_titleTextView = (TextView)findViewById(R.id.titleTextView);
		_titleTextView.setText(Html.fromHtml("<u>" + title.toUpperCase() + "</u>"));
		_titleTextView.setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/AUdimat-Regular.otf"));
		
		_textView = (TextView)findViewById(R.id.textView);
		_textView.setVisibility(View.GONE);
		_textView.setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/AUdimat-Regular.otf"));

		_button1 = (Button)findViewById(R.id.button1);
		_button1.setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/AUdimat-Regular.otf"));

		_button2 = (Button)findViewById(R.id.button2);
		_button2.setVisibility(View.GONE);
		_button2.setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/AUdimat-Regular.otf"));
		
		getWindow().setLayout(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
	}
	
	public void showTextView(String text)
	{
		_textView.setText(text.toUpperCase());
		_textView.setVisibility(View.VISIBLE);
	}
	
	public void setPrimaryButton(String text, Button.OnClickListener listener)
	{
		if (listener == null)
		{
			listener = new Button.OnClickListener()
			{
				@Override
				public void onClick(View v)
				{
					dismiss();
				}
			};
		}

		_button1.setText(text.toUpperCase());
		_button1.setOnClickListener(listener);
		
		_button1OnClickListener = listener;
	}
	
	public void showSecondaryButton(String text, Button.OnClickListener listener)
	{		
		if (listener == null)
		{
			listener = new Button.OnClickListener()
			{
				@Override
				public void onClick(View v)
				{
					dismiss();
				}
			};
		}

		_button2.setText(text.toUpperCase());
		_button2.setVisibility(View.VISIBLE);
		_button2.setOnClickListener(listener);
		
		_button2OnClickListener = listener;
	}
}