package ua.com.todd.babyapp.settings;

import ua.com.todd.babyapp.Animal;
import ua.com.todd.babyapp.AnimalDefault;
import ua.com.todd.babyapp.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.*;

public class ActivitySettingsAnimalsEdit extends Activity implements OnTouchListener {

	private AnimalDefault animal;
	private TextView nameAnimal;
	private ImageView image;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_edit);
		nameAnimal = (TextView)findViewById(R.id.textAnimalNameEdit);
		nameAnimal.setOnTouchListener(this);
		
		image = (ImageView)findViewById(R.id.imageAnimal);
		
		animal = (AnimalDefault)getIntent().getSerializableExtra(Animal.ANIMAL);
		
		if(animal == null)
			;
		else{
			
			nameAnimal.setText(animal.getName());
			image.setImageResource(animal.getAnimalImage());
		}
		
		
	}
	
	@Override
	public void onBackPressed() {
		Intent intent = new Intent();
		
		intent.putExtra(Animal.ANIMAL, animal);
	    setResult(RESULT_OK, intent);
	    finish();

	}

	int x = 0;
	int y = 0;
	
	@Override
	public boolean onTouch(View view, MotionEvent event) {
		switch(event.getAction()){
		
		case MotionEvent.ACTION_DOWN:
			
			x = (int)event.getX();
			y = (int)event.getY();
			
			return true;
		
		case MotionEvent.ACTION_UP:
			return true;
			
		case MotionEvent.ACTION_MOVE:
			
			int rX = (int)event.getX() - x;
			int rY = (int)event.getY() - y;
			x = (int)event.getX();
			y = (int)event.getY();
			
			nameAnimal.layout(view.getLeft() + rX, view.getTop() + rY,
					view.getRight() + rX, view.getBottom() + rY);
			
			
			return true;
		}
		return false;
	}
}
