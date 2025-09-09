package dev.football.sports.impl;

import dev.football.sports.domain.entity.Match;
import dev.football.sports.domain.entity.MatchId;
import dev.football.sports.domain.entity.Score;
import dev.football.sports.domain.entity.Team;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryScoreBoardRepositoryImplTest {
    private InMemoryScoreBoardRepositoryImpl repository;

    @BeforeEach
    void setUp() {
        repository = new InMemoryScoreBoardRepositoryImpl();
    }

    @Test
    void testFindAll_size() {
        repository.storeMatch(new Match(MatchId.random(), new Score(0, 0), new Team("Home"), new Team("Away"), Instant.now()));
        repository.storeMatch(new Match(MatchId.random(), new Score(1, 1), new Team("Home"), new Team("Away"), Instant.now()));
        repository.storeMatch(new Match(MatchId.random(), new Score(2, 3), new Team("Home"), new Team("Away"), Instant.now()));

        assertEquals(3, repository.findAll().size());
    }

    @Test
    void testFindByMatchId_correctFind() {
        MatchId matchId = MatchId.random();
        repository.storeMatch(new Match(matchId, new Score(0, 0), new Team("Home"), new Team("Away"), Instant.now()));

        Match match = repository.findByMatchId(matchId);

        assertEquals("Home", match.getHome().getName());
        assertEquals("Away", match.getAway().getName());
        assertEquals(0, match.getScore().getHome());
        assertEquals(0, match.getScore().getAway());
    }

    @Test
    void testFindByMatchId_notExists() {
        assertNull(repository.findByMatchId(MatchId.random()));
    }

    @Test
    void testStoreMatch_correct() {
        Match match = new Match(MatchId.random(), new Score(1, 1), new Team("Home"), new Team("Away"), Instant.now());
        repository.storeMatch(match);
        assertEquals(match, repository.findByMatchId(match.getMatchId()));
    }

    @Test
    void testRemoveMatch_correct() {
        Match match = new Match(MatchId.random(), new Score(1, 1), new Team("Home"), new Team("Away"), Instant.now());
        repository.storeMatch(match);

        assertTrue(repository.removeMatch(match.getMatchId()));
        assertNull(repository.findByMatchId(match.getMatchId()));
    }
}