package ua.com.todd.babyapp;

import java.io.* ;

import ua.com.todd.babyapp.settings.ActivitySettings;
import ua.com.todd.babyapp.settings.Settings;

import android.app.Activity;
import android.content.Intent;
import android.os.*;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.*;
import android.view.View.*;
import android.widget.*;

/**
 *
 */
public class ActivityMain extends Activity implements OnLongClickListener, OnClickListener {


	private boolean isLock = false;
	private ImageView imgLock;
	private ImageView imgLeft;
	private ImageView imgRight;
	private ViewPager viewPager;
	private Settings settings;
	private File fileSettings;
	
	public final static String DIR_SETTINGS  = "babyApp";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_main);

		viewPager = (ViewPager)findViewById(R.id.viewPager);
	    imgLock = (ImageView)findViewById(R.id.imageView_lock);
	    imgLeft = (ImageView)findViewById(R.id.imageView_left);
	    imgRight = (ImageView)findViewById(R.id.imageView_right);

		imgLock.setOnLongClickListener(this);
		imgLeft.setOnClickListener(this);
		imgRight.setOnClickListener(this);

		
			
		
		settings = new Settings();
		fileSettings = Environment.getExternalStorageDirectory();
		fileSettings = new File(fileSettings + "/" + DIR_SETTINGS);
		fileSettings.mkdirs();
		fileSettings = new File(fileSettings, Settings.FILE_SETTINGS);
		
		try
	      {
			 fileSettings.createNewFile();
	         FileInputStream fileIn = new FileInputStream(fileSettings);
	         ObjectInputStream in = new ObjectInputStream(fileIn);
	         settings = (Settings) in.readObject();
	         in.close();
	         fileIn.close();
	      }
		catch(IOException i)
	      {
	       }
		catch(ClassNotFoundException c)
	      {
	       }
	      
		
		}

	protected void onResume(){
		super.onResume();
		

		SamplePagerAdapter pageAdapter = new SamplePagerAdapter(settings.getVisibleAnimals(), viewPager);
		
		viewPager.setAdapter(pageAdapter);
		viewPager.setOnPageChangeListener(pageAdapter);
		viewPager.setCurrentItem(1);
		}
	
	protected void onStop(){
		super.onStop();
		
		try {
			FileOutputStream fileOut = new FileOutputStream(fileSettings);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(settings);
	         out.close();
	          fileOut.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
	     }catch (IOException e) {
			e.printStackTrace();
		}
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode,Intent data){
		settings = (Settings)data.getSerializableExtra(Settings.FILE_SETTINGS);
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
        	System.out.print("hghgjgjgkkg");
        	Intent i = new Intent(this, ActivitySettings.class);
        	i.putExtra(Settings.FILE_SETTINGS, settings);
        	startActivityForResult(i, 1);
            return true;
            
        case R.id.menu_exit:
        	ByteArrayOutputStream baOut = new ByteArrayOutputStream();
        	PrintStream out = new PrintStream(baOut);
        	System.setOut(out);
        	System.setErr(out);
        	 out.println("hvjuv");
        	 System.out.println("gufufu");
        	
        	TextView t = (TextView)findViewById(R.id.textView1);
        	
        	t.setText(baOut.toString() + "");
        	//finish();
        	
            return true;
            
        default:
            return super.onOptionsItemSelected(item);
        }
    }
	
	@Override
	public void onAttachedToWindow() {
	    this.getWindow().setType(WindowManager.LayoutParams.TYPE_KEYGUARD);
	    super.onAttachedToWindow();
	}

	@Override
	public void onBackPressed() {
		if(!isLock) finish();
	}
	
	@Override
	public boolean onLongClick(View arg0) {
		if(isLock){
			isLock = false;
			imgLock.setImageResource(R.drawable.unlock);
		}
		else{
			isLock = true;
			imgLock.setImageResource(R.drawable.lock);

		}
		return false;
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.imageView_left:
			viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
			break;

		case R.id.imageView_right:
			viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
			break;
		}

	}
}
