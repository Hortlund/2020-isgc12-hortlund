package se.cloudworks.labb3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void onClick(View view){
        TextView artistSearch = findViewById(R.id.textView);

        ApiCall apiCall = new ApiCall(getApplicationContext());
        apiCall.execute(artistSearch.getText().toString());
    }

}