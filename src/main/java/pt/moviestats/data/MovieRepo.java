package pt.moviestats.data;

import org.springframework.data.repository.CrudRepository;

public interface MovieRepo extends CrudRepository<MovieDAO, Long> {

    

}
