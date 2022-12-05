package com.example.movie.movie.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Data
@Getter
@Setter
@NoArgsConstructor
public class MovieDto {
    private Long movieId;
    private String name;
    private String linkImdb;
    private Integer createYear;
    private Double imdbScore;
}
