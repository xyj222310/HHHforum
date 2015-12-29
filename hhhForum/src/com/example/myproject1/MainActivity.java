 package com.example.myproject1;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

public class MainActivity extends Activity {
private static String TAG="LIFECYCLE";
private static final int SHOW_TIME_MIN = 500; 
private static final int FAILURE = 0; // ʧ�� 
private static final int SUCCESS = 1; // �ɹ� 
private static final int OFFLINE = 2; // ���֧�������Ķ�����������ģʽ  
private TextView mVersionNameText;   
private String mVersionName = "v1.1"; 
		@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);//ȥ��������
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//ȥ����Ϣ��
		Log.i(TAG, "(1) onCreate()");
		setContentView(R.layout.activity_main);
		mVersionNameText = (TextView) findViewById(R.id.version_name); 
        mVersionNameText.setText(mVersionName); 
		new AsyncTask<Void,Void, Integer>() {
			@Override
			//�������ʱ��800ms
			protected Integer doInBackground(Void... params) { 
			    int result; 
			    long startTime = System.currentTimeMillis(); 
			    result = loadingCache(); 
			    long loadingTime = System.currentTimeMillis() - startTime; 
			    if (loadingTime < SHOW_TIME_MIN) { 
			        try { 
			            Thread.sleep(SHOW_TIME_MIN - loadingTime); 
			        } catch (InterruptedException e) { 
			            e.printStackTrace(); 
			        } 
			    } 
			    return result; 
			} 
			protected void onPostExecute(Integer result) { 
			    Intent intent = new Intent(); 
			    intent.setClass(MainActivity.this, TabbedActivity.class); 
			    startActivity(intent); 
			    finish(); 
			    //���������ֱ��ʾ����Ķ���,�˳��Ķ��� 
			  //  overridePendingTransition(R.anim.fade_in, R.anim.fade_out); 
			};
		}.execute(new Void[]{});
	}
	private int loadingCache() { 
//	    if (BaseApplication.mNetWorkState == NetworkUtils.NETWORN_NONE) { 
//	        return OFFLINE; 
//	    } 
		//�д�����
	    return SUCCESS; 
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
