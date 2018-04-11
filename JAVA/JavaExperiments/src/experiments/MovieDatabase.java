package experiments;

import java.util.HashMap;

public class MovieDatabase {
    
    HashMap<String, Double> movieRatings;
    
    public MovieDatabase() {
        movieRatings = new HashMap<String, Double>();
    }
    
    public void addMovie(String movie, double rating) {
        movieRatings.put(movie, rating);
    }
}
