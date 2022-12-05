package com.example.movie.movie.service;

import com.example.movie.director.model.Director;
import com.example.movie.director.repository.DirectorRepository;
import com.example.movie.movie.model.Movie;
import com.example.movie.movie.model.MovieDto;
import com.example.movie.movie.repository.MovieRepository;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import javax.transaction.Transactional;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

@Service

public class MovieService {
    private MovieRepository movieRepository;
    private ModelMapper modelMapper;
    private DirectorRepository directorRepository;
    public MovieService(MovieRepository movieRepository,
                        ModelMapper modelMapper,
                        DirectorRepository directorRepository
    ) {
        this.movieRepository = movieRepository;
        this.modelMapper = modelMapper;
        this.directorRepository = directorRepository;

    }

    public Page<Movie> getMovieBysearch(String movieName,
                                        Integer createYear,
                                        String view_Status,
                                        String watch_Later,
                                        String movie_sorting,
                                        String create_sorting,
                                        int page,
                                        int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        if (!movie_sorting.isEmpty()) {
            if (movie_sorting.equals("sort_by_name")) {
                pageable = PageRequest.of(page, size, Sort.by("name"));
            } else if (movie_sorting.equals("sort_by_name_descending")) {
                pageable = PageRequest.of(page, size, Sort.by("name").descending());
            } else if (create_sorting.equals("sort_by_create")) {
                pageable = PageRequest.of(page, size, Sort.by("createYear"));
            } else if (create_sorting.equals("sort_by_create_descending")) {
                pageable = PageRequest.of(page, size, Sort.by("createYear").descending());
            }
        }

        Page<Movie> moviePage;
        Boolean watchLaterBoolean = null;
        Boolean viewStatusBoolean = null;
        if (!movieName.isEmpty()) {
            movieName = movieName.toLowerCase();
        }
        if (watch_Later.equals("yes")) {
            watchLaterBoolean = Boolean.TRUE;
        } else if (watch_Later.equals("no")) {
            watchLaterBoolean = Boolean.FALSE;
        }
        if (view_Status.equals("seen")) {
            viewStatusBoolean = Boolean.TRUE;
        } else if (view_Status.equals("unseen")) {
            viewStatusBoolean = Boolean.FALSE;
        }
        return movieRepository.findAll(Specification.where(MovieSpecifiction.containName(movieName)).
                        or(MovieSpecifiction.hasCreateYear(createYear)).
                        or(MovieSpecifiction.hasWatchMovie(viewStatusBoolean)).
                        or(MovieSpecifiction.hasWatchLater(watchLaterBoolean)),
                pageable);

    }

    public String setCsv(MultipartFile file, Model model) {
        // validate file
        if (file.isEmpty()) {
            model.addAttribute("message", "Please select a CSV file to upload.");
            model.addAttribute("status", false);
        } else {

            // parse CSV file to create a list of `User` objects
            try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {

                // create csv bean reader
                CsvToBean<Movie> csvToBean = new CsvToBeanBuilder(reader).withType(Movie.class).withIgnoreLeadingWhiteSpace(true).build();

                // convert `CsvToBean` object to list of users
                List<Movie> movies = csvToBean.parse();

                // TODO: save users in DB?

                // save users list on model
                model.addAttribute("users", movies);
                model.addAttribute("status", true);

            } catch (Exception ex) {
                model.addAttribute("message", "An error occurred while processing the CSV file.");
                model.addAttribute("status", false);
            }
        }

        return "file-upload-status";
    }

    public Movie addNewMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    public void deleteMovie(Long movieId) {
        movieRepository.deleteById(movieId);
    }

    @Transactional
    public Movie updateMovie(Long movieId, MovieDto movieDto) {
        Movie movie = movieRepository.findById(movieId).get();
//            .orElseThrow(() -> new IllegalStateException(
//                    "movie with id " + movieId + " does not exist!"
//            ));
        Movie movieRequest = modelMapper.map(movieDto, Movie.class);
        movie.setName(movieRequest.getName());
        movie.setCreateYear(movieRequest.getCreateYear());
        movie.setImdbScore(movieRequest.getImdbScore());
        movie.setLinkImdb(movieRequest.getLinkImdb());
        movieRepository.save(movie);
        return movie;
    }

    public Movie movieDirectors(Long movieId, Long directorId) {
        Movie movie = movieRepository.findById(movieId).get();
        Director director = directorRepository.findByDirectorId(directorId).get();
        movie.setDirectors(director);
        return movieRepository.save(movie);
    }

    public Page<Movie> getMovie(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Movie> movies;
        movies = movieRepository.findAll(pageable);
        return movies;
    }
}

