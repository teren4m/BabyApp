package ua.com.todd.babyapp.settings;

import ua.com.todd.babyapp.R;
import ua.com.todd.babyapp.settings.SettingsAdapter.AnimalHolder;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListAdapter;
import android.widget.ListView;

public class SettingsListView extends ListView {

	public final  static int FIRST = 1, LAST = 2, NON = 3;
	
	private int last = 0;
	private int bound;
	private SettingsAdapter adapter;
	private OnItemFocusListener focusListener;
	
	public SettingsListView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		
	}

	public SettingsListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public SettingsListView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void setAdapter(ListAdapter adapter){
		super.setAdapter(adapter);
		this.adapter = (SettingsAdapter) adapter;
	}
	
	public void setOnItemFocusListener(OnItemFocusListener focusListener){
		this.focusListener = focusListener;
	}

    public void itemUp(){
    	AnimalHolder animalHolderPrev = adapter.getItemAnimal(last);
    	
    	setItemSelection(last - 1);
    	
    	AnimalHolder animalHoldernext = adapter.getItemAnimal(last);
    	
    	animalHolderPrev.animal.changeWith(animalHoldernext.animal);
    	
    	adapter.notifyDataSetChanged();
		}
    
    public void itemDown(){
    	AnimalHolder animalHolderPrev = adapter.getItemAnimal(last);
    	
    	setItemSelection(last + 1);
    	
    	AnimalHolder animalHoldernext = adapter.getItemAnimal(last);
    	
    	animalHolderPrev.animal.changeWith(animalHoldernext.animal);
    	
    	adapter.notifyDataSetChanged();
	}
    
	
    public void setItemSelection(int position){
    	AnimalHolder animalHolderPrev = adapter.getItemAnimal(last);
    	animalHolderPrev.img = R.drawable.black;
    	
    	last = position;
    	
    	AnimalHolder animalHolderNext = adapter.getItemAnimal(last);
    	animalHolderNext.img = R.drawable.red;
    	
    	if(last == 0)
    		bound = FIRST;
    	else if(last == adapter.getCount() - 1)
    		bound = LAST;
    	else
    		bound = NON;
    	
    	if(!(focusListener == null))
    		focusListener.onItemChangeFocus(adapter, animalHolderNext.view, last, bound);
    	
    	adapter.notifyDataSetChanged();
    }
    public int getItemSelection(){
    	return last;
    }
}
