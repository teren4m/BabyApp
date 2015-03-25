package ua.com.todd.babyapp.settings;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import ua.com.todd.babyapp.Animal;
import ua.com.todd.babyapp.AnimalDefault;
import ua.com.todd.babyapp.R;


public class Settings implements Serializable {
	
	public final static String FILE_SETTINGS = "settings";
	
	
	private int images[]  = {R.drawable.dog, R.drawable.cat, R.drawable.cow, R.drawable.donkey, R.drawable.hourse, R.drawable.elephant, R.drawable.frog};
	private int sounds[]  = {R.raw.dog, R.raw.cat, R.raw.cow, R.raw.donkey, R.raw.hourse, R.raw.elephant, R.raw.frog};
	private String names[] = {"dog", "cat", "cow", "donkey", "hourse", "elephant", "frog"};

	private boolean checkAll = true;
	
	private List<Animal> listOfAnimals = new LinkedList<Animal>();
	/**
	 * 
	 */
	private static final long serialVersionUID = -1054663745047476452L;
	
	public Settings(){
		for(int i = 0; i < images.length; i++){
			listOfAnimals.add(new AnimalDefault(names[i], sounds[i], images[i], true));
		}
		
	}
	
public List<Animal> getVisibleAnimals(){
		return listOfAnimals;
	}
	
	public boolean isCheckAll() {
		return checkAll;
	}


	public void setCheckAll(boolean checkAll) {
		this.checkAll = checkAll;
	}


	public List<Animal> getListOfAnimals() {
		return listOfAnimals;
	}


	public void setListOfAnimals(List<Animal> listOfAnimals) {
		this.listOfAnimals = listOfAnimals;
	}


	public void setAllCheckBox(boolean b){
		
			for(Animal animal : listOfAnimals)
				animal.setVisible(b);
	}
	
	public boolean isAllCheckTrue(){
		
		for(Animal animal : listOfAnimals)
			if(!animal.isVisible())
				return false;
		
		return true;
	}	
}
