package ua.com.todd.babyapp;

import java.io.Serializable;

public abstract class Animal implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public final static String ANIMAL = "animal";
	
	protected String animalName;
	protected int animalSound;
	protected int animalImage;
	protected int background;
	
	protected String soundUri;
	protected boolean isVisible;
	protected boolean isDefault;
	
	public abstract void copyTo(Animal animalCopy);
	public abstract void changeWith(Animal animalChange);
	
	public int getAnimalSound() {
		return animalSound;
	}
	public int getAnimalImage() {
		return animalImage;
	}
	public String getName() {
		return animalName;
	}
	public boolean isVisible() {
		return isVisible;
	}
	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}
	public boolean isDefault() {
		return isDefault;
	}
	public int getBackground() {
		return background;
	}
	public void setBackground(int background) {
		this.background = background;
	}
}
