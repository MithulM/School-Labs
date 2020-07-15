
/**
* The Car class is designed to create the class to store it's year
* , brand gas, capacity, and fuel efficiency.
*
* @author : Mithul Manivannan
* Collaborators: None
* Teacher Name: Mrs. Garg
* Period: 3rd
* Due Date: 3/29/19
*/

public class Car
{
  /** Default initialization values */
  public static final int DEFAULT_YEAR = 1999;
  public static final String DEFAULT_MAKE_MODEL = "Ford Pinto";
  public static final double DEFAULT_GAS_CAPACITY = 14.5;
  public static final int DEFAULT_FUEL_EFFICIENCY = 24;
  
  //the car's current fuel level
  private double currentGallons;
  
  //the car's tank size
  private double fuelCapacity;
  
  //the car's fuel efficiency
  private int fuelEfficiency;
  
  
  /** Manages unique ID numbers for all cars */
  private static int nextVIN = 1001;

  /** the car's model year */
  private int year;

  /** the make and model of the car */
  private String makeAndModel;

  /** the car's unique VIN (Vehicle Identification Number) */
  private int vin;

  /** the current mileage on the car */
  private double odometer;

  /** Constructs a Car with default settings
   */
  public Car()
  {
    year = DEFAULT_YEAR;
    makeAndModel = DEFAULT_MAKE_MODEL;
    vin = getNextVIN();
    odometer = 0;
    currentGallons = 0;
    fuelCapacity = DEFAULT_GAS_CAPACITY;
    fuelEfficiency = DEFAULT_FUEL_EFFICIENCY;
  }

  /** Create a car with the given name, year, and odometer
   *  @param year the year the car was manufactured
   *  @param makeAndModel the car's make and model
   *  @param odometer the current mileage on the car
   */
  public Car(int year, String makeAndModel, double odometer)
  {
    this.year = year;
    this.makeAndModel = makeAndModel;
    this.odometer = odometer;
    this.vin = getNextVIN();
    fuelCapacity = DEFAULT_GAS_CAPACITY;
    fuelEfficiency = DEFAULT_FUEL_EFFICIENCY;
  }

  /* Create a car with the given name, year, odometer,
   * starting fuel level, fuel tank capacity, and fuel efficiency
   * @param year the year of the car
   * @param makeAndModel the make and model of the car
   * @param odometer the mileage of the car
   * @param startingFuelLevel the amount of fuel currently in the car
   * @param fuelTankCapacity the size of the fuel tank
   * @param fuelEfficiencyNum the car's fuel efficiency
   *
   */
  public Car(int year, String makeAndModel, double odometer,
   double startingFuelLevel, double fuelTankCapacity, int fuelEfficiencyNum){
    
    this.year = year;
    this.makeAndModel = makeAndModel;
    this.odometer = odometer;
    this.vin = getNextVIN();
    
    currentGallons = startingFuelLevel;
    fuelCapacity = fuelTankCapacity;
    fuelEfficiency = fuelEfficiencyNum; 
    
  }


  /** Change the make and model of this car
   *  @param newMakeAndModel the new Make and Model
   */
  public void changeMakeAndModel(String newMakeAndModel)
  {
    makeAndModel = newMakeAndModel;
  }

  /** Drives the car a given number of miles
   *  @param miles the number of miles driven
   */
  public void drive(double miles)
  {
    double gallonsExpended = (miles / fuelEfficiency);
    
    if(gallonsExpended > currentGallons){
      odometer += (currentGallons * fuelEfficiency);
      currentGallons = 0;
    }
    
    else{
      
      odometer += miles;
      currentGallons = currentGallons - (miles / fuelEfficiency);
    }
    
  }

  /** Drives the car a given number of miles per day
   *  @param daysDriven the number of days of driving
   *  @param milesPerDay the number of miles driven per day
   */
  public void drive(int daysDriven, double milesPerDay)
  {
    double totalMiles = daysDriven * milesPerDay;
    drive(totalMiles);
  }

  /** Retrieves the current mileage on the odometer
   *  @return the odometer setting
   */
  public double getMileage()
  {
    return odometer;
  }

  /** Retrieves the car's VIN
   *  @return the VIN
   */
  public int getVIN()
  {
    return vin;
  }

  /** Provides this car's information in a string
   *  @return car info as a string
   */
  @Override
  public String toString()
  {
    return year + " " + makeAndModel + " " + vin + " "  + String.format("%,.1f", odometer)
      + " " + String.format("%,.4f" ,currentGallons) + " "
     	+ String.format("%,.3f",fuelCapacity)
   	+ " " + fuelEfficiency;
  }

  /** Determines if 2 cars are the same based on its VIN
   *  @param obj the car to compare to this one
   *  @return true if this and obj have the same VIN, false otherwise
   */
  @Override
  public boolean equals(Object obj)
  {
    if (obj == null || !(obj instanceof Car))
      return false;

    Car otherCar = (Car) obj;
    return this.vin == otherCar.vin;
  }

  /** Calculates and returns the next VIN for any car
   *  @return unique VIN for a car
   */
  private static int getNextVIN()
  {
    int newVIN = nextVIN;
    nextVIN++;
    return newVIN;
  }
  
  /* Gets the car's current fuel level
   *@return the car's fuel level
   *
   */
   public double getFuelLevel(){
      
    return currentGallons;
   }
  
   /* Adds fuel to the maximum capacity
    * @return always true because gas is added
    *
    */
   public boolean addFuel(){
    
    currentGallons = fuelCapacity;
    return true;
   }
   /* Adds a specific amount of fuel to the tank
    * @param addGallons is the number of gallons to add
    * @return if fuel can be added or not depending on current fuel level
    */
   public boolean addFuel(double addGallons){
    boolean fuelAdded = false;
    
    if(currentGallons + addGallons <= fuelCapacity){
      
      currentGallons += addGallons;
      fuelAdded = true;
    }
    else{
      currentGallons = fuelCapacity;
      return fuelAdded;
    }
    
    
    return fuelAdded;
   }
  
}

