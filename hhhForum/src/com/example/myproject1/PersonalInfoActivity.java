package com.example.myproject1;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class PersonalInfoActivity extends Activity {
	TextView actionbar;
	TextView username;
	ImageView head;
	Button homepage;
	Button register;
	Button logout;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_personal_info);
		getActionBar().setCustomView(R.layout.myactionbarlayout);
		getActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
		actionbar = (TextView)findViewById(R.id.ActionBarTitle);
		actionbar.setText("我的");  //设置标题
		
		logout = (Button)findViewById(R.id.logout);
		register = (Button)findViewById(R.id.register);
		homepage= (Button)findViewById(R.id.homepage);

		logoutlistener();
		registerlistener();
		homepagelistener();
		
	}
	private void registerlistener() {
		// TODO Auto-generated method stub
		register.setOnClickListener( new Button.OnClickListener(){
			@Override
			public void onClick(View view) {
				 // TODO Auto-generated method stub
				startActivity(new Intent(PersonalInfoActivity.this,RegActivity.class));
				Toast.makeText(PersonalInfoActivity.this, "a sec ..  to register",Toast.LENGTH_LONG)
				.show();
			}
		});
	}

	private void logoutlistener() {
		// TODO Auto-generated method stub
		logout.setOnClickListener( new Button.OnClickListener(){
			@Override
			public void onClick(View view) {
				 // TODO Auto-generated method stub
				startActivity(new Intent(PersonalInfoActivity.this,LoginActivity.class));
				Toast.makeText(PersonalInfoActivity.this, "a sec ..  to logining",Toast.LENGTH_LONG)
				.show();
			}
		});
	}

	private void homepagelistener() {
		// TODO Auto-generated method stub
		homepage.setOnClickListener( new Button.OnClickListener(){
			@Override
			public void onClick(View view) {
				 // TODO Auto-generated method stub
				Toast.makeText(PersonalInfoActivity.this, "等我做完了就告诉你可以进入个人主页了",Toast.LENGTH_LONG)
				.show();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.personal_info, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
