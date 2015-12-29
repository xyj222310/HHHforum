 package com.example.myproject1;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.example.classpro1.adapter.PersonalDBAdapter;
import com.example.classpro1.polo.Forum;
import com.example.classpro1.polo.PersonalInfo;
import com.example.myproject1.R;

@SuppressLint("NewApi")
public class LoginActivity extends Activity {
	private EditText edit1 ;
	private EditText edit2;
	private Button button1;
	private Button button2;
	private List<PersonalInfo> list1;
	private static String TAG="LIFECYCLE";
	/*定义访问模式*/
	public static int MODE = MODE_PRIVATE;  
	/*定义一个SharedPreferences名。之后将以这个名字保存在Android文件系统中*/
	public static final String PREFERENCE_NAME = "SaveSetting";
	//跳转到注册页面
		@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.i(TAG, "(1) onCreate()");
		setContentView(R.layout.activity_login);
		getActionBar().setDisplayHomeAsUpEnabled(true); //启动导航功能。
		   /*获取SharedPreferences实例。如果不存在将新建一个  */
		SharedPreferences sharedPreferences = getSharedPreferences(PREFERENCE_NAME, MODE);
		      /*读取SharedPreferences中保存的键值:如果文件或键值不在，则用缺省值 */
		edit1 = (EditText)findViewById(R.id.usernameEdit);
		edit2 = (EditText)findViewById(R.id.passwordEdit);
		String username = sharedPreferences.getString("username","");
		String password =  sharedPreferences.getString("password",""); 
		edit1.setText(username);
		edit2.setText(password);
		 button1=(Button)findViewById(R.id.confirm);
		button2=(Button)findViewById(R.id.reset);
		final CheckBox cb1 = (CheckBox)findViewById(R.id.checkbox1);
		TextView textreg = (TextView)findViewById(R.id.textReg);
		button1.setOnClickListener(new Button.OnClickListener() {
		@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(edit1.getText().toString().equals(""))
				{
					new AlertDialog.Builder(LoginActivity.this).setTitle("请补充以下内容").setMessage("用户名不能为空，请完善").setPositiveButton("确定", null).show();
				}
				else{
						if(cb1.isChecked()){
							String username=((EditText)findViewById(R.id.usernameEdit)).getText().toString().trim();
							String password=((EditText)findViewById(R.id.passwordEdit)).getText().toString().trim();
							SharedPreferences sharedPreferences = getSharedPreferences(PREFERENCE_NAME, MODE);
							SharedPreferences.Editor editor = sharedPreferences.edit();
							editor.putString("username", username);
						    editor.putString("password", password);
						  //editor.putFloat("male", "");
							editor.commit(); 
						}
						//条件跳转
						list1 = new ArrayList<PersonalInfo>();
						PersonalDBAdapter pDB = new PersonalDBAdapter(LoginActivity.this);
						pDB.open();
						Cursor result =  pDB.query("select * from person");
						if(result.moveToFirst() ){
							for(int i = 0;i<result.getCount();i++){
								list1.add(new PersonalInfo(result.getString(result.getColumnIndex("username")),
										result.getString(result.getColumnIndex("password")),
										result.getString(result.getColumnIndex("sex"))));
								result.moveToNext() ;
								if(edit1.getText().toString().equals(list1.get(i).getName())
										&& edit2.getText().toString().equals(list1.get(i).getPassword())){
									Intent intent=new Intent(LoginActivity.this,TabbedActivity.class);
									Bundle bundle=new Bundle();
						              bundle.putCharSequence("user",edit1.getText().toString());
							              intent.putExtras(bundle);  
							      	 startActivity(intent);
								}
								else{
									new AlertDialog.Builder(LoginActivity.this).setTitle("请补充以下内容").setMessage("用户名或密码错误！").setPositiveButton("确定", null).show();
								}
							}
						}
				    }	
				}
			});
		button2.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				edit1.setText("");
				edit2.setText("");
			}
		});
		textreg.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(LoginActivity.this,RegActivity.class);
				startActivity(intent);
			}
		});
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		if(item.getItemId() ==  android.R.id.home){
	        finish();  
	        return true; 
		} 
		return super.onOptionsItemSelected(item);
	}
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		Log.i(TAG, "(2) onStart()");
	}
	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
		Log.i(TAG, "(6) onRestart()");
	}
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		Log.i(TAG, "(4) onResume()");
	}
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		Log.i(TAG, "(7) onPause()");
	}
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		Log.i(TAG, "(8) onStop()");
	}
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Log.i(TAG, "(9) onDestroy()");
	}
}
