package pt.moviestats.exceptions;

public class MovieNotFoundException extends RuntimeException {
    public MovieNotFoundException(String msg) {
        super(msg);
    }

    public static MovieNotFoundException fromMovieId(long id) {
        return new MovieNotFoundException(
            String.format("There is no movie with id %d", id)
        );
    }
}
