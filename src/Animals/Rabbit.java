package Animals;

import Animals.*;
import Field.*;
import Graph.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple model of a rabbit.
 * Rabbits age, move, breed, and die.
 * 
 * @author David J. Barnes and Michael Kolling.  Modified by David Dobervich 2007-2022
 */
public class Rabbit extends Animal{
    private boolean isDiseased;

    private double DISEASE_PROBABILITY = 0.009;

    /**
     * Create a new rabbit. A rabbit may be created with age
     * zero (a new born) or with a random age.
     * 
     * @param startWithRandomAge If true, the rabbit will have a random age.
     */
    public Rabbit(boolean startWithRandomAge)
    {
        super(startWithRandomAge);
        BREEDING_AGE = 2;

        // The age to which all rabbits can live.
        MAX_AGE = 29;

        // The likelihood of a rabbit breeding.
        BREEDING_PROBABILITY = 0.0795;
        // The maximum number of births.
        MAX_LITTER_SIZE = 5;
        age = 0;
        alive = true;
        if(startWithRandomAge) {
            age = (int)(Math.random()*MAX_AGE);
        }
        if (Math.random() < DISEASE_PROBABILITY) {
            isDiseased = true;
        } else {
            isDiseased = false;
        }

    }
    public boolean getDiseased() {
        return isDiseased;
    }
    
    /**
     * This is what the rabbit does most of the time - it runs 
     * around. Sometimes it will breed or die of old age.
     * @param updatedField The field to transfer to.
     * @param babyRabbitStorage A list to add newly born rabbits to.
     */
    public void act(Field currentField, Field updatedField, ArrayList<Animal> babyRabbitStorage)
    {
        incrementAge();
        if(alive) {
            int births = breed();
            for(int b = 0; b < births; b++) {
                Rabbit newRabbit = new Rabbit(false);
                babyRabbitStorage.add(newRabbit);
                Location loc = updatedField.randomAdjacentLocation(location);
                newRabbit.setLocation(loc);
                updatedField.put(newRabbit, loc);
            }
            Location newLocation = updatedField.freeAdjacentLocation(location);
            // Only transfer to the updated field if there was a free location
            if(newLocation != null) {
                setLocation(newLocation);
                updatedField.put(this, newLocation);
            }
            else {
                // can neither move nor stay - overcrowding - all locations taken
                alive = false;
            }
        }
    }
    
    /**
     * Increase the age.
     * This could result in the rabbit's death.
     */
    private void incrementAge()
    {
        age++;
        if(age > MAX_AGE) {
            alive = false;
        }
    }
    
    /**
     * Generate a number representing the number of births,
     * if it can breed.
     * @return The number of births (may be zero).
     */
    private int breed()
    {
        int births = 0;
        if(canBreed() && Math.random() <= BREEDING_PROBABILITY) {
            births = (int)(Math.random()*MAX_LITTER_SIZE) + 1;
        }
        return births;
    }

    /**
     * A rabbit can breed if it has reached the breeding age.
     * @return true if the rabbit can breed, false otherwise.
     */
    private boolean canBreed()
    {
        return age >= BREEDING_AGE;
    }
    
    /**
     * Check whether the rabbit is alive or not.
     * @return true if the rabbit is still alive.
     */
    public boolean isAlive()
    {
        return alive;
    }

    /**
     * Tell the rabbit that it's dead now :(
     */
    public void setEaten()
    {
        alive = false;
    }
    
    /**
     * Set the animal's location.
     * @param row The vertical coordinate of the location.
     * @param col The horizontal coordinate of the location.
     */
    public void setLocation(int row, int col)
    {
        this.location = new Location(row, col);
    }

    /**
     * Set the rabbit's location.
     * @param location The rabbit's location.
     */

    public void setLocation(Location location)
    {
        this.location = location;
    }
}
