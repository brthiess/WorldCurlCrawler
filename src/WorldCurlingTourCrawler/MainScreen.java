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
   Button downloadButton;
   boolean crawlHasFinished, crawlIsInProgress, teamHasBeenClickedOn;
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
   }
   

   public void paint( Graphics g ) {
	   if (teamHasBeenClickedOn) {
		   g.setColor(Color.white);
		   g.drawString("" + teamWins, 50, 300);
	   }
	   if (crawlHasFinished) {
	      g.setColor(Color.white);
	      String data = "";
	      	for (int i = 0; i < teams.size(); i++) {
	      		choice.add(teams.get(i).getNameString());
	      			//data += teams.get(i).getNameString() + "\tW:" + teams.get(i).getWins() + "\tL:" + teams.get(i).getLosses() + "\tPts Differential:" + teams.get(i).getTeamPtsDifferential() + "\tHammer Differential:" + teams.get(i).getHammerNetPts() + "\t1 pt % with Hammer:" + teams.get(i).getHammerBreakdown(1) + "\t2 pt % with Hammer:" + teams.get(i).getHammerBreakdown(2) + "\n";
	      	}
	      	add(choice);
	       crawlHasFinished = false;
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
   
   public void actionPerformed(ActionEvent evt)    { 
// Here we will ask what component called this method 
        if (evt.getSource() == downloadButton)  {
        	crawlIsInProgress = true;
        	beginCrawl();
        	crawlIsInProgress = false;
        	crawlHasFinished = true;
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
