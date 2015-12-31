package com.example.classpro1.mFragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myproject1.LoginActivity;
import com.example.myproject1.PublishActivity;
import com.example.myproject1.R;
import com.example.myproject1.RegActivity;


public class ThirdSonfrag extends Fragment {
	/*定义访问模式*/
	/*定义一个SharedPreferences名。之后将以这个名字保存在Android文件系统中*/
	private static final String PREFERENCE_NAME = "SaveSetting";
	SharedPreferences sharedPreferences;
	int MODE = Activity.MODE_PRIVATE;  
	TextView actionbar;
	TextView username;
	ImageView head;
	Button homepage;
	Button register;
	Button logout;
	TextView tv1;
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		getActivity();
		   /*获取SharedPreferences实例。如果不存在将新建一个  */
				sharedPreferences = getActivity().getSharedPreferences(PREFERENCE_NAME, MODE);
				String name = sharedPreferences.getString("username","");
		logout = (Button)getActivity().findViewById(R.id.logout);
		register = (Button)getActivity().findViewById(R.id.register);
		homepage= (Button)getActivity().findViewById(R.id.homepage);
		tv1 = (TextView)getActivity().findViewById(R.id.username);
		initview();	
		if(getActivity().getIntent().getExtras() == null){
			if("".equals(name)){
				name="尚未登录";
			}
		}
		else{
			name = getActivity().getIntent().getExtras().getString("user");
		}
		tv1.setText(name);
//		
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		tv1 = (TextView)getActivity().findViewById(R.id.username);
//		tv1.setText(this.getArguments().getString("user"));
		return inflater.inflate(R.layout.activity_personal_info, container, false);
	}
	public void initview(){
		register.setOnClickListener( new Button.OnClickListener(){
			@Override
			public void onClick(View view) {
				 // TODO Auto-generated method stub
				startActivity(new Intent(getActivity(),RegActivity.class));
				Toast.makeText(getActivity(), "a sec ..  to register",Toast.LENGTH_LONG)
				.show();
			}
		});
		logout.setOnClickListener( new Button.OnClickListener(){
			@Override
			public void onClick(View view) {
				 // TODO Auto-generated method stub
				SharedPreferences.Editor editor = sharedPreferences.edit();
			    editor.putString("password", "");
			    editor.putString("username", "");
				editor.commit(); 
				startActivity(new Intent(getActivity(),LoginActivity.class));
				Toast.makeText(getActivity(), "a sec ..  to logining",Toast.LENGTH_LONG)
				.show();
			}
		});
		homepage.setOnClickListener( new Button.OnClickListener(){
			@Override
			public void onClick(View view) {
				 // TODO Auto-generated method stub
//				Toast.makeText(getActivity(), "等我做完了就告诉你可以进入个人主页了",Toast.LENGTH_LONG)
//				.show();
				if(!("尚未登录".equals(tv1.getText()))){
					Intent intent=new Intent(getActivity(),PublishActivity.class);
					Bundle bundle=new Bundle();
		              bundle.putCharSequence("user",tv1.getText().toString());
			              intent.putExtras(bundle);  
			      	 startActivity(intent);
				}
				else{
					new AlertDialog.Builder(getActivity()).setMessage("qing登录").setPositiveButton("确定", null).show();
				}
			}
		});
	}
}
