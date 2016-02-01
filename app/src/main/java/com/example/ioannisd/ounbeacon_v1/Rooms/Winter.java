package com.example.ioannisd.ounbeacon_v1.Rooms;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.example.ioannisd.ounbeacon_v1.Rooms.WinterClasses.Lesson1;
import com.example.ioannisd.ounbeacon_v1.Rooms.WinterClasses.Lesson2;
import com.google.android.glass.media.Sounds;
import com.google.android.glass.widget.CardBuilder;
import com.google.android.glass.widget.CardScrollView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ioannis.D on 18-Jan-16.
 */
public class Winter extends Activity {


    private CardScrollView WintView;
    private static final int message = 0;
    private static final int lesson1 = 1;
    private static final int lesson2 = 2;
    @Override
    protected void onCreate(Bundle bundle){
        super.onCreate(bundle);

        WintView=new CardScrollView(this);
        WintView.setAdapter(new CardAdapter(createCards(this)));
        setCardScrollerListener();
        setContentView(WintView);
/**
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                setContentView(testing);
            }
        }, 5000);
 */
    }//onCreate

    private List<CardBuilder> createCards(Context context) {
        ArrayList<CardBuilder> cards = new ArrayList<>();

        cards.add(message, new CardBuilder(context, CardBuilder.Layout.TEXT_FIXED)
                        .setText("Hello! It seems you are in room Winter. Slide to the right to see some lessons about the equipment here!")
                        .setFootnote("Welten Institute")
        );

        cards.add(lesson1, new CardBuilder(context, CardBuilder.Layout.MENU)
                        .setText("Connect to the beamer through HDMI cable")
                        .setFootnote("Welten Institute")

        );
        cards.add(lesson2, new CardBuilder(context, CardBuilder.Layout.MENU)
                        .setText("Connect to the beamer through VGA cable")
                        .setFootnote("Welten Institute")
        );

        return cards;
    }//List

    private void setCardScrollerListener() {
        WintView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                int soundEffect = Sounds.TAP;
                switch (position) {
                    case message:
                        soundEffect = Sounds.TAP;
                        break;

                    case lesson1:
                        startActivity(new Intent(Winter.this, Lesson1.class));
                        break;

                    case lesson2:
                        startActivity(new Intent(Winter.this, Lesson2.class));
                        break;


                    default:
                        soundEffect = Sounds.ERROR;
                        //Log.d(TAG, "Don't show anything");
                }
                // Play sound.
                AudioManager am = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
                am.playSoundEffect(soundEffect);
            }
        });
    }
    @Override
    protected void onResume(){
        super.onResume();
        WintView.activate();
    }//onResume
    @Override
    protected void onPause(){
        WintView.deactivate();
        super.onPause();
    }//onPause


}//Winter
