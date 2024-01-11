package pt.moviestats.data;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface MovieRepo extends CrudRepository<MovieDAO, Long> {

    List<MovieDAO> findByTitleLike(String title);

    List<MovieDAO> findByDateBetween(long from, long to);

}
