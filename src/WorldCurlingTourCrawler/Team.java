package WorldCurlingTourCrawler;
//
import java.util.ArrayList;

public class Team {
String name;
int numberOfGames;
int wins;
int losses;
double ptsDifferential;
double hammerNetPts;
ArrayList<Game> games;
TeamStatistics teamStats;

public Team(String name) {
this.name = name;
this.numberOfGames = 0;
this.wins = 0;
this.losses = 0;
this.ptsDifferential = 0;
this.hammerNetPts = 0;
this.games = new ArrayList<Game>();
this.teamStats = new TeamStatistics(this);
}

public String getNameString() {
return name;
}

public void addWin(Game g) {
games.add(g);
wins++;
numberOfGames++;
}

public void addLoss(Game g) {
games.add(g);
losses++;

numberOfGames++;
}

public int getWins() {
return wins;
}

public int getLosses() {
return losses;
}

public void addGame(Game g) {
games.add(g);
}

public ArrayList<Game> getGames() {
return games;
}

public void setTeamPtsDifferential(double ptsDifferential) {
this.ptsDifferential = ptsDifferential;
}

public double getTeamPtsDifferential() {
return this.ptsDifferential;
}

public void setHammerNetPts(double hammerNetPts) {
this.hammerNetPts = hammerNetPts;
}

public double getHammerNetPts() {
return this.hammerNetPts;
}
}
