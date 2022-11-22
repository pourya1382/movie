package com.example.movie.movie.service;

import com.example.movie.director.model.Director;
import com.example.movie.movie.model.Movie;
import com.example.movie.movie.model.MovieDto;
import com.example.movie.director.repository.DirectorRepository;
import com.example.movie.movie.repository.MovieRepository;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    public MovieService(MovieRepository movieRepository, ModelMapper modelMapper, DirectorRepository directorRepository) {
        this.movieRepository = movieRepository;
        this.modelMapper = modelMapper;
        this.directorRepository = directorRepository;
    }

    public Page<Movie> getMovie(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Movie> moviePage;
        moviePage = movieRepository.findAll(pageable);
        return moviePage;
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
                CsvToBean<Movie> csvToBean = new CsvToBeanBuilder(reader)
                        .withType(Movie.class)
                        .withIgnoreLeadingWhiteSpace(true)
                        .build();

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
        Director director = directorRepository.findByDirectorId(directorId);
        movie.setDirector(director);
        return movieRepository.save(movie);
    }
}

