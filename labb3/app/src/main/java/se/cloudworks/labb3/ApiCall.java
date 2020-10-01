package se.cloudworks.labb3;

import android.content.Context;
import android.os.AsyncTask;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class ApiCall extends AsyncTask<String, Void, List> {
    private Context _ctx;
    private List<String> similarArtists;
    private URL url;

    public ApiCall(Context ctx){
        _ctx = ctx;
    }

    @Override
    protected List doInBackground(String... artist) {
        try {
            url = new URL("http://ws.audioscrobbler.com/2.0/?method=artist.getsimilar&artist="+artist+"&api_key=YOUR_API_KEY");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(List result) {
        super.onPostExecute(result);
    }
}
