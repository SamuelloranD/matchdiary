package samuel.matchdiary.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import samuel.matchdiary.model.Team;
import samuel.matchdiary.repository.TeamRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeamService {

    private final TeamRepository teamRepository;

    public List<Team> getAll() {
        return teamRepository.findAll();
    }

    public Team getTeamById(Long id) {
        return teamRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Time não encontrado com o id: " + id));
    }

    public Team save(Team team) {
        return teamRepository.save(team);
    }

    public Team update(Team team) {
        if (team.getId() == null || !teamRepository.existsById(team.getId())) {
            throw new RuntimeException("Não é possível atualizar: Time inexistente.");
        }
        return teamRepository.save(team);
    }

    public void delete(Long id) {
        if (!teamRepository.existsById(id)) {
            throw new RuntimeException("Não é possível deletar: Time inexistente.");
        }
        teamRepository.deleteById(id);
    }
}