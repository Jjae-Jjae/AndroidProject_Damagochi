package com.example.damagochproject;

import android.os.Bundle;

import java.io.Serializable;

public abstract class TamaCharacter implements Serializable {

    protected static final int[][] CHAANI_PATH = {{R.drawable.nomal_blue,R.drawable.broken_blue,R.drawable.nomalslime_blue,R.drawable.whaleslime_blue,R.drawable.whale_blue,R.drawable.whale_dark},
                                                {R.drawable.nomal_pink,R.drawable.broken_pink,R.drawable.nomalslime_pink,R.drawable.lionslime_pink,R.drawable.lion_pink,R.drawable.lion_dark},
                                                {R.drawable.nomal_green,R.drawable.broken_green,R.drawable.nomalslime_green,R.drawable.dragonslime_green,R.drawable.dragon_green,R.drawable.dragon_dark}};
    public static final int WHALE_ID = 0, LION_ID = 1, DRAGON_ID = 2;
    public static final String NAME_KEY = "name", STATE_KEY = "stae", MYTAMA_KEY = "mytama";
    public static final int LV_INDEX = 0, HUNGER_INDEX = 1, BOARING_INDEX = 2, ENERGY_INDEX = 3, EXP_INDEX = 4, STRESS_INDEX = 5, DEPRAVITY_INDEX = 6, POOP_INDEX = 7,HYGNE_INDEX = 8, CHARACTERID_INDEX = 9;

    private String name;
    protected int[] state = new int[10];

    int i;

    abstract public int getDrawable();

    void setState(int[] state){
        this.state = state;
    }

    void setBundle(Bundle bundle){
        name = bundle.getString(NAME_KEY);
        state = bundle.getIntArray(STATE_KEY);
    }
    public void setName(String name){
        this.name = name;
    }

    Bundle getBundle(){
        Bundle callbundle = new Bundle();
        callbundle.putString(NAME_KEY, name);
        callbundle.putIntArray(STATE_KEY, state);
        return callbundle;
    }


    public void increseExp(int increValue) {
        state[EXP_INDEX] += increValue;
        if (state[EXP_INDEX] >= 100){ lvUp(); state[EXP_INDEX] = 0;}
    }
    public void increseHunger(int increValue) {
        state[HUNGER_INDEX] += increValue;
        if (state[HUNGER_INDEX] > 100){state[HUNGER_INDEX] = 100;}
    }
    public void increseBoaring(int increValue) {
        state[BOARING_INDEX] += increValue;
        if (state[BOARING_INDEX] > 100){state[BOARING_INDEX] = 100;}
    }
    public void increseEnergy(int increValue) {
        state[ENERGY_INDEX] += increValue;
        if (state[ENERGY_INDEX] > 100){state[ENERGY_INDEX] = 100;}
    }
    public void increseStress(int increValue) {
        state[STRESS_INDEX] += increValue;
        if (state[STRESS_INDEX] > 100){state[STRESS_INDEX] = 100;}
    }
    public void increseDeparty(int increValue) {
        state[DEPRAVITY_INDEX] += increValue;
        if (state[DEPRAVITY_INDEX] > 100){state[DEPRAVITY_INDEX] = 100;}
    }
    public void incresePoop(int increValue) {
        state[POOP_INDEX] += increValue;
        if (state[POOP_INDEX] > 100){state[POOP_INDEX] = 100;}
    }
    public void increseHygene(int increValue) {
        state[HYGNE_INDEX] += increValue;
        if (state[HYGNE_INDEX] > 100){state[HYGNE_INDEX] = 100;}
    }

    public void decreseHunger(int increValue) {
        state[HUNGER_INDEX] -= increValue;
        if (state[HUNGER_INDEX] < 0){state[HUNGER_INDEX] = 0;}
    }
    public void decreseBoaring(int increValue) {
        state[BOARING_INDEX] -= increValue;
        if (state[BOARING_INDEX] < 0){state[BOARING_INDEX] = 0;}
    }
    public void decreseEnergy(int increValue) {
        state[ENERGY_INDEX] -= increValue;
        if (state[ENERGY_INDEX] < 0){state[ENERGY_INDEX] = 0;}
    }
    public void decreseStress(int increValue) {
        state[STRESS_INDEX] -= increValue;
        if (state[STRESS_INDEX] < 0){state[STRESS_INDEX] = 0;}
    }
    public void decreseDeparty(int increValue) {
        state[DEPRAVITY_INDEX] -= increValue;
        if (state[DEPRAVITY_INDEX] < 0){state[DEPRAVITY_INDEX] = 0;}
    }
    public void decresePoop(int increValue) {
        state[POOP_INDEX] -= increValue;
        if (state[POOP_INDEX] < 0){state[POOP_INDEX] = 0;}
    }
    public void decreseHygene(int increValue) {
        state[HYGNE_INDEX] -= increValue;
        if (state[HYGNE_INDEX] < 0){state[HYGNE_INDEX] = 0;}
    }

    void lvUp(){
        if (state[LV_INDEX] <= 3){
            state[LV_INDEX]++;
        }else {
            state[LV_INDEX] = 4;
        }
    }

    public String getName(){return name;}
    public int getLv(){return state[LV_INDEX];}




}
