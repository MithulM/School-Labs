/**
 * This class has all the attributes to a movie and the compareTo method to sort it.
 *
 *
 * @author Mithul Manivannan
 * Collaborators: None
 * Teacher Name: Mrs. Ishman
 * Period: 2
 * Due Date: 5/18/2020
 */

import java.util.*;

public class MovieList
{

    private PriorityQueue<Movie> movieList;
    private Set<String> watched;

    /**
     * Initializes all instance variables
     **/
    public MovieList()
    {
        movieList = new PriorityQueue<>();
        watched = new TreeSet<>();
    }

    /**
     * adds Movie object to the list
     * @param Movie object that is added
     **/
    public void add(Movie mov)
    {
        movieList.add(mov);
    }

    /**
     * returns the list of movies based on priority
     * @return the movie list
     **/
    public PriorityQueue<Movie> getList()
    {
        return movieList;
    }

    /**
     * removes a movie object from the list
     * @param movie that is to be removed
     **/
    public void remove(Movie movie)
    {
        movieList.remove(movie);
    }

    /**
     * adds movie to list of already watched movies
     * @param name of movie that is already watched.
     **/
    public void watchedMovie(String name)
    {
        watched.add(name);
    }

    /**
     * sells tickets on random
     **/
    public void sellMovie()
    {
        PriorityQueue<Movie> tempQueue = new PriorityQueue<>(movieList);
        while(!tempQueue.isEmpty())
        {
            Movie movie = tempQueue.poll();
            int rand = (int)(Math.random() * 21);
            if(rand > 10)
                movie.buyTickets(rand - 10);
            if(movie.isSold())
                remove(movie);
        }
    }
    
    /**
     * Checks if age requirement is met
     * @param Movie 
     * @param age to check if it meets age requirement
     * @return whether the age met or surpassed the requirement.
     **/
    public boolean isAllowed(Movie mov, int age)
    {
        int required = 0;
        switch(mov.getAgeRating())
        {
            case("NC-17"):
                required = 18;
                break;
            case("R"):
                required = 17;
                break;
            case("PG-13"):
                required = 13;
                break;
            case("PG"):
                required = 10;
                break;
            default:
                break;
        }
        return age >= required;
    }

    /**
     * checks how may tickets the user wants to purchase.
     * @param movie 
     * @param number of tickets the user wants 
     **/
    public void howManyTickets(Movie mov, int tickets)
    {
        if(mov.getLeft() >= tickets)
            mov.buyTickets(tickets);
        if(mov.isSold())
            remove(mov);
    }

    /**
     * resorts the list
     * @param the method of choice of resorting
     **/
    public void reSort(String mode)
    {
        Movie.setPriority(mode);
        PriorityQueue<Movie> tempQueue = new PriorityQueue<>();
        while(!movieList.isEmpty())
            tempQueue.add(movieList.poll());
        movieList = tempQueue;
    }

    /**
     * checks if a movie was watched or not
     * @param name of movie that is being checked
     * @return whethe ror not it was watched
     **/
    public boolean watched(String name)
    {
        return watched.contains(name);
    }

    /**
     * checks if a movie is sold out
     * @param movie that is checked
     **/
    public boolean isSold(Movie mov)
    {
        return mov.isSold();
    }
    
    /**
     * formats the output
     * @return the formatted output
     **/
    @Override
    public String toString()
    {
        StringBuilder str = new StringBuilder();
        PriorityQueue<Movie> tempQueue = new PriorityQueue<>(movieList);
        str.append("The list of Movies in ").append(Movie.getPriority()).append(" order\n");
        str.append(String.format("%-40s|%-13s|%-13s|%-13s|%-13s|%-13s\n", "Movie Name", "Genre", "Age Rating", "Rating", "Tickets Left", "Year"));
        while(!tempQueue.isEmpty())
        {
            Movie mov = tempQueue.poll();
            if(!watched(mov.getName()))
                str.append(mov.toString()).append("\n");
        }
        return str.toString();
    }
}