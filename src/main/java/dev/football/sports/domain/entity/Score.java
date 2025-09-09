package dev.football.sports.domain.entity;

import java.util.Objects;

public class Score {
    private final int home;
    private final int away;

    public Score(int home, int away) {
        this.home = home;
        this.away = away;
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
