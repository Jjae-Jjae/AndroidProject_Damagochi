package com.example.damagochproject;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.os.Looper;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Handler;

public class EggChoicDialog{

    private Button Confirm, begBtnGo;
    public ImageView Body;
    public int eggId;
    Context context;
    private static final int[] EGGANI_PATH = {R.drawable.nomal_blue, R.drawable.nomal_pink, R.drawable.nomal_green};

    public EggChoicDialog(Context context, Button btn){
        begBtnGo = btn;
        this.context = context;
    }

    public int callFunction() {
        final Dialog dig = new Dialog(context);

        Random random = new Random();
        random.setSeed(System.nanoTime());

        eggId = random.nextInt(3);

        dig.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dig.setContentView(R.layout.eggchoic_dialog);
        dig.setCancelable(false);

        dig.show();

        Confirm=(Button)dig.findViewById(R.id.diaBtnConfirm);
        Body = (ImageView)dig.findViewById(R.id.diaImgEgg);
        Body.setBackgroundResource(EGGANI_PATH[eggId]);

        BeginingActivity.EggMove(Body, EGGANI_PATH[eggId]);
        Confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                begBtnGo.setEnabled(true);
                begBtnGo.setBackgroundColor(0xff00ffff);
                begBtnGo.setTextColor(0xff000000);
                dig.dismiss();
            }
        });
        return eggId;
    }
}