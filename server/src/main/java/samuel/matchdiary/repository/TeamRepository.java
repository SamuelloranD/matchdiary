package samuel.matchdiary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import samuel.matchdiary.model.Team;

import java.util.List;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {

    @Query(value = "SELECT t.* FROM team t " +
            "JOIN (" +
            "    SELECT team_id, COUNT(*) as total FROM (" +
            "        SELECT team_one_id AS team_id FROM matches " +
            "        UNION ALL " +
            "        SELECT team_two_id AS team_id FROM matches" +
            "    ) sub " +
            "    WHERE team_id IS NOT NULL " +
            "    GROUP BY team_id " +
            "    ORDER BY total DESC " +
            "    LIMIT 1" +
            ") popular ON t.id = popular.team_id", nativeQuery = true)
    Team findMostWatchedTeam();

    List<Team> findAllByOrderByIdAsc();
}