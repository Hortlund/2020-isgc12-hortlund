package se.cloudworks.labb4;

import android.content.Context;
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

    private Movie tmp=null;

    public AdapterClass(ArrayList<Movie> list, Context context, int flag) {
        this.list = list;
        this.context = context;
        this.flag = flag;
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

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if(flag == 1){
            View view = convertView;
            if (view == null) {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(R.layout.movie_result_listview, null);
            }

            TextView tvContact= (TextView)view.findViewById(R.id.movie);
            tvContact.setText(list.get(position).toString());

            Button callbtn= (Button)view.findViewById(R.id.btn);
            callbtn.setText("Save");

            callbtn.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    Storage movies = new SqliteHandler(context);
                    //Log.d("walla", list.get(position).get_title() + list.get(position).get_imdbid() + list.get(position).get_year());
                    tmp = new Movie(list.get(position).get_title(),list.get(position).get_imdbid(),list.get(position).get_year());
                    movies.add(tmp);
                    movies.close();

                    Toast.makeText(context, list.get(position).toString() + " Saved!", Toast.LENGTH_LONG).show();

                }
            });
            return view;
        } else if( flag == 2){
            View view = convertView;
            if (view == null) {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(R.layout.movie_result_listview, null);
            }

            TextView tvContact= (TextView)view.findViewById(R.id.movie);
            tvContact.setText(list.get(position).toString());

            Button callbtn= (Button)view.findViewById(R.id.btn);
            callbtn.setText("Remove");

            callbtn.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    //Storage movies = new SqliteHandler(context);
                    //list = movies.getAll();
                    //Log.d("walla", list.get(position).get_title() + list.get(position).get_imdbid() + list.get(position).get_year());
                    //tmp = new Movie(list.get(position).get_title(),list.get(position).get_imdbid(),list.get(position).get_year());
                    //movies.add(tmp);
                    Toast.makeText(context, list.get(position).toString() + " Removed", Toast.LENGTH_LONG).show();

                }
            });
            return view;
        } else{
            return null;
        }



    }

}
