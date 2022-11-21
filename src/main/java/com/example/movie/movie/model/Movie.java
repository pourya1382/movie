package com.example.movie.movie.model;

import com.opencsv.bean.CsvBindByName;

import javax.persistence.*;
import java.util.List;

@Entity(name = "movie")
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

    @Column(name = "movie_id")
    private Long movieId;
    @CsvBindByName
    private String name;
    @CsvBindByName
    private String linkImdb;
    @CsvBindByName
    private Integer createYear;
    @CsvBindByName
    private Double imdbScore;

    @OneToMany(mappedBy = "movie")
    private List<Director> directors;

    public Movie() {
    }

    public Movie(Long movieId,
                 String name,
                 String linkImdb,
                 Integer createYear,
                 Double imdbScore) {
        this.movieId = movieId;
        this.name = name;
        this.linkImdb = linkImdb;
        this.createYear = createYear;
        this.imdbScore = imdbScore;

    }
    public Movie(String name,
                 String linkImdb,
                 Integer createYear,
                 Double imdbScore) {
        this.name = name;
        this.linkImdb = linkImdb;
        this.createYear = createYear;
        this.imdbScore = imdbScore;
    }



    public Long getmovieId() {
        return movieId;
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

    public void setmovieId(Long movieId) {
        this.movieId = movieId;
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
                "id=" + movieId +
                ", name='" + name + '\'' +
                ", linkImdb='" + linkImdb + '\'' +
                ", createYear=" + createYear +
                ", imdbScore=" + imdbScore +
                '}';
    }
}
