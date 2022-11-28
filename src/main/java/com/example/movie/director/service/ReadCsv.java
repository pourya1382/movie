package com.example.movie.director.service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import com.example.movie.director.model.Director;
import com.example.movie.director.repository.DirectorRepository;
import com.example.movie.movie.model.Movie;
import com.example.movie.movie.repository.MovieRepository;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartFile;

@Configuration
public class ReadCsv {
    public static String TYPE = "text/csv";
    static String[] HEADERs = { "name", "linkImdb", "createYear", "imdbScore" };
    @Autowired
    private DirectorRepository directorRepository;
    public ReadCsv(DirectorRepository directorRepository) {
        this.directorRepository = directorRepository;
    }

    @Bean
    public void csvToDirector() {
        try (BufferedReader fileReader = new BufferedReader(new FileReader("src/main/resources/directors.csv"));
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {
            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            for (CSVRecord csvRecord : csvRecords) {
                Director director = new Director(
                        csvRecord.get("\uFEFFdirectorName"),
                        csvRecord.get("family"),
                        Integer.parseInt(csvRecord.get("age"))
                );

                directorRepository.save(director);
            }

        } catch (IOException e) {
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        }
    }

}
