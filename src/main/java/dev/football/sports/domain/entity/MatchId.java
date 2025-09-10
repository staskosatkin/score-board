package dev.football.sports.domain.entity;

import java.util.Objects;
import java.util.UUID;

public class MatchId {
    private final UUID uuid;

    public MatchId(UUID uuid) {
        this.uuid = Objects.requireNonNull(uuid);
    }

    public static MatchId random() {
        return new MatchId(UUID.randomUUID());
    }

    public static MatchId fromString(String matchId) {
        return new MatchId(UUID.fromString(matchId));
    }

    public UUID getUuid() {
        return uuid;
    }

    public String toString() {
        return uuid.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        MatchId matchId = (MatchId) o;
        return Objects.equals(uuid, matchId.uuid);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(uuid);
    }
}
