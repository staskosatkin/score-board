package dev.football.sports.domain.entity;

import java.time.Instant;
import java.util.Objects;

public class Match {
    private final MatchId matchId;
    private final Score score;
    private final Team home;
    private final Team away;
    private final Instant startTime;

    public Match(MatchId matchId, Score score, Team home, Team away, Instant startTime) {
        this.matchId = matchId;
        this.score = score;
        this.home = home;
        this.away = away;
        this.startTime = startTime;
    }

    public MatchId getMatchId() {
        return matchId;
    }

    public Score getScore() {
        return score;
    }

    public Team getHome() {
        return home;
    }

    public Team getAway() {
        return away;
    }

    public Instant getStartTime() {
        return startTime;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Match match = (Match) o;
        return Objects.equals(matchId, match.matchId) && Objects.equals(score, match.score) && Objects.equals(home, match.home) && Objects.equals(away, match.away) && Objects.equals(startTime, match.startTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(matchId, score, home, away, startTime);
    }
}
