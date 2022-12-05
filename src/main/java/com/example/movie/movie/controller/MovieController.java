package com.example.movie.movie.controller;

import com.example.movie.director.service.DirectorService;
import com.example.movie.movie.model.Movie;
import com.example.movie.movie.model.MovieDto;
import com.example.movie.movie.service.MovieService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/movies")
public class MovieController {

    private final MovieService movieService;
    private final DirectorService directorService;

    public MovieController(MovieService movieService, DirectorService directorService) {
        this.movieService = movieService;
        this.directorService = directorService;
    }

    @GetMapping()
    public Page<Movie> getMovie(@RequestParam(defaultValue = "0") int page,
                                @RequestParam(defaultValue = "3") int size
    ) {
        return movieService.getMovie(page, size);
    }


    @GetMapping("{movie_name}")
    public Page<Movie> getMovie(@PathVariable(value = "movie_name", required = false) String movie_name,
                                @RequestParam(required = false) Integer create_year,
                                @RequestParam(required = false, defaultValue = " ") String view_status,
                                @RequestParam(required = false, defaultValue = " ") String watch_later,
                                @RequestParam(required = false, defaultValue = " ") String movie_sorting,
                                @RequestParam(required = false, defaultValue = " ") String create_sorting,
                                @RequestParam(defaultValue = "0") Integer page,
                                @RequestParam(defaultValue = "3") Integer size
    ) {
        return movieService.getMovieBysearch(movie_name,
                create_year,
                view_status,
                watch_later,
                movie_sorting,
                create_sorting,
                page,
                size
        );
    }


    @PostMapping()
    public Movie addNewMovie(@RequestBody Movie movie) {
        return movieService.addNewMovie(movie);
    }

    @PutMapping(path = "{movieId}/movies_directors/{directorId}")
    public Movie movieDirectors(@PathVariable("movieId") Long movieId,
                                @PathVariable("directorId") Long directorId
    ) {
        return movieService.movieDirectors(movieId, directorId);
    }

    @PutMapping(path = "{movieId}")
    public Movie updateMovie(@PathVariable("movieId") Long movieId, @RequestBody MovieDto movieDto) {
        return movieService.updateMovie(movieId, movieDto);
    }

    @DeleteMapping(path = "{movieId}")
    public void deleteMovie(@PathVariable("movieId") Long movieId) {
        movieService.deleteMovie(movieId);
    }

}
