package com.example.damagochproject;

public class TamagoCharacterFactory extends TamagoFactory {

    @Override
    public TamaCharacter createTama(int id){
        switch (id){
            case 0: return new blueEggCharacter();
            case 1: return new pinkEggCharacter();
            case 2: return new greenEggCharacter();
        }
        return null;
    }
}
