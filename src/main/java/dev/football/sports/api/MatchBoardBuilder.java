package dev.football.sports.api;

import dev.football.sports.internal.DefaultMatchBoard;
import dev.football.sports.internal.InMemoryScoreBoardRepositoryImpl;
import dev.football.sports.spi.Clock;
import dev.football.sports.spi.ScoreBoardRepository;

public class MatchBoardBuilder {
    private ScoreBoardRepository scoreBoardRepository;
    private Clock clock;

    public MatchBoardBuilder setScoreBoardRepository(ScoreBoardRepository scoreBoardRepository) {
        this.scoreBoardRepository = scoreBoardRepository;
        return this;
    }

    public MatchBoardBuilder setClock(Clock clock) {
        this.clock = clock;
        return this;
    }

    public static MatchBoardBuilder builder() {
        return new MatchBoardBuilder();
    }

    public MatchBoard build() {
        return new DefaultMatchBoard(scoreBoardRepository, clock);
    }

    public static MatchBoard MatchBoardInMemory() {
        return MatchBoardBuilder.builder().
                setScoreBoardRepository(new InMemoryScoreBoardRepositoryImpl()).
                build();
    }

    public static MatchBoard MatchBoardCustom(ScoreBoardRepository scoreBoardRepository) {
        return MatchBoardBuilder.builder().
                setScoreBoardRepository(scoreBoardRepository).
                build();
    }
}