/**
 * The main methods acts as a tester class.
 *
 * @author Mithul Manivannan
 * Collaborators: None
 * Teacher Name: Mrs. Ishman
 * Period: 2
 * Due Date: 5/18/2020
 */

import java.util.*;
import java.io.*;

public class Main
{
    /**
     * The main method creates the UI for the user to input
     * their desired choices.
     */
    public static void main(String[] args) throws IOException
    {
        Scanner in = new Scanner(new File("MovieData.txt"));
        Set<String> movieList = new TreeSet<>();
        MovieList list = new MovieList();
        while(in.hasNextLine())
        {

            String[] line = in.nextLine().split(" ");
            String genre = line[line.length - 1];
            int year = Integer.parseInt(line[line.length - 2]);
            double rating = Double.parseDouble(line[line.length - 3]);
            String ageRating = line[line.length - 4];
            StringBuilder name = new StringBuilder();
            for(int i = 0; i < line.length - 4; i++)
                name.append(line[i]).append(" ");
            list.add(new Movie(name.toString(), year, rating, ageRating, 100, genre));
        }


        Scanner input = new Scanner(System.in);
        String userIn = "yes";
        while(userIn.equalsIgnoreCase("yes") || userIn.equalsIgnoreCase("y"))
        {
            String[] sortingOrder = {"name", "year(newest last)" , "year(newest first)", "rating", "age rating"};
            switch(getAction("reorder the list", "add watched movie",
                    "buy tickets for a movie", "check if you are old enough for a movie", "print current list"))
            {
                case(1):
                    list.reSort(sortingOrder[getAction(sortingOrder) - 1]);
                    break;
                case(2):
                    list.watchedMovie(getMovie(list).getName());
                    break;
                case(3):
                    list.howManyTickets(getMovie(list), getChoice(5, "number of tickets to buy up to 5"));
                    break;
                case(4):
                    System.out.println("Pick a movie to buy tickets for.");
                    System.out.println(list.isAllowed(getMovie(list), getChoice(99, "age")));
                    break;
                default:
                    System.out.println(list.toString());
            }
            System.out.println("Tickets are being bought");
            list.sellMovie();
            System.out.print("Do you want to continue (y or yes for YES otherwise NO)");
            userIn = input.next();
            System.out.println();
        }
        in.close();
    }

    /**
     * records the input of the user's choice.
     * @param length 
     * @param action that is taken 
     * @return the int value of input
     **/
    private static int getChoice(int len, String action)
    {
        Scanner ins = new Scanner(System.in);
        String userIn = "";
        String str = ""+len;
        int lens = Integer.parseInt(str.substring(str.length()-1));
        while(true)
        {
            if (len >= 10) {
                if (userIn.matches("1?[0-" + lens + "]"))
                    break;
            } else {
                if (userIn.matches("[0-" + lens + "]")) {
                    break;
                }
            }
            System.out.print("Enter a valid " + action + ": ");
            userIn = ins.nextLine();
        }
        System.out.println();
        return Integer.parseInt(userIn);
    }

    /**
     * Records the action and returns the choice
     * @param String value of the action
     * @return the choice based on the Movie
     **/
    private static int getAction(String... actions)
    {
        System.out.println("What action would you like to do?");
        for(int i = 1; i < actions.length + 1; i++)
            System.out.println(i + ") " + actions[i - 1]);
        return getChoice(actions.length, "action");
    }
 
    /**
     * returns list of available movies based on input
     * @param list of movies
     * @return list of unwatched movies
     **/
    private static Movie getMovie(MovieList list)
    {
        ArrayList<Movie> array_list = new ArrayList<Movie>();
        PriorityQueue<Movie> temp = new PriorityQueue<>(list.getList());
        while(!temp.isEmpty())
        {
            Movie mov = temp.poll();
            if(!(list.watched(mov.getName()) || list.isSold(mov)))
                array_list.add(mov);
        }
        for(int i = 0; i < array_list.size(); i++)
            System.out.println((i + 1) + ") " + array_list.get(i).getName());
        return array_list.get(getChoice(array_list.size(), "Movie number") - 1);
    }
}