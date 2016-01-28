package com.example.ioannisd.ounbeacon_v1.Rooms.WinterClasses;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import com.example.ioannisd.ounbeacon_v1.R;
import com.example.ioannisd.ounbeacon_v1.Rooms.CardAdapter;
import com.google.android.glass.widget.CardBuilder;
import com.google.android.glass.widget.CardScrollView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ioannis.D on 19-Jan-16.
 */
public class Lesson1 extends Activity{

    private CardScrollView WintLess1;

    @Override
    protected void onCreate(Bundle bundle){
        super.onCreate(bundle);
        WintLess1=new CardScrollView(this);
        WintLess1.setAdapter(new CardAdapter(createCards(this)));
        setContentView(WintLess1);
    }//onCreate

    private List<CardBuilder> createCards(Context context) {
        ArrayList<CardBuilder> cards = new ArrayList<>();

        cards.add(new CardBuilder(context, CardBuilder.Layout.COLUMNS_FIXED)
                        .setText("First look for a cable that looks like the one in the image")
                        .setIcon(R.drawable.hdmicable)
        );
        cards.add(new CardBuilder(context, CardBuilder.Layout.COLUMNS_FIXED)
                        .setText("Then connect it to your laptop to the port that looks like the one on the image")
                        .setIcon(R.drawable.hdmiport)
        );
        cards.add(new CardBuilder(context, CardBuilder.Layout.COLUMNS_FIXED)
                        .setText("Congrats! Now you should see your screen projected on the wall!")
                        .setIcon(R.drawable.jaimelike)
        );
        return cards;
    }//List
    @Override
    protected void onResume(){
        super.onResume();
        WintLess1.activate();
    }//onResume
    @Override
    protected void onPause(){
        WintLess1.deactivate();
        super.onPause();
    }//onPause
}
