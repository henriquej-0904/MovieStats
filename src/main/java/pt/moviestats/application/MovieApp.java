package pt.moviestats.application;

import java.util.List;

import org.springframework.stereotype.Service;

import pt.moviestats.exceptions.DeletedMovieException;
import pt.moviestats.exceptions.MovieNotFoundException;
import pt.moviestats.presentation.MovieDTO;
import pt.moviestats.presentation.MovieDTO.CreateMovieDTO;
import pt.moviestats.presentation.MovieDTO.UpdateMovieDTO;
import pt.moviestats.service.MovieService;

@Service
public class MovieApp {

    private final MovieService movieService;

    /**
     * @param movieService
     */
    public MovieApp(MovieService movieService) {
        this.movieService = movieService;
    }

    public MovieDTO addMovie(CreateMovieDTO movie) {
        return MovieDTO.fromDAO(movieService.addMovie(movie.toDAO()));
    }

    public MovieDTO updateMovie(long id, UpdateMovieDTO update) {
        return MovieDTO.fromDAO(movieService.updateMovie(id, update.toDAO()));
    }

    public MovieDTO getMovieById(long id) {
        final var m = movieService.getMovieById(id);
        if (m.isPresent())
            return MovieDTO.fromDAO(m.get());

        throw MovieNotFoundException.fromMovieId(id);
    }

    public void deleteMovie(long id) {
        movieService.deleteMovie(id);
        throw DeletedMovieException.fromMovieId(id);
    }

    public List<MovieDTO> getAllMoviesFilteredByLaunchDate(long from, long to) {
        final var list = movieService.getAllMoviesFilteredByLaunchDate(from, to);
        return list.stream().map(MovieDTO::fromDAO).toList();
    }

    public long getTotalNumberMovies() {
        return movieService.getMoviesRepoSize();
    }

}
