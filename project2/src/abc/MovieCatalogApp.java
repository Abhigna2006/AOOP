package abc;

import java.util.*;

public class MovieCatalogApp {

    // Movie class
    static class Movie implements Comparable<Movie>, Cloneable {
        private String title;
        private int releaseYear;
        private double rating;

        public Movie(String title, int releaseYear, double rating) {
            this.title = title;
            this.releaseYear = releaseYear;
            this.rating = rating;
        }

        @Override
        public int compareTo(Movie other) {
            return this.title.compareTo(other.title);
        }

        @Override
        protected Object clone() throws CloneNotSupportedException {
            return super.clone();
        }

        @Override
        public String toString() {
            return String.format("Title: %s, Year: %d, Rating: %.1f", title, releaseYear, rating);
        }

        public double getRating() {
            return rating;
        }

        public int getReleaseYear() {
            return releaseYear;
        }
    }

    // MovieCatalog class
    static class MovieCatalog implements Iterable<Movie> {
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

        public void sortByRating() {
            movies.sort(Comparator.comparingDouble(Movie::getRating));
        }

        public void sortByReleaseYear() {
            movies.sort(Comparator.comparingInt(Movie::getReleaseYear));
        }

        @Override
        public Iterator<Movie> iterator() {
            return movies.iterator();
        }

        public MovieCatalog cloneCatalog() throws CloneNotSupportedException {
            MovieCatalog cloned = new MovieCatalog();
            for (Movie movie : movies) {
                cloned.addMovie((Movie) movie.clone());
            }
            return cloned;
        }
    }

    // Main method
    public static void main(String[] args) {
        MovieCatalog catalog = new MovieCatalog();

        // Adding movies
        catalog.addMovie(new Movie("Inception", 2010, 8.8));
        catalog.addMovie(new Movie("The Godfather", 1972, 9.2));
        catalog.addMovie(new Movie("The Dark Knight", 2008, 9.0));
        catalog.addMovie(new Movie("Pulp Fiction", 1994, 8.9));

        // Sorting by title
        System.out.println("Movies sorted by title:");
        catalog.sortByTitle();
        for (Movie movie : catalog) {
            System.out.println(movie);
        }

        // Sorting by rating
        System.out.println("\nMovies sorted by rating:");
        catalog.sortByRating();
        for (Movie movie : catalog) {
            System.out.println(movie);
        }

        // Sorting by release year
        System.out.println("\nMovies sorted by release year:");
        catalog.sortByReleaseYear();
        for (Movie movie : catalog) {
            System.out.println(movie);
        }

        // Cloning the catalog
        try {
            MovieCatalog clonedCatalog = catalog.cloneCatalog();
            System.out.println("\nCloned Catalog:");
            for (Movie movie : clonedCatalog) {
                System.out.println(movie);
            }
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }
}
