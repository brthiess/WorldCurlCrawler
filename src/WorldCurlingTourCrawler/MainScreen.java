package WorldCurlingTourCrawler;

import java.applet.Applet;
import java.awt.Button;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Event;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.util.ArrayList;

public class MainScreen extends Applet implements ActionListener  {

   int width, height;
   ArrayList<Team> teams;
   Button downloadButton, showTeamStats;
   boolean crawlHasFinished, crawlIsInProgress, teamHasBeenClickedOn, showTeamStatsClicked;
   Choice choice;
   int teamWins = 0;

   public void init() {
      /*
       * Display Stuff
       */
	   setLayout(new FlowLayout());
	   width = getSize().width;
      height = getSize().height;
      setBackground( Color.black );
      setFont(new Font("Helvetica", Font.BOLD, 36));
      //Drop Down List
      choice = new Choice();
      choice.setLocation(50, 80);
       
      //Download Button
      downloadButton = new Button("Download");
      add(downloadButton);
      downloadButton.addActionListener(this); 
      
      crawlHasFinished = false;
      teamHasBeenClickedOn = false;
      showTeamStatsClicked = false;
      
      //ShowTeamStats Button
      showTeamStats = new Button("Show Team Stats");
      add(showTeamStats);
      showTeamStats.addActionListener(this);      
   }
   

   public void paint( Graphics g ) {
	   
	   //Displays the stats of the team that
	   //has been clicked on from the drop 
	   //down list
	   if (teamHasBeenClickedOn) {
		   g.setColor(Color.white);
		   g.drawString("" + teamWins, 50, 300);
	   }
	   
	   //If the crawl has finished or the file has been loaded
	   //from the local disk, then
	   //this method gets activated.  It adds
	   //all of the teams to a drop down list
	   if (crawlHasFinished || showTeamStatsClicked) {
	      g.setColor(Color.white);
	      String data = "";
	      	for (int i = 0; i < teams.size(); i++) {
	      		choice.add(teams.get(i).getNameString());
	      			//data += teams.get(i).getNameString() + "\tW:" + teams.get(i).getWins() + "\tL:" + teams.get(i).getLosses() + "\tPts Differential:" + teams.get(i).getTeamPtsDifferential() + "\tHammer Differential:" + teams.get(i).getHammerNetPts() + "\t1 pt % with Hammer:" + teams.get(i).getHammerBreakdown(1) + "\t2 pt % with Hammer:" + teams.get(i).getHammerBreakdown(2) + "\n";
	      	}
	      add(choice);
	      crawlHasFinished = false;
	      showTeamStatsClicked = false;
	   }
	   
   }
   
   private void beginCrawl() {
	      /*
	       * Analytical Stuff
	       */
	      ArrayList<Game> games;
	      Retriever r = new Retriever();
	      Navigator n = new Navigator();
	      BufferedReader br;
	      int j = 0;
	      String url = "http://www.worldcurl.com/schedule.php?eventtypeid=21";

	      while (j<5) {
	    	 
	    	  br = r.getPageSource(url);
	    	  r.Retrieve(url);
	    	  url = n.nextURL(br);
	    	  j++;
	      }

	      games = r.Retrieve(url);
	      Statistics s = new Statistics(games);
	      this.teams = s.UpdateStatistics();

   }
   
   /**
    * This method loads the teams from the file locally stored
    * on the computer
    */
   private void loadTeams() {
	   FileManager fm = new FileManager();
	   this.teams = fm.openFile();
   }
   
   public void actionPerformed(ActionEvent evt)    { 
// Here we will ask what component called this method 
       
	   //Download Teams from Internet
	   if (evt.getSource() == downloadButton)  {
        	crawlIsInProgress = true;
        	beginCrawl();
        	crawlIsInProgress = false;
        	crawlHasFinished = true;
            repaint();
        }
        //Load Teams from File Locally
        if (evt.getSource() == showTeamStats) {
        	loadTeams();
        	showTeamStatsClicked = true;
        	repaint();
        }
                
   }
   public boolean action(Event event, Object object) {
	    if (event.target == choice) {
	       	teamHasBeenClickedOn = true;
	        String selection = choice.getSelectedItem();
	        for(int i = 0; i < teams.size(); i++) {        		 
	        		if (selection.equals(teams.get(i).getNameString())){
	        			teamWins = teams.get(i).getWins();
	        			repaint();
	        			break;
	        		}
	        }
	    }
	    return true;
	 }
   
}
