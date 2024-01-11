package pt.moviestats.data;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;

@Entity
public class MovieDAO {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "generatorMovie")
    @SequenceGenerator(name="generatorMovie", sequenceName="MOVIE_SEQ")
    private long id;
    private String title;
    private long date;
    private int rank;
    private int revenue;
    
    /**
     * Create a new Movie
     */
    public MovieDAO() {
        this.id = 0;
    }

    /**
     * Create a new Movie
     * 
     * @param title the title of the movie
     * @param date the launch date
     * @param rank raking (0 to 10)
     * @param revenue the revenue generated by the movie
     */
    public MovieDAO(String title, long date, int rank, int revenue) {
        this.id = 0;
        this.title = title;
        this.date = date;
        this.rank = rank;
        this.revenue = revenue;
    }

    /**
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * @return the title of the movie
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title of the movie
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the launch date
     */
    public long getDate() {
        return date;
    }

    /**
     * @param date the launch date
     */
    public void setDate(long date) {
        this.date = date;
    }

    /**
     * @return raking (0 to 10)
     */
    public int getRank() {
        return rank;
    }

    /**
     * @param rank raking (0 to 10)
     */
    public void setRank(int rank) {
        this.rank = rank;
    }

    /**
     * @return the revenue generated by the movie
     */
    public int getRevenue() {
        return revenue;
    }

    /**
     * @param revenue the revenue generated by the movie
     */
    public void setRevenue(int revenue) {
        this.revenue = revenue;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (id ^ (id >>> 32));
        result = prime * result + ((title == null) ? 0 : title.hashCode());
        result = prime * result + (int) (date ^ (date >>> 32));
        result = prime * result + rank;
        result = prime * result + revenue;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        MovieDAO other = (MovieDAO) obj;
        return id == other.id;
    }

    /**
     * UpdateMovieDAO
     */
    public record UpdateMovieDAO(String title, long date, int rank, int revenue) {}
}
