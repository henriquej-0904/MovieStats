package pt.moviestats.presentation;

import java.security.InvalidParameterException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import pt.moviestats.application.MovieApp;
import pt.moviestats.exceptions.DeletedMovieException;
import pt.moviestats.exceptions.MovieNotFoundException;
import pt.moviestats.presentation.MovieDTO.CreateMovieDTO;
import pt.moviestats.presentation.MovieDTO.UpdateMovieDTO;

@RestController
public class MovieStatsController implements MovieStatsAPI {

    private final MovieApp movieApp;

    /**
     * @param movieApp
     */
    public MovieStatsController(MovieApp movieApp) {
        this.movieApp = movieApp;
    }

    @Override
    public MovieDTO getOneMovie(long movieId) {
        return movieApp.getMovieById(movieId);
    }

    @Override
    public long addOneMovie(CreateMovieDTO movie) {
        return movieApp.addMovie(movie).id();
    }

    @Override
    public MovieDTO updateOneMovie(long movieId, UpdateMovieDTO value) {
        return movieApp.updateMovie(movieId, value);
    }

    @Override
    public void deleteOneMovie(long movieId) {
        movieApp.deleteMovie(movieId);
    }

    @Override
    public List<MovieDTO> getAllMoviesFilteredByLaunchDate(long from, long to) {
        return movieApp.getAllMoviesFilteredByLaunchDate(from, to);
    }

    @ResponseStatus(value= HttpStatus.NOT_FOUND)
    @ExceptionHandler(MovieNotFoundException.class)
    void notFound() {}

    @ResponseStatus(value=HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidParameterException.class)
    void badRequest() {}

    @ResponseStatus(value=HttpStatus.ACCEPTED)
    @ExceptionHandler(DeletedMovieException.class)
    void accepted() {}

}
