package dev.football.sports.domain.entity;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class MatchIdTest {

    @Test
    void testMatchId_assertUuid() {
        UUID uuid = UUID.randomUUID();
        MatchId matchId = new MatchId(uuid);
        assertEquals(uuid, matchId.getUuid());
    }
}