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
    public Page<Movie> getMovie(@RequestParam(required = false) String movieName,
                                @RequestParam(required = false,defaultValue = "0") int createYear,
                                @RequestParam(required = false) String viewStatus,
                                @RequestParam(defaultValue = "0") int page,
                                @RequestParam(defaultValue = "3") int size
    ) {
        return movieService.getMovie(movieName, createYear, viewStatus, page, size);
    }

    @GetMapping("sort")
    public Page<Movie> searchAndSorting(@RequestParam(defaultValue = "0") int page,
                                        @RequestParam(defaultValue = "3") int size,
                                        @RequestParam(defaultValue = "asc") String ascOrDesc
    ) {
        return movieService.sortingMovie(page,size,ascOrDesc);

    }

    @GetMapping(path = "view_on_another_occasion")
    public Page<Movie> viewOnAnotherOccasion(@RequestParam(defaultValue = "0") int page,
                                             @RequestParam(defaultValue = "3") int size
    ) {
        return movieService.viewOnAnotherOccasion(page, size);
    }

    @PostMapping(path = "add-movie")
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

//    @PostMapping(path = "csv-file")
//    public String setCsv(@RequestParam("file") MultipartFile file, Model model){
//        return movieService.setCsv(file, model);
//    }

}
