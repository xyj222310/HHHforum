package com.example.classpro1.util;

import java.util.List;

import com.example.classpro1.polo.Forum;

public interface ForuminfoOpration {
	public void InsertIntoforuminfo();
	public void deleteAll();
	public List<Forum> queryAll() ;
	public void updatefruminfo(Forum forum,int pos);
	public void open();
	public void exec(String sql);
	void deleteByID(long id, String table);
}
