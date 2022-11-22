package com.example.movie.movie.model;

import com.example.movie.director.model.Director;
import lombok.Data;

import java.util.List;
@Data
public class MovieDto {
    private Long movieId;
    private String name;
    private String linkImdb;
    private Integer createYear;
    private Double imdbScore;
//    private String nameDirector;
//    private String family;
//    private Integer age;
    private List<Director> directors;

}
