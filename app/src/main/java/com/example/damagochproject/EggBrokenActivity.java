package com.example.damagochproject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

public class EggBrokenActivity extends AppCompatActivity {

    ImageView charackter, background;
    Button btnCnt;
    AnimationDrawable anidraw;
    TamaCharacter myTama;
    final Handler mHandler = new Handler();

    int eggStateCnt = 0;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eggbroken);

        Toast.makeText(getApplicationContext(), "알을 흔들어서 깨워 보자!", Toast.LENGTH_SHORT).show();

        intent = getIntent();
        btnCnt = (Button)findViewById(R.id.btnCnt);
        background = (ImageView)findViewById(R.id.mainImgBack);
        charackter = (ImageView)findViewById(R.id.mainImgEgg);
        myTama = (TamaCharacter)intent.getSerializableExtra(TamaCharacter.MYTAMA_KEY);

        Glide.with(this).load(R.raw.room).into(background);
        charackter.setBackgroundResource(myTama.getDrawable());
        anidraw = (AnimationDrawable) charackter.getBackground();


        btnCnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (anidraw.isRunning()) {
                    anidraw.stop();
                }
                anidraw.start();
                eggStateCnt++;
                ChkEggState();
            }
        });

    }

    private void ChkEggState(){
        switch (eggStateCnt) {
            case 2 :
                Toast.makeText(getApplicationContext(), "알이 안에서 조금씩 반응이 오기 시작했다..?", Toast.LENGTH_SHORT).show();
                break;
            case 8 :
                Toast.makeText(getApplicationContext(), "알이 안에서 흔들리기 시작했다!", Toast.LENGTH_SHORT).show();
                break;
            case 10 :
                btnCnt.setEnabled(false);
                Toast.makeText(getApplicationContext(), "엇...?", Toast.LENGTH_SHORT).show();
                eggBroken();
        }
    }

    public void eggBroken(){
        LvUpAndStartAni();
        mHandler.postDelayed(new Runnable()  {
            public void run() {
                AnimationCollet.FadeOutAni(charackter, EggBrokenActivity.this);
            }
        }, 2000);

        mHandler.postDelayed(new Runnable()  {
            public void run() {
                LvUpAndStartAni();

                AnimationCollet.FadeInAni(charackter, EggBrokenActivity.this);
                Toast.makeText(getApplicationContext(), "알이 부화했다!", Toast.LENGTH_SHORT).show();
            }
        }, 3000);
        goToHome();
    }

    void LvUpAndStartAni(){
        myTama.increseExp(100);
        charackter.setBackgroundResource(myTama.getDrawable());
        anidraw = (AnimationDrawable) charackter.getBackground();
        anidraw.start();
    }

    void goToHome(){
        mHandler.postDelayed(new Runnable()  {
            public void run() {
                intent = new Intent(getApplicationContext(), HouseActivity.class);
                intent.putExtra(TamaCharacter.MYTAMA_KEY, myTama);
                startActivity(intent);
                finish();
            }
        }, 6000);
    }
}
