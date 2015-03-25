package ua.com.todd.babyapp;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.ImageView;

import ua.com.todd.babyapp.settings.Settings;

public class ActivityMain extends ActionBarActivity implements OnLongClickListener, OnClickListener {

    private ImageView imgLock;
    private ImageView imgLeft;
    private ImageView imgRight;
    private ViewPager viewPager;
    private Settings settings;
    private View mDecorView;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDecorView = getWindow().getDecorView();
        mDecorView.setOnSystemUiVisibilityChangeListener(
                new View.OnSystemUiVisibilityChangeListener() {
                    @Override
                    public void onSystemUiVisibilityChange(int flags) {
                        boolean visible = (flags & View.SYSTEM_UI_FLAG_HIDE_NAVIGATION) == 0;
                        if(visible)
                            showSystemUI();
                        toolbar.animate()
                                .alpha(visible ? 1 : 0)
                                .translationY(visible ? 0 : toolbar.getHeight());
                    }
                });
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        viewPager = (ViewPager) findViewById(R.id.viewPager);
        imgLock = (ImageView) toolbar.findViewById(R.id.imageView_lock);
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
    public boolean onLongClick(View arg0) {
        hideSystemUI();
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

    private void hideSystemUI() {
        mDecorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_IMMERSIVE);
    }

    private void showSystemUI() {
        mDecorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
    }
}
