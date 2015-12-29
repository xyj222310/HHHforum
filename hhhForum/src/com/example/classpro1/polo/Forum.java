package com.example.classpro1.polo;

import java.io.Serializable;

import android.R.integer;
import android.widget.CheckBox;
import android.widget.ListView;

import com.example.myproject1.R;

public class Forum implements Serializable {

	private String title;
	private String sender;
	private String discuessNum;
	private String sendTime;
	private String state;
	private Integer id;
	//private String lastDiscusstime;
	private Integer imageSrc;
	
	public Forum(String title, String sender, String discuessNum,
			String sendTime, Integer imageSrc,String state) {
		super();
		this.title = title;
		this.sender = sender;
		this.discuessNum = discuessNum;
		this.sendTime = sendTime;
		//this.lastDiscusstime = lastDiscusstime;
		this.imageSrc = imageSrc;
		this.state = state;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getDiscuessNum() {
		return discuessNum;
	}

	public void setDiscuessNum(String discuessNum) {
		this.discuessNum = discuessNum;
	}

	public String getSendTime() {
		return sendTime;
	}

	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}

/*	public String getLastDiscusstime() {
		return lastDiscusstime;
	}

	public void setLastDiscusstime(String lastDiscusstime) {
		this.lastDiscusstime = lastDiscusstime;
	}*/

	public Integer getImageSrc() {
		return imageSrc;
	}

	public void setImageSrc(Integer imageSrc) {
		this.imageSrc = imageSrc;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
}
