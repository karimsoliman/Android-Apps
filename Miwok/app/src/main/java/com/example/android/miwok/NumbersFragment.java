package com.example.android.miwok;


import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class NumbersFragment extends Fragment {

    private MediaPlayer mp;
    private AudioManager audioManager;

    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            releaseMediaPlayer();
        }
    };

    private AudioManager.OnAudioFocusChangeListener onAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if (focusChange == AudioManager.AUDIOFOCUS_GAIN){
                mp.start();
            }
            else if (focusChange == AudioManager.AUDIOFOCUS_LOSS){
                releaseMediaPlayer();
            }
            else if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT || focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK){
                mp.stop();
                mp.seekTo(0);
            }
        }
    };

    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mp != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mp.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mp = null;

            audioManager.abandonAudioFocus(onAudioFocusChangeListener);
        }
    }

    public NumbersFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView= inflater.inflate(R.layout.word_list, container,false);

        final ArrayList<word> words = new ArrayList<>();
        words.add(new word("lutti", "one", R.raw.number_one,R.drawable.number_one));
        words.add(new word("otiiko", "two", R.raw.number_two,R.drawable.number_two));
        words.add(new word("tolookosu", "three", R.raw.number_three,R.drawable.number_three));
        words.add(new word("oyyisa", "four", R.raw.number_four,R.drawable.number_four));
        words.add(new word("massokka", "five", R.raw.number_five, R.drawable.number_five));
        words.add(new word("temmokka", "six", R.raw.number_six, R.drawable.number_six));
        words.add(new word("kenekaku", "seven", R.raw.number_seven, R.drawable.number_seven));
        words.add(new word("kawinta", "eight", R.raw.number_eight, R.drawable.number_eight));
        words.add(new word("wo'e", "nine", R.raw.number_nine, R.drawable.number_nine));
        words.add(new word("na'aacha", "ten", R.raw.number_ten, R.drawable.number_ten));

        wordAdapter itemsAdapter = new wordAdapter(getActivity(), words,R.color.category_numbers);
        ListView listView = (ListView) rootView.findViewById(R.id.list);
        listView.setAdapter(itemsAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                word currentWord = words.get(i);
                releaseMediaPlayer();
                audioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);
                int result = audioManager.requestAudioFocus(onAudioFocusChangeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                if(result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED){
                    mp = MediaPlayer.create(getActivity(), currentWord.getSoundResourceId());
                    mp.start();
                    mp.setOnCompletionListener(mCompletionListener);
                }

            }
        });

        return rootView;
    }

    @Override
    public void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }
}
