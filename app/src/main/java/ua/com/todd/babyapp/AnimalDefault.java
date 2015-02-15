package ua.com.todd.babyapp;

import java.io.Serializable;

public class AnimalDefault extends Animal implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public AnimalDefault(String animalName, int animalSound, int animalImage, boolean isVisible){
		this.animalName = animalName;
		this.animalSound = animalSound;
		this.animalImage = animalImage;
		this.isVisible = isVisible;
		this.isDefault = true;
		this.background = 0xfff;
	}
	
	@Override
    public void copyTo(Animal animalCopy){
		
		animalCopy.animalName = this.animalName;
		animalCopy.animalSound = this.animalSound;
		animalCopy.animalImage = this.animalImage;
		animalCopy.soundUri = this.soundUri;
		animalCopy.isVisible = this.isVisible;
		animalCopy.background = this.background;
		
	}
    
    @Override
    public void changeWith(Animal animalChange){
    	AnimalDefault change = new AnimalDefault(null, 0, 0, false);
		
		this.copyTo(change);
		animalChange.copyTo(this);
		change.copyTo(animalChange);
	}	
}
