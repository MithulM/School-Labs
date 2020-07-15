/**
* The FuelTankTester class is designed to get the details of the cars
* that were created using the car class. 
*
* @author 
* Collaborators: None
* Teacher Name: Mrs. Garg
* Period: 3rd
* Due Date: 3/29/19
*/

public class FuelTankTester
{
   public static void main(String[] args)
   {
     // Create a Car object using the default constructor
     // Create a Car object using the 3-parameter constructor
    // Display both cars using toString
    Car ford = new Car();
    Car porsche = new Car(2002, "Porsche 911", 1_700);



     // Drive both cars. Display each car's fuel tank level and odometer
     // using accessor methods (not toString)
    
    System.out.println ("Car 1: " + ford.toString());
    System.out.println ("Car 2: " + porsche.toString());
    
    ford.drive(750);
    porsche.drive(5280);
    
    System.out.println ();
    
    System.out.println ("Car 1's odometer: " + ford.getMileage());
    System.out.println ("Car 1's fuel level: " + ford.getFuelLevel());
    
    System.out.println ();
    
    System.out.println ("Car 2's odometer: " + porsche.getMileage());
    System.out.println ("Car 2's fuel level: " + porsche.getFuelLevel());
    
    

     // 100-pt Version:
     
     // Create a Car object using your 6-parameter constructor
     // Display the car using toString
    Car hornet = new Car(1951, "Hudson Hornet", 87_293.8, 10, 18.5, 16);
    System.out.println ();
    System.out.println ("Car 3: " + hornet.toString());
    
    System.out.println ();
  

     // Drive your car and add fuel, displaying the results
     // as directed on your lab sheet


    hornet.drive(100);
    System.out.println ("After 100 miles: " + hornet.toString());
    hornet.drive(75);
    System.out.println ("After 75 miles: " + hornet.toString());
    
    hornet.addFuel();
    System.out.println ("After filling tank: " + hornet.getFuelLevel());

    System.out.println ();
    hornet.drive(500);
    System.out.println ("After 500 miles: " + hornet.toString());
    
    
    System.out.println ();
    
    System.out.println ("Able to add 6.8 gal? " + hornet.addFuel(6.8));
    System.out.println ("Current fuel level: " + hornet.getFuelLevel());
    System.out.println ("Able to add 11.7 gal? " + hornet.addFuel(11.7));
    System.out.println ("Current fuel level: " + hornet.getFuelLevel());
    System.out.println ("Able to add 5.25 gal? " + hornet.addFuel(5.25));
    System.out.println ("Current fuel level: " + hornet.getFuelLevel());
    System.out.println ("Car 3: " + hornet.toString());
    System.out.println ();
   }
}