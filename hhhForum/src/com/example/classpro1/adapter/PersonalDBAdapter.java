package com.example.classpro1.adapter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.classpro1.polo.PersonalInfo;


public class PersonalDBAdapter{
	
	private final Context context;
	private SQLiteDatabase db;
	private DBOpenHelper dbOpenHelper;
	public PersonalDBAdapter(Context context) {
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
	public Cursor query(String DB_QUERY){
		Cursor result = db.rawQuery(DB_QUERY, null);
		return result;
	}
	//insert
	public long Insert(PersonalInfo person){
		long r = 0;
		ContentValues newValues=new	ContentValues();
		newValues.put("username", person.getName());
		newValues.put("password", person.getPassword());
		newValues.put("sex", person.getMale());
		try{
			r=  db.insert("person", null, newValues);
		}catch(SQLiteException E){
			Log.e(null,E+"≤Â»Î”–¥Ì£°£°£°");
		}
		return r;
	}
	//delete
	public long deleteAllData(){
		return db.delete("person", null, null);
	}
	//inside class DBopenhelper
	public static class DBOpenHelper extends SQLiteOpenHelper{

		public DBOpenHelper(Context context, String name,
				CursorFactory factory, int version) {
			super(context, name, factory, version);
			// TODO Auto-generated constructor stub
		}
		private static String DB_CREATE = "create table person(" +
				"id integer primary key autoincrement," +
				"username text not null," +
				"password text," +
				"sex text)";
		@Override
		public void onCreate(SQLiteDatabase _db) {
			// TODO Auto-generated method stub
			_db.execSQL(DB_CREATE);
		}
		@Override
		public void onUpgrade(SQLiteDatabase _db, int arg1, int arg2) {
			// TODO Auto-generated method stub
			_db.execSQL("DROP TABLE IF EXISTS person");
			onCreate(_db);
		}
	}
}