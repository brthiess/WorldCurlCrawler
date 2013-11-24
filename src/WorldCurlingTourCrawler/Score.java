package WorldCurlingTourCrawler;

import java.io.Serializable;

public class Score implements Serializable{


int[][] scoreboard;
int numberOfEnds;


public Score() {
	this.scoreboard = new int[11][2];
	this.numberOfEnds = 0;
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

public int getNumberOfEnds() {
return this.numberOfEnds;
}

public void setNumberOfEnds(int numberOfEnds) {
	this.numberOfEnds = numberOfEnds;
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
