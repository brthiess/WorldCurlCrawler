package WorldCurlingTourCrawler;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

public class Navigator {
String HTTPWORLDCURL = "http://www.worldcurl.com/";
String EVENTS = "events";
String SHOWDRAWID = "showdrawid";
String EVENTID = "eventid";
String TASK = "task";
String CONTACT = "Contact";
String BASEURL = "http://www.worldcurl.com/schedule.php?eventtypeid=21&eventyear=2013";
ArrayList<String> historyOfURLs;

public Navigator() {
historyOfURLs = new ArrayList<String>();
}
/**
*
* @param br HTML source for webpage
* @return Next URL to visit
*/
public String nextURL(BufferedReader br) {
String url = findNextURL(br);	
return url;
}

private String findNextURL(BufferedReader br) {
String url = "";

String wctSource;
try {
wctSource = br.readLine();

while (wctSource != null) {

//Check the page for the "Scores" link
if (wctSource.contains(">Scores</a></td>")) {
url = wctSource.split("href=|>Scores<")[1];	
if (URLHasNotBeenVisited(url)) {
historyOfURLs.add(url);
return HTTPWORLDCURL.concat(url);
}
}

//Check the page for the "draw number" links
if (wctSource.contains("linescoredrawlink")) {
for (int i = 1; i < wctSource.split("href='|'>").length-1; i++) {
url = wctSource.split("href='|'>")[i];
if (URLHasNotBeenVisited(url) && url.contains(EVENTS) && url.contains(SHOWDRAWID)) {
historyOfURLs.add(url);
return HTTPWORLDCURL.concat(url);
}	
}	
}

if (wctSource.contains("Arial")) {
for (int i = 0; i < wctSource.split("A HREF=|>").length; i++) {
url = wctSource.split("A HREF=|>")[i];
if (URLHasNotBeenVisited(url) && url.contains(EVENTID) && url.contains(TASK) && !url.contains(CONTACT)) {
historyOfURLs.add(url);
return HTTPWORLDCURL.concat(url);
}
}
}

wctSource = br.readLine();	
}

} catch (Exception e) {
// TODO Auto-generated catch block
e.printStackTrace();
}
return BASEURL;
}

private boolean URLHasNotBeenVisited(String url) {
for (int i = 0; i<historyOfURLs.size(); i++) {
if (historyOfURLs.get(i).contentEquals(url)) {
return false;
}	
}

return true;
}
}
