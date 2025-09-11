## About the project

This is a simple football score bard. It shows the list of ongoing matches with their scores sorted by total score.

## Installation

This library is available on GitHub.

Maven Installation
```maven
<dependency>
  <groupId>io.github.staskosatkin</groupId>
  <artifactId>score-board</artifactId>
  <version>1.0.1</version>
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

## Notes

I tried to follow as simple solution as possible
This library can be created by builder, but also can be instantiated in spring boot as a bean
Basic in-memory storage is a simple hashmap. Currently I don't have any concurrent operation, but for incrementing operation it will require new storage implementation based on threadsafe structures, such as ConcurrentHashMap

## Ideas for improvement

- Store teams in db as well
- Add match statuses (Scheduled/started/finished/canceled/delayed)
- Add interface allows increase/decrease score for home/away teams
- gradle support