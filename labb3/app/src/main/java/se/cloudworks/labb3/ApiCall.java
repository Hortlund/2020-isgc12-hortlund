package se.cloudworks.labb3;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
public class ApiCall extends AsyncTask<String, Void, ArrayList> {
    //Declaring variables and data structures
    private Context _ctx;
    private ArrayList<String> similarArtists = new ArrayList<>();
    private URL url;
    private String name;

    //Constructor of class that takes the application context of main activity
    public ApiCall(Context ctx){
        _ctx = ctx;
    }

    @Override
    protected ArrayList<String> doInBackground(String... artist) {
        try {
            //Since asyncTask takes a array of strings as parameter, take out the first one(only one)
            name = artist[0];
            //Create a new list for every search
            similarArtists = new ArrayList<>();
            //Encode the search term to be urlsafe
            String urlSafe = URLEncoder.encode(name, "utf-8");
            //new url object
            url = new URL("http://ws.audioscrobbler.com/2.0/?method=artist.getsimilar&artist="+urlSafe+"&api_key=YOUR_API_KEY");
            //create a xml factory
            XmlPullParserFactory factory = null;
            //initialize
            factory = XmlPullParserFactory.newInstance();
            //new parser
            XmlPullParser parser = factory.newPullParser();
            //ser url stream as input
            parser.setInput(url.openStream(), null);
            //get parser event
            int parserEvent = parser.getEventType();
            //For storing the tag name
            String tagName;
            //check that we are not at the end of document
            while(parserEvent != XmlPullParser.END_DOCUMENT){
                //if we are at a start tag
                if(parserEvent == XmlPullParser.START_TAG){
                    //get tag name
                    tagName = parser.getName();
                    //compare and check for errors and add them tho the list, there are more errors to check for, but this will due for now
                    if(tagName.equals("error")){
                        similarArtists.add(parser.nextText());
                    }
                    //if we get tag name "name", get the text and add to list
                    if(tagName.equals("name")){
                        similarArtists.add(parser.nextText());
                    }
                }
                //continue trough next tag
                parserEvent = parser.next();
            }
            //check for xml or IO exception
        } catch (XmlPullParserException | IOException e) {
            e.printStackTrace();
        }
        //return array list
        return similarArtists;
    }

    @Override
    //runs after doInbackrgound, parameter is the list.
    protected void onPostExecute(ArrayList similarArtists) {
        super.onPostExecute(similarArtists);
        //new intent to get back to mainactivity
        Intent intent = new Intent("se.cloudworks.MainActivity");
        //Return artist name to display the search term
        intent.putExtra("name", name);
        //Return list
        intent.putStringArrayListExtra("list",similarArtists);
        //Flag to start the activity as new task
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //GO! :D
        _ctx.startActivity(intent);
    }
}
