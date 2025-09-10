package dev.football.sports.domain.entity;

import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MatchTest {

    @Test
    void testMatchGetters() {
        UUID uuid = UUID.randomUUID();
        Instant now = Instant.now();
        Match match = Match.builder()
                .setMatchId(new MatchId(uuid))
                .setScore(new Score(0, 0))
                .setHome(new Team("Home"))
                .setAway(new Team("Away"))
                .setStartTime(now)
                .build();

        assertEquals(uuid.toString(), match.getMatchId().toString());
        assertEquals(new Score(0, 0), match.getScore());
        assertEquals(new Team("Home"), match.getHome());
        assertEquals(new Team("Away"), match.getAway());
        assertEquals(now, match.getStartTime());
    }
}