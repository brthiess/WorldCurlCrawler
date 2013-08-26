package WorldCurlingTourCrawler;

import java.io.BufferedReader;
import java.util.ArrayList;

public class Main {

public static void main(String[] args) {
ArrayList<Game> games;
Retriever r = new Retriever();
Navigator n = new Navigator();
BufferedReader br;
int j = 0;
String url = "http://www.worldcurl.com/schedule.php?eventtypeid=21&eventyear=2013";

while (j<5) {
br = r.getPageSource(url);
r.Retrieve(url);
url = n.nextURL(br);
j++;
System.out.println(url);
}

games = r.Retrieve(url);
Statistics s = new Statistics(games);
ArrayList<Team> teams = s.UpdateStatistics();

for (int i = 0; i < teams.size(); i++) {
System.out.println(teams.get(i).getNameString() + " W:" + teams.get(i).getWins() + " L:" + teams.get(i).getLosses() + " Pts Differential:" + teams.get(i).getTeamPtsDifferential() + " Hammer Differential:" + teams.get(i).getHammerNetPts());
}


/*for (int i = 0; i < games.size(); i++) {
System.out.println(games.get(i).getHomeTeamString() + "\t" + games.get(i).getHomeScoreString());
System.out.println(games.get(i).getAwayTeamString() + "\t" + games.get(i).getAwayScoreString());
System.out.println();
}*/
}

//
}
