package com.example.damagochproject;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

public class BeginingActivity extends AppCompatActivity {

    Button btnGo;
    ImageButton[] begImgEgg = new ImageButton[9];
    int[] imgId = {
            R.id.BegImgEgg0, R.id.BegImgEgg1, R.id.BegImgEgg2,
            R.id.BegImgEgg3, R.id.BegImgEgg4, R.id.BegImgEgg5,
            R.id.BegImgEgg6, R.id.BegImgEgg7, R.id.BegImgEgg8
    };
    int i;
    int eggId;
    TamaCharacter myTama;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_begining);

        btnGo = (Button)findViewById(R.id.begBtnGo);
        for (i = 0; i < imgId.length; i++){
            final int index = i;
            begImgEgg[index] = (ImageButton) findViewById(imgId[index]);
            AnimationCollet.FadeInAni(begImgEgg[index], BeginingActivity.this);
            begImgEgg[index].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for (i=0; i<begImgEgg.length;i++){
                        int num = i;
                        begImgEgg[i].setClickable(false);
                    }
                    AnimationCollet.FlipAni(begImgEgg[index], BeginingActivity.this);
                    DialogLogic(2000, begImgEgg[index]);
                }
            });
        }

        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), EggBrokenActivity.class);
                intent.putExtra(TamaCharacter.MYTAMA_KEY, myTama);
                startActivity(intent);
                finish();
            }
        });
    }

    void DialogLogic(int sec, final ImageView img){
        final Handler mHandler = new Handler();
        mHandler.postDelayed(new Runnable()  {
            public void run() {
                EggChoicDialog dig = new EggChoicDialog(BeginingActivity.this, btnGo);
                eggId = dig.callFunction();

                TamagoFactory factory = new TamagoCharacterFactory();
                myTama = factory.createTama(eggId);
                int[] state = {0,100,100,100,0,0,0,0,0,eggId};
                myTama.setState(state);

                AnimationCollet.FadeInAni(img, BeginingActivity.this);
                img.setImageDrawable(null);
                EggMove(img, myTama.getDrawable());

            }
        }, sec);
    }

    public static void EggMove(ImageView img, int draw){
        img.setBackgroundResource(draw);

        final AnimationDrawable eggAni = (AnimationDrawable)img.getBackground();

        final Timer tim = new Timer();
        TimerTask tt = new TimerTask() {
            @Override
            public void run() {
                eggAni.start();
            }
        };
        TimerTask td = new TimerTask() {
            @Override
            public void run() {
                eggAni.stop();
            }
        };
        tim.schedule(tt,0, 1000);
        tim.schedule(td, 400, 1000);
    }
}
