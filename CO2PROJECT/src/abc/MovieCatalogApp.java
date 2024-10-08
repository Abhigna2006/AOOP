package abc;


	import java.util.ArrayList;
	import java.util.Collections;
	import java.util.Comparator;
	import java.util.Iterator;
	import java.util.List;
	import java.util.Scanner;

	// Movie class implementing Comparable and Cloneable
	class Movie implements Comparable<Movie>, Cloneable {
	    private String title;
	    private String director;
	    private int releaseYear;
	    private double rating;

	    public Movie(String title, String director, int releaseYear, double rating) {
	        this.title = title;
	        this.director = director;
	        this.releaseYear = releaseYear;
	        this.rating = rating;
	    }

	    // Getters
	    public String getTitle() { return title; }
	    public String getDirector() { return director; }
	    public double getRating() { return rating; }

	    @Override
	    public int compareTo(Movie other) {
	        return this.title.compareTo(other.title); // Natural ordering by title
	    }

	    @Override
	    protected Object clone() throws CloneNotSupportedException {
	        return super.clone();
	    }

	    @Override
	    public String toString() {
	        return String.format("%s (%d) by %s - Rating: %.1f", title, releaseYear, director, rating);
	    }
	}

	// Comparator for sorting by director
	class MovieDirectorComparator implements Comparator<Movie> {
	    @Override
	    public int compare(Movie m1, Movie m2) {
	        return m1.getDirector().compareTo(m2.getDirector());
	    }
	}

	// Comparator for sorting by rating
	class MovieRatingComparator implements Comparator<Movie> {
	    @Override
	    public int compare(Movie m1, Movie m2) {
	        return Double.compare(m1.getRating(), m2.getRating());
	    }
	}

	// MovieCatalog class implementing Iterable
	class MovieCatalog implements Iterable<Movie> {
	    private List<Movie> movies;

	    public MovieCatalog() {
	        movies = new ArrayList<>();
	    }

	    public void addMovie(Movie movie) {
	        movies.add(movie);
	    }

	    public void removeMovie(Movie movie) {
	        movies.remove(movie);
	    }

	    public void sortByTitle() {
	        Collections.sort(movies);
	    }

	    public void sortByDirector() {
	        Collections.sort(movies, new MovieDirectorComparator());
	    }

	    public void sortByRating() {
	        Collections.sort(movies, new MovieRatingComparator());
	    }

	    @Override
	    public Iterator<Movie> iterator() {
	        return movies.iterator();
	    }

	    public void displayCatalog() {
	        for (Movie movie : movies) {
	            System.out.println(movie);
	        }
	    }
	}

	// Main application class
	public class MovieCatalogApp {
	    public static void main(String[] args) {
	        MovieCatalog catalog = new MovieCatalog();
	        Scanner scanner = new Scanner(System.in);

	        // Add sample movies
	        catalog.addMovie(new Movie("Inception", "Christopher Nolan", 2010, 8.8));
	        catalog.addMovie(new Movie("The Godfather", "Francis Ford Coppola", 1972, 9.2));
	        catalog.addMovie(new Movie("Pulp Fiction", "Quentin Tarantino", 1994, 8.9));

	        System.out.println("Movie Catalog:");
	        catalog.displayCatalog();

	        System.out.println("\nSorting by Title:");
	        catalog.sortByTitle();
	        catalog.displayCatalog();

	        System.out.println("\nSorting by Director:");
	        catalog.sortByDirector();
	        catalog.displayCatalog();

	        System.out.println("\nSorting by Rating:");
	        catalog.sortByRating();
	        catalog.displayCatalog();

	        // Clone a movie
	        try {
	            Movie clonedMovie = (Movie) catalog.iterator().next().clone();
	            System.out.println("\nCloned Movie: " + clonedMovie);
	        } catch (CloneNotSupportedException e) {
	            e.printStackTrace();
	        }

	        scanner.close();
	    }
	}
