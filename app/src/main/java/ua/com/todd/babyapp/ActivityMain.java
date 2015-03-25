package ua.com.todd.babyapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.ImageView;

import ua.com.todd.babyapp.settings.Settings;

public class ActivityMain extends Activity implements OnLongClickListener, OnClickListener {


    private boolean isLock = false;
    private ImageView imgLock;
    private ImageView imgLeft;
    private ImageView imgRight;
    private ViewPager viewPager;
    private Settings settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        viewPager = (ViewPager) findViewById(R.id.viewPager);
        imgLock = (ImageView) findViewById(R.id.imageView_lock);
        imgLeft = (ImageView) findViewById(R.id.imageView_left);
        imgRight = (ImageView) findViewById(R.id.imageView_right);

        imgLock.setOnLongClickListener(this);
        imgLeft.setOnClickListener(this);
        imgRight.setOnClickListener(this);

        settings = new Settings();
    }

    protected void onResume() {
        super.onResume();
        SamplePagerAdapter pageAdapter = new SamplePagerAdapter(settings.getVisibleAnimals(), viewPager);
        viewPager.setAdapter(pageAdapter);
        viewPager.setOnPageChangeListener(pageAdapter);
        viewPager.setCurrentItem(1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        settings = (Settings) data.getSerializableExtra(Settings.FILE_SETTINGS);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.menu_settings:
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        if (!isLock) finish();
    }

    @Override
    public boolean onLongClick(View arg0) {
        if (isLock) {
            isLock = false;
            imgLock.setImageResource(R.drawable.unlock);
        } else {
            isLock = true;
            imgLock.setImageResource(R.drawable.lock);

        }
        return false;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imageView_left:
                viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
                break;

            case R.id.imageView_right:
                viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                break;
        }

    }
}
