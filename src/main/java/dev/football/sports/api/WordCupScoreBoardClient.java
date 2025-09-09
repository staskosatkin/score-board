package dev.football.sports.api;

import dev.football.sports.spi.ScoreBoardRepository;

import java.util.List;

public class WordCupScoreBoardClient {
    private final ScoreBoardRepository scoreBoardRepository;

    public WordCupScoreBoardClient(ScoreBoardRepository scoreBoardRepository) {
        this.scoreBoardRepository = scoreBoardRepository;
    }

    public MatchView startMatch() { return null; }

    public MatchView finishMatch(String matchId) { return null; }

    public MatchView updateScore(String matchId, int home, int away) { return null; }

    public List<MatchView> getSummaryByTotalScore() { return null; }
}
