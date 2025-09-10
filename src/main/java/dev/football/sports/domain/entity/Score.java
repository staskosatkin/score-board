package dev.football.sports.domain.entity;

import dev.football.sports.api.errors.InvalidScoreException;

import java.util.Objects;

public class Score {
    private final int home;
    private final int away;

    public Score(int home, int away) {
        if (home < 0 || away < 0) {
            throw new InvalidScoreException("Home or Away score cannot be negative. Provided: " + home + ":" + away);
        }
        this.home = home;
        this.away = away;
    }

    public static Score zero() {
        return new Score(0, 0);
    }

    public int getHome() {
        return home;
    }

    public int getAway() {
        return away;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Score score = (Score) o;
        return home == score.home && away == score.away;
    }

    @Override
    public int hashCode() {
        return Objects.hash(home, away);
    }
}
