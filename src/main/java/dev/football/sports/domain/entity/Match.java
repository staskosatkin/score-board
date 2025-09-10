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
        this.matchId = Objects.requireNonNull(matchId);
        this.score = Objects.requireNonNull(score);
        this.home = Objects.requireNonNull(home);
        this.away = Objects.requireNonNull(away);
        this.startTime = Objects.requireNonNull(startTime);
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

    public static MatchBuilder builder() {
        return new MatchBuilder();
    }

    public static class MatchBuilder {
        private MatchId matchId;
        private Score score;
        private Team home;
        private Team away;
        private Instant startTime;

        public MatchBuilder fromEntity(Match match) {
            matchId = match.matchId;
            score = match.score;
            home = match.home;
            away = match.away;
            startTime = match.startTime;
            return this;
        }

        public MatchBuilder setMatchId(MatchId matchId) {
            this.matchId = matchId;
            return this;
        }

        public MatchBuilder setScore(Score score) {
            this.score = score;
            return this;
        }

        public MatchBuilder setHome(Team home) {
            this.home = home;
            return this;
        }

        public MatchBuilder setAway(Team away) {
            this.away = away;
            return this;
        }

        public MatchBuilder setStartTime(Instant startTime) {
            this.startTime = startTime;
            return this;
        }

        public Match build() {
            return new Match(matchId, score, home, away, startTime);
        }
    }
}
