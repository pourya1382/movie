package com.example.movie.director.repository;

import com.example.movie.director.model.Director;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface DirectorRepository extends JpaRepository<Director, Long> {
    @Query("SELECT d FROM directors d where d.id=?1")
    Optional<Director> findByDirectorId(Long id);
}
