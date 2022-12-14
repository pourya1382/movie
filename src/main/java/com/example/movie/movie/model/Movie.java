package com.example.movie.movie.model;

import com.example.movie.director.model.Director;
import lombok.Getter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "movies")
@Table
@Getter

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
    private boolean watchMovie;
    private boolean watchLater;


    @ManyToMany()
    @JoinTable(
            name = "moviedirectors",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "director_id")
    )
    private Set<Director> directors = new HashSet<>();

    public void setDirectors(Director director) {
        directors.add(director);
    }

    public Movie(String name,
                 String linkImdb,
                 Integer createYear,
                 Double imdbScore,
                 boolean watchMovie,
                 boolean watchLater
    ) {
        this.name = name;
        this.linkImdb = linkImdb;
        this.createYear = createYear;
        this.imdbScore = imdbScore;
        this.watchMovie = watchMovie;
        this.watchLater = watchLater;
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
                 Double imdbScore,
                 Set<Director> directors
    ) {
        this.name = name;
        this.linkImdb = linkImdb;
        this.createYear = createYear;
        this.imdbScore = imdbScore;
        this.directors = directors;
    }

    public boolean isWatchLater() {
        return watchLater;
    }

    public void setWatchLater(boolean watchLater) {
        this.watchLater = watchLater;
    }

    public boolean isWatchMovie() {
        return watchMovie;
    }

    public void setWatchMovie(boolean watchMovie) {
        this.watchMovie = watchMovie;
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
                ", director=" + directors +
                '}';
    }
}
