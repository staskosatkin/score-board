package dev.football.sports.domain.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TeamTest {

    @Test
    void testTeam_assertName() {
        Team team = new Team("Test Name");

        assertEquals("Test Name", team.getName());
    }
}