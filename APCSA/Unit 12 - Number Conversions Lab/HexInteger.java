
/**
* It convers the hex values to integers.
*
* @author : Mithul Manivannan
* Collaborators: None
* Teacher Name: Mrs. Garg
* Period: 3rd
* Due Date: 3/29/19
*/

public class HexInteger implements Comparable<HexInteger>, Countable
{


  private static final int HEX_BASE_VALUE = 16;
  
  
  /** the hexadecimal value */
  private String hexValue;
  
  /** Constructs a HexInteger from a decimal integer
   *  @param decNum a decimal integer to translate into a hex number
   */
  public HexInteger(int decNum)
  {
    reset(decNum);
  }
  
  /** Constructs a HexInteger with the given value
   *  @param hexNum a string containing a valid hexadecimal value
   */
  public HexInteger(String hexNum)
  {
    hexValue = hexNum;
  }
  
  /** Sets the hexValue to a hex value, using the toHexString() method
   *  @param newDecNum the number to convert
   */
  public void reset(int newDecNum)
  {

  hexValue = Integer.toHexString(newDecNum).toUpperCase();



  }
  
  /** Sets the hexValue to the string so it can be converted
   *  @param newHexNum is the string containing the number to be converted
   */
  public void reset(String newHexNum)
  {
  hexValue = newHexNum;
  }
  
  /** Determines whether or not 2 HexIntegers are the same
   *  @param obj the other HexInteger to compare agains
   *  @return true if this matches obj; false otherwise
   */
  @Override
  public boolean equals(Object obj)
  {
    if (obj == null || !(obj instanceof HexInteger))
      return false;
    
    HexInteger other = (HexInteger) obj;
    return this.getValue() == other.getValue();
  }
  
  /** Compare 2 HexIntegers to determine which is "smaller"
   *  based on its decimal integer value
   *  @param other the other HexInteger to compare against
   *  @return 0 if this.equals(other) is true
   *          < 0 if this < other
   *          > 0 if this > other
   */
  @Override
  public int compareTo(HexInteger other)
  {
    if (this.equals(other))
      return 0;
    
    return getValue() - other.getValue();
  }
  
  /** Provides this HexInteger information in a string
   *  @return HexInteger info as a string
   */
  @Override
  public String toString()
  {
    return hexValue;
  }
  
  
  /** Retrieve the object's integer value
   *  @return the integer value
   */
   @Override
    
  public int getValue(){
    
    int convertedNumber = 0;
    
    convertedNumber = Integer.valueOf(hexValue, HEX_BASE_VALUE);
    
    return convertedNumber;
    
  } 
}
