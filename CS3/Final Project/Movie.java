    /**
 * This class has all the attributes to a movie and the compareTo method to sort it.
 
 *
 * @author Mithul Manivannan
 * Collaborators: None
 * Teacher Name: Mrs. Ishman
 * Period: 2
 * Due Date: 5/18/2020
 */


public class Movie implements Comparable<Movie>
{
    private int left;
    private String name;
    private int year;
    private double rating;
    private String ageRating;
    private String genre;
    private static String priority;

    /**
     * initializes all variables
     * @param name the name of the Movie
     * @param year the year of the Movie
     * @param rating the rating of the Movie
     * @param ageRating the ageRating of the Movie
     * @param the priority of the movieList
     **/
    public Movie(String name, int year, double rating, String ageRating, int left, String genre)
    {
        boolean sold = false;
        this.left = left;
        this.name = name;
        this.year = year;
        this.rating = rating;
        this.ageRating = ageRating;
        priority = "default";
        this.genre = genre;
    }

    /**
     * gets if the movie is sold
     * @return if the movie is sold
     **/
    public boolean isSold()
    {
        return left <= 0;
    }

    /**
     * gets the movie name
     * @return the movie name
     **/
    public String getName()
    {
        return name;
    }

    /**
     * gets the age rating
     * @return the year the movie was made in.
     **/
    public int getYear()
    {
        return year;
    }

    /**
     * gets the priority
     * @return the priority of the list
     **/
    public static String getPriority()
    {
        return priority;
    }

    /**
     * gets the movies rating
     * @return the rating of the movie
     **/
    public double getRating()
    {
        return rating;
    }

    /**
     * gets the age rating
     * @return the ageRating
     **/
    public String getAgeRating()
    {
        return ageRating;
    }

    /**
     * buys tickets
     * @param amount the amount of tickets bought
     **/
    public void buyTickets(int amount)
    {
        left -= amount;
    }

    /**
     * gets the genre of the movie.
     * @reurn the genre
     **/
    public String getGenre()
    {
        return genre;
    }

    /**
     * gets how many tickets are left for this movie
     * @return the amount of tickets left for the movie
     **/
    public int getLeft()
    {
        return left;
    }
    /**
     * sets priority to choice.
     * @param choice sets the priority of the movie list.
     **/
    public static void setPriority(String choice)
    {
        priority = choice;
    }

    /**
     * @return a string for debugging
     **/
    @Override
    public String toString()
    {
        return String.format("%-40s %-13s %-13s %-13.1f %-13d %-13d", name, genre, ageRating, rating, left, year);
    }

    /**
     * compares two Movie objects
     * @return the integer value of the comparison.
     * @param mov the Mov object is being compared with.
     **/
    public int compareTo(Movie mov)
    {
        if(priority.equalsIgnoreCase("name"))
            return this.getName().compareTo(mov.getName());
        if(priority.equalsIgnoreCase("year(newest last)"))
            return this.getYear() - mov.getYear();
        if(priority.equalsIgnoreCase("rating"))
            return (int)(this.getRating() * 10 - mov.getRating() * 10);
        if(priority.equalsIgnoreCase("year(newest first)"))
            return mov.getYear() - this.getYear();
        else                                                            //agerating
            return age(this.getAgeRating()) - age(mov.getAgeRating());
    }

    /**
     * gets the age requirement for the movie.
     * @return the age required to watch the Movie
     * @param rating the age rating of the movie.
     **/
    private int age(String rating)
    {
        switch(rating)
        {
            case("NC-17"):
                return 18;
            case("R"):
                return 17;
            case("PG-13"):
                return 13;
            case("PG"):
                return 10;
            default:
                return 0;
        }
    }
}