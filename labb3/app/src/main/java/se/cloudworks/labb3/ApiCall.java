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
    private Context _ctx;
    private ArrayList<String> similarArtists = new ArrayList<>();
    private URL url;
    private String name;

    public ApiCall(Context ctx){
        _ctx = ctx;
    }

    @Override
    protected ArrayList<String> doInBackground(String... artist) {
        try {
            name = artist[0];
            similarArtists = new ArrayList<>();
            String urlSafe = URLEncoder.encode(name, "utf-8");
            url = new URL("http://ws.audioscrobbler.com/2.0/?method=artist.getsimilar&artist="+urlSafe+"&api_key=YOUR_API_KEY");
            XmlPullParserFactory factory = null;
            factory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = factory.newPullParser();
            parser.setInput(url.openStream(), null);
            int parserEvent = parser.getEventType();
            String tagName;
            while(parserEvent != XmlPullParser.END_DOCUMENT){
                if(parserEvent == XmlPullParser.START_TAG){
                    tagName = parser.getName();
                    if(tagName.equals("error")){
                        similarArtists.add(parser.nextText());
                    }
                    if(tagName.equals("name")){
                        similarArtists.add(parser.nextText());
                    }
                }

                parserEvent = parser.next();
            }

        } catch (XmlPullParserException | IOException e) {
            e.printStackTrace();
        }
        return similarArtists;
    }

    @Override
    protected void onPostExecute(ArrayList similarArtists) {
        super.onPostExecute(similarArtists);
        Intent intent = new Intent("se.cloudworks.MainActivity");
        intent.putExtra("name", name);
        intent.putStringArrayListExtra("list",similarArtists);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        _ctx.startActivity(intent);
    }
}
