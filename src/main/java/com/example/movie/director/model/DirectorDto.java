package com.example.movie.director.model;

import lombok.Data;

@Data
public class DirectorDto {
    private Long directorId;
    private Long movieId;
    private String name;
    private String family;
    private Integer age;
}
