package com.example.classpro1.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.classpro1.polo.Forum;
import com.example.myproject1.R;

public class ForumAdapter extends BaseAdapter{

//	boolean[] cbchecked ;
//	
//	public boolean[] getCbchecked() {
//		return cbchecked;
//	}
//
//	public void setCbchecked(boolean[] cbchecked) {
//		this.cbchecked = cbchecked;
//	}
	private List<Forum> mlist = new ArrayList<Forum>();
	private Context mContext;
	private LayoutInflater inflater;
	
	public ForumAdapter(List<Forum> mlist, Context mContext) {
		super();
		this.mlist = mlist;
		this.mContext = mContext;
		this.inflater = LayoutInflater.from(mContext);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mlist.size();
	}
	@Override
	public Object getItem( int position) {
		// TODO Auto-generated method stub
		return mlist.get(position);
	}
	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView	, ViewGroup parent) {
		// TODO Auto-generated method stub
		DataWrapper datawrapper = new DataWrapper();
		if (convertView == null) {
	        convertView=inflater.inflate(R.layout.list_item_linear, null);
	        datawrapper.title = (TextView) convertView.findViewById(R.id.forum_title);
	        datawrapper.sender = (TextView) convertView.findViewById(R.id.forum_sender);
	        //datawrapper.lastDiscusstime = (TextView) convertView.findViewById(R.id.forum_lastDiscusstime);
	        datawrapper.discuessNum = (TextView) convertView.findViewById(R.id.forum_discuessNum);
	        datawrapper.imageSrc  = (ImageView) convertView.findViewById(R.id.forum_image);
	        datawrapper.sendTime  = (TextView) convertView.findViewById(R.id.forum_sendTime);
//	        datawrapper.checkbox  = (CheckBox) convertView.findViewById(R.id.forum_cb);
			convertView.setTag(datawrapper);
		} else {
			datawrapper = (DataWrapper) convertView.getTag();
		}
		datawrapper.title.setText(mlist.get(position).getTitle());
		datawrapper.sender.setText("发布人："+mlist.get(position).getSender());
		datawrapper.sendTime.setText("发布时间："+mlist.get(position).getSendTime());
		datawrapper.discuessNum.setText("回帖数："+mlist.get(position).getDiscuessNum());
		//datawrapper.lastDiscusstime.setText("最后回帖时间："+mlist.get(position).getLastDiscusstime());
		datawrapper.imageSrc.setImageResource(mlist.get(position).getImageSrc());
//		cbchecked = new boolean[getCount()];
//		datawrapper.checkbox.setChecked(cbchecked[position]);
		additemlistener(convertView,position,datawrapper);
		return convertView; 
	}

	public void additemlistener(final View convertView,final int pos,DataWrapper datawrapper) {
			datawrapper.title.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Toast.makeText(mContext, "getView:listview title"+String.valueOf(pos), Toast.LENGTH_SHORT).show();
			}
		});
	}

	public final class DataWrapper {
		private TextView title;
		private TextView sender;
		private TextView discuessNum;
		private TextView sendTime;
		//private TextView lastDiscusstime;
		private ImageView imageSrc;
	}
}