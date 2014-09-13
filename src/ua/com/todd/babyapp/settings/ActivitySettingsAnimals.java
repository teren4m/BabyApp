package ua.com.todd.babyapp.settings;


import java.util.List;

import ua.com.todd.babyapp.Animal;
import ua.com.todd.babyapp.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.*;
import android.widget.CompoundButton.OnCheckedChangeListener;

public class ActivitySettingsAnimals extends Activity implements OnItemClickListener, OnCheckedChangeListener, OnClickListener, OnItemFocusListener{
	
	private List<Animal> listOfAnimals;
	SettingsListView listView;
	private Settings settings;
	private SettingsAdapter settingsAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		
		this.setContentView(R.layout.activity_animals_settings);
		
		settings = (Settings)getIntent().getSerializableExtra(Settings.FILE_SETTINGS);
		listOfAnimals = settings.getListOfAnimals();
		
		ImageButton buttonNew = (ImageButton)findViewById(R.id.buttonNew);
		ImageButton buttonUp = (ImageButton)findViewById(R.id.buttonUp);
		ImageButton buttonDown = (ImageButton)findViewById(R.id.buttonDown);
		Button buttonEdit = (Button)findViewById(R.id.buttonEdit);
		ImageButton buttonDel  = (ImageButton)findViewById(R.id.buttonDel);
		
		CheckBox checkAll   = (CheckBox)findViewById(R.id.checkAll);
		checkAll.setChecked(settings.isCheckAll());
		checkAll.setOnClickListener(this);
		
		buttonNew.setOnClickListener(this);
		buttonUp.setOnClickListener(this);
		buttonDown.setOnClickListener(this);
		buttonEdit.setOnClickListener(this);
		buttonDel.setOnClickListener(this);
		
		listView = (SettingsListView)findViewById(R.id.listView1);
		
		settingsAdapter = new SettingsAdapter(this, listOfAnimals);
		listView.setAdapter(settingsAdapter);
		listView.setOnItemClickListener(this);
		listView.setOnItemFocusListener(this);
		listView.setItemSelection(0);
		if(listOfAnimals.get(0).isDefault())
			buttonDel.setEnabled(false);
	}

	@Override
	public void onBackPressed() {
		Intent intent = new Intent();
		
		intent.putExtra(Settings.FILE_SETTINGS, settings);
	    setResult(RESULT_OK, intent);
	    finish();

	}
	@Override
	public void onClick(View view) {
		Intent intent;
		
		Log.i("MyAnimal"," " + view.getId());
		
		switch(view.getId()){
		
		case R.id.checkAll:
			CheckBox checkAll = (CheckBox)view;
			settings.setAllCheckBox(checkAll.isChecked());
			settings.setCheckAll(checkAll.isChecked());
			settingsAdapter.notifyDataSetChanged();
			return;
		
		case R.id.buttonNew:
			intent = new Intent(this, ActivitySettingsAnimalsEdit.class);
        	startActivityForResult(intent, 1);
			return;
		case R.id.buttonUp:
			
			listView.itemUp();
			return;
			
		case R.id.buttonDown:
			
			listView.itemDown();
			return;
			
		case R.id.buttonEdit:
			
			intent = new Intent(this, ActivitySettingsAnimalsEdit.class);
			//intent.putExtra(Animal.ANIMAL, listOfAnimals.get(last)); ///!!!!!!
        	startActivityForResult(intent, 1);
			return;
			
		case R.id.buttonDel:
			return;
		}	
	}

	@Override
	public void onItemClick(AdapterView<?> adapter, View view, int position, long id){
		ImageButton buttonDel  = (ImageButton)findViewById(R.id.buttonDel);
		
		if(listOfAnimals.get(position).isDefault())
			buttonDel.setEnabled(false);
		else
			buttonDel.setEnabled(true);
		
			listView.setItemSelection(position);
		}


	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		Animal animal = listOfAnimals.get(buttonView.getId());
		animal.setVisible(isChecked);
		//settingsAdapter.notifyDataSetChanged();
	}

	@Override
	public void onItemChangeFocus(SettingsAdapter adapter,
			View view, int position, int bound) {
		ImageButton buttonUp = (ImageButton)findViewById(R.id.buttonUp);
		ImageButton buttonDown = (ImageButton)findViewById(R.id.buttonDown);
		
		switch(bound){
		
		case SettingsListView.FIRST:
			buttonDown.setEnabled(true);
			buttonUp.setEnabled(false);
			return;
			
		case SettingsListView.LAST:
			buttonUp.setEnabled(true);
			buttonDown.setEnabled(false);
			return;
			
		case SettingsListView.NON:
			buttonUp.setEnabled(true);
			buttonDown.setEnabled(true);
			return;
		}
	}
}

