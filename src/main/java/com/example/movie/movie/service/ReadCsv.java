package com.example.movie.movie.service;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import com.example.movie.movie.model.Movie;
import com.example.movie.movie.repository.MovieRepository;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartFile;

@Configuration
public class ReadCsv {
    public static String TYPE = "text/csv";
    static String[] HEADERs = { "name", "linkImdb", "createYear", "imdbScore" };
    private MovieRepository movieRepository;

    public ReadCsv(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Bean
    public List<Movie> csvToMovie() {
        System.out.println("this is run!");
        try (BufferedReader fileReader = new BufferedReader(new FileReader("src/main/resources/movie.csv"));
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

            List<Movie> movieList = new ArrayList<>();

            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            for (CSVRecord csvRecord : csvRecords) {
                Movie movie = new Movie(
                        csvRecord.get("name"),
                        csvRecord.get("linkImdb"),
                        Integer.parseInt(csvRecord.get("createYear")),
                        Double.parseDouble(csvRecord.get("imdbScore"))
                );

                movieRepository.save(movie);
            }

            return movieList;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        }
    }

}
