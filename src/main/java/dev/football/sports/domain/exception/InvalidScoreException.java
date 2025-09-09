package dev.football.sports.domain.exception;

public class InvalidScoreException extends RuntimeException {
    public InvalidScoreException(String s) {
        super(s);
    }
}
