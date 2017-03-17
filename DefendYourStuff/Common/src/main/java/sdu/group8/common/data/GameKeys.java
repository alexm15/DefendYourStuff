/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdu.group8.common.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author Martin
 */
public class GameKeys {

    // use this for keycode reference: https://libgdx.badlogicgames.com/nightlies/docs/api/constant-values.html
    public final int UP = 19; // Up arrow
    public final int DOWN = 20; // down arrow
    public final int LEFT = 21; // left arrow
    public final int RIGHT = 22; // right arrow

    public final int SPACE = 62; // space
    public final int ESCAPE = 131; // escape
    public final int ENTER = 66; // Enter

    public final int A = 29; // A
    public final int W = 51; // W
    public final int S = 47; // S
    public final int D = 32; // D
    public final int E = 33; // E

    public final int NUM_1 = 8; // Numpad 1
    public final int NUM_2 = 9; // Numpad 2
    public final int NUM_3 = 10; // Numpad 3
    public final int NUM_4 = 11; // Numpad 4

    private Map<Integer, Boolean> currentKeyStates = new ConcurrentHashMap<>();
    private Map<Integer, Boolean> previousKeyStates = new ConcurrentHashMap<>();

    public GameKeys() {
        currentKeyStates.put(UP, false);
        currentKeyStates.put(DOWN, false);
        currentKeyStates.put(LEFT, false);
        currentKeyStates.put(RIGHT, false);

        currentKeyStates.put(SPACE, false);
        currentKeyStates.put(ESCAPE, false);
        currentKeyStates.put(ENTER, false);

        currentKeyStates.put(A, false);
        currentKeyStates.put(S, false);
        currentKeyStates.put(W, false);
        currentKeyStates.put(D, false);
        currentKeyStates.put(E, false);

        currentKeyStates.put(NUM_1, false);
        currentKeyStates.put(NUM_2, false);
        currentKeyStates.put(NUM_3, false);
        currentKeyStates.put(NUM_4, false);

        update();
    }

    public void update() {
        previousKeyStates.clear();
        previousKeyStates.putAll(currentKeyStates);
    }

    public boolean isKeyDown(int key) {
        return currentKeyStates.get(key);
    }

    public boolean isKeyPressed(int key) {
        return currentKeyStates.get(key) && !previousKeyStates.get(key);
    }

    public void setKeyState(int key, boolean state) {
        currentKeyStates.put(key, state);
    }
}
