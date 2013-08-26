package WorldCurlingTourCrawler;
//
import java.util.ArrayList;

public class TeamStatistics {

Team t;
ArrayList<Game> games; //Games for the one team 't'

public TeamStatistics(Team t) {
this.t = t;
this.games = t.getGames();
}

public double getTeamPtsDifferential() {
double netPoints = 0;
double numberOfEnds = 0;
for (int i = 0; i < games.size(); i++) {	
for (int j = 0; j < games.get(i).getScore().numberOfEnds(); j++) {
netPoints += getNetPts(j, games.get(i));
numberOfEnds++;
}
}
double ptsDifferential = netPoints/numberOfEnds;
return ptsDifferential;	
}

public double getHammerNetPts() {
double netPoints = 0;
double numberOfEnds = 0;
boolean hasHammer = false;

for (int i = 0; i < games.size(); i++) {
for (int j = 0; j < games.get(i).getScore().numberOfEnds(); j++) {
if (hasHammer(j,games.get(i))) {
netPoints += getNetPts(j, games.get(i));
numberOfEnds++;
}
}	
}
double hammerDifferential = netPoints/numberOfEnds;
return hammerDifferential;
}

public int getNetPts(int endNumber, Game g) {
int netPoints = 0;
if (isTeamHomeTeam(g)) {	//If they are the home team, then add the home team's points and subtract the away team's point
netPoints += g.getScore(endNumber,0) - g.getScore(endNumber, 1);
}
else { netPoints += g.getScore(endNumber, 1) - g.getScore(endNumber, 0);
}
return netPoints;
}

public boolean hasHammer(int endNumber, Game g) {
boolean hasHammer = true;

for (int i = endNumber - 1; i >= 0; i--) {
if (getNetPts(i, g) < 0) {
hasHammer = true;
return hasHammer;
}
else if (getNetPts(i, g) > 0) {
hasHammer = false;
return hasHammer;
}
}
return g.isHammerTeam(t);
}

private boolean isTeamHomeTeam(Game g) {
boolean teamIsHomeTeam;
if (t.getNameString().equals(g.getHomeTeamString())) {	//Check if team is the home team or away team
teamIsHomeTeam = true;
}
else {teamIsHomeTeam = false;
}
return teamIsHomeTeam;
}
}
