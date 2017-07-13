package com.example.android.miwork;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import static com.example.android.miwork.R.id.numbers;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Create an ArrayList of AndroidFlavour object
//        ArrayList<AndroidFlavor> androidFlavors = new ArrayList<>();
//
//        androidFlavors.add(new AndroidFlavor("Cupcake", "1.5", R.drawable.cupcake));
//        androidFlavors.add(new AndroidFlavor("Donut", "1.6", R.drawable.donut));
//        androidFlavors.add(new AndroidFlavor("Eclair", "2.0-2.1", R.drawable.Eclair));
//        androidFlavors.add(new AndroidFlavor("Froyo", "2.2-2.2.3", R.drawable.Froyo));
//        androidFlavors.add(new AndroidFlavor("GingerBread", "3.0-3.2.6", R.drawable.GingerBread));
//        androidFlavors.add(new AndroidFlavor("Honeycomb", "4.0-4.0.4", R.drawable.Honeycomb));
//        androidFlavors.add(new AndroidFlavor("Ice Cream Sandwich", "1.5", R.drawableIce Cream Sandwich.));
//        androidFlavors.add(new AndroidFlavor("Jelly Bean", "4.1-4.3.1", R.drawable.Jelly Bean));
//        androidFlavors.add(new AndroidFlavor("KitKat", "4.4-4.4.4", R.drawable.KitKat));
//        androidFlavors.add(new AndroidFlavor("Lollipop", "5.0-5.1.1", R.drawable.Lollipop));
//

//        set the content of the activity to use the activity_main.xml layout file
        setContentView(R.layout.activity_main);


        //Find the View that shows the numbers category
        TextView numbers = (TextView)findViewById(R.id.numbers);

        //Set a clicklistener on that View
        numbers.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                //Create a new intent to open the {@link NumbersActivity}
                Intent numbersIntent = new Intent(MainActivity.this,
                        NumbersActivity.class);

                //Start the new activity
                startActivity(numbersIntent);

            }
        });



        //Find the View that shows the family category
        TextView family = (TextView)findViewById(R.id.family);

        //Set a clicklistener on that View
        family.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                //Create a new intent to open the {@link FamilyActivity}
                Intent familyIntent = new Intent(MainActivity.this,
                        FamilyActivity.class);

                //Start the new activity
                startActivity(familyIntent);

            }
        });
        //Find the View that shows the colors category
        TextView colors = (TextView)findViewById(R.id.colors);

        //Set a clicklistener on that View
        colors.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                //Create a new intent to open the {@link ColorsActivity}
                Intent colorsIntent = new Intent(MainActivity.this,
                        ColorsActivity.class);

                //Start the new activity
                startActivity(colorsIntent);

            }
        });

        //Find the View that shows the phrases category
        TextView phrases = (TextView)findViewById(R.id.phrases);

        //Set a clicklistener on that View
        phrases.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                //Create a new intent to open the {@link PhrasesActivity}
                Intent phrasesIntent = new Intent(MainActivity.this,
                        PhrasesActivity.class);

                //Start the new activity
                startActivity(phrasesIntent);

            }
        });
    }


    }


