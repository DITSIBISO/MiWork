package com.example.android.miwork;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class FamilyActivity extends AppCompatActivity {

/** Handles playback of all the sound files*/
private MediaPlayer mMediaPlayer;

    /** Handles audio focus of all the sound files*/
    private AudioManager mAudioManager;
    AudioManager.OnAudioFocusChangeListener mOnAudioFocusChargeListener =
            new  AudioManager.OnAudioFocusChangeListener() {
                public  void onAudioFocusChange(int focusChange) {
                    if ( focusChange== AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                            focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                        //pause playback
                        mMediaPlayer.pause();
                        mMediaPlayer.seekTo(0);
                    }else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                        //The AUDIOFOCUS_GAIN case means we have regained focus and can
                        // resume playback
                        mMediaPlayer.start();

                    } else if (focusChange== AudioManager.AUDIOFOCUS_LOSS){
//                        am.unregisterMediaButtonEventReceiver(RemoteControlReceiver);
//                        am.abandonAudioFocus(afChangeListener);
//                        // stop playback
                        //the AUDIOFOCUS_LOSS case means we ve lost audio focus and
                        //stop playback and clean up resources
                        releaseMediaPlayer();
                    }
                }


            };


    /**
     * This listener get triggered when the {@link MediaPlayer} has completed
     * playing the audio file
     */
    private MediaPlayer.OnCompletionListener mCompletionListener =
            new MediaPlayer.OnCompletionListener(){

                @Override
                public void onCompletion(MediaPlayer mp) {
                    releaseMediaPlayer();
                }

            };
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.word_list);
// create and setup the {@link AudioManager} to request audio focus
            mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

            //Create an array of word
            //ArrayList<String> words = new ArrayList<String>();
            final ArrayList<Word> words = new ArrayList<Word>();

            //words.add ("one");
            words.add(new Word("father", "ede",R.drawable.family_father, R.raw.family_father));
            words.add(new Word("mother", "eta",R.drawable.family_mother, R.raw.family_mother));
            words.add(new Word("son", "angsi",R.drawable.family_son, R.raw.family_son));
            words.add(new Word("daughter", "tune",R.drawable.family_daughter, R.raw.family_daughter));
            words.add(new Word("older brother", "taachi",R.drawable.family_older_brother,
                    R.raw.family_older_brother));
            words.add(new Word("younger brother", "chalitti",R.drawable.family_younger_brother,
                    R.raw.family_younger_brother));
            words.add(new Word("older sister", "tete",R.drawable.family_older_sister,
                    R.raw.family_older_sister));
            words.add(new Word("younger sister", "kolliti",R.drawable.family_younger_sister,
                    R.raw.family_younger_sister));
            words.add(new Word("grandmother", "ama",R.drawable.family_grandmother,
                    R.raw.family_grandmother));
            words.add(new Word("grandfather", "paapa",R.drawable.family_grandfather,
                    R.raw.family_grandfather));
            // Create an {@link ArrayAdapter}, whose data source is list of String. The
            // adapter knows how to create layouts for each item in the list, using the
            // simple_list_item_1.xml layout resource defined in the Android framework.
            //This list item layout contains a single{@link TextView}, which the adapter will set
            //display a single word
//        ArrayAdapter<String> itemsAdapter =
//                new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, words);

//        ArrayAdapter<Word> itemsAdapter =
//                new ArrayAdapter<Word>(this, R.layout.list_item, words);
            WordAdapter adapter =
                    new WordAdapter(this, words ,R.color.category_family);
            // Find the {@link ListView} object in the view hierarchy of the {@link Activity}.
            // There should be a {@link ListView} with the view ID called list, which is declared in
            // activity_number.xml layout file
            ListView listView = (ListView) findViewById(R.id.list);
            // GridView listView = (GridView) findViewById(R.id.list);
            // Make the {@link ListView} use the {@link ArrayAdapter} we created above, so that the
            //{@link ListView} will display list items for each word in the list of words.
            // Do this by calling the setAdapter method on the {@link ListView} object and pass in
            // 1 argument, which is the {@link ArrayAdapter} with the variable name itemsAdapter.


           listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Word word = words.get(position);
                    releaseMediaPlayer();
                    //Request audio focus for playback
                    int result = mAudioManager.requestAudioFocus(mOnAudioFocusChargeListener,
                            // use the music stream
                            AudioManager.STREAM_MUSIC,
                            //Request permanent focus.
                            AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                    if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                        //mAudioManager.registerMediaButtonEventReceiver(RemoteControlReceiver);
                        // start playback.
                        //we have a audio focus now.
                        mMediaPlayer = MediaPlayer.create(FamilyActivity.this, word.getmAudioResourceId());
                        // Start the audio file
                        mMediaPlayer.start();
                        // Setup a listener on the media player, so that we can stop and release the
                        // media player once the sounds has finished playing.
                        mMediaPlayer.setOnCompletionListener(mCompletionListener);

                }
                }
            });




            //Find the root view of the whole layout
            //LinearLayout rootView = (LinearLayout)findViewById(rootView);
            //Create a variable to keep track of the current index position
//        for (int index = 0; index < words.size(); index++)
            // {
            // Create a new TextView
            //TextView wordView = new TextView(this);
            //Set the text to be word at the current index
            //wordView.setText(words.get(index));
            // Add this TextView as another child to the root view of this layout
            //rootView.addView(wordView);
            //Update counter variable
            // index++;

//        TextView wordView = new TextView(this);
//        wordView.setText(words.get(0));
//        rootView.addView(wordView);
//
//       index = index + 1;
//        TextView wordView2 = new TextView(this);
//        wordView2.setText(words.get(1));
//        rootView.addView(wordView2);
//       index = index + 1;
//        TextView wordView3 = new TextView(this);
//        wordView3.setText(words.get(2));
//        rootView.addView(wordView3);
//
//      index = index + 1;
//        TextView wordView4 = new TextView(this);
//        wordView4.setText(words.get(3));
//        rootView.addView(wordView4);


        }

    @Override
    protected void onStop() {
        super.onStop();
        //when the activity is stopped , released the media player resources because we wont
        //be playing any more sounds.
        releaseMediaPlayer();
    }

    /**
     * Clean up the media player by releasing its resources.
     */

    private void releaseMediaPlayer()
    {
        // if the media player is not null, then it may be currently playing a sound.
        if (mMediaPlayer != null) {
            mMediaPlayer.release();
            mMediaPlayer = null;
            // regardless of whether or not we were granted audio focus, abandon it. this also
            // unregisters the AudioFocusChangeListener so we dont get anymore callbacks.
            mAudioManager.abandonAudioFocus(mOnAudioFocusChargeListener);
        }
    }
    }


