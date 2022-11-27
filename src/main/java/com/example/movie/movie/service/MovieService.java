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
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.*;
import java.util.List;

@Service
public class MovieService {
    private MovieRepository movieRepository;
    private ModelMapper modelMapper;
    private DirectorRepository directorRepository;
    private String line;


//    public void readCsv() {
//        try {
//            BufferedReader bufferedReader = new BufferedReader(new FileReader("src/main/resources/movie.csv"));
//            while ((line = bufferedReader.readLine()) != null) {
//                String[] data = line.split(",");
//                int dataint = Integer.parseInt(data[2]);
//
//
//
//            Movie movie = new Movie();
//            movie.setName(data[0]);
//            movie.setLinkImdb(data[1]);
//            movie.setCreateYear(data[2]);
//            movie.setImdbScore(data[3]);
//            movieRepository.save(movie);
//            }
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//
//
//    }

    public MovieService(MovieRepository movieRepository, ModelMapper modelMapper, DirectorRepository directorRepository) {
        this.movieRepository = movieRepository;
        this.modelMapper = modelMapper;
        this.directorRepository = directorRepository;
    }

    public Page<Movie> getMovieBysearch(String movieName, int createYear, String viewStatus, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Movie> moviePage;
        if (viewStatus.equals("seen")) {
            moviePage = movieRepository.findByWatchMovieAndNameContainingAndCreateYear(Boolean.TRUE, movieName, createYear, pageable);
        } else if (viewStatus.equals("unseen")) {
            moviePage = movieRepository.findByWatchMovieAndNameContainingAndCreateYear(Boolean.FALSE, movieName, createYear, pageable);
        } else if (createYear == 0) {
            moviePage = movieRepository.findByNameContaining(movieName, pageable);
        } else {
            moviePage = movieRepository.findByNameContainingAndCreateYear(movieName, createYear, pageable);
        }
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
        Director director = directorRepository.findByDirectorId(directorId).get();
        movie.setDirectors(director);
        return movieRepository.save(movie);
    }

    public Page<Movie> viewOnAnotherOccasion(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Movie> moviePage;
        return movieRepository.findByViewOnAnotherOccasion(Boolean.TRUE, pageable);
    }

    public Page<Movie> sortingMovie(int page, int size, String ascOrDesc) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Movie> moviePage;
        if (ascOrDesc.equals("asc")) {
            moviePage = movieRepository.findByOrderByName(pageable);
        } else if (ascOrDesc.equals("invers")) {
            moviePage = movieRepository.findByOrderByNameDesc(pageable);
        } else {
            moviePage = movieRepository.findAll(pageable);
        }
        return moviePage;
    }

    public Page<Movie> getMovie(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Movie> movies;
        movies=movieRepository.findAll(pageable);
        return movies;
    }
}

