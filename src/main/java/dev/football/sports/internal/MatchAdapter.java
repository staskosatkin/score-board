package dev.football.sports.internal;

import dev.football.sports.api.MatchView;
import dev.football.sports.domain.entity.Match;

public class MatchAdapter {
    public static MatchView viewOf(Match match) {
        return new MatchView(match.getMatchId().getUuid().toString(),
                match.getHome().getName(),
                match.getAway().getName(),
                match.getScore().getHome(),
                match.getScore().getAway());
    }
}
