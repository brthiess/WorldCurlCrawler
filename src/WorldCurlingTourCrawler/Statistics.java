package WorldCurlingTourCrawler;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Statistics {

	ArrayList<Game> games;
	ArrayList<Team> teams;

	public Statistics(ArrayList<Game> games) {
		this.games = games;
		teams = new ArrayList<Team>();
	}

	/**
* 	Get all the data from the ArrayList 'games' organized. Figure out which teams are in it.
* 	Calculate what each team's win-loss record is and return a sorted ArrayList of Teams.
* 	@return
	*/
	public ArrayList<Team> UpdateStatistics() {
		createListOfTeams();
		equivocateTeams();	//Make Java treat teams with the same players as the same teams
		addTeamRecords();
		addTeamStatistics();
		addHammerStats();
		//Sort the teams
		sortTeams();		
		writeToFile();
		return teams;
	}
	//
	
	private void sortTeams() {
		TeamSorter ts = new TeamSorter(teams);
		teams = ts.sortTeamsByName();
	}
	
	private void writeToFile() {
		FileOutputStream fos;
		try {
			fos = new FileOutputStream("teams.tmp");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(teams);
			oos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private void createListOfTeams() {
		Team h;
		Team a;
		for (int i = 0; i < games.size(); i++) {
			h = games.get(i).getHomeTeam();
			a = games.get(i).getAwayTeam();
			if (!teamAlreadyInList(h)) {
				teams.add(h);
			}
			if (!teamAlreadyInList(a)) {
				teams.add(a);
			}
		}	
	}
	
	private void equivocateTeams() {
		for (int i = 0; i < teams.size(); i++) {
			for ( int j = 0; j < games.size(); j++) {
//	If the team in 'games' equals the team in 'teams' then the 'games' one gets equaled to the 'teams' one
				if (games.get(j).getHomeTeamString().contains(teams.get(i).getNameString())) {
					games.get(j).setHomeTeam(teams.get(i));
				}
				else if (games.get(j).getAwayTeamString().contains(teams.get(i).getNameString())) {
					games.get(j).setAwayTeam(teams.get(i));
				}
			}
		}
	}
	
	private boolean teamAlreadyInList(Team t) {
		for (int i = 0; i < teams.size(); i++) {
			if (t.getNameString().contentEquals(teams.get(i).getNameString())) {
				return true;
			}
		}
		return false;
	}
	
	private void addTeamRecords() {
		Team w, l;
		for (int i = 0; i < games.size(); i++) {
			w = findWinner(games.get(i));	
			l = findLoser(games.get(i));			
//	Add wins and losses to each team
			w.addWin(games.get(i));
			l.addLoss(games.get(i));
		}
	}
	
	private Team findWinner(Game g) {
		int totalScoreA = 0;
		int totalScoreB = 0;
		Score score;
		score = g.getScore();
		
//	Total score for team A
		for (int i = 0; i < score.getLength(); i++) {
			totalScoreA += score.getScore(i, 0);
		}
		
//	Total score for Team B
		for (int i = 0; i < score.getLength(); i++) {
			totalScoreB += score.getScore(i, 1);
		}
		
//	Check who has greater score (i.e. who won)
		if (totalScoreA >= totalScoreB) {
			return g.getHomeTeam();
		}
		else return g.getAwayTeam();
	}
	
	private Team findLoser(Game g) {
		int totalScoreA = 0;
		int totalScoreB = 0;
		Score score;
		score = g.getScore();
		
//	Total score for team A
		for (int i = 0; i < score.getLength(); i++) {
			totalScoreA += score.getScore(i, 0);
		}
		
//	Total score for Team B
		for (int i = 0; i < score.getLength(); i++) {
			totalScoreB += score.getScore(i, 1);
		}
		
//	Check who has lesser score (i.e. who lost)
		if (totalScoreA >= totalScoreB) {
			return g.getAwayTeam();
		}
		else return g.getHomeTeam();
	}
	
	/**
* 	Goes through every team and adds their pts. differential
	*/
	private void addTeamStatistics() {
		addPtsDifferential();	
		addHammerStats();
	}
	
	private void addPtsDifferential() {
		for (int i = 0; i < teams.size(); i++) {
			TeamStatistics ts = new TeamStatistics(teams.get(i));	
			teams.get(i).setTeamPtsDifferential(ts.getTeamPtsDifferential());	
		}
	}
	
	private void addHammerStats() {
		addHammerDifferential();
		addPointsBreakdownHammer();
	}
	/**
 * 	Goes through every team one by one and adds their net hammer points
 	*/
	private void addHammerDifferential() {
		for (int i = 0; i < teams.size(); i++) {
			TeamStatistics ts = new TeamStatistics(teams.get(i));
			teams.get(i).setHammerNetPts(ts.getHammerNetPts());
			}
	}
	
	private void addPointsBreakdownHammer() {
		for (int i = 0; i < teams.size(); i++) {
			TeamStatistics ts = new TeamStatistics(teams.get(i));
			for (int j = -2; j < 4; j++) {
				teams.get(i).setHammerBreakdown(j, ts.getHammerBreakdown(j));
			}
		}
	}
	
	private void addPointsBreakdownNotHammer() {
		for (int i = 0; i < teams.size(); i++) {
			TeamStatistics ts = new TeamStatistics(teams.get(i));
			for (int j = -3; j < 3; j++) {
				teams.get(i).setNotHammerBreakdown(j, ts.getNotHammerBreakdown(j));
			}
		}
	}
}
