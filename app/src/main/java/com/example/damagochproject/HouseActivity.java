package com.example.damagochproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class HouseActivity extends AppCompatActivity {

    ProgressBar HungerBar, BoaringBar, TiredBar;
    TextView NameView;
    Bundle stateBundle;
    Button btnPly, btnFed, btnCln, btnSlp;
    ImageButton saveBtn;

    ImageView CharacImg, SpeachImg, BackImg;
    AnimationDrawable chaAni;

    String name;

    Intent intent;
    TamaCharacter myTama;
    int[] state = new int[10];

    SaveClass save = new SaveClass(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house);
        intent = getIntent();
        myTama = (TamaCharacter)intent.getSerializableExtra(TamaCharacter.MYTAMA_KEY);

        btnCln = (Button)findViewById(R.id.btnClean);
        btnPly = (Button)findViewById(R.id.btnPlay);
        btnSlp = (Button)findViewById(R.id.btnSleep);
        btnFed = (Button)findViewById(R.id.btnFeed);

        saveBtn = (ImageButton)findViewById(R.id.saveImgBtn);

        CharacImg = (ImageView)findViewById(R.id.homImgEgg);
        BackImg = (ImageView)findViewById(R.id.homImgBack);
        SpeachImg = (ImageView)findViewById(R.id.homImgTalk);

        HungerBar = (ProgressBar)findViewById(R.id.hugProBar);
        BoaringBar = (ProgressBar)findViewById(R.id.TieProBar);
        TiredBar = (ProgressBar)findViewById(R.id.HpProBar);

        NameView = (TextView)findViewById(R.id.homTvName);


        Glide.with(this).load(R.raw.livingroom).into(BackImg);
        String tamaName = myTama.getName();
        if(TextUtils.isEmpty(tamaName)){
            NameSetDialrog();
        }else {
            Toast.makeText(getApplicationContext(), "게임을 불러왔습니다", Toast.LENGTH_SHORT).show();
            NameView.setText(tamaName);
        }

        stateBundle = myTama.getBundle();
        state = stateBundle.getIntArray(TamaCharacter.STATE_KEY);

        HungerBar.setProgress(state[TamaCharacter.HUNGER_INDEX]);
        BoaringBar.setProgress(state[TamaCharacter.BOARING_INDEX]);
        TiredBar.setProgress(state[TamaCharacter.ENERGY_INDEX]);

        StateCheck();

        stateValue();

        btnFed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myTama.increseHunger(15);
                myTama.increseEnergy(10);
                myTama.increseExp(10);
                StateCheck();
            }
        });

        btnSlp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myTama.increseEnergy(15);
                myTama.increseBoaring(10);
                myTama.increseExp(10);
                StateCheck();
            }
        });

        btnPly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myTama.decreseEnergy(5);
                myTama.increseBoaring(20);
                myTama.decreseHunger(4);
                myTama.increseExp(20);
                StateCheck();
            }
        });

        btnCln.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myTama.increseEnergy(15);
                myTama.increseBoaring(15);
                myTama.decreseHunger(15);
                myTama.increseExp(10);
                StateCheck();
            }
        });

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String str = save.Save(myTama);
                Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void NameSetDialrog(){
        final AlertDialog.Builder dig = new AlertDialog.Builder(HouseActivity.this);
        final EditText input = new EditText(HouseActivity.this);
        dig.setTitle("이름을 지어주세요!");
        dig.setView(input);
        dig.setPositiveButton("확 인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                myTama.setName(input.getText().toString());
                name = myTama.getName();
                NameView.setText(name);
                dialog.dismiss();
            }
        });
        dig.show();
    }

    void StateCheck() {
        CharacImg.setBackgroundResource(myTama.getDrawable());
        chaAni = (AnimationDrawable)CharacImg.getBackground();
        chaAni.start();


        HungerBar.setProgress(state[TamaCharacter.HUNGER_INDEX]);
        BoaringBar.setProgress(state[TamaCharacter.BOARING_INDEX]);
        TiredBar.setProgress(state[TamaCharacter.ENERGY_INDEX]);
    }


    void stateValue(){
        final Timer tim = new Timer();
        TimerTask tt = new TimerTask() {
            @Override
            public void run() {
                StateCheck();

                myTama.decreseHunger (makeRandomVale(0,10));
                myTama.decreseEnergy (makeRandomVale(1,10));
                myTama.decreseBoaring (makeRandomVale(2,10));
            }
        };
        tim.schedule(tt,0, 2500);

        /*final int[] UTIL = {R.drawable.poop0, R.drawable.fill, R.drawable.controler0};

        ImageView StateImg = (ImageView)findViewById(R.id.homImgUtil);
        if (state[TamaCharacter.HUNGER_INDEX] < 90){
            StateImg.setImageResource(UTIL[0]);
        }
        if (state[TamaCharacter.BOARING_INDEX] < 90){
            StateImg.setImageResource(UTIL[0]);
        }

        if (state[TamaCharacter.ENERGY_INDEX] < 90){
            StateImg.setImageResource(UTIL[0]);
        }*/
    }


    int makeRandomVale(int i,int max){
        Random random = new Random();
        random.setSeed(System.nanoTime() + i);
        return random.nextInt(max);
    }

}
