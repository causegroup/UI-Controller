package Model;

import java.util.Random;

public class Yut{
    private int value;
    Random generator = new Random();
    public int throwYut(){
        value = generator.nextInt(6) + 1;
        if(value == 6)
            value = -1;
        return value;
    }

    public int getValue() {
        return value;
    }
}