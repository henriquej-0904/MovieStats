package pt.moviestats.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import pt.moviestats.data.MovieDAO;
import pt.moviestats.data.MovieDAO.UpdateMovieDAO;
import pt.moviestats.data.MovieRepo;
import pt.moviestats.domain.MoviesDomainService;

@Service
public class MovieService {

    private final MoviesDomainService moviesDomainService;
    private final MovieRepo movieRepo;

    /**
     * @param moviesDomainService
     * @param movieRepo
     */
    public MovieService(MoviesDomainService moviesDomainService, MovieRepo movieRepo) {
        this.moviesDomainService = moviesDomainService;
        this.movieRepo = movieRepo;
    }

    public MovieDAO addMovie(MovieDAO movie) {
        return moviesDomainService.addMovie(movie);
    }

    public MovieDAO updateMovie(long id, UpdateMovieDAO update) {
        return moviesDomainService.updateMovie(id, update);
    }

    public Optional<MovieDAO> getMovieById(long id) {
        return movieRepo.findById(id);
    }

    public void deleteMovie(long id) {
        moviesDomainService.deleteMovieById(id);
    }

    public List<MovieDAO> getAllMoviesFilteredByLaunchDate(long from, long to) {
        return moviesDomainService.getAllMoviesFilteredByLaunchDate(from, to);
    }
}
