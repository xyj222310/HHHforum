package com.example.myproject1;

import java.util.ArrayList;
import java.util.List;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.classpro1.adapter.PersonalDBAdapter;
import com.example.classpro1.adapter.SpinnerAdapter;
import com.example.classpro1.polo.Institution;
import com.example.classpro1.polo.PersonalInfo;
public class RegActivity extends Activity {
	CheckBox check1;
	CheckBox check2;
	CheckBox check3;
	CheckBox check4;
	CheckBox check5;
	CheckBox check6;
	SpinnerAdapter adapter1;
	List<Institution> list2;
	EditText username;
	EditText password;
	RadioButton male;
	RadioButton female;
	RadioGroup group;
	String checkedText ;
	String sex ="";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_reg);
		 check1 = (CheckBox)findViewById(R.id.regcheck1);
		 check2 = (CheckBox)findViewById(R.id.regcheck2);
		 check3 = (CheckBox)findViewById(R.id.regcheck3);
		 check4 = (CheckBox)findViewById(R.id.regcheck4);
		 check5 = (CheckBox)findViewById(R.id.regcheck5);
		 check6 = (CheckBox)findViewById(R.id.regcheck6);
		
		Spinner spinner2 = (Spinner)findViewById(R.id.spinner2);
		list2 = new ArrayList<Institution>();
		list2.add(new Institution("��������ѧ","�����ѧԺ","������ϵ"));
		list2.add(new Institution("��������ѧ","҅�ļ���ѧԺ","������ϵ"));
		list2.add(new Institution("��������ѧ","��ʽ�ط���iѧԺ","������ϵ"));
	
		adapter1 = new SpinnerAdapter(list2,this);
		spinner2.setAdapter(adapter1);
		Button buttonreg = (Button)findViewById(R.id.confirmreg);
		buttonreg.setOnClickListener(new View.OnClickListener(){	
			@Override
			public void onClick(View v) {
				String username=((EditText)findViewById(R.id.usernamereg)).getText().toString().trim();
				String password=((EditText)findViewById(R.id.passwordreg)).getText().toString().trim();
				if("".equals(username) || "".equals(password) || "".equals(sex)){
					new AlertDialog.Builder(RegActivity.this).setTitle("�벹����������").setMessage("�û���������haiyou�Ա�������").setPositiveButton("ȷ��", null).show();
					savetoDB();
				}
				TextView txv1 = (TextView)findViewById(R.id.feedback1);
				TextView txv2 = (TextView)findViewById(R.id.feedback2);
				txv1.setText(username);
				txv2.setText(password);
				savetoDB();
			}
		});
		CheckBox.OnClickListener checkBoxListener = new CheckBox.OnClickListener(){
			@Override
			public void onClick(View view) {
				 // TODO Auto-generated method stub
				CheckBox cb = (CheckBox)view;
				if(cb.isChecked()){
					String checkedText = ((CheckBox)view).getText().toString();
					Toast.makeText(RegActivity.this, checkedText+"��ѡ��",Toast.LENGTH_LONG)
					.show();
					
				}else{
					checkedText = ((CheckBox)view).getText().toString();
					Toast.makeText(RegActivity.this, checkedText+"��ȡ��",Toast.LENGTH_LONG)
					.show();
				}
			}
		};
		check1.setOnClickListener(checkBoxListener);
		check2.setOnClickListener(checkBoxListener);
		check3.setOnClickListener(checkBoxListener);
		check4.setOnClickListener(checkBoxListener);
		check5.setOnClickListener(checkBoxListener);
		check6.setOnClickListener(checkBoxListener);
		group = (RadioGroup)findViewById(R.id.group);
		male = (RadioButton)findViewById(R.id.male);
		female = (RadioButton)findViewById(R.id.female);
		group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			 public void onCheckedChanged(RadioGroup group, int checkedId) { 
	                // TODO Auto-generated method stub 
	                if(checkedId==female.getId()){ 
	                	Toast.makeText(RegActivity.this, "Ů",Toast.LENGTH_LONG)
						.show();
	                     sex = "Ů";
	                }else if(checkedId==male.getId()){ 
	                	sex = "��";
	                } 
			 }
	        }); 
	}
	public void savetoDB(){	
		username = (EditText)findViewById(R.id.usernamereg);
		password= (EditText)findViewById(R.id.passwordreg);
		PersonalInfo perinfo = new PersonalInfo(username.getText().toString(),
				password.getText().toString(),sex) ;
		PersonalDBAdapter pAdapter = new PersonalDBAdapter(RegActivity.this);
		pAdapter.open();
		pAdapter.Insert(perinfo);
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.reg, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if(id ==  android.R.id.home){
	        finish();  
	        return true; 
		} 
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
