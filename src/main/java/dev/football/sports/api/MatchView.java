package dev.football.sports.api;

import java.io.Serializable;
import java.util.Objects;

public class MatchView implements Serializable {
    private final String id;
    private final String teamHomeName;
    private final String teamAwayName;
    private final int teamHomeScore;
    private final int teamAwayScore;

    public MatchView(String id, String teamHomeName, String teamAwayName, int teamHomeScore, int teamAwayScore) {
        this.id = id;
        this.teamHomeName = teamHomeName;
        this.teamAwayName = teamAwayName;
        this.teamHomeScore = teamHomeScore;
        this.teamAwayScore = teamAwayScore;
    }

    public String getId() {
        return id;
    }

    public String getTeamHomeName() {
        return teamHomeName;
    }

    public String getTeamAwayName() {
        return teamAwayName;
    }

    public int getTeamHomeScore() {
        return teamHomeScore;
    }

    public int getTeamAwayScore() {
        return teamAwayScore;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        MatchView matchView = (MatchView) o;
        return teamHomeScore == matchView.teamHomeScore && teamAwayScore == matchView.teamAwayScore && Objects.equals(id, matchView.id) && Objects.equals(teamHomeName, matchView.teamHomeName) && Objects.equals(teamAwayName, matchView.teamAwayName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, teamHomeName, teamAwayName, teamHomeScore, teamAwayScore);
    }
}
