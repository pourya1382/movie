package com.example.movie.movie.controller;

import com.example.movie.movie.model.Movie;
import com.example.movie.movie.model.MovieDto;
import com.example.movie.movie.service.DirectorService;
import com.example.movie.movie.service.MovieService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "movie")
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

    @PostMapping(path = "add-movie")
    public Movie addNewMovie(@RequestBody Movie movie) {
        return movieService.addNewMovie(movie);
    }
    @PutMapping(path = "{movieId}")
    public Movie updateMovie(@PathVariable("movieId") Long movieId, @RequestBody MovieDto movieDto) {
        return movieService.updateMovie(movieId, movieDto);
    }
    @DeleteMapping(path = "{movieId}")
    public void deleteMovie(@PathVariable("movieId")Long movieId){
        movieService.deleteMovie(movieId);
    }

//    @PostMapping(path = "csv-file")
//    public String setCsv(@RequestParam("file") MultipartFile file, Model model){
//        return movieService.setCsv(file, model);
//    }

}
