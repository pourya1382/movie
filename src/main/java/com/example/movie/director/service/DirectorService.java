package com.example.movie.director.service;

import com.example.movie.director.model.Director;
import com.example.movie.director.model.DirectorDto;
import com.example.movie.director.repository.DirectorRepository;
import com.example.movie.movie.repository.MovieRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Service

public class DirectorService {
    private DirectorRepository directorRepository;
    private ModelMapper modelMapper;

    public DirectorService(DirectorRepository directorRepository, ModelMapper modelMapper) {
        this.directorRepository = directorRepository;
        this.modelMapper = modelMapper;
    }

    public Page<Director> getDirector(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Director> directors;
        directors = directorRepository.findAll(pageable);
        return directors;
    }

    public Director addNewDirector(Director director) {
        Director addirector = directorRepository.save(director);
//        addirector.setMovies(movieRepository.findByMovieId(director.getMovieId()));
        directorRepository.save(addirector);

        return addirector;
    }

    @Transactional

    public Director updateMovie(Long directorId, DirectorDto directorDto) {
        Director director = directorRepository.findById(directorId)
                .orElseThrow(() -> new IllegalStateException(
                        "director with id " + directorId + " does not exist!"
                ));
//        Director directorRequest = modelMapper.map(directorDto, Director.class);
        director.setAge(directorDto.getAge());
        director.setFamily(directorDto.getFamily());
        director.setDirectorName(directorDto.getDirectorName());
//        director.setMovieId(directorDto.getMovieId());
//        director.setMovies(movieRepository.findByMovieId(directorDto.getMovieId()));
        directorRepository.save(director);
        return director;
    }

    public void deleteDirector(Long directorId) {
        directorRepository.deleteById(directorId);
    }
}
