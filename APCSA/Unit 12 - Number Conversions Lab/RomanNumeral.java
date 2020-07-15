
/**
* This class implements the Countalbe and Comparable and it converts
* the numbers to roman numeral
*
* @author : Mithul Manivannan
* Collaborators: None
* Teacher Name: Mrs. Garg
* Period: 3rd
* Due Date: 3/29/19
*/
public class RomanNumeral implements Comparable<RomanNumeral>, Countable
{
  /** array of possible Roman Numeral letters */
  private static final String[] ROMAN_LETTERS =
    {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
  
  /** array of values for corresponding Roman Numeral letters */
  private static final int[] ROMAN_VALUES =
    {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};

  /** the value as a roman numeral */
  private String romanStr;
  
  /** the roman numeral as an integer */
  private int romanInt;
  
  /** Constructs a RomanNumeral with the given roman numeral
   *  @param romanNum a string containing a valid roman numeral
   */
  public RomanNumeral(String romanNum)
  {
    reset(romanNum);
  }
  
  /** Constructs a roman numeral with the given decimal integer
   *  @param num an integer from which to create a roman numeral
   */
  public RomanNumeral(int num)
  {
    reset(num);
  }
  
  
  /** Converts the given string to the number by transversing the array
   *@param newRomanNum the string to be converted
   *
   */
  public void reset(String newRomanNum)
  {
    
    
    int numValue = 0;

    for(int i = 0; i < newRomanNum.length(); i++){
      for(int k = 0; k < ROMAN_LETTERS.length; k++){
        
        if(i + 1 < newRomanNum.length() &&
          ROMAN_LETTERS[k].equals(newRomanNum.substring(i, i + 2))){
        
          numValue += ROMAN_VALUES[k];
          i++;
          break;
        }
        else if(ROMAN_LETTERS[k].equals(newRomanNum.substring(i, i + 1))){
          numValue += ROMAN_VALUES[k];
          break;
        }
      }
    }
    
    
    romanInt = numValue;
    romanStr = newRomanNum;

  }


  /** Converts the number to a roman numeral by transversing the ROMAN_LETTERS array
   *@param newNum the number to convert
   */
  public void reset(int newNum)
  {
  
  romanInt = newNum;
  String sum = "";
  while(newNum > 0){
    
    for(int j = 0; j < ROMAN_LETTERS.length; j++){
      if(newNum >= ROMAN_VALUES[j]){
        sum += "" + ROMAN_LETTERS[j];
        newNum -= ROMAN_VALUES[j];
        break;
      }
    }
  }


  romanStr = sum;

  }
  
  
  /** Determines whether or not 2 roman numerals are the same
   *  @param obj the other roman numeral to compare agains
   *  @return true if this matches obj; false otherwise
   */
  @Override
  public boolean equals(Object obj)
  {
    if (obj == null || !(obj instanceof RomanNumeral))
      return false;
    
    RomanNumeral other = (RomanNumeral) obj;
    return this.getValue() == other.getValue();
  }
  
  /** Compare 2 RomanNumeral to determine which is "smaller"
   *  based on its decimal integer value
   *  @param other the other RomanNumeral to compare against
   *  @return 0 if this.equals(other) is true
   *          < 0 if this < other
   *          > 0 if this > other
   */
  @Override
  public int compareTo(RomanNumeral other)
  {
    
    if(this.equals(other)){
      return 0;
    }
      
    return getValue() - other.getValue(); 
      
  }
  
  /** Provides this RomanNumeral information in a string
   *  @return RomanNumeral info as a string
   */
  @Override
  public String toString()
  {
    return romanStr;
  }
  
  
  /** Retrieve the object's integer value
   *  @return the integer value
   */
  public int getValue(){
    
    
    
    return romanInt;
  }
}

