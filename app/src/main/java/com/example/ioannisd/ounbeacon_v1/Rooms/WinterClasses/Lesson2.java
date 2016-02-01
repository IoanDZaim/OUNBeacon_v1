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
 * Created by Ioannis.D on 21-Jan-16.
 */
public class Lesson2 extends Activity{

    private CardScrollView WintLess2;

    @Override
    protected void onCreate(Bundle bundle){
        super.onCreate(bundle);
        WintLess2=new CardScrollView(this);
        WintLess2.setAdapter(new CardAdapter(createCards(this)));
        setContentView(WintLess2);
    }//onCreate

    private List<CardBuilder> createCards(Context context) {
        ArrayList<CardBuilder> cards = new ArrayList<>();

        cards.add(new CardBuilder(context, CardBuilder.Layout.TEXT_FIXED)
                        .setText("In case your laptop doesn't have an HDMI port, try to connect the projector through the VGA port")
        );
        cards.add(new CardBuilder(context, CardBuilder.Layout.COLUMNS_FIXED)
                        .setText("The VGA port looks like the image on left")
                        .setIcon(R.drawable.vgaport)
        );
        cards.add(new CardBuilder(context, CardBuilder.Layout.COLUMNS_FIXED)
                        .setText("Find the VGA cable of the projector and connected to the port of your laptop. The cable must look like the one on the left")
                        .setIcon(R.drawable.vgacable)
        );
        cards.add(new CardBuilder(context, CardBuilder.Layout.TEXT_FIXED)
                        .setText("Congratulations! You connected your laptop to the projector!")
        );
        return cards;
    }//List
    @Override
    protected void onResume(){
        super.onResume();
        WintLess2.activate();
    }//onResume
    @Override
    protected void onPause(){
        WintLess2.deactivate();
        super.onPause();
    }//onPause
}
