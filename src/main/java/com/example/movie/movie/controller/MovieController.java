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
    ){
        return movieService.getMovie(page,size);
    }


    @GetMapping(path = "search")
    public Page<Movie> getMovie(@RequestParam(required = false) String movie_name,
                                @RequestParam(required = false,defaultValue = "0") int create_year,
                                @RequestParam(required = false) String view_status,
                                @RequestParam(required = false) String watch_later,
                                @RequestParam(required = false) String movie_sorting,
                                @RequestParam(required = false) String create_sorting,
                                @RequestParam(defaultValue = "0") int page,
                                @RequestParam(defaultValue = "3") int size
    ) {
        return movieService.getMovieBysearch(movie_name, create_year, view_status, watch_later,movie_sorting,create_sorting,page, size);
    }

    @GetMapping("sort")
    public Page<Movie> searchAndSorting(@RequestParam(defaultValue = "0") int page,
                                        @RequestParam(defaultValue = "3") int size,
                                        @RequestParam(defaultValue = "asc") String ascOrDesc
    ) {
        return movieService.sortingMovie(page, size, ascOrDesc);

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
