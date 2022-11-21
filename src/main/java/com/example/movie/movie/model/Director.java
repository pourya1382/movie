package com.example.movie.movie.model;

import javax.persistence.*;

@Entity(name = "director")
@Table
public class Director {
    @Id
    @SequenceGenerator(
            name = "director_sequence",
            sequenceName = "director_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.IDENTITY,
            generator = "director_sequence"
    )
    @Column(name = "director_id")
    private Long directorId;
    private String name;
    private String family;
    private Integer age;
    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;

    public Director(String name, String family, Integer age) {
        this.name = name;
        this.family = family;
        this.age = age;
    }

    public Director(Long directorId, String name, String family, Integer age, Movie movie) {
        this.directorId = directorId;
        this.name = name;
        this.family = family;
        this.age = age;
        this.movie = movie;

    }

    public Director() {
    }

    public Director(String name, String family, Integer age, Movie movie) {
        this.name = name;
        this.family = family;
        this.age = age;
        this.movie = movie;

    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public Long getDirectorId() {
        return directorId;
    }

    public void setDirectorId(Long movieId) {
        this.directorId = directorId;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }


}
