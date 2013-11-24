package WorldCurlingTourCrawler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class Retriever {
String url;
Sorter sort;

public Retriever() {	
this.sort = new Sorter();
}

public ArrayList<Game> Retrieve(String url) {
this.url = url;

ArrayList<Game> data = new ArrayList<Game>();
try {
            URL my_url = new URL(url);
            BufferedReader br = new BufferedReader(new InputStreamReader(my_url.openStream()));
            sort.addBufferedReader(br);
            data = sort.sort();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
return data;
}

public BufferedReader getPageSource(String url) {
BufferedReader br;
URL my_url;
try {
my_url = new URL(url);
br = new BufferedReader(new InputStreamReader(my_url.openStream()));
return br;
} catch (Exception e) {
// TODO Auto-generated catch block
e.printStackTrace();
}
        return null;
}

}
