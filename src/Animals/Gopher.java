package Animals;

import Field.Field;
import Field.Location;

import java.util.List;

public class Gopher extends Animal{

    private int age;

    private boolean alive;

    private Location location;

    public Gopher(boolean startWithRandomAge)
    {
        super(startWithRandomAge);
        BREEDING_AGE = 2;

        MAX_AGE = 35;

        BREEDING_PROBABILITY = 0.089;

        MAX_LITTER_SIZE = 13;

        age = 0;

        alive = true;
        if(startWithRandomAge) {
            age = (int)(Math.random()*MAX_AGE);
        }
    }

    /**
     * This is what the rabbit does most of the time - it runs
     * around. Sometimes it will breed or die of old age.
     * @param updatedField The field to transfer to.
     * @param babyGopherStorage A list to add newly born rabbits to.
     */

    public void run(Field updatedField, List<Gopher> babyGopherStorage)
    {
        incrementAge();
        if(alive) {
            int births = breed();
            for(int b = 0; b < births; b++) {
                Gopher newGopher = new Gopher(false);
                babyGopherStorage.add(newGopher);
                Location loc = updatedField.randomAdjacentLocation(location);
                newGopher.setLocation(loc);
                updatedField.put(newGopher, loc);
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

    private void incrementAge()
    {
        age++;
        if(age > MAX_AGE) {
            alive = false;
        }
    }
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