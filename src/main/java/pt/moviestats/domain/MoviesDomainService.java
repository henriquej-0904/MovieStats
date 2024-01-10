package pt.moviestats.domain;

import java.security.InvalidParameterException;

import org.springframework.stereotype.Service;

import pt.moviestats.data.MovieDAO;
import pt.moviestats.data.MovieRepo;

@Service
public class MoviesDomainService {

    public static final int MIN_RANK = 0;
    public static final int MAX_RANK = 10; 

    private final MovieRepo movieRepo;

    /**
     * @param movieRepo
     */
    public MoviesDomainService(MovieRepo movieRepo) {
        this.movieRepo = movieRepo;
    }

    public MovieDAO addMovie(MovieDAO movie) {
        checkMovieFields(movie);
        return movieRepo.save(movie);
    }

    void checkMovieFields(MovieDAO movie) {
        String title = movie.getTitle();
        if (title == null || title.isBlank())
            throw new InvalidParameterException("The title must be a valid string of characters.");

        if (movie.getLaunchDate() <= 0)
            throw new InvalidParameterException("The launch date must be a valid date.");

        int rank = movie.getRank();
        if (rank < MIN_RANK || rank > MAX_RANK)
            throw new InvalidParameterException(
                String.format("The rank of the movie must be a value between %d and %d.",
                MIN_RANK, MAX_RANK)
            );
    }

}
