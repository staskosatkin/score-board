package dev.football.sports.spi;

import java.time.Instant;

@FunctionalInterface
public interface Clock {
    Instant now();
}
