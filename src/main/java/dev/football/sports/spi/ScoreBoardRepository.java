package dev.football.sports.spi;

import dev.football.sports.domain.entity.Match;
import dev.football.sports.domain.entity.MatchId;

import java.util.List;

public interface ScoreBoardRepository {

    List<Match> findAll();

    Match findByMatchId(MatchId matchId);

    void storeMatch(Match match);

    boolean removeMatch(MatchId matchId);
}
