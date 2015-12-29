package com.example.classpro1.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.classpro1.polo.Institution;
import com.example.myproject1.R;

public class SpinnerAdapter extends BaseAdapter{
	private List<Institution> mlist = new ArrayList<Institution>();
	private Context mContext;
	private LayoutInflater inflater;
	public SpinnerAdapter(List<Institution> mlist, Context pContext) {
		this.mlist = mlist;
		this.mContext = pContext;
		this.inflater = LayoutInflater.from(pContext);
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
		DataWrapper datawrapper;
		TextView university;
		TextView school;
		TextView major;
		if (convertView == null) {
	        convertView=inflater.inflate(R.layout.spin_item, null);
	    	university = (TextView) convertView.findViewById(R.id.spin2_tv_1);
	    	school = (TextView) convertView.findViewById(R.id.spin2_tv_2);
	    	major = (TextView) convertView.findViewById(R.id.spin2_tv_3);
			
			datawrapper = new DataWrapper(university,school,major);
			convertView.setTag(datawrapper);
		} else {
			datawrapper = (DataWrapper) convertView.getTag();
			university = datawrapper.university;
			school = datawrapper.school;
			major = datawrapper.major;
		}
		university.setText(mlist.get(position).getUniversity());
		school.setText(mlist.get(position).getSchool());
		major.setText(mlist.get(position).getMajor());
			
		return convertView; 
	}
	public final class DataWrapper {
		public TextView university;
		public TextView school;
		public TextView major;
		public DataWrapper(TextView university, TextView school, TextView major) {
			this.university = university;
			this.school = school;
			this.major = major;
		}
	}

}

