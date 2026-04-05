package pjatk.tpo.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/data")

public class AnimeController {

    private final AnimeService animeService;
    public AnimeController(AnimeService animeService) {
        this.animeService = animeService;
    }

    // do maljacej oceny wyswietlania w postman
    // /data?sort=rate_desc
    @GetMapping()
    public List<AnimeDto> getAllAnime(@RequestParam(required = false) String sort) {
        return animeService.getAllAnime(sort);
    }

    @PostMapping
    public ResponseEntity<AnimeDto> createAnime(@RequestBody AnimeDto animeDto) {
        AnimeDto created = animeService.createAnime(animeDto);
        return ResponseEntity.status(201).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AnimeDto> updateAnime(@PathVariable Long id, @RequestBody AnimeDto animeDto) {
        AnimeDto updated = animeService.updateAnime(id, animeDto);
        if (updated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAnime(@PathVariable Long id) {
        boolean deleted = animeService.deleteAnime(id);
        if (!deleted) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }

}
