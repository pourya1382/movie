package com.example.movie.movie.service;
import com.example.movie.movie.model.Director;
import com.example.movie.movie.model.DirectorDto;
import com.example.movie.movie.model.Movie;
import com.example.movie.movie.repository.DirectorRepository;
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

    public DirectorService(DirectorRepository directorRepository,ModelMapper modelMapper) {
        this.directorRepository = directorRepository;
        this.modelMapper=modelMapper;
    }

    public Page<Director> getDirector(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Director> directors;
        directors = directorRepository.findAll(pageable);
        return directors;
    }

    public Director addNewDirector(Director director) {
        Director director1 = directorRepository.save(director);
        return director1;
    }
    @Transactional

    public Director updateMovie(Long directorId, DirectorDto directorDto) {
        Director director = directorRepository.findById(directorId)
                .orElseThrow(() -> new IllegalStateException(
                        "director with id " + directorId + " does not exist!"
                ));
        Director directorRequest = modelMapper.map(directorDto, Director.class);
        director.setAge(directorRequest.getAge());
        director.setFamily(directorRequest.getFamily());
        director.setName(directorRequest.getName());
        directorRepository.save(director);
        return director;
    }

    public void deleteDirector(Long directorId) {
        directorRepository.deleteById(directorId);
    }
}
