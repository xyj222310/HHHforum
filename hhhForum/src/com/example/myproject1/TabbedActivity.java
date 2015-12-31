package com.example.myproject1;

import java.util.Locale;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v13.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.classpro1.mFragments.FirstSonfrag;
import com.example.classpro1.mFragments.SecondSonfrag;
import com.example.classpro1.mFragments.ThirdSonfrag;

public class TabbedActivity extends Activity implements ActionBar.TabListener {
	/**
	 * The {@link android.support.v4.view.PagerAdapter} that will provide
	 * fragments for each of the sections. We use a {@link FragmentPagerAdapter}
	 * derivative, which will keep every loaded fragment in memory. If this
	 * becomes too memory intensive, it may be best to switch to a
	 * {@link android.support.v13.app.FragmentStatePagerAdapter}.
	 */

	private int TAB_INDEX_COUNT = 3;
	private FirstSonfrag mFragment1 = new FirstSonfrag();
	private SecondSonfrag mFragment2 = new SecondSonfrag();
	private ThirdSonfrag mFragment3 = new ThirdSonfrag();
	private SectionsPagerAdapter mSectionsPagerAdapter;
	private ViewPager mViewPager;
	public String username = "尚未登录";
	Bundle bundle = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tabbed);
		// Set up the action bar.
		
		setUpActionBar();
		setUpViewPager();
		setUpTabs();
		Intent intent=getIntent();
		bundle=intent.getExtras();

	}
    private void setUpTabs() {
    	final ActionBar actionBar = getActionBar();
    	for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++) {
    		actionBar.addTab(actionBar.newTab()
    				.setText(mSectionsPagerAdapter.getPageTitle(i))
    				.setTabListener(this));
    	}
    }
	private void setUpActionBar() {
    	final ActionBar actionBar = getActionBar();
    	actionBar.setHomeButtonEnabled(false);
    	actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
    	actionBar.setDisplayShowTitleEnabled(false);
    	actionBar.setDisplayShowHomeEnabled(false);
	}
	@SuppressWarnings("deprecation")
  	private void setUpViewPager() {
    	mSectionsPagerAdapter = new SectionsPagerAdapter(getFragmentManager());
    	mViewPager = (ViewPager)findViewById(R.id.pager);
    	mViewPager.setAdapter(mSectionsPagerAdapter);
    	// When swiping between different sections, select the corresponding
		// tab. We can also use ActionBar.Tab#select() to do this if we have
		// a reference to the Tab.
    	mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
    		@Override
    		public void onPageSelected(int position) {
    			final ActionBar actionBar = getActionBar();
    			actionBar.setSelectedNavigationItem(position);
    		}
    		@Override
    		public void onPageScrollStateChanged(int state) {
    			switch(state) {
    				case ViewPager.SCROLL_STATE_IDLE:
    					//TODO
    					break;
    				case ViewPager.SCROLL_STATE_DRAGGING:
    					//TODO
    					Toast.makeText(TabbedActivity.this, "请不要翻过去",  Toast.LENGTH_SHORT).show();
    					break;
    				case ViewPager.SCROLL_STATE_SETTLING:
    					//TODO
    					break;
    				default:
    					//TODO
    					break;
    			}
    		}
    	});
    }
	@Override
	public void onTabSelected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
			if(mSectionsPagerAdapter.getItem(tab.getPosition()) == null){
				fragmentTransaction.add(R.id.pager,mSectionsPagerAdapter.getItem(tab.getPosition()));
			}
			else{
				fragmentTransaction.attach(mSectionsPagerAdapter.getItem(tab.getPosition()));
			}
//			fragmentTransaction.show(mSectionsPagerAdapter.getItem(tab.getPosition()));
			mViewPager.setCurrentItem(tab.getPosition());
//			if(tab.getPosition() == 2){
//				TextView tv = (TextView)mSectionsPagerAdapter. (tab.getPosition())
//				.getView().findViewById(R.id.username);
//				tv.setText(username);	
//			}
	}

	@Override
	public void onTabUnselected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
		if (mSectionsPagerAdapter.getItem(tab.getPosition()) != null){
			fragmentTransaction.detach(mSectionsPagerAdapter.getItem(tab.getPosition()));
		}
//		fragmentTransaction.hide(mSectionsPagerAdapter.getItem(tab.getPosition()));
	}

	@Override
	public void onTabReselected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.tabbed, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
	 * one of the sections/tabs/pages.
	 */
	public class SectionsPagerAdapter extends FragmentStatePagerAdapter {

		public SectionsPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		public Fragment getItem(int position) {
			// getItem is called to instantiate the fragment for the given page.
			// Return a PlaceholderFragment (defined as a static inner class
			// below).
			switch (position) {
			case 0:
			    return mFragment1;
			case 1:
			    return mFragment2 ;
			case 2:
			    return mFragment3 ;
			default:
				throw new IllegalArgumentException("Invalid section number");
            }
		}

		@Override
		public int getCount() {
			// Show 3 total pages.
			return TAB_INDEX_COUNT;
		}
		@Override
		public CharSequence getPageTitle(int position) {
			Locale l = Locale.getDefault();
			switch (position) {
			case 0:
				return getString(R.string.title_section1).toUpperCase(l);
			case 1:
				return getString(R.string.title_section2).toUpperCase(l);
			case 2:
				return getString(R.string.title_section3).toUpperCase(l);
			}
			return null;
		}
	}
}
