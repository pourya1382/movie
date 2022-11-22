package com.example.movie.movie.model;
import com.example.movie.director.model.Director;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter

public class StateDto {
    private Long movieId;
    private String name;
    private String linkImdb;
    private Integer createYear;
    private Double imdbScore;
    private String nameDirector;
    private String family;
    private Integer age;
    private List<Director> directors;
}
