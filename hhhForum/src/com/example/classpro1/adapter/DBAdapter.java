package com.example.classpro1.adapter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.classpro1.polo.Forum;


public class DBAdapter{
	
	private final Context context;
	private SQLiteDatabase db;
	private DBOpenHelper dbOpenHelper;
	public DBAdapter(Context context) {
		super();
		this.context = context;
	}
	public void open(){
		dbOpenHelper = new DBOpenHelper(context, "forum.db", null, 1);
		try{
			db = dbOpenHelper.getWritableDatabase();
		}
		catch( SQLiteException ex ){
			db = dbOpenHelper.getReadableDatabase();
		}
	}
	public void exec(String sql){
		db.execSQL(sql);
	}
	//insert
	public long InsertIntoForuminfo(Forum item ){
		long r = 0;
		ContentValues newValues=new	ContentValues();
		newValues.put("title", item.getTitle());
		newValues.put("sender", item.getSender());
		newValues.put("discussNum", item.getDiscuessNum());
		newValues.put("sendTime", item.getSendTime());
		newValues.put("imageSrc", item.getImageSrc());
		newValues.put("state", item.getState());
		try{
			r=  db.insert("foruminfo", null, newValues);
		}catch(SQLiteException E){
			Log.e(null,E+"≤Â»Î”–¥Ì£°£°£°");
		}
		return r;
	}
	//delete
	public long deleteAllData(){
		return db.delete("foruminfo", null, null);
	}
	public long deleteByID(long id){
		return db.delete("foruminfo", "id="+id, null);
	}
	//update
	public int updateForuminfoById(Forum item,Integer id){
		ContentValues newValues=new	ContentValues();
		newValues.put("title", item.getTitle());
		newValues.put("sender", item.getSender());
		newValues.put("discussNum", item.getDiscuessNum());
		newValues.put("sendTime", item.getSendTime());
		newValues.put("imageSrc", item.getImageSrc());
		newValues.put("state", item.getState());
		return db.update("foruminfo",newValues, "id="+id, null);
	}
	//query
	public Cursor query(String DB_QUERY){
		Cursor result = db.rawQuery(DB_QUERY, null);
		return result;
	}
	//inside class DBopenhelper
	public static class DBOpenHelper extends SQLiteOpenHelper{

		public DBOpenHelper(Context context, String name,
				CursorFactory factory, int version) {
			super(context, name, factory, version);
			// TODO Auto-generated constructor stub
		}
		private static String DB_CREATE = "create table foruminfo(id integer primary key autoincrement," +
				"title text not null," +
				"sender text," +
				"discussNum text," +
				"sendTime text," +
				"imageSrc integer,"+
				"state text DEFAULT null)";
		private static String DB_CREATE2 = "create table person(" +
				"id integer primary key autoincrement," +
				"username text not null," +
				"password text," +
				"sex text)";
		@Override
		public void onCreate(SQLiteDatabase _db) {
			// TODO Auto-generated method stub
			_db.execSQL(DB_CREATE);
			_db.execSQL(DB_CREATE2);
		}
		@Override
		public void onUpgrade(SQLiteDatabase _db, int arg1, int arg2) {
			// TODO Auto-generated method stub
			_db.execSQL("DROP TABLE IF EXISTS foruminfo");
			_db.execSQL("DROP TABLE IF EXISTS person");
			onCreate(_db);
		}
	}
}