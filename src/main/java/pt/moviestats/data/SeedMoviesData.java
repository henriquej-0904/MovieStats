package pt.moviestats.data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import pt.moviestats.domain.MoviesDomainService;
import pt.moviestats.service.MovieService;

@Component
public class SeedMoviesData implements CommandLineRunner {

    private final MovieRepo movieRepo;

    /**
     * @param movieRepo
     */
    public SeedMoviesData(MovieRepo movieRepo) {
        this.movieRepo = movieRepo;
    }

    private void addMovies(int n) {

        List<MovieDAO> movies = new ArrayList<>(n);
        Random r = new Random();
        for (int i = 0; i < n; i++)
        {
            var date = Calendar.getInstance();
            date.add(Calendar.DAY_OF_MONTH, r.nextInt(-1000, 365));
            int rank = r.nextInt(MoviesDomainService.MIN_RANK, MoviesDomainService.MAX_RANK + 1);

            movies.add(new MovieDAO(String.valueOf(i), date.getTimeInMillis() / 1000, rank, n));
        }

        movieRepo.saveAll(movies);
    }

    @Override
    public void run(String... args) throws Exception {
        addMovies(10000);
    }

}
