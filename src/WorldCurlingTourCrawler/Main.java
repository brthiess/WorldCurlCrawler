package WorldCurlingTourCrawler;
//Github is fucking painful
import java.io.BufferedReader;
import java.util.ArrayList;

public class Main {

	
public static void main(String[] args) {
ArrayList<Game> games;
Retriever r = new Retriever();
Navigator n = new Navigator();
BufferedReader br;
int j = 0;
String url = "http://www.worldcurl.com/schedule.php?eventtypeid=21";

while (j<100) {
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
System.out.println(teams.get(i).getNameString() + "\tW:" + teams.get(i).getWins() + "\tL:" + teams.get(i).getLosses() + "\tPts Differential:" + teams.get(i).getTeamPtsDifferential() + "\tHammer Differential:" + teams.get(i).getHammerNetPts() + "\t1 pt % with Hammer:" + teams.get(i).getHammerBreakdown(1) + "\t2 pt % with Hammer:" + teams.get(i).getHammerBreakdown(2));
}


for (int i = 0; i < games.size(); i++) {
System.out.println(games.get(i).getHomeTeamString() + "\t" + games.get(i).getHomeScoreString());
System.out.println(games.get(i).getAwayTeamString() + "\t" + games.get(i).getAwayScoreString());
System.out.println();
}
}

//
}
