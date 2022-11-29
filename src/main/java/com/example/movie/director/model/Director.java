package com.example.movie.director.model;

import com.example.movie.movie.model.Movie;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "directors")
@Table
public class Director {
    @Id
    @SequenceGenerator(
            name = "director_sequence",
            sequenceName = "director_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "director_sequence"
    )
    @Column(name = "id")
    private Long id;
    private String directorName;
    private String family;
    private Integer age;
    @JsonIgnore
    @ManyToMany(mappedBy = "directors", fetch = FetchType.LAZY)
    private Set<Movie> movies = new HashSet<>();


    public Director(Long id, String directorName, String family, Integer age) {
        this.id = id;
        this.directorName = directorName;
        this.family = family;
        this.age = age;

    }

    public Director(String directorName, String family, Integer age) {
        this.directorName = directorName;
        this.family = family;
        this.age = age;

    }


    public Set<Movie> getMovies() {
        return movies;
    }

    public Director() {
    }

    public Director(Long id, String directorName, String family, Integer age, Set<Movie> movies) {
        this.id = id;
        this.directorName = directorName;
        this.family = family;
        this.age = age;
        this.movies = movies;
    }

    public void setMovies(Movie movie) {
        movies.add(movie);
    }

    public String getDirectorName() {
        return directorName;
    }

    public void setDirectorName(String nameDirector) {
        this.directorName = nameDirector;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Director{" +
                "id=" + id +
                ", nameDirector='" + directorName + '\'' +
                ", family='" + family + '\'' +
                ", age=" + age +
                '}';
    }
}
