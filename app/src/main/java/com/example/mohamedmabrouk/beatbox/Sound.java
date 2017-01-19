package com.example.mohamedmabrouk.beatbox;

/**
 * Created by Mohamed Mabrouk on 22/04/2016.
 */
public class Sound {
    private String mAssetsPath;
    private String mName;
    private Integer mSoundId;
//************* for get name without .wav just name  ********//
    public Sound(String assetPath){
        mAssetsPath=assetPath;
        String components[]=assetPath.split("/");
        String FileName=components[components.length-1];
        mName=FileName.replace(".wav","");

    }

    public String getAssetsPath() {
        return mAssetsPath;
    }

    public String getName() {
        return mName;
    }

    public Integer getSoundId() {
        return mSoundId;
    }

    public void setSoundId(Integer mSoundId) {
        this.mSoundId = mSoundId;
    }
}
