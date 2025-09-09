package dev.football.sports.impl;

import dev.football.sports.domain.entity.Match;
import dev.football.sports.domain.entity.MatchId;
import dev.football.sports.spi.ScoreBoardRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryScoreBoardRepositoryImpl implements ScoreBoardRepository {
    private final Map<MatchId, Match> map = new HashMap<>();

    @Override
    public List<Match> findAll() {
        return new ArrayList<>(map.values());
    }

    @Override
    public Match findByMatchId(MatchId matchId) {
        return map.get(matchId);
    }

    @Override
    public void storeMatch(Match match) {
        map.put(match.getMatchId(), match);
    }

    @Override
    public boolean removeMatch(MatchId matchId) {
        return map.remove(matchId) != null;
    }
}
