package dev.football.sports.examples;

import dev.football.sports.api.MatchBoard;
import dev.football.sports.api.MatchBoardBuilder;
import dev.football.sports.api.MatchView;

public class QuickStart {
    public static void main(String[] args) throws InterruptedException {
        MatchBoard matchBoard = MatchBoardBuilder.MatchBoardInMemory();

        MatchView mc = matchBoard.startMatch("Mexico", "Canada");
        Thread.sleep(10);
        MatchView sb = matchBoard.startMatch("Spain", "Brazil");
        Thread.sleep(10);
        MatchView gf = matchBoard.startMatch("Germany", "France");
        Thread.sleep(10);
        MatchView ui = matchBoard.startMatch("Uruguay", "Italy");
        Thread.sleep(10);
        MatchView aa = matchBoard.startMatch("Argentina", "Australia");

        matchBoard.updateScore(mc.getId(), 0, 5);
        matchBoard.updateScore(sb.getId(), 10, 2);
        matchBoard.updateScore(gf.getId(), 2, 2);
        matchBoard.updateScore(ui.getId(), 6, 6);
        matchBoard.updateScore(aa.getId(), 3, 1);

        matchBoard.getSummaryByTotalScore().forEach(m -> System.out.printf("%s %d - %s - %d%n", m.getTeamHomeName(), m.getTeamHomeScore(), m.getTeamAwayName(), m.getTeamAwayScore()));

        MatchView uu = matchBoard.startMatch("USA", "UK");

        matchBoard.finishMatch(ui.getId());
        matchBoard.updateScore(uu.getId(), 4, 0);

        System.out.println("=============");

        matchBoard.getSummaryByTotalScore().forEach(m -> System.out.printf("%s %d - %s - %d%n", m.getTeamHomeName(), m.getTeamHomeScore(), m.getTeamAwayName(), m.getTeamAwayScore()));
    }
}
