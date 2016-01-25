package com.example.ioannisd.ounbeacon_v1.Rooms.WinterClasses;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import com.example.ioannisd.ounbeacon_v1.Rooms.CardAdapter;
import com.google.android.glass.widget.CardBuilder;
import com.google.android.glass.widget.CardScrollView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ioannis.D on 19-Jan-16.
 */
public class Lesson1 extends Activity{

    private CardScrollView testing;

    @Override
    protected void onCreate(Bundle bundle){
        super.onCreate(bundle);
        testing=new CardScrollView(this);
        testing.setAdapter(new CardAdapter(createCards(this)));
        setContentView(testing);
    }//onCreate

    private List<CardBuilder> createCards(Context context) {
        ArrayList<CardBuilder> cards = new ArrayList<>();

        cards.add(new CardBuilder(context, CardBuilder.Layout.TEXT_FIXED)
                        .setText("Testing to see if this thing works")
        );
        cards.add(new CardBuilder(context, CardBuilder.Layout.TEXT_FIXED)
                        .setText("Well, if you see this then it works!")
        );
        cards.add(new CardBuilder(context, CardBuilder.Layout.TEXT_FIXED)
                        .setText("Mijn Nederlands is niet goed :/")
        );
        return cards;
    }//List
    @Override
    protected void onResume(){
        super.onResume();
        testing.activate();
    }//onResume
    @Override
    protected void onPause(){
        testing.deactivate();
        super.onPause();
    }//onPause
}
