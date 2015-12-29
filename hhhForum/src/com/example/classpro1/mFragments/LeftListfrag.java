package com.example.classpro1.mFragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.myproject1.R;


public class LeftListfrag extends Fragment {
	ListView listTitles; //
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		return inflater.inflate(R.layout.fragment_list, container, false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		listTitles = (ListView) getActivity().findViewById(R.id.title);
		listTitles.setAdapter(new ArrayAdapter<String>(getActivity(),R.layout.list_itemfrag, Data.TITLES)); // 为列表设置适配器
		listTitles.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,	int position, long arg3) {
				
			}
		});
	}
	
	
	public final static class Data {
	    public final static String[] TITLES = {
	            "第一个title",   
	            "第二个title",
	            "第三个",       
	            "第四个"
	    };
	}
}
