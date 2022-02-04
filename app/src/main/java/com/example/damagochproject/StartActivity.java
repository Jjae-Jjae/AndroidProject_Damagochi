package com.example.damagochproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

public class StartActivity extends AppCompatActivity {

    ImageView background, egg;
    AnimationDrawable eggAni;

    Button strBtnNew, strBtnLoad;
    SaveClass save = new SaveClass(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        background = (ImageView)findViewById(R.id.strImgBack);
        egg = (ImageView)findViewById(R.id.strImgEgg);
        strBtnLoad = (Button)findViewById(R.id.strBtnLoad);
        strBtnNew = (Button)findViewById(R.id.strBtnNew);


        Glide.with(this).load(R.raw.forest).into(background);
        egg.setBackgroundResource(R.drawable.questionegg);
        eggAni = (AnimationDrawable)egg.getBackground();

        eggAni.start();

        strBtnNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), BeginingActivity.class);
                startActivity(intent);
                finish();
            }
        });

        strBtnLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TamaCharacter myTama;
                myTama = save.Load();
                Intent intent = new Intent(getApplicationContext(), HouseActivity.class);
                intent.putExtra(TamaCharacter.MYTAMA_KEY, myTama);
                startActivity(intent);
            }
        });
    }
    public void finish(View v){
       AlertDialog.Builder confirm = new AlertDialog.Builder(this);
       confirm.setTitle("종료");
       confirm.setMessage("게임을 종료 하시겠습니까?");
       confirm.setPositiveButton("확 인", new DialogInterface.OnClickListener() {
           @Override
           public void onClick(DialogInterface dialog, int which) {
               finish();
           }
       });
       confirm.setNegativeButton("취 소 ", null);
       confirm.show();
    }
}
