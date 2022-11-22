package com.example.movie.director.model;

import com.example.movie.movie.model.Movie;

import javax.persistence.*;
import java.util.List;

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
    private String nameDirector;
    private String family;
    private Integer age;
//    @ManyToMany(mappedBy = "directors")
//    private Set<Movie> movie;
    @OneToMany(mappedBy = "director")
    private List<Movie> movies;


    public Director(Long id, String nameDirector, String family, Integer age) {
        this.id = id;
        this.nameDirector = nameDirector;
        this.family = family;
        this.age = age;

    }

    public Director(String nameDirector, String family, Integer age) {
        this.nameDirector = nameDirector;
        this.family = family;
        this.age = age;

    }


    public List<Movie> getMovies() {
        return movies;
    }

    public Director() {
    }


    public String getName() {
        return nameDirector;
    }

    public void setName(String nameDirector) {
        this.nameDirector = nameDirector;
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
                ", nameDirector='" + nameDirector + '\'' +
                ", family='" + family + '\'' +
                ", age=" + age +
                '}';
    }
}
