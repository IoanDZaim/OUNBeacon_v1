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
 * Created by Ioannis.D on 21-Jan-16.
 */
public class Lesson2 extends Activity{

    private CardScrollView SummLes2;

    @Override
    protected void onCreate(Bundle bundle){
        super.onCreate(bundle);
        SummLes2=new CardScrollView(this);
        SummLes2.setAdapter(new CardAdapter(createCards(this)));
        setContentView(SummLes2);
    }//onCreate

    private List<CardBuilder> createCards(Context context) {
        ArrayList<CardBuilder> cards = new ArrayList<>();

        cards.add(new CardBuilder(context, CardBuilder.Layout.TEXT_FIXED)
                        .setText("In case you don't have an HDMI port, then you can use the VGA cable.")//assumption that there is no HMDI port. Should I change this?
        );
        cards.add(new CardBuilder(context, CardBuilder.Layout.COLUMNS_FIXED)
                        .setText("The VGA port on your laptop should look like the one on the image.")
                        .setIcon(R.drawable.vgaport)
        );
        cards.add(new CardBuilder(context, CardBuilder.Layout.COLUMNS_FIXED)
                        .setText("Find the VGA cable on the table and connected to the port of your laptop.")
                        .setIcon(R.drawable.vgacable)
        );
        cards.add(new CardBuilder(context, CardBuilder.Layout.TEXT_FIXED)
                        .setText("Well done! If everything went right then you must see your screen projected on the wall.")
        );
        return cards;
    }//List
    @Override
    protected void onResume(){
        super.onResume();
        SummLes2.activate();
    }//onResume
    @Override
    protected void onPause(){
        SummLes2.deactivate();
        super.onPause();
    }//onPause
}
