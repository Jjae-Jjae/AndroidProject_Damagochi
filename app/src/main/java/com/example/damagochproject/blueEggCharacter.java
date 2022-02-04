package com.example.damagochproject;

public class blueEggCharacter extends TamaCharacter {

    private final int[] CHARACTER_DRAWABLE = new int[6];
    private final int ID = WHALE_ID;
    int i;
    public blueEggCharacter(){
        state[CHARACTERID_INDEX] = ID;
        for (i=0; i<CHARACTER_DRAWABLE.length;i++) {
            int index = i;
            CHARACTER_DRAWABLE[index] = CHAANI_PATH[ID][index];
        }
    }
    @Override
    public int getDrawable(){
        return CHARACTER_DRAWABLE[getLv()];
    }

}
