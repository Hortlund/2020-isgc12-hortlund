package se.cloudworks.labb3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void onClick(View view){
        EditText artistSearch = findViewById(R.id.artistSearch);

        ApiCall apiCall = new ApiCall(getApplicationContext());
        apiCall.execute(artistSearch.getText().toString().replace(' ', '+'));
    }

    public void onResume() {
        super.onResume();

    }

}