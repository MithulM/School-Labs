/** 
 * The Pokemon class contains all the information on the attributes of each pokemon and 
 * is used by the player class to assign Pokemon objects to a player.
 * @author: Rishi Villa
 * Collaborators : None
 * Teacher Name : Mrs. Ishman 
 * Period: 3
 * Due date : 05/07/2020
 * 
 */

import java.util.Map;
import java.util.TreeMap;

public class Pokemon implements Comparable<Pokemon>
{
  private String name;
  private int attack;
  private int defense;
  private int speed;
  private int hp;
  private static Map<String, Integer> pokemonPop = new TreeMap<>();
  private static String pref;
  
  /**
   * The constructor initializes all the instance variables
   * @param name of the Pokemon 
   * @param attack stats of the pokemon 
   * @param defense stats of the pokemon 
   * @param speed 
   */
  public Pokemon(String name, int attack, int defense, int speed, int hp)
  {
    this.name = name;
    this.attack = attack;
    this.defense = defense;
    this.speed = speed;
    this.hp = hp;
    pokemonPop.put(name, (int)(Math.random() * 11 + 10));
  }
  
  /**
   * retrieves the name of the Pokemon 
   * @return the name 
   */
  public String getName()
  {
    return name;
  }
  
  /**
   * retrieves the attack stats of the Pokemon
   * @return attack of pokemon
   */
  public int getAttack()
  {
    return attack;
  }

  public static void repopulate(String name){
    pokemonPop.put(name, pokemonPop.get(name) + (int)(Math.random() * 5 + 1));
  }
  /**
   * retrieves the defense of the pokemon
   * @return defense stats of Pokemon
   */
  public int getDefense()
  {
    return defense;
  }
  
  /**
   * retrieves the speed of the pokemon
   * @return speed stats of Pokemon
  */
  public int getSpeed()
  {
    return speed;
  }
 
  /**
   * retrieves the HP of the pokemon
   * @return HP of Pokemon
   */ 
  public int getHp()
  {
    return hp;
  }

  /**
   *
   * @param name
   * @return
   */
  public static int getPopulation(String name)
  {
    return pokemonPop.get(name);
  }

  /**
   *
   * @param pokemonName
   * @param numPokemon
   */
  public static void addPopulation(String pokemonName, int numPokemon)
  {
    if(pokemonPop.containsKey(pokemonName))
    {
       pokemonPop.put(pokemonName, pokemonPop.get(pokemonName) + numPokemon);
    }
    else
    {
      pokemonPop.put(pokemonName, numPokemon);
    }
  }

  /**
   *
   * @param pokemonName
   * @param numPokemon
   * @return
   */
  public static boolean removePopulation(String pokemonName, int numPokemon)
  {
    if(pokemonPop.containsKey(pokemonName))
    {
      if(pokemonPop.get(pokemonName) - numPokemon >= 0)
      {
        pokemonPop.put(pokemonName, pokemonPop.get(pokemonName) - numPokemon);
      }
      else
      {
        pokemonPop.put(pokemonName, 0);
      }
      return true;
    }
    return false;
  }

  public int formula(String prefs)
  {
    switch(pref)
    {
      case("attack"):
        return 5 * getAttack() + getDefense() + getSpeed() + getHp();
      case("defense"):
        return getAttack() + 5 * getDefense() + getSpeed() + getHp();
      case("speed"):
        return getAttack() + getDefense() + 5 * getSpeed() + getHp();
      default:
        return getAttack() + getDefense() + getSpeed() + 5 * getHp();
    }
  }
  /**
   *
   * @param pok
   * @return
   */
  @Override
  public int compareTo(Pokemon pok)
  {
    if ("name".equals(pref))
      return this.getName().compareTo(pok.getName());
    return this.formula(pref) - pok.formula(pref);
  }
  
  /**
   * gets preference string value 
   * @return pref value
   */
  public String getPref()
  {
    return pref;
  }

  /**
   *
   * @param key
   */
  public static void setPref(String key)
  {
    pref = key;
  }
  
  @Override
  public boolean equals(Object pok)
  {
    return ((Pokemon)pok).getHp() == this.getHp() &&
    ((Pokemon)pok).getAttack() == this.getAttack() &&
    ((Pokemon)pok).getDefense() == this.getDefense() &&
    ((Pokemon)pok).getSpeed() == this.getSpeed();
  }
}