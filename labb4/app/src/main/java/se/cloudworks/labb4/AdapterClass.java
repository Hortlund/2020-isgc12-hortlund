package se.cloudworks.labb4;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class AdapterClass extends BaseAdapter implements ListAdapter {

    private ArrayList<Movie> list;
    private Context context;
    private int flag;
    private Storage movies;

    private Movie tmp=null;

    public AdapterClass(ArrayList<Movie> list, Context context, int flag) {
        this.list = list;
        this.context = context;
        this.flag = flag;
        this.movies = new SqliteHandler(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int pos) {
        return pos;
    }

    @Override
    public long getItemId(int pos) {
        return 0;
    }

    public void update(){
        this.notifyDataSetChanged();
    }

    @Override
    public View getView(final int position, final View convertView, final ViewGroup parent) {
            View view = convertView;
            if (view == null) {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(R.layout.movie_result_listview, null);
            }

            TextView tvContact= (TextView)view.findViewById(R.id.movie);
            tvContact.setText(list.get(position).toString());

            Button callbtn= (Button)view.findViewById(R.id.btn);
            Button getSimilar = (Button)view.findViewById(R.id.getSimilar);
            Button getActors = (Button)view.findViewById(R.id.btn3);
            if(flag == 1){
                callbtn.setText("Save");
                callbtn.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        tmp = new Movie(list.get(position).get_title(),list.get(position).get_imdbid(),list.get(position).get_year());
                        movies.add(tmp);

                        Toast.makeText(context, list.get(position).toString() + " Saved!", Toast.LENGTH_LONG).show();

                    }
                });
                getSimilar.setText("Get Similar");
                getSimilar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ApiCall apiCall = new ApiCall(context);
                        //apiCall.execute(movieTitle);
                        apiCall.doRequest(list.get(position).get_imdbid(),2);
                    }
                });
                getActors.setText("Get Actors");
                getActors.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ApiCall apiCall = new ApiCall(context);
                        //apiCall.execute(movieTitle);
                        apiCall.doRequest(list.get(position).get_imdbid(),3);
                    }
                });
            }else if(flag == 2){
                callbtn.setText("Remove");
                getSimilar.setVisibility(View.GONE);
                getActors.setVisibility(View.GONE);
                callbtn.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        movies.delete(list.get(position).get_imdbid());
                        Toast.makeText(context, list.get(position).toString() + " Removed", Toast.LENGTH_LONG).show();
                        Intent i =  new Intent("se.cloudworks.ShowSavedMoviesActivity");
                        context.startActivity(i);


                    }
                });
            }


            return view;
        }

}
