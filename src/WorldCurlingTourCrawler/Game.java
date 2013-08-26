package WorldCurlingTourCrawler;

public class Game {

private Team homeTeam;
private Team awayTeam;
private Score score;
private Team hammerTeam;

public Game(Team homeTeam, Team awayTeam, Score score, Team hammerTeam) {
this.homeTeam = homeTeam;
this.awayTeam = awayTeam;
this.score = score;

if (hammerTeam.getNameString().equals(homeTeam.getNameString())) {
this.hammerTeam = homeTeam;
}
else {
this.hammerTeam = awayTeam;
}
}

public String getHomeTeamString() {
return homeTeam.getNameString();
}

public String getAwayTeamString() {
return awayTeam.getNameString();
}

public String getHomeScoreString() {
return score.getHomeScoreInString();
}

public String getAwayScoreString() {
return score.getAwayScoreInString();
}

public Team getHomeTeam() {
return homeTeam;
}

public void setHomeTeam(Team home) {
this.homeTeam = home;
}

public void setAwayTeam(Team away) {
this.awayTeam = away;
}

public Team getAwayTeam() {
return awayTeam;
}

public Score getScore() {
return score;
}

public int getScore(int endNumber, int teamNumber) {
return score.getScore(endNumber, teamNumber);
}

public void setHammerTeam(Team t) {
hammerTeam = t;
}

public Team getHammerTeam() {
if (hammerTeam.getNameString().equals(homeTeam.getNameString())) {
return homeTeam;
}
else {
return awayTeam;
}
}
public boolean isHammerTeam(Team t) {
if (t.getNameString().contains(hammerTeam.getNameString())) {	//Check if the given team had the hammer to begin with
return true;
}
else return false;
}
}

