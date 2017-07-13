package com.example.android.miwork;


import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.ParcelUuid;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;
import static android.media.AudioManager.AUDIOFOCUS_LOSS;
import static android.media.AudioManager.AUDIOFOCUS_LOSS_TRANSIENT;
import static android.os.Build.VERSION_CODES.M;

//import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
//import android.widget.ListView;
//
//import java.util.ArrayList;

//import static com.example.android.miwork.R.id.rootView;

public class NumbersActivity extends AppCompatActivity {

    /**
     * Handles playback of all the sound files*/

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
        words.add(new Word("one", "lutti", R.drawable.number_one, R.raw.number_one));
        words.add(new Word("two", "ottiko", R.drawable.number_two, R.raw.number_two));
        words.add(new Word("three", "tolookosu", R.drawable.number_three, R.raw.number_three));
        words.add(new Word("four", "oyyisa", R.drawable.number_four,R.raw.number_four));
        words.add(new Word("five", "massokka", R.drawable.number_five, R.raw.number_five));
        words.add(new Word("six", "temmokka", R.drawable.number_six, R.raw.number_six));
        words.add(new Word("seven", "kenekaku", R.drawable.number_seven, R.raw.number_seven));
        words.add(new Word("eight", "kawinta", R.drawable.number_eight, R.raw.number_eight));
        words.add(new Word("nine", "wo'e", R.drawable.number_nine, R.raw.number_nine));
        words.add(new Word("ten", "na'aacha", R.drawable.number_ten,R.raw.number_ten));
        // Create an {@link ArrayAdapter}, whose data source is list of String. The
        // adapter knows how to create layouts for each item in the list, using the
        // simple_list_item_1.xml layout resource defined in the Android framework.
        //This list item layout contains a single{@link TextView}, which the adapter will set
        //display a single word
//        ArrayAdapter<String> itemsAdapter =
//                new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, words);

//        ArrayAdapter<Word> itemsAdapter =
//                new ArrayAdapter<Word>(this, R.layout.list_item, words);
        WordAdapter adapter = new WordAdapter(this, words, R.color.category_numbers);
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
            private MediaPlayer mMediaPlayer;
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(NumbersActivity.this, "List item clicked", Toast.LENGTH_SHORT);
                Word word = words.get(position);
                // Release the media player if it currently exists because we are about to
                // play a different sound file.
                releaseMediaPlayer();

                //Request audio focus for playback
                int result = mAudioManager.requestAudioFocus(mOnAudioFocusChargeListener,
                        // use the music stream
                        AudioManager.STREAM_MUSIC,
                        //Request permanent focus.
                        AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                    if (result== AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                        //mAudioManager.registerMediaButtonEventReceiver(RemoteControlReceiver);
                        // start playback.
                        //we have a audio focus now.


                mMediaPlayer = MediaPlayer.create(NumbersActivity.this,word.getmAudioResourceId() );
                mMediaPlayer.start();//no need to call prepare(); create() does for you

//                mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener(){
//                    @Override
//                    public void onCompletion(MediaPlayer mp) {
//
//                    }
//                });
                // Setup a listener on the media player, so that we can stop and release the
                // media player once the sounds has finished playing.
                mMediaPlayer.setOnCompletionListener(mCompletionListener);
            }
            }
        });


//        listView.setOnClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(NumbersActivity.this, "List item clicked", Toast.LENGTH_SHORT);


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

           // }

       // });
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
