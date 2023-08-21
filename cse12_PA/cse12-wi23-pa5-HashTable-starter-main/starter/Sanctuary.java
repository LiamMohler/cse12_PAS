/*
 * Name: Liam Mohler
 * Email: lmohler@ucsd.edu
 * PID: A12345678
 * Sources used: None
 * 
 * This file is used to create a sanctuary object.
 */
import java.util.HashMap;


/**
 * This class creates a sanctuary with animals and species.
 * 
 * Instance variables:
 * maxAnimals - max animals
 * maxSpecies - max species.
 */
public class Sanctuary {

    /** Instance variables */
    HashMap<String, Integer> sanctuary;
    private final int maxAnimals;
    private final int maxSpecies;


    /**
     * The course constructor. Will make a course with the following...
     * 
     * @param maxAnimals - the max animals
     * @param maxSpecies - the max species
     */
    public Sanctuary(int maxAnimals, int maxSpecies){
        if(maxAnimals <=0 || maxSpecies<=0){
            throw new IllegalArgumentException();
        }
        if(maxSpecies > maxAnimals){
            throw new IllegalArgumentException();
        }
        //set instance vars.
        sanctuary = new HashMap<>();
        this.maxAnimals = maxAnimals;
        this.maxSpecies = maxSpecies;
    }

    /**
     * The ammount of animals in a spcies.
     * 
     * @param maxAnimals - the max animals
     * @param maxSpecies - the max species
     * @return - the # of animals in a species
     */
    public int countForSpecies(String species){
        if(species == null)
            throw new IllegalArgumentException();
        //check if has.
        if(sanctuary.containsKey(species))
            return sanctuary.get(species);
        return 0;
    }
    /**
     * The ammount of animals in all species.
     * 
     * @return - the total # of animals
     */
    public int getTotalAnimals(){
        int total = 0;
        //loop through
        for(Integer i: sanctuary.values()){
            total += i;
        }
        return total;
    }
    /**
     * The total # of species.
     * 
     * @return - the total species
     */
    public int getTotalSpecies(){
        return sanctuary.size();
    }
    /**
     * The max animals.
     * 
     * @return - the max animals
     */
    public int getMaxAnimals(){
        return maxAnimals;
    }
    /**
     * The max animals.
     * 
     * @return - the max animals
     */
    public int getMaxSpecies(){
        return maxSpecies;
    }
    /**
     * This function will rescue a species
     * 
     * @param species - species to save
     * @param num - the num to save
     * @return - the # that couldnt be saved
     */
    public int rescue(String species, int num){
        //check if in bounds
        if(species == null || num <= 0){
            throw new IllegalArgumentException();
        }
        //if not enough species slots return
        if(getTotalSpecies() >= maxSpecies){
            if(!sanctuary.containsKey(species)){
                return num;
            }
        }
        //add to the slot while can
        while(getTotalAnimals() < maxAnimals && num > 0){
            if(!sanctuary.containsKey(species)){
                sanctuary.putIfAbsent(species, 0);
            }
            sanctuary.put(species, sanctuary.get(species) + 1);
            num--;
        }
        return num;
    }
    /**
     * This function will release the # of a species
     * 
     * @param species - species to relies
     * @param num - the num to release
     */
    public void release(String species, int num){
        //check to see errors
        if(species == null || !sanctuary.containsKey(species)){
            throw new IllegalArgumentException();
        }
        if(num <=0 || num > countForSpecies(species)){
            throw new IllegalArgumentException();
        }
        //subtract
        sanctuary.put(species, countForSpecies(species) - num);
        if(sanctuary.get(species) == 0){
            sanctuary.remove(species);
        }
    }
}
