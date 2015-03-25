package ua.com.todd.babyapp;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.view.View;
import android.view.View.OnClickListener;

public class AnimalOnClickListener implements OnClickListener {

    private MediaPlayer mMediaPlayer;

    public AnimalOnClickListener(Context context, Animal animal) {
        MediaPlayer soundAnimals = MediaPlayer.create(context, animal.getAnimalSound());
        soundAnimals.setAudioStreamType(AudioManager.STREAM_MUSIC);
        this.mMediaPlayer = soundAnimals;
    }

    @Override
    public void onClick(View v) {
        mMediaPlayer.start();
    }

}
