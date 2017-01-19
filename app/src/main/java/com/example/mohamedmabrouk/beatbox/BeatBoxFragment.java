package com.example.mohamedmabrouk.beatbox;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.List;

/**
 * Created by Mohamed Mabrouk on 22/04/2016.
 */
public class BeatBoxFragment extends Fragment{
      private BeatBok beatBok;
    //**************** for return instance from BeatBox Fragment ************//
    public static BeatBoxFragment newInstance(){
        return new BeatBoxFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        beatBok=new BeatBok(getActivity());
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_beat_box,container,false);

        RecyclerView recyclerView=(RecyclerView)view.findViewById(R.id.fragment_beat_box_recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        recyclerView.setAdapter(new SoundAdepter(beatBok.getSounds()));

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        beatBok.release();
    }

    //************** create view holder (Sound Holder) **********//
    private class SoundHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
           private Button mButtonSound;
        private Sound mSound;
        public SoundHolder(LayoutInflater inflater,ViewGroup group) {
            super(inflater.inflate(R.layout.list_item_sound,group,false));
            mButtonSound=(Button)itemView.findViewById(R.id.list_item_sound_button);
            mButtonSound.setOnClickListener(this);
        }

        public  void bindsound(Sound sound){
            mSound=sound;
            mButtonSound.setText(mSound.getName());
        }

        @Override
        public void onClick(View v) {
            beatBok.play(mSound);
        }
    }

    //************* create View Adepter *******//
    private class SoundAdepter extends RecyclerView.Adapter<SoundHolder>{
             private List<Sound> mSounds;
        public SoundAdepter(List<Sound> sounds){
          mSounds=sounds;
        }
        @Override
        public SoundHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater=LayoutInflater.from(getActivity());
            return new SoundHolder(inflater,parent);
        }

        @Override
        public void onBindViewHolder(SoundHolder holder, int position) {
        Sound sound=mSounds.get(position);
            holder.bindsound(sound);
        }

        @Override
        public int getItemCount() {
            return mSounds.size();
        }
    }


}
