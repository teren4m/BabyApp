package ua.com.todd.babyapp;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.*;
import android.widget.ImageView.ScaleType;

public class SamplePagerAdapter extends PagerAdapter implements OnPageChangeListener {

	private List<View> pages =  new ArrayList<View>();
	private ViewPager viewPager;
	private int currentPage = 0;

	public SamplePagerAdapter(List<Animal> animals, ViewPager viewPager){
		
		Context context = viewPager.getContext();
		int lastIndex = animals.size() - 1;
		
		pages.add(getPageView(context, animals.get(lastIndex)));
		for(Animal animal : animals){
			
			View v = getPageView(context, animal);
			pages.add(v);	
			
		}
		
		pages.add(getPageView(context, animals.get(0)));
		
		this.viewPager = viewPager;
	}

	@Override
	public Object instantiateItem(View collection, int position){
		View v = pages.get(position);
		((ViewPager)collection).addView(v,0);
		return v;
		}

	@Override
	public void destroyItem(View collection, int position, Object view){
		((ViewPager)collection).removeView((View)view);
	}

	@Override
	public int getCount() {
		return pages.size();
	}

	@Override
	public boolean isViewFromObject(View view, Object object) {
		return view.equals(object);
	}

	@Override
	public void finishUpdate(View arg0){

	}

	@Override public void restoreState(Parcelable arg0, ClassLoader arg1){

	}

	@Override
	public Parcelable saveState(){
		return null;
	}

	@Override
	public void startUpdate(View arg0){

	}

	@Override
	public void onPageScrollStateChanged(int arg0) {
		if (arg0 == ViewPager.SCROLL_STATE_IDLE) {
            int pageCount = getCount();
            if (currentPage == 0){
                viewPager.setCurrentItem(pageCount-2,false);
            } else if (currentPage == pageCount-1){
                viewPager.setCurrentItem(1,false);
            }
        }

	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		// TODO Auto-generated method stub
		}

	@Override
	public void onPageSelected(int arg0) {
		currentPage = arg0;

	}
	
	private View getPageView(Context context, Animal animal) {
		RelativeLayout layout = new RelativeLayout(context);
		ImageView imageView = new ImageView(context);
		imageView.setImageResource(animal.getAnimalImage());
		LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		imageView.setLayoutParams(params);
		imageView.setBackgroundColor(0xfff);
		imageView.setScaleType(ScaleType.FIT_CENTER);
		
		layout.addView(imageView);
		
		MediaPlayer soundAnimals = MediaPlayer.create(context, animal.getAnimalSound());
		soundAnimals.setAudioStreamType(AudioManager.STREAM_MUSIC);
		
		layout.setOnClickListener(new AnimalOnClickListener(context, soundAnimals));
		
		return layout;
	}

	
}
