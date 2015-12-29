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
private static final int FAILURE = 0; // 失败 
private static final int SUCCESS = 1; // 成功 
private static final int OFFLINE = 2; // 如果支持离线阅读，进入离线模式  
private TextView mVersionNameText;   
private String mVersionName = "v1.1"; 
		@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//去掉信息栏
		Log.i(TAG, "(1) onCreate()");
		setContentView(R.layout.activity_main);
		mVersionNameText = (TextView) findViewById(R.id.version_name); 
        mVersionNameText.setText(mVersionName); 
		new AsyncTask<Void,Void, Integer>() {
			@Override
			//限制最短时间800ms
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
			    //两个参数分别表示进入的动画,退出的动画 
			  //  overridePendingTransition(R.anim.fade_in, R.anim.fade_out); 
			};
		}.execute(new Void[]{});
	}
	private int loadingCache() { 
//	    if (BaseApplication.mNetWorkState == NetworkUtils.NETWORN_NONE) { 
//	        return OFFLINE; 
//	    } 
		//有待完善
	    return SUCCESS; 
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
