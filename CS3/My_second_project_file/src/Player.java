/** 
 * The Player class is responsible for maintaining the list 
 * of Pokemon objects that are assigned to each player.
 * @author: Rishi Villa
 * Collaborators : None
 * Teacher Name : Mrs. Ishman 
 * Period: 3
 * Due date : 05/018/2020
 * 
 */
import java.util.*;
import java.io.*;

public class Player
{
  private String playerName;
  private PriorityQueue<Pokemon>list;
  
/**
 * The constructor initializes all the instance variables
 *@param name of the Player
 */
  public Player(String name)
  {
    playerName = name;
    list = new PriorityQueue<>();
  }
  
  /**
   * The addPokemon method adds a pokmeon 
   * object to the player class
   * @param The pokemon that is to be added
   * @return if adding Pokemon was successfull
   */
  public boolean addPokemon(Pokemon pok)
  {
    if(canHavePokemon(pok.getName()))
    {
        list.add(pok);
        return true;
    }
    return false;
  }
  
  /**
   * The removePokemon method removes a pokemon 
   * object from a player class
   * @param the Pokemon object that is to be removed
   */
  public void removePokemon(Pokemon pok)
  {
      list.remove(pok);
  }
  
  /**
   * The addPopulation method adds a certain amount 
   * of pokemon to a species at random 
   * @param name of pokemon species
   * @param number of pokemon to addPopulation
   */
  public static void addPopulation(String name, int numPokemon)
  {
      Pokemon.addPopulation(name, numPokemon);
  }
  
  /**
   * this method removes a certain number of pokemon of a certain
   * species at random
   * @param name of pokemon species
   * @param number of pokemon to be removed.
   */
  public static void removePopulation(String name, int numPokemon)
  {
      Pokemon.removePopulation(name, numPokemon);
  }
  
  public void reset(String pref)
  {
    Pokemon.setPref(pref);
    PriorityQueue<Pokemon> rand = new PriorityQueue<>();
    while(!list.isEmpty())
    {
      rand.add(list.poll());
    }
    list = rand;
  }

    /**
     * The toString method prints the desired output.
     * @return the String value
     */
    public String toString()
    {
        StringBuilder str = new StringBuilder();
        PriorityQueue<Pokemon> pref = new PriorityQueue<>(list);
        str.append("Player ").append(playerName).append("has pokemons \n");
        int size = pref.size();
        for(int i = 0; i < size; i++)
        {
            str.append(i + ") " + pref.poll() + "\n");
        }
        return str.toString();
    }

    // Private methods
    /**
     * The method checks if a player has reached
     * his limit on pokemons.
     * @param name of Player
     * @return if pokemon has getPopulation
     */
    private static boolean canHavePokemon(String name)
    {
        return (Pokemon.getPopulation(name)) > 0;
    }
}