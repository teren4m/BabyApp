package ua.com.todd.babyapp.settings;

import java.util.ArrayList;
import java.util.List;

import ua.com.todd.babyapp.Animal;
import ua.com.todd.babyapp.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.TextView;



public class SettingsAdapter extends ArrayAdapter<String> {
	private List<AnimalHolder> listAnimalHolder= new ArrayList<AnimalHolder>();
	private LayoutInflater mInflater;
	private Context context;
	
	class AnimalHolder{
		Animal animal;
	    View view;
	  }
	
	public SettingsAdapter(Context context, List<Animal> listOfAnimals) {
		super(context, 0, new String[listOfAnimals.size()]);
		this.context = context;
		mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		for(Animal animal : listOfAnimals){
		
			AnimalHolder animalHolder = new AnimalHolder();
			animalHolder.animal = animal;
			listAnimalHolder.add(animalHolder);
			
		}
		
	}
	

	public AnimalHolder getItemAnimal(int position){
			return listAnimalHolder.get(position);
	}

	@Override
	public int getCount(){
		return listAnimalHolder.size();
	}
	
	@Override
    public View getView(int position, View convertView, ViewGroup parent) {
	
		AnimalHolder animalHolder = listAnimalHolder.get(position);
		
		View view = mInflater.inflate(R.layout.row, parent, false);
		ImageView imgView   = (ImageView)view.findViewById(R.id.imageAnimalIcon);
		TextView textView   = (TextView)view.findViewById(R.id.textAnimalName);
		CheckBox checkBox   = (CheckBox)view.findViewById(R.id.checkAnimalIsVisible);
		
		view.setId(position);
		
		imgView.setImageResource(animalHolder.animal.getAnimalImage());
	
		textView.setText(animalHolder.animal.getName());
		
		checkBox.setChecked(animalHolder.animal.isVisible());
		checkBox.setFocusable(false);
		checkBox.setId(position);
		checkBox.setOnCheckedChangeListener((OnCheckedChangeListener) context);
		
		animalHolder.view = view;
		
		return view;
	}
}
