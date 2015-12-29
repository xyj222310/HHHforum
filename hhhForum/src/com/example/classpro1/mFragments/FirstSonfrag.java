package com.example.classpro1.mFragments;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnCreateContextMenuListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.example.classpro1.adapter.ForumAdapter;
import com.example.classpro1.polo.Forum;
import com.example.classpro1.util.ForuminfoOpration;
import com.example.classpro1.util.impl.ForuminfoOprationImpl;
import com.example.myproject1.InformActivity;
import com.example.myproject1.R;


public class FirstSonfrag extends Fragment {
	// 创建一个DetailFragment的新实例，其中包括要传递的数据包
	private List<Forum> list1;
	public ForumAdapter adapter1;
	ForuminfoOpration dbo;
	ListView listview1;
	final static int MENU_00=Menu.FIRST;
	final static int MENU_01=Menu.FIRST+1;
	final static int MENU_02=Menu.FIRST+2;
	final static int MENU_03=Menu.FIRST+3;
	final static int MENU_04=Menu.FIRST+4;
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		dbo = new ForuminfoOprationImpl(getActivity());
//		dbo.InsertIntoforuminfo();
//		dbo.deleteAll();
		initview();
		ItemOnLongClick();
	
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return inflater.inflate(R.layout.forum1, container, false);
	}
	private void ItemOnLongClick() {
		// TODO Auto-generated method stub
		listview1 = (ListView)getActivity().findViewById(R.id.forumlistview1);
		listview1.setOnCreateContextMenuListener(new OnCreateContextMenuListener(){
			@Override
			public void onCreateContextMenu(ContextMenu menu, View arg1,
					ContextMenuInfo arg2) {
				// TODO Auto-generated method stub
				menu.add(0,MENU_00,0,"写得好赏五十美金");
				menu.add(0,MENU_01,1,"暂时不看他了");
				menu.add(0,MENU_02,2,"收藏待会看");
				menu.add(0,MENU_03,3,"举报");
			}
		});
	}
	public boolean onContextItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item
				.getMenuInfo();
	  switch(item.getItemId()){
		case MENU_00:
			Toast.makeText(getActivity(),"感谢您的打赏", 
                    Toast.LENGTH_SHORT).show(); 
			return true;
		case MENU_01:
			restoreitem((int)info.id);
			return true;
		case MENU_02:
			collectitem((int)info.id);
			return true;
		case MENU_03:
			return true;
		case MENU_04:
			Toast.makeText(getActivity(),"看在程序员的辛苦上还是不要举报了吧", 
                    Toast.LENGTH_SHORT).show(); 
			return true;
		default:
			return false;			
		}
	}
	private void collectitem(int id) {
		// TODO Auto-generated method stub
		dbo = new ForuminfoOprationImpl(getActivity());
		dbo.exec("update foruminfo set state = 'collect' where title = '"+list1.get(id).getTitle()+"' ");
	}
	private void restoreitem(int id) {
		// TODO Auto-generated method stub
//		dbo = new ForuminfoOprationImpl(getActivity());
//		dbo.exec("update foruminfo set state = 'restore' where id = '"+item.getItemId()+"' ");
	}
	public void initview(){
		listview1 = (ListView)getActivity().findViewById(R.id.forumlistview1);
		ForuminfoOpration dbo = new ForuminfoOprationImpl(getActivity());
		list1 = new ArrayList <Forum> ();
		list1 = dbo.queryAll();
		adapter1 = new ForumAdapter(list1,getActivity());
		listview1.setAdapter(adapter1);
		listview1.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,long arg3) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(getActivity(),InformActivity.class);
				Bundle bundle=new Bundle();
		        bundle.putSerializable("position", position);
		        bundle.putSerializable("list1",(Serializable) list1);
		        intent.putExtras(bundle); 
				startActivity(intent);
				Toast.makeText(getActivity(),"onItemClick:listview1"+String.valueOf(position), Toast.LENGTH_LONG).show();
			}
		});
	}
}
