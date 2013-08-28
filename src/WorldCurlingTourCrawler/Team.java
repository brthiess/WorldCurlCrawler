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
double hammerBreakdownSteal2Pt, hammerBreakdownSteal1Pt, hammerBreakdown0Pt, hammerBreakdown1Pt, hammerBreakdown2pt, hammerBreakdown3pt;
double notHammerBreakdownSteal2Pt, notHammerBreakdownSteal1Pt, notHammerBreakdown0Pt, notHammerBreakdown1Pt, notHammerBreakdown2pt, notHammerBreakdown3pt;
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
	
	public void setHammerBreakdown(int X, double percentage) {
		if (X == -2) {
			this.hammerBreakdownSteal2Pt = percentage;
		}
		
		if (X == -1) {
			this.hammerBreakdownSteal2Pt = percentage;
		}
		if (X == -2) {
			this.hammerBreakdownSteal1Pt = percentage;
		}
		if (X == 1) {
			this.hammerBreakdown1Pt = percentage;
		}
		if (X == 2) {
			this.hammerBreakdown2pt = percentage;
		}
		if (X == 3) {
			this.hammerBreakdown3pt = percentage;
		}
	
	
	}
	public double getHammerBreakdown(int X) {
	if (X == -2) {
		return this.hammerBreakdownSteal2Pt;
	}
	else if (X == -1) {
		return this.hammerBreakdownSteal1Pt;
	}
	else if (X == 0) {
		return this.hammerBreakdown0Pt;
	}
	else if (X == 1) {
		return this.hammerBreakdown1Pt;
	}
	else if (X == 2) {
		return this.hammerBreakdown2pt;
	}
	else if (X == 3) {
		return this.hammerBreakdown3pt;
	}
		
return -1;
	}
	
	public double getNotHammerBreakdown(int X) {
		if (X == -3) {
			return this.notHammerBreakdownSteal2Pt;
		}
		else if (X == -2) {
			return this.notHammerBreakdownSteal1Pt;
		}
		else if (X == -1) {
			return this.notHammerBreakdown0Pt;
		}
		else if (X == 0) {
			return this.notHammerBreakdown1Pt;
		}
		else if (X == 1) {
			return this.notHammerBreakdown2pt;
		}
		else if (X == 2) {
			return this.notHammerBreakdown3pt;
		}
			
	return 8;
		}
	
	public void setNotHammerBreakdown(int X, double percentage) {
		if (X == -3) {
			this.notHammerBreakdown3pt = percentage;
		}
		else if (X == -2) {
			this.notHammerBreakdownSteal2Pt = percentage;
		}		
		else if (X == -1) {
			this.notHammerBreakdownSteal2Pt = percentage;
		}
		else if (X == -2) {
			this.notHammerBreakdownSteal1Pt = percentage;
		}
		else if (X == 1) {
			this.notHammerBreakdown1Pt = percentage;
		}
		else if (X == 2) {
			this.notHammerBreakdown2pt = percentage;
		}
		
	}
}
	