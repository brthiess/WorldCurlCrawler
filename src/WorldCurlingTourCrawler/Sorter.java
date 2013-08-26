package WorldCurlingTourCrawler;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

/**
* This class takes in data from a buffered reader and sorts it game by game
*
* @author Drad
*
*/
public class Sorter {
String LINESCORETEAM = "linescoreteam";
String LINESCOREEND = "linescoreend";
String LINESCOREHEAD = "linescorehead";
String HAMMER = "linescorehammer";
BufferedReader br;
ArrayList<Game> games;
/**
* Constructor
* @param br Data from the WCT website
*/
public Sorter(BufferedReader br) {
this.br = br;
games = new ArrayList<Game>();
}

public Sorter() {
games = new ArrayList<Game>();
}

public void addBufferedReader(BufferedReader br) {
this.br = br;
}
/**
* Sorts the data game by game
* @return Returns a readable string
* of statistics of all the teams
* from all the games it received
*/
public ArrayList<Game> sort() {
String wctSource = "";
int numberOfTeamsLeftToBeScanned = 2;
Team A = new Team("Default A");
Team B = new Team("Default B");
Team hammerTeam = new Team("Hammer");
Score score = new Score(new int[9][2]);
boolean teamALinescoreHasBeenChecked = false;
boolean teamBLinescoreHasBeenChecked = false;
try {
wctSource = br.readLine();
while (wctSource != null) {

if (wctSource.contains(LINESCOREHEAD)) {	//Checks for beginning of a new linescore
numberOfTeamsLeftToBeScanned = 2;
score = new Score(new int[9][2]);
}

if (wctSource.contains(LINESCORETEAM)) {	//Checks for the team name
numberOfTeamsLeftToBeScanned--;
if (numberOfTeamsLeftToBeScanned == 1) {
A = new Team(wctSource.split("[<>]")[6]); //Set the team name for team A
}
else {
B = new Team(wctSource.split("[<>]")[6]); //Set the team name for team B
}	
}

if (wctSource.contains(LINESCOREEND)) { //Scans the linescores and adds them
if (teamALinescoreHasBeenChecked) {
teamBLinescoreHasBeenChecked = true;
}
score = getScore(wctSource, teamALinescoreHasBeenChecked, score);
teamALinescoreHasBeenChecked = true;
}

if (wctSource.contains(HAMMER)) {
if (wctSource.contains("http://www.curlingzone.com/forums/images/hammer.gif")) {
if (teamALinescoreHasBeenChecked) {
hammerTeam = B;
}
else {
hammerTeam = A;
}
}
}

if (numberOfTeamsLeftToBeScanned == 0 && teamALinescoreHasBeenChecked && teamBLinescoreHasBeenChecked) {
games.add(new Game(A, B, score, hammerTeam));
teamALinescoreHasBeenChecked = false;
teamBLinescoreHasBeenChecked = false;
numberOfTeamsLeftToBeScanned = 2;
}
wctSource = br.readLine();
}
} catch (IOException e) {
// TODO Auto-generated catch block
e.printStackTrace();
}
return games;
}

private static Score getScore(String wctSource, boolean teamALinescoreHasBeenAdded, Score scoreboard) {
int team = 0;	//integer 0 represents team A, integer 1 represents Team B
if (teamALinescoreHasBeenAdded) {
team = 1;
}
for (int i = 0; i<9; i++) {	
String endScore = wctSource.split("[&;]")[(i+1)*4-2]; //(i+1)*4-2 is the conversion for the html to find the scores for each end
if (!endScore.contains("X") && !endScore.contentEquals("") && !endScore.contains("<b>")) {
scoreboard.addScore(i,Integer.parseInt(endScore), team);
}
}
//Score score = new Score(scoreboard);
return scoreboard;
}
}
