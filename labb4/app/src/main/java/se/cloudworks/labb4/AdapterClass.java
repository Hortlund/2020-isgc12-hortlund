package se.cloudworks.labb4;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

// alot happens in this class, could probably split this up a bit. Custom listadapter
// Idea and some code came from: https://stackoverflow.com/questions/40862154/how-to-create-listview-items-button-in-each-row
// The code have been heavily modified but some elements remains the same
public class AdapterClass extends BaseAdapter implements ListAdapter {

    //Creates private objects and variables we need
    private ArrayList<Movie> list;
    private ArrayList<Actor> actors;
    private Context context;
    private int flag;
    private Storage movies;
    private ApiCall apiCall;

    private Movie m=null;

    public AdapterClass(ArrayList<Movie> list,ArrayList<Actor> actors, Context context, int flag, Storage movies) {
        //Takes parameters and assigns them to class variables/objects
        this.actors = actors;
        this.list = list;
        this.context = context;
        this.flag = flag;
        this.movies = movies;
        this.apiCall = new ApiCall(context);
    }

    @Override
    //returns the size of the list that was sent, checks if its actors or movies
    public int getCount() {
        if(actors != null){
            return actors.size();
        }else{
            return list.size();
        }

    }

    @Override
    //Returns positions of item
    public Object getItem(int pos) {
        return pos;
    }

    @Override
    //gets items id, overides listadapter function, returns 0.
    public long getItemId(int pos) {
        return 0;
    }

    @Override
    //Generates the view and returns it with the content in place
    public View getView(final int position, final View convertView, final ViewGroup parent) {
        //Get view context
            View view = convertView;
            //if view is null the assign layout to it.
            if (view == null) {
                //layoutinflater is there for instantiate the layout we later provide and build the view objects
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                //Here we give the layout for our custom listview
                view = inflater.inflate(R.layout.movie_result_listview, null);
            }
            //gets texitviw form our custom layout
            TextView movie= view.findViewById(R.id.movie);
            //if we got actors list the get that value otherwise go with movie list
            if(actors != null){
                movie.setText(actors.get(position).toString());
            }else{
                movie.setText(list.get(position).toString());
            }

            //Gets the buttons we use in the custom layout.
            Button save= view.findViewById(R.id.save);
            Button getSimilar = view.findViewById(R.id.getSimilar);
            Button getActors = view.findViewById(R.id.getActors);
            //if flag is 1, we came from mainactivity with movie search
            if(flag == 1){
                //sets save button text
                save.setText("Save");
                //when we click the button we create a new movie object and the calls the add function from the sqlitehandler class
                save.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        m = new Movie(list.get(position).get_title(),list.get(position).get_imdbid(),list.get(position).get_year());
                        movies.add(m);
                        //Toast confirmation of movie saved
                        Toast.makeText(context, list.get(position).toString() + " Saved!", Toast.LENGTH_LONG).show();

                    }
                });
                //Set text to get similar
                getSimilar.setText("Get Similar");
                //the when clicked, we call apicall and calls do request and send the movie we clicked as parameter and flag 2
                getSimilar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        apiCall.doRequest(list.get(position).get_imdbid(),2);
                    }
                });
                //set text to get actors
                getActors.setText("Get Actors");
                //when clicked on get actors we call apicall object and send the movie we clicked on as parameter but with flag 3
                getActors.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        apiCall.doRequest(list.get(position).get_imdbid(),3);
                    }
                });
            }else if(flag == 2){
                //set text to remove
                save.setText("Remove");
                //hides the other buttons since we only gonna have the option to remove movies
                getSimilar.setVisibility(View.GONE);
                getActors.setVisibility(View.GONE);
                //When we click on remove we get the movie we clicked on and calls the delete function from sqlitehandler, then toasts
                //To update the view and show that the movie was removed immediately we call an intent to showsavedmoviesactivity again.
                save.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        movies.delete(list.get(position).get_imdbid());
                        Toast.makeText(context, list.get(position).toString() + " Removed", Toast.LENGTH_LONG).show();
                        Intent i =  new Intent("se.cloudworks.ShowSavedMoviesActivity");
                        context.startActivity(i);


                    }
                });
                //If flag is 3 we only gonna show actors and not gonna do anything with them, so hide all buttons, and only allow search again
                //which will show movies and buttons again.
            }else if(flag == 3){
                save.setVisibility(View.GONE);
                getSimilar.setVisibility(View.GONE);
                getActors.setVisibility(View.GONE);
            }

            //show the custom list
            return view;
        }

}
