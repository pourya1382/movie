package com.example.movie.director.controller;
import com.example.movie.director.model.Director;
import com.example.movie.director.model.DirectorDto;
import com.example.movie.director.service.DirectorService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "directors")
public class DirectorController {
    private final DirectorService directorService;

    public DirectorController(DirectorService directorService) {
        this.directorService = directorService;
    }

    @GetMapping()
    public Page<Director> getDirector(@RequestParam(defaultValue = "0") int page,
                                      @RequestParam(defaultValue = "3") int size
    ) {
        return directorService.getDirector(page, size);
    }

    @PostMapping(path = "add-director")
    public Director addNewDirector(@RequestBody Director director) {
        return directorService.addNewDirector(director);
    }

    @PutMapping(path = "{directorId}")
    public Director updateMovie(@PathVariable("directorId") Long directorId,
                                @RequestBody DirectorDto directorDto
    ) {
        return directorService.updateMovie(directorId, directorDto);
    }

    @DeleteMapping(path = "{directorId}")
    public void deleteDirector(@PathVariable("directorId") Long directorId) {
        directorService.deleteDirector(directorId);
    }
}
