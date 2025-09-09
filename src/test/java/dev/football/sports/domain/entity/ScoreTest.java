package dev.football.sports.domain.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ScoreTest {

    @Test
    void testScore_getters() {
        Score score = new Score(1,2);
        assertEquals(1, score.getHome());
        assertEquals(2, score.getAway());
    }
}