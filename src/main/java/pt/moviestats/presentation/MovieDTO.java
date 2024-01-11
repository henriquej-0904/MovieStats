package pt.moviestats.presentation;

import pt.moviestats.data.MovieDAO;
import pt.moviestats.data.MovieDAO.UpdateMovieDAO;

public record MovieDTO(long id, String title, long date, int rank, int revenue) {

    public static MovieDTO fromDAO(MovieDAO movie) {
        return new MovieDTO(movie.getId(), movie.getTitle(), movie.getDate(), movie.getRank(),
        movie.getRevenue());
    }

    public record CreateMovieDTO(String title, long date, int rank, int revenue) {

        public MovieDAO toDAO() {
            return new MovieDAO(title, date, rank, revenue);
        }

    }

    public record UpdateMovieDTO(String title, long date, int rank, int revenue) {

        public UpdateMovieDAO toDAO() {
            return new UpdateMovieDAO(title, date, rank, revenue);
        }

    }
}
