package pt.moviestats;

import java.security.InvalidParameterException;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import pt.moviestats.application.MovieApp;
import pt.moviestats.domain.MoviesDomainService;
import pt.moviestats.exceptions.DeletedMovieException;
import pt.moviestats.exceptions.MovieNotFoundException;
import pt.moviestats.presentation.MovieDTO;
import pt.moviestats.presentation.MovieDTO.CreateMovieDTO;

@RunWith(SpringRunner.class)
@SpringBootTest
class MovieStatsApplicationTests {

	@Autowired
	private MovieApp app;

	@Test
	void testAddMovie() {
		final var movieCreate = new CreateMovieDTO("ola", System.currentTimeMillis(), 5, 55);
		final var result = app.addMovie(movieCreate);
		final var expected = new MovieDTO(result.id(), movieCreate.title(), movieCreate.date(), movieCreate.rank(), movieCreate.revenue());
		Assert.assertEquals(expected, result);
	}

	@Test
	void testGetMovie() {
		final var movieCreate = new CreateMovieDTO("ola", System.currentTimeMillis(), 5, 55);
		final var expected = app.addMovie(movieCreate);
		final var result = app.getMovieById(expected.id());
		Assert.assertEquals(expected, result);
	}

	@Test
	void testGetNonExistentMovie() {
		final var id = app.getTotalNumberMovies() + 100;
		Assert.assertThrows(MovieNotFoundException.class, () -> app.getMovieById(id));
	}

	@Test
	void testUpdateMovie() {
		final var movieCreate = new CreateMovieDTO("ola", System.currentTimeMillis(), 5, 55);
		var movie = app.addMovie(movieCreate);
		final var id = movie.id();

		final var updateMovie = new MovieDTO.UpdateMovieDTO("ola2", movie.date() + 1, movieCreate.rank() + 1, 100);
		movie = app.updateMovie(id, updateMovie);
		final var expected = new MovieDTO(id, updateMovie.title(), updateMovie.date(), updateMovie.rank(), updateMovie.revenue());
		Assert.assertEquals(expected, movie);
	}

	@Test
	void testInvalidUpdateMovie() {
		final var movieCreate = new CreateMovieDTO("ola", System.currentTimeMillis(), 5, 55);
		var movie = app.addMovie(movieCreate);
		final var id = movie.id();

		final var updateMovie1 = new MovieDTO.UpdateMovieDTO("ola2", movie.date() + 1, MoviesDomainService.MAX_RANK + 1, 100);
		final var updateMovie2 = new MovieDTO.UpdateMovieDTO("ola2", movie.date() + 1, MoviesDomainService.MIN_RANK - 1, 100);
		final var updateMovie3 = new MovieDTO.UpdateMovieDTO(" ", movie.date() + 1, 5, 100);
		
		Assert.assertThrows(InvalidParameterException.class, () -> app.updateMovie(id, updateMovie1));
		Assert.assertThrows(InvalidParameterException.class, () -> app.updateMovie(id, updateMovie2));
		Assert.assertThrows(InvalidParameterException.class, () -> app.updateMovie(id, updateMovie3));
	}

	@Test
	void testDeleteMovie() {
		final var movieCreate = new CreateMovieDTO("ola", System.currentTimeMillis(), 5, 55);
		final var movie = app.addMovie(movieCreate);
		Assert.assertThrows(DeletedMovieException.class, () -> app.deleteMovie(movie.id()));
		Assert.assertThrows(MovieNotFoundException.class, () -> app.getMovieById(movie.id()));
	}

	@Test
	void testDeleteNonExistentMovie() {
		final var movieCreate = new CreateMovieDTO("ola", System.currentTimeMillis(), 5, 55);
		final var movie = app.addMovie(movieCreate);
		Assert.assertThrows(DeletedMovieException.class, () -> app.deleteMovie(movie.id()));

		// delete again
		Assert.assertThrows(MovieNotFoundException.class, () -> app.deleteMovie(movie.id()));
	}

}
