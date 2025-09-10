package dev.football.sports.api;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MatchViewTest {

    @Test
    void testMatchView_getters() {
        MatchView matchView = new MatchView("some id", "Team Home", "Team Away", 2, 3);
        assertEquals("some id", matchView.getId());
        assertEquals("Team Home", matchView.getTeamHomeName());
        assertEquals("Team Away", matchView.getTeamAwayName());
        assertEquals(2, matchView.getTeamHomeScore());
        assertEquals(3, matchView.getTeamAwayScore());
    }
}