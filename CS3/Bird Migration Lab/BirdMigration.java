/**
 * The BirdMigration class has birds in a V formation in a linkedList
 * The leader is held in the middle.
 * It can add new birds, remove weakest birds, change leaders,
 * and split the group into half.
 *
 * @author Mithul Manivannan
 * Collaborators: None
 * Teacher Name: Mrs. Ishman
 * Period: 2
 * Due Date: 4/16/2020
 */

import java.util.*;

public class BirdMigration
{
  /* a linked list of migrating birds in a "V" formation */
  private LinkedList<Bird> echelon;
  
  /* boolean to track if the leader should fall back to left or right end */
  private boolean fallBackLeft;

  private boolean addedLeft;
  
  /* track the leader */
  private Bird leader;

  /**
   * Constructs an echelon of birds
   */
  public BirdMigration()
  {
    echelon = new LinkedList<>();
    fallBackLeft = true;
    leader = null;
    addedLeft = true;
  }
  
  /**
   * Retrieves the number of birds in the flock
   * @return bird count 
   */
  public int size()
  {
    return echelon.size();
  }
  
  /**
   * Adds the given bird to alternating ends of the formation
   * @param newBird the bird we're adding  
   */
  public void addBird(Bird newBird)
  {
    if(leader == null) {
        leader = newBird;
        leader.makeLeader();
        echelon.add(leader);
        return;
    }
    else if (addedLeft)
        echelon.addFirst(newBird);
    else
        echelon.addLast(newBird);
    addedLeft = !addedLeft;
  }
  
  /**
   * Moves the current leader to alternating ends of the formation
   * @return the new leader of the echelon; null if empty
   */
  public Bird leaderFallBack()
  {
    if(echelon.isEmpty())
        return null;
    Bird curr = null;
    for (ListIterator<Bird> iter = echelon.listIterator(); iter.hasNext();){
        curr = iter.next();
        if(curr.isLeader()){
            iter.remove();
            if(fallBackLeft){
                leader = iter.previous();
                iter.next();
            }
            else
                leader = iter.next();
            leader.makeLeader();
            break;
        }
    }
    curr.makeFollower();
    if(fallBackLeft)
        echelon.addFirst(curr);
    else
        echelon.addLast(curr);
    fallBackLeft = !fallBackLeft;
    return leader;
  }
  
  /**
   * Remove the weakest bird in the flock
   * @return weakest bird that was removed; null if empty
   */
    public Bird removeWeakestBird(){
    if(echelon.isEmpty()) return null;
    Bird weakest = new Bird("test", Integer.MAX_VALUE);
    Bird curr1;
    for(ListIterator<Bird> iter = echelon.listIterator();iter.hasNext();){
        curr1 = iter.next();
        if(curr1.getStrengthLevel() < weakest.getStrengthLevel())
            weakest = curr1;
    }
    if(!weakest.isLeader()){
        echelon.remove(weakest);
        leader.makeFollower();
        ListIterator<Bird> iterator = echelon.listIterator();
        for(int i = 0; i <= echelon.size() / 2; i++)
            leader = iterator.next();
        leader.makeLeader();
    }
    else{
        Bird curr2;
        for(ListIterator<Bird> iterator = echelon.listIterator();iterator.hasNext();){
            curr2 = iterator.next();
            if(curr2.isLeader()) {
                iterator.previous();
                leader = iterator.previous();
                leader.makeLeader();
                iterator.next();iterator.next();
                iterator.remove();
            }
        }
        leader.makeLeader();
        weakest.makeFollower();
    }
    return weakest;
    }
  
  /** 
   * Splits the flock in half by alternating removing from the ends.
   * Returns a new flock comprised of birds removed. 
   * If odd size, keeps the larger amount
   * @return flock of removed birds
   */
  public BirdMigration splitFormation()
  {
    BirdMigration migration = new BirdMigration();
    int change = 0;
    int size = echelon.size();
    for(int i = 0; i < size / 2; i++){
        if(change % 2 == 0)
            migration.addBird(echelon.removeFirst());
        else
            migration.addBird(echelon.remove(echelon.size() - 1));
        change++;
    }
    return migration;
  }
  
  /**
   * Retrieves list of birds from left end to right end 
   * @return birds from left to right of "V"
   */
  @Override
  public String toString()
  {
    return echelon.toString();
  }
}