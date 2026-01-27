package samuel.matchdiary.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import samuel.matchdiary.model.Match;
import samuel.matchdiary.service.MatchService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/match")
public class MatchController {

    private final MatchService matchService;

    @GetMapping("/all")
    public ResponseEntity<List<Match>> getAllMatches() {
        return ResponseEntity.ok(matchService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Match> getMatchById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(matchService.getById(id));
    }

    @PostMapping
    public ResponseEntity<Match> addMatch(@RequestBody Match match) {
        return new ResponseEntity<>(matchService.save(match), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Match> updateMatch(@RequestBody Match match) {
        return ResponseEntity.ok(matchService.update(match));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMatch(@PathVariable("id") Long id) {
        matchService.delete(id);
        return ResponseEntity.noContent().build();
    }
}