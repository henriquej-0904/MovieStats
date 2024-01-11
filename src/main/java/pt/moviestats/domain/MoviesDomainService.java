package pt.moviestats.domain;

import java.security.InvalidParameterException;
import java.util.List;

import org.springframework.stereotype.Service;

import pt.moviestats.data.MovieDAO;
import pt.moviestats.data.MovieRepo;
import pt.moviestats.data.MovieDAO.UpdateMovieDAO;
import pt.moviestats.exceptions.MovieNotFoundException;

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

    public MovieDAO updateMovie(long id, UpdateMovieDAO update) {
        checkMovieFields(update);
        
        final var m = movieRepo.findById(id).
            orElseThrow(() -> MovieNotFoundException.fromMovieId(id));

        m.setTitle(update.title());
        m.setDate(update.date());
        m.setRank(update.rank());
        m.setRevenue(update.revenue());

        return movieRepo.save(m);
    }

    public void deleteMovieById(long id) {
        if (!movieRepo.existsById(id))
            throw MovieNotFoundException.fromMovieId(id);

        movieRepo.deleteById(id);
    }

    public List<MovieDAO> getAllMoviesFilteredByLaunchDate(long from, long to) {
        if (from <= 0 || to <= 0 || from > to)
            throw new InvalidParameterException("Launch date filter is composed by a start and end dates.");
        
        return movieRepo.findByDateBetween(from, to);
    }

    void checkMovieFields(MovieDAO movie) {
        checkMovieFields(movie.getTitle(), movie.getDate(), movie.getRank());
    }

    void checkMovieFields(UpdateMovieDAO movie) {
        checkMovieFields(movie.title(), movie.date(), movie.rank());
    }

    void checkMovieFields(String title, long date, int rank) {
        if (title == null || title.isBlank())
            throw new InvalidParameterException("The title must be a valid string of characters.");

        if (date <= 0)
            throw new InvalidParameterException("The launch date must be a valid date.");

        if (rank < MIN_RANK || rank > MAX_RANK)
            throw new InvalidParameterException(
                String.format("The rank of the movie must be a value between %d and %d.",
                MIN_RANK, MAX_RANK)
            );
    }

}
