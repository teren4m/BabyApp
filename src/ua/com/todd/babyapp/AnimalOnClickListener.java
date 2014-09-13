package ua.com.todd.babyapp;

import android.content.Context;
import android.media.MediaPlayer;
import android.view.View;
import android.view.View.OnClickListener;

public class AnimalOnClickListener implements OnClickListener {

	private MediaPlayer mMediaPlayer;

	public AnimalOnClickListener(Context context, MediaPlayer mMediaPlayer){

		this.mMediaPlayer = mMediaPlayer;
		
	}

	@Override
	public void onClick(View v) {
		
			mMediaPlayer.start();
		
		
	} 

}
