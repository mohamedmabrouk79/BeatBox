package com.example.mohamedmabrouk.beatbox;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mohamed Mabrouk on 22/04/2016.
 */

//*********** for control the assets **********//
public class BeatBok {
    private static final String TAG = "BeatBox";
    private static final String SOUNDS_FOLDER = "sample_sound";
    private static final int MAX_SOUNDS = 5;
    private AssetManager mAssets;
    private SoundPool mSoundPool;
    private List<Sound> mSounds=new ArrayList<>();
    public BeatBok(Context mContext){
        mAssets=mContext.getAssets();
        mSoundPool=new SoundPool(MAX_SOUNDS, AudioManager.STREAM_MUSIC, 0);
        loadSounds();
    }

    //**************for load sound using Id for indivadual sound ************//
    private void Load(Sound sound) throws Exception{
        AssetFileDescriptor assetFileDescriptor=mAssets.openFd(sound.getAssetsPath());
        int SoundId=mSoundPool.load(assetFileDescriptor,1);
        sound.setSoundId(SoundId);
    }

    //************ for get a list  items in  assets  **********//
    private void loadSounds() {
        String[] soundNames;
        try {
            soundNames = mAssets.list(SOUNDS_FOLDER);
            Log.i(TAG, "Found " + soundNames.length + " sounds");
        } catch (IOException ioe) {
            Log.e(TAG, "Could not list assets", ioe);
            return;
        }
        for (String FileName:soundNames) {
            try{
            String assetPath=SOUNDS_FOLDER+"/"+FileName;
            Sound sound=new Sound(assetPath);
                Load(sound);
            mSounds.add(sound);
            }catch (Exception e){
                Log.e(TAG," could not load sound" +FileName,e);
            }
        }
    }

    //************** for playing sound **********//
    public void play(Sound sound) {
        Integer soundId = sound.getSoundId();
        if (soundId == null) {
            return;
        }
        mSoundPool.play(soundId, 1.0f, 1.0f, 1, 0, 1.0f);
    }

    public void release() {
        mSoundPool.release();
    }
 public List<Sound> getSounds(){
        return mSounds;
    }

}
