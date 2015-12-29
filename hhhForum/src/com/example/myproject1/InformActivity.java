package com.example.myproject1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.classpro1.polo.Forum;

public class InformActivity extends Activity {
		final static int MENU_00=Menu.FIRST;
		final static int MENU_01=Menu.FIRST+1;
		final static int MENU_02=Menu.FIRST+2;
		final static int MENU_03=Menu.FIRST+3;
		final static int MENU_04=Menu.FIRST+4;
		final static int MENU_05=Menu.FIRST+5;
		String FILE_NAME = "savedInfo";
		String SDFilename = "forumSDFile"+System.currentTimeMillis()+".txt";	
		File  newfile ;
		TextView textContext;
		List<Forum> list1;
		int position;
		TextView title ;
		TextView sender ;
		TextView sendtime ;
	@SuppressWarnings("unchecked")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_inform);
			getActionBar().setDisplayHomeAsUpEnabled(true); //启动导航功能。
		textContext = (TextView)findViewById(R.id.textContext);
		registerForContextMenu(textContext);
		Intent intent=getIntent();
		Bundle bundle=intent.getExtras();
		list1 = (List<Forum>) bundle.get("list1");
		position =  (Integer) bundle.get("position");
		title= (TextView)findViewById(R.id.info_forum_title);
		sender= (TextView)findViewById(R.id.info_forum_sender);
		sendtime= (TextView)findViewById(R.id.info_forum_sendTime);
		title.setText(list1.get(position).getTitle());
		sender.setText(list1.get(position).getSender());
		sendtime.setText(list1.get(position).getSendTime());
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
	public void onCreateContextMenu(ContextMenu menu,View v,ContextMenuInfo menuInfo){
		super.onCreateContextMenu(menu, v, menuInfo);
		menu.add(0,MENU_00,0,"复制");
		menu.add(0,MENU_01,1,"赞扬个");
		menu.add(0,MENU_02,2,"参与讨论");
		menu.add(0,MENU_03,3,"举报");
		menu.add(0,MENU_04,4,"save the page");
		menu.add(0,MENU_05,5,"save the page to external storage");
	}
	public boolean onContextItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
	  switch(item.getItemId()){
		case MENU_00:
			textContext.setText("复制");
			return true;
		case MENU_01:
			textContext.setText("赞一个");
			return true;
		case MENU_02:
			textContext.setText("参与讨论");
			return true;
		case MENU_03:
			textContext.setText("举报");
			return true;
		case MENU_04:
			fileOut();
			filein("");
			return true;
		case MENU_05:
			sdout();
			filein("sd");
			return true;
		default:
			return false;			
		}
	}
	public void filein(String source){
		FileInputStream fisd = null;
		FileInputStream fis = null;
		String text = null;
		newfile = new File(Environment.getExternalStorageDirectory(),SDFilename);
 	    try{
    		fis = openFileInput(FILE_NAME);
			if (fis.available() == 0){
				textContext.setText("The content of this article from the storage doesn't exists");
				return;
			}
			byte[] readBytes = new byte[fis.available()];
			while(fis.read(readBytes) != -1);
			fis.read(readBytes);
    		if(source == "sd"){
    			fisd =  new FileInputStream(newfile); fisd.read(readBytes);
    		}
			text = new String(readBytes);
			if(text.equals("")|text.equals(null)){
				 textContext.setText("the source file is empty");
			}
 	    }catch (FileNotFoundException e){
 	    	e.printStackTrace();
 	    }
		catch (IOException e) {
		    e.printStackTrace();
		}
		finally{
			if (fis != null){
				try {fis.close();} 
				catch (IOException e) {e.printStackTrace();}
			}
		}
    	textContext.setText(text);
		Log.e(null, text+"666666666666666");
	}
	public void fileOut(){
		Log.e(null, "555555555555555555555555");
		String text = title.getText()+""+sender.getText()+sendtime.getText();
		FileOutputStream fos = null;
		 try {
			 fos = openFileOutput(FILE_NAME,Context.MODE_PRIVATE);  
			 fos.write(text.getBytes());    //String类型变量text：是需要保存的内容
			 Log.e(null, text+"555555555555555555555555");
		 }
		 catch (FileNotFoundException e) { e.printStackTrace(); }
		 catch (IOException e) {e.printStackTrace();}
		 finally{
			 if (fos != null){
				try{
					fos.flush();
					fos.close();
				}
				catch (IOException e) {e.printStackTrace();}
			} 
		}
	}
	private boolean sdout() {
		// TODO Auto-generated method stub
		String text = title.getText()+""+sender.getText()+sendtime.getText();
		if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
			Log.e(null, Environment.getExternalStorageDirectory()+"here!!!!!!!!!!!!	");
//			Log.e(null, Environment.MEDIA_MOUNTED+"here!!!!!!!!!!!!	");
			newfile = new File(Environment.getExternalStorageDirectory(),SDFilename);		
			FileOutputStream fosd= null;
			try {
				 newfile.createNewFile();
				 if(newfile.exists() && newfile.canWrite()){
					 fosd = new FileOutputStream(newfile);
					 fosd.write(text.getBytes());    //String类型变量text：是需要保存的内容
					 Toast.makeText(this, SDFilename+"save complete", Toast.LENGTH_SHORT).show();
					 textContext.setText(text);
					 Log.e(null, text+"fsdaaagdsgdsgs	");
				 }else{
				 	if(!newfile.exists()){
					 	Toast.makeText(this, "file create failed", Toast.LENGTH_SHORT).show();
				}
				else if(!newfile.canWrite()){
					Toast.makeText(this, "file write failed", Toast.LENGTH_SHORT).show();
					}
					return false;
				 }
			 }
			 catch (FileNotFoundException e) { e.printStackTrace(); }
			 catch (IOException e) {e.printStackTrace();}
			 finally{
				 if (fosd != null){
					try{
						fosd.flush();
						fosd.close();
					}
					catch (IOException e) {e.printStackTrace();}
				} 
			}
		}
		return true;
	}
}

