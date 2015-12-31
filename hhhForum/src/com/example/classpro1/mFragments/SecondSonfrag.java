package com.example.classpro1.mFragments;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.example.classpro1.adapter.ForumAdapter;
import com.example.classpro1.polo.Forum;
import com.example.classpro1.util.ForuminfoOpration;
import com.example.classpro1.util.impl.ForuminfoOprationImpl;
import com.example.myproject1.InformActivity;
import com.example.myproject1.R;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View.OnCreateContextMenuListener;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;


public class SecondSonfrag extends Fragment {
	/*定义访问模式*/
	/*定义一个SharedPreferences名。之后将以这个名字保存在Android文件系统中*/
	
	private List<Forum> list1;
	private List<Forum> list2;
	private ForumAdapter adapter1;
	private ForuminfoOpration dbo;
	private ListView listview1;
	final static int MENU_00=Menu.FIRST;
	private static final String PREFERENCE_NAME = "SaveSetting";
	int MODE = Activity.MODE_PRIVATE;  
	String name;
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		getActivity();
		list2 = new ArrayList<Forum>();
		   /*获取SharedPreferences实例。如果不存在将新建一个  */
				initview();		
		ItemOnLongClick();
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return inflater.inflate(R.layout.forum2, container, false);
	}
	private void ItemOnLongClick() {
		// TODO Auto-generated method stub
		listview1 = (ListView)getActivity().findViewById(R.id.forumlistview2);
		listview1.setOnCreateContextMenuListener(new OnCreateContextMenuListener(){
			@Override
			public void onCreateContextMenu(ContextMenu menu, View arg1,
					ContextMenuInfo arg2) {
				// TODO Auto-generated method stub
				menu.add(0,MENU_00,0,"移出收藏");
			}
		});
	}
	public boolean onContextItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item
				.getMenuInfo();
		if( MENU_00 == item.getItemId()){
			Toast.makeText(getActivity(),"感谢您的支持，小编会继续努力", 
	                Toast.LENGTH_SHORT).show(); 
			dbo = new ForuminfoOprationImpl(getActivity()); //修改数据库的state为默认null
			dbo.exec("update foruminfo set state = null where " +
					"title = '"+list2.get((int)info.id).getTitle()+"' ");
			setdatasource();
			return true;
		 }
		else{
			return false;
		}
		
	}
	public void initview(){
		listview1 = (ListView)getActivity().findViewById(R.id.forumlistview2);
		ForuminfoOpration dbo = new ForuminfoOprationImpl(getActivity());
		list1 = new ArrayList <Forum> ();
		list1 = dbo.queryAll();
		SharedPreferences sharedPreferences = getActivity().getSharedPreferences(PREFERENCE_NAME, MODE);
		 name = sharedPreferences.getString("username","");
		if(getActivity().getIntent().getExtras() == null){
//				||!("".equals(name))){
			if("".equals(name)){
				Toast.makeText(getActivity(),"尚未登录，无法查看收藏夹", Toast.LENGTH_LONG).show();
			}
		}
		else{
			name = getActivity().getIntent().getExtras().getString("user");
		}
		for(int i=0;i<list1.size();i++){
			if(name.equals(list1.get(i).getState())){
				list2.add(new Forum(list1.get(i).getTitle(),
					list1.get(i).getSender(),
					list1.get(i).getDiscuessNum(),
					list1.get(i).getSender(),
					list1.get(i).getImageSrc(),
					list1.get(i).getState()));
			}
		}
		adapter1 = new ForumAdapter(list2,getActivity());
		listview1.setAdapter(adapter1);
		listview1.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,long arg3) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(getActivity(),InformActivity.class);
				Bundle bundle=new Bundle();
		        bundle.putSerializable("position", position);
		        if(list2.size()>0){
		        	bundle.putSerializable("list1",(Serializable) list2);
			        intent.putExtras(bundle); 
					startActivity(intent);
					Toast.makeText(getActivity(),"onItemClick:listview1"+String.valueOf(position), Toast.LENGTH_LONG).show();
		        }
		        else{
		        	Toast.makeText(getActivity(),"onItemClick:listview1"+String.valueOf(position)+"没有内容可查阅", Toast.LENGTH_LONG).show();
		        }
		     }
		});
	}
	public void setdatasource() {
		// TODO Auto-generated method stub
		list2.clear();
		for(int i=0;i<list1.size();i++){
			if(getActivity().getIntent().getExtras().getString("user")
					.equals(list1.get(i).getState())){
					list2.add(new Forum(list1.get(i).getTitle(),
							list1.get(i).getSender(),
							list1.get(i).getDiscuessNum(),
							list1.get(i).getSender(),
							list1.get(i).getImageSrc(),
							list1.get(i).getState()));
				}
		}
		adapter1.notifyDataSetChanged();
	}
}
