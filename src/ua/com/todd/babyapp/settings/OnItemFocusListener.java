package ua.com.todd.babyapp.settings;

import android.view.View;

public interface OnItemFocusListener {

	void onItemChangeFocus(SettingsAdapter adapter, View view, int position, int bound);
	
}
