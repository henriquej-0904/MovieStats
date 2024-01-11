package pt.moviestats.exceptions;

public class DeletedMovieException extends RuntimeException {

    public DeletedMovieException(String msg) {
        super(msg);
    }

    public static DeletedMovieException fromMovieId(long id) {
        return new DeletedMovieException(
            String.format("The movie with id %d was successfully deleted", id)
        );
    }

}
