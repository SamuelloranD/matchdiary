package samuel.matchdiary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import samuel.matchdiary.model.Match;

import java.util.List;

@Repository
public interface MatchRepository extends JpaRepository<Match, Long> {

    @Query(value = "SELECT CAST(EXTRACT(EPOCH FROM (CURRENT_TIMESTAMP - MAX(date))) / 86400 AS INTEGER) FROM matches", nativeQuery = true)
    Integer getDaysWithoutWatching();

    List<Match> findAllByOrderByDateDesc();
}