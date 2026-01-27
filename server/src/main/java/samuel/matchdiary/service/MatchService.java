package samuel.matchdiary.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import samuel.matchdiary.model.Match;
import samuel.matchdiary.repository.MatchRepository; // Certifique-se de ter essa interface

import java.util.List;

@Service
@RequiredArgsConstructor
public class MatchService {

    private final MatchRepository matchRepository;

    public List<Match> getAll() {
        return matchRepository.findAll();
    }

    public Match getById(Long id) {
        return matchRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Partida não encontrada com o ID: " + id));
    }

    public Match save(Match match) {
        return matchRepository.save(match);
    }

    public Match update(Match match) {
        if (match.getId() == null || !matchRepository.existsById(match.getId())) {
            throw new RuntimeException("Não é possível atualizar: Partida inexistente.");
        }
        return matchRepository.save(match);
    }

    public void delete(Long id) {
        if (!matchRepository.existsById(id)) {
            throw new RuntimeException("Não é possível deletar: Partida inexistente.");
        }
        matchRepository.deleteById(id);
    }
}