package com.example.ioannisd.ounbeacon_v1.Rooms.SummerClasses;

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

    private CardScrollView SummLes1;

    @Override
    protected void onCreate(Bundle bundle){
        super.onCreate(bundle);
        SummLes1=new CardScrollView(this);
        SummLes1.setAdapter(new CardAdapter(createCards(this)));
        setContentView(SummLes1);
    }//onCreate

    private List<CardBuilder> createCards(Context context) {
        ArrayList<CardBuilder> cards = new ArrayList<>();

        cards.add(new CardBuilder(context, CardBuilder.Layout.TEXT_FIXED)
                        .setText("Hello! This lesson will demonstrate you how to connect your laptop to the projector throught the use of an HDMI cable.")
        );
        cards.add(new CardBuilder(context, CardBuilder.Layout.COLUMNS_FIXED)
                        .setText("First things first, check if you have an HDMI port on your laptop. It looks like the image on the left.")
                        .setIcon(R.drawable.hdmiport)
        );
        cards.add(new CardBuilder(context, CardBuilder.Layout.COLUMNS_FIXED)
                        .setText("If you do, great! Find the HDMI cable and connect it to the port of your PC. The cable looks like the one on the image and it's usually on top of the table")
                        .setIcon(R.drawable.hdmicable)
        );
        cards.add(new CardBuilder(context, CardBuilder.Layout.COLUMNS_FIXED)
                        .setText("Well done! Now you must see your screen projected on the wall!")
                        .setIcon(R.drawable.vaultboy)
        );
        return cards;
    }//List
    @Override
    protected void onResume(){
        super.onResume();
        SummLes1.activate();
    }//onResume
    @Override
    protected void onPause(){
        SummLes1.deactivate();
        super.onPause();
    }//onPause
}
