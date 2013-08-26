package WorldCurlingTourCrawler;

public class Score {

int[][] scoreboard; //First dimension represents end. Second dimension represents which team. (0 or 1)

public Score(int[][] scoreboard) {
this.scoreboard = scoreboard;
}

public Score() {
}

public String getHomeScoreInString() {
String s = "";
for (int i = 0; i<scoreboard.length; i++) {
s = s.concat(" " + Integer.toString(scoreboard[i][0]));
}
return s;
}

public String getAwayScoreInString() {
String s = "";
for (int i = 0; i<scoreboard.length; i++) {
s = s.concat(" " + Integer.toString(scoreboard[i][1]));
}
return s;
}

public int numberOfEnds() {
return this.getLength();
}

public void addScore(int endNumber, int numberOfPoints, int teamNumber) {
scoreboard[endNumber][teamNumber] = numberOfPoints;
}

public int getScore(int endNumber, int teamNumber) {
return scoreboard[endNumber][teamNumber];
}

public int getLength() {
int length = 8;
for (int i = length; i >= 0; i--) {
if ( (scoreboard[length][0] == 0) && scoreboard[length][1] == 0) {
length--;
}
else break;
}
return length+1;
}
}
