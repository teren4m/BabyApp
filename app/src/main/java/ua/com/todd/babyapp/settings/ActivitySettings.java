package ua.com.todd.babyapp.settings;

import ua.com.todd.babyapp.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class ActivitySettings extends Activity implements OnClickListener {

	private TextView linkToAnimals;
	private Settings settings;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_settings);
		
		settings = (Settings)getIntent().getSerializableExtra(Settings.FILE_SETTINGS);
		
		linkToAnimals = (TextView)findViewById(R.id.textView1);
		linkToAnimals.setOnClickListener(this);
		
	}

	@Override
	public void onBackPressed() {
		Intent intent = new Intent();
		intent.putExtra(Settings.FILE_SETTINGS, settings);
	    setResult(RESULT_OK, intent);
	    finish();
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode,Intent data){
		settings = (Settings)data.getSerializableExtra(Settings.FILE_SETTINGS);
	}
	
	@Override
	public void onClick(View view) {
		switch(view.getId()){
		
		case R.id.textView1:
			
			Intent i = new Intent(this, ActivitySettingsAnimals.class);
        	i.putExtra(Settings.FILE_SETTINGS, settings);
        	startActivityForResult(i, 1);
        	
			return;
		}
		
	}
}
