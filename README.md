## About the project

This is a simple football score bard. It shows the list of ongoing matches with their scores sorted by total score.

## Installation

This library is available on GitHub.

Maven Installation
```maven
<dependency>
  <groupId>io.github.staskosatkin</groupId>
  <artifactId>score-board</artifactId>
  <version>1.0.0</version>
</dependency>
```
}

## Usage

### Basic implementation provides in-memory storage

```java
// Create default MatchBoard object
MatchBoard matchBoard = MatchBoardBuilder.MatchBoardInMemory();

// Add as many matches as you want
MatchView mc = matchBoard.startMatch("Mexico", "Canada");
MatchView aa = matchBoard.startMatch("Argentina", "Australia");


// Finish any match by matchId
MatchView finalMatchState = matchBoard.finishMatch("3f306c2a-bf9b-4cd1-bfe5-9f38778f2544");

// Update match score
matchBoard.updateScore(aa.getId(), 3, 1);

// Receive current matches ordered by total score
List<MatchView> currentMatches = matchBoard.getSummaryByTotalScore();
```

### This implementation can be replaced with your own

```java
import dev.football.sports.api.MatchBoard;

// Custom Implementation of ScoreBoardRepository
class MyOwnStorage implements ScoreBoardRepository {
}

// MatchBoardBuilder provides possibility to create customized version of the service
MatchBoard matchBoard = MatchBoardBuilder.MatchBoardCustom(myOwnStorageObject);
```