package com.example.movie.movie.model;

import com.example.movie.director.model.Director;

import javax.persistence.*;

@Entity(name = "movies")
@Table

public class Movie {
    @Id
    @SequenceGenerator(
            name = "movie_sequence",
            sequenceName = "movie_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "movie_sequence"
    )
    @Column(name = "id")
    private Long id;


    private String name;
    private String linkImdb;
    private Integer createYear;
    private Double imdbScore;

//    @ManyToMany
//    @JoinTable(name = "category",joinColumns =@JoinColumn(name="movie_id"),inverseJoinColumns=@JoinColumn(name="director_id"))
//    private Set<Director> directors;//    public Set<Director> getDirectors() {
////        return directors;
////    }
////
////    public void setDirectors(Set<Director> directors) {
////        this.directors = directors;
////    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "director_id", referencedColumnName = "id")
    private Director director;

    public void setDirector(Director director) {
        this.director = director;
    }

    public Movie() {
    }

    public Movie(String name, String linkImdb, Integer createYear, Double imdbScore) {
        this.name = name;
        this.linkImdb = linkImdb;
        this.createYear = createYear;
        this.imdbScore = imdbScore;
    }

    public Movie(String name,
                 String linkImdb,
                 Integer createYear,
                 Double imdbScore, Director director) {
        this.name = name;
        this.linkImdb = linkImdb;
        this.createYear = createYear;
        this.imdbScore = imdbScore;
        this.director = director;
    }


    public String getName() {
        return name;
    }

    public String getLinkImdb() {
        return linkImdb;
    }

    public Integer getCreateYear() {
        return createYear;
    }

    public Double getImdbScore() {
        return imdbScore;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setLinkImdb(String linkImdb) {
        this.linkImdb = linkImdb;
    }

    public void setCreateYear(Integer createYear) {
        this.createYear = createYear;
    }

    public void setImdbScore(Double imdbScore) {
        this.imdbScore = imdbScore;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", linkImdb='" + linkImdb + '\'' +
                ", createYear=" + createYear +
                ", imdbScore=" + imdbScore +
                ", director=" + director +
                '}';
    }
}
