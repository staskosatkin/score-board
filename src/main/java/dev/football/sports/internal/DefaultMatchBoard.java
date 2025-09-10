package dev.football.sports.internal;

import dev.football.sports.api.MatchBoard;
import dev.football.sports.api.errors.MatchNotFoundException;
import dev.football.sports.api.MatchView;
import dev.football.sports.domain.entity.Match;
import dev.football.sports.domain.entity.MatchId;
import dev.football.sports.domain.entity.Score;
import dev.football.sports.domain.entity.Team;
import dev.football.sports.spi.Clock;
import dev.football.sports.spi.ScoreBoardRepository;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

public class DefaultMatchBoard implements MatchBoard {
    private final ScoreBoardRepository scoreBoardRepository;
    private final Clock clock;

    public DefaultMatchBoard(ScoreBoardRepository scoreBoardRepository, Clock clock) {
        this.scoreBoardRepository = Objects.requireNonNull(scoreBoardRepository);
        this.clock = clock;
    }

    public MatchView startMatch(String teamHomeName, String teamAwayName) {
        Match match = Match.builder().
                setMatchId(MatchId.random()).
                setScore(Score.zero()).
                setHome(new Team(teamHomeName)).
                setAway(new Team(teamAwayName)).
                setStartTime(clock.now()).
                build();

        scoreBoardRepository.storeMatch(match);
        return MatchAdapter.viewOf(match);
    }

    public MatchView finishMatch(String matchId) throws MatchNotFoundException {
        Match match = scoreBoardRepository.removeMatch(new MatchId(UUID.fromString(matchId)));
        if (match == null) {
            throw new MatchNotFoundException(String.format("Match with id %s not found", matchId));
        }
        return MatchAdapter.viewOf(match);
    }

    public MatchView updateScore(String matchId, int home, int away) throws MatchNotFoundException {
        Match oldMatch = scoreBoardRepository.findByMatchId(MatchId.fromString(matchId));
        if (oldMatch == null) {
            throw new MatchNotFoundException(String.format("Match with id %s not found", matchId));
        }
        Match newMatch = Match.builder().
                fromEntity(oldMatch).
                setScore(new Score(home,away)).
                build();
        scoreBoardRepository.storeMatch(newMatch);
        return MatchAdapter.viewOf(newMatch);
    }

    public List<MatchView> getSummaryByTotalScore() {
        return scoreBoardRepository.findAll().stream().
                sorted(Comparator.comparingInt((Match m) -> m.getScore().getHome() + m.getScore().getAway())
                        .reversed()
                        .thenComparing(Match::getStartTime)).
                map(MatchAdapter::viewOf).
                collect(Collectors.toList());
    }
}
