/**
 * The Bird class represents a bird that is part of a flock.
 * A bird has a type, strength level, and whether or not it is
 * a leader. The class provides methods to access a bird's 
 * information and mutator to change its leader status.
 * @author Plano ISD CS Teachers
 * Due Date: 04-16-2020
 */

public class Bird
{
  private String species;
  private int strength;
  private boolean isLeader;
  
  /**
   * Constructs a Bird with the given type and strength level. A Bird
   * is not a leader at construction.
   * 
   * @param type the species type
   * @param strengthLevel the level of strength, where 0 is the least strong
   */
  public Bird(String type, int strengthLevel)
  {
    species = type;
    strength = strengthLevel;
    isLeader = false;
  }
  
  /** 
   * Retrieves the species of this bird
   * 
   * @return the species type
   */
  public String getSpecies()
  {
    return species;
  }
  
  /** 
   * Retrieves the strength level of this bird
   * 
   * @return the strength level
   */
  public int getStrengthLevel()
  {
    return strength;
  }
  
  /** 
   * Retrieves whether or not this bird is a leader
   * 
   * @return true if a leader; false otherwise
   */
  public boolean isLeader()
  {
    return isLeader;
  }
  
  /** 
   * Makes this bird a leader.
    */
  public void makeLeader()
  {
    isLeader = true;
  }
  
  /** 
   * Makes this bird a follower.
    */
  public void makeFollower()
  {
    isLeader = false;
  }

 /** 
   * Provides this bird's information as a string
   * 
   * @return formatted string with bird data
   */
  @Override
  public String toString()
  {
    String leaderState = isLeader() ? "LEADER" : "follower";
    return String.format("%s %d %s", getSpecies(), getStrengthLevel(), leaderState);
  }
  
  /**
   * Determines whether or not two birds are the same 
   * 
   * @return true if birds match; false otherwise
   */
  @Override
  public boolean equals(Object other)
  {
    if (other == null || ! (other instanceof Bird))
      return false;
      
    Bird otherBird = (Bird) other;
    return species.equals(otherBird.species) && strength == otherBird.strength
      && isLeader == otherBird.isLeader;
  }
}