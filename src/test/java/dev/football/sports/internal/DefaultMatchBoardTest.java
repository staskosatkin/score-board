package dev.football.sports.internal;

import dev.football.sports.api.errors.InvalidScoreException;
import dev.football.sports.api.errors.MatchNotFoundException;
import dev.football.sports.api.MatchView;
import dev.football.sports.domain.entity.Match;
import dev.football.sports.domain.entity.MatchId;
import dev.football.sports.domain.entity.Score;
import dev.football.sports.domain.entity.Team;
import dev.football.sports.spi.Clock;
import dev.football.sports.spi.ScoreBoardRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DefaultMatchBoardTest {
    private DefaultMatchBoard matchBoard;

    @Mock
    private ScoreBoardRepository scoreBoardRepository;

    private Match match;

    Instant startTime = Instant.now();

    @BeforeEach
    void setUp() {
        scoreBoardRepository = mock(ScoreBoardRepository.class);
        Clock clock = () -> startTime;
        matchBoard = new DefaultMatchBoard(scoreBoardRepository, clock);

        match = Match.builder().setMatchId(MatchId.random()).setScore(Score.zero()).setHome(new Team("home")).setAway(new Team("away")).setStartTime(startTime).build();
    }

    @Test
    void testStartMatch_success() {
        MatchView view = matchBoard.startMatch("home", "away");
        assertEquals("home", view.getTeamHomeName());
        assertEquals("away", view.getTeamAwayName());
        assertEquals(0, view.getTeamHomeScore());
        assertEquals(0, view.getTeamAwayScore());
    }

    @Test
    void testFinishMatch_success() {
        when(scoreBoardRepository.removeMatch(any())).thenReturn(match);

        MatchView view = matchBoard.finishMatch(match.getMatchId().getUuid().toString());

        assertEquals(match.getMatchId().getUuid().toString(), view.getId());

        verify(scoreBoardRepository, times(1)).removeMatch(match.getMatchId());
    }

    @Test
    void testFinishMatch_matchNotFound() {
        when(scoreBoardRepository.removeMatch(any())).thenReturn(null);
        assertThrowsExactly(MatchNotFoundException.class, () -> matchBoard.finishMatch(match.getMatchId().getUuid().toString()));
    }

    @Test
    void testUpdateScore_success() {
        when(scoreBoardRepository.findByMatchId(any())).thenReturn(match);
        MatchView view = matchBoard.updateScore(match.getMatchId().getUuid().toString(), 1,1);

        assertEquals(1, view.getTeamHomeScore());
        assertEquals(1, view.getTeamAwayScore());

        verify(scoreBoardRepository, times(1)).storeMatch(any());
    }

    @Test
    void testUpdateScore_matchNotFound() {
        when(scoreBoardRepository.findByMatchId(any())).thenThrow(MatchNotFoundException.class);

        assertThrowsExactly(MatchNotFoundException.class, () -> matchBoard.updateScore(match.getMatchId().getUuid().toString(), 1, 1));

        verify(scoreBoardRepository, times(0)).storeMatch(any());
    }

    @Test
    void testUpdateScore_negativeValues() {
        when(scoreBoardRepository.findByMatchId(any())).thenReturn(match);

        assertThrowsExactly(InvalidScoreException.class, () -> matchBoard.updateScore(match.getMatchId().getUuid().toString(), -1, -1));

        verify(scoreBoardRepository, times(0)).storeMatch(any());
    }

    @Test
    void testGetSummaryByTotalScore_success() {
        Instant now = Instant.now();
        Instant before = now.minusSeconds(1);

        List<Match> matches = List.of(
                Match.builder().setMatchId(new MatchId(UUID.fromString("3f306c2a-bf9b-4cd1-bfe5-9f38778f2540"))).setStartTime(now).setScore(new Score(2,3)).setHome(new Team("home")).setAway(new Team("away")).build(),
                Match.builder().setMatchId(new MatchId(UUID.fromString("3f306c2a-bf9b-4cd1-bfe5-9f38778f2541"))).setStartTime(now).setScore(new Score(5,5)).setHome(new Team("home")).setAway(new Team("away")).build(),
                Match.builder().setMatchId(new MatchId(UUID.fromString("3f306c2a-bf9b-4cd1-bfe5-9f38778f2542"))).setStartTime(before).setScore(new Score(2,3)).setHome(new Team("home")).setAway(new Team("away")).build()
        );

        when(scoreBoardRepository.findAll()).thenReturn(matches);

        List<MatchView> views = matchBoard.getSummaryByTotalScore();

        assertEquals("3f306c2a-bf9b-4cd1-bfe5-9f38778f2540", views.get(2).getId());
        assertEquals("3f306c2a-bf9b-4cd1-bfe5-9f38778f2541", views.get(0).getId());
        assertEquals("3f306c2a-bf9b-4cd1-bfe5-9f38778f2542", views.get(1).getId());
    }
}