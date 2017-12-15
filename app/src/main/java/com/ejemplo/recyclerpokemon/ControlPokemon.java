package com.ejemplo.recyclerpokemon;

/**
 * Created by SERGIO on 14/12/17.
 */

public class ControlPokemon {

    private static ControlPokemon control = new ControlPokemon();

    public static ControlPokemon getInstance() {
        return control;
    }

    public Pokemon currentPokemon;
}
