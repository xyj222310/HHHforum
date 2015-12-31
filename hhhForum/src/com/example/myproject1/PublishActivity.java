package com.example.myproject1;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.classpro1.polo.Forum;
import com.example.classpro1.util.ForuminfoOpration;
import com.example.classpro1.util.impl.ForuminfoOprationImpl;

public class PublishActivity extends Activity {
		List<Forum> list1;
		EditText title ;
		EditText editText ;
		Button confirm;
		Bundle bundle;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_publish);
			getActionBar().setDisplayHomeAsUpEnabled(true); //启动导航功能。
			Intent intent = getIntent(); 
			bundle = intent.getExtras();
		title = (EditText) findViewById(R.id.title_edit);
		editText= (EditText) findViewById(R.id.editText1);
		confirm = (Button)findViewById(R.id.confirm_pub);
		init();
	}
	
	private void init() {
		// TODO Auto-generated method stub
		title.getText();
		confirm.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if("".equals(title.getText().toString())){
					new AlertDialog.Builder(PublishActivity.this).setTitle("请补充内容").setPositiveButton("确定", null).show();
				}
				else{
					Bundle bundle = getIntent().getExtras();
					ForuminfoOpration db = new ForuminfoOprationImpl(PublishActivity.this);
					SimpleDateFormat format = new SimpleDateFormat("yyymmddhhmmss");
					Date date = new Date(System.currentTimeMillis());
					String time  = format.format(date);
					db.InsertIntoforuminfo(title.getText().toString(), bundle.getString("user"), time);
					Toast.makeText(PublishActivity.this, "发布成功", 1).show();
				}
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.inform, menu);
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
		if(id ==  android.R.id.home){
	        finish();  
	        return true; 
		} 
		return super.onOptionsItemSelected(item);
	}
}

