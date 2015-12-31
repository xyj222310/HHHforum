package com.example.classpro1.util.impl;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.example.classpro1.adapter.DBAdapter;
import com.example.classpro1.polo.Forum;
import com.example.classpro1.util.ForuminfoOpration;
import com.example.myproject1.R;

public class ForuminfoOprationImpl implements ForuminfoOpration{
	private Context context;
	private DBAdapter dbadapter ;
	public ForuminfoOprationImpl(Context context) {
		super();
		this.context = context;
	}
	@Override
	public void open(){
		dbadapter = new DBAdapter(context);
		dbadapter.open();
	}
	public void exec(String sql){
		this.open();
		dbadapter.exec(sql);
	}
	public void deleteAll() {
		// TODO Auto-generated method stub
		this.open();
		dbadapter.deleteAllData();
	}
	@Override
	public void deleteByID(long id,String table) {
		// TODO Auto-generated method stub
		this.open();
		dbadapter.deleteByID(id);
	}
	public List<Forum> queryAll() {
		// TODO Auto-generated method stub
		this.open();
		String DB_QUERY = "SELECT * FROM foruminfo order by id desc;";
		Cursor result = dbadapter.query(DB_QUERY);
		List<Forum> list = new ArrayList<Forum>();
		if(result.moveToFirst() ){
			for(int i = 0;i<result.getCount();i++){
				list.add(new Forum(result.getString(result.getColumnIndex("title")),
								   result.getString(result.getColumnIndex("sender")),
								   result.getString(result.getColumnIndex("sendTime")),
								   result.getString(result.getColumnIndex("discussNum")),
							   	   result.getInt(result.getColumnIndex("imageSrc")),
							   	   result.getString(result.getColumnIndex("state"))
							   	   ));
				result.moveToNext() ;
			}
		}
//		Log.e(null,list.get(0).getTitle()+"hereherehere!!!!!!!!!!1");
		return list;
	}
	public void InsertIntoforuminfo(String title,String user,String time){	
		this.open();
		List<Forum> list1 = new ArrayList <Forum> ();
		list1.add(new Forum(title,user,"290",time,R.drawable.icon1,null));
//		list1.add(new Forum("�й���ȫ�����M2M�г�","aaa","290","2015-9-20 09:49",R.drawable.icon1,null));
//		list1.add(new Forum("��չ�е����������ѡxxxx��","bbb","390","2015-9-21 09:49",R.drawable.icon2,null));
//		list1.add(new Forum("ISIS������˹�������䣿","ccc","345","2015-9-21 09:49",R.drawable.background,null));
//		list1.add(new Forum("������+shishenmegui��ʲô��","fff","320","2015-9-21 09:49",R.drawable.background2,null));
//		list1.add(new Forum("[������Ļ��]BBallBreakdown��ׯ�񡪡����������ǵ����� ��","hupu","678","2015-12-21 05:49",R.drawable.icon3, null));
//		list1.add(new Forum("[��������ʿ83��89��ʿ������22+15+7��ղķ˹ 25+9���ָ�10+18����ʿȫ�Ӵ���","hupu","345","2015-12-26 05:49",R.drawable.camera, null));
//		list1.add(new Forum("ȫ��������ͶƱ���Ʊ�һ�����","hupu","4567","2015-12-26 13:49",R.drawable.x, null));
		for(int i=0;i<list1.size();i++){
			long rrr = dbadapter.InsertIntoForuminfo(list1.get(i));
			Log.e(null, list1.get(i).getTitle()+"heaheharheahfksaghksdg");
			Log.e(null, rrr+"���ҷ��ؽ������ķ��ؽ����");
		}
	}
	public void updatefruminfo(Forum forum, int pos){
		this.open();
		dbadapter.updateForuminfoById(forum,pos);
	}

}
