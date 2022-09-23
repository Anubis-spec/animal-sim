package Animals;

import Field.Field;
import Field.Location;

import java.util.ArrayList;

public class Animal {
    protected int BREEDING_AGE;
    protected int MAX_AGE;
    protected double BREEDING_PROBABILITY;
    protected int MAX_LITTER_SIZE;
    protected int age;
    protected boolean alive;

    protected Location location;

    protected int RABBIT_FOOD_VALUE;

    protected int GOPHER_FOOD_VALUE;


    public Animal(boolean startWithRandomAge) {
        age = 0;
        alive = true;
        if(startWithRandomAge) {
            age = (int)(Math.random()*MAX_AGE);
        }
    }

    public boolean isAlive() {
        return alive;
    }
    public void act(Field CurrentField, Field UpdatedField, ArrayList<Animal> newAnimal) {

    }
}
