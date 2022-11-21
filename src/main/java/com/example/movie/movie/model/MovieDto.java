package com.example.movie.movie.model;

import lombok.Data;

import java.util.List;
@Data
public class MovieDto {
    private Long movieId;
    private String name;
    private String linkImdb;
    private Integer createYear;
    private Double imdbScore;
    private List<Director> directors;

}
