package dev.football.sports.api;

import dev.football.sports.api.errors.MatchNotFoundException;

import java.util.List;

public interface MatchBoard {
    MatchView startMatch(String teamHomeName, String teamAwayName);
    MatchView finishMatch(String matchId) throws MatchNotFoundException;
    MatchView updateScore(String matchId, int home, int away) throws MatchNotFoundException;
    List<MatchView> getSummaryByTotalScore();
}
