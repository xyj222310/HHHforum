package com.example.classpro1.mFragments;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myproject1.LoginActivity;
import com.example.myproject1.R;
import com.example.myproject1.RegActivity;


public class ThirdSonfrag extends Fragment {
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
		logout = (Button)getActivity().findViewById(R.id.logout);
		register = (Button)getActivity().findViewById(R.id.register);
		homepage= (Button)getActivity().findViewById(R.id.homepage);
		tv1 = (TextView)getActivity().findViewById(R.id.username);
		initview();	
		Intent intent = getActivity().getIntent(); 
		Bundle bundle = intent.getExtras();
		if(bundle!=null){
			String username  = bundle.getString("user");
			tv1.setText(username);
		}
		else{
			tv1.setText("尚未登录");
		}
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
				startActivity(new Intent(getActivity(),LoginActivity.class));
				Toast.makeText(getActivity(), "a sec ..  to logining",Toast.LENGTH_LONG)
				.show();
			}
		});
		homepage.setOnClickListener( new Button.OnClickListener(){
			@Override
			public void onClick(View view) {
				 // TODO Auto-generated method stub
				Toast.makeText(getActivity(), "等我做完了就告诉你可以进入个人主页了",Toast.LENGTH_LONG)
				.show();
			}
		});
	}
}
