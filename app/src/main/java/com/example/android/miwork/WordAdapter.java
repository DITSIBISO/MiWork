package com.example.android.miwork;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.media.Image;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * {@link WordAdapter} is an {@link ArrayAdapter} that can provide the layout for each list item
 * based on data source, which is list of {@link Word} objects
 */

public class WordAdapter extends ArrayAdapter<Word> {

    /**Resource Id for the background color for this list of words*/
     private int mColorResourceId;



    /**
     *  Creat a new {@link WordAdapter} object
     *
     * @param context is the current (i.e. Activity) that the adapter is being created in
     * @param words is the list of {@link Word}s to be display
     */

    public  WordAdapter(Context context, ArrayList<Word> words, int colorResourceId) {
        super(context,0, words);
        mColorResourceId = colorResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.activity_list_item,parent,false);
        }
        Word currentWord = getItem(position);
//       AndroidFlavor currentAndroidFlavor = getItem(position);
        TextView miwokTextView = (TextView) listItemView.findViewById(R.id.miwork_text_view);
        miwokTextView.setText(currentWord.getMiworkTranslation());

        TextView defaultTextView = (TextView) listItemView.findViewById(R.id.default_text_view);
        defaultTextView.setText(currentWord.getDefaultTranslation());

        ImageView imageView= (ImageView) listItemView.findViewById(R.id.image);
        if(currentWord.hasImage()) {
            imageView.setImageResource(currentWord.getImageResourceId());
            //Make sure the view is visible
            imageView.setVisibility(View.VISIBLE);
        }
        else {
            //Otherwise hide the ImageView (set visibility to GONE)
            imageView.setVisibility(View.GONE);
        }
        View textContainer = listItemView.findViewById(R.id.text_container);
        int color = ContextCompat.getColor(getContext(), mColorResourceId);
        textContainer.setBackgroundColor(color);
//        iconView.setImageResource(currentAndroidFlavor.getImageResourceId());
        return  listItemView;


    }
}
