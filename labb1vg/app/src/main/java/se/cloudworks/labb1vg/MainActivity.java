package se.cloudworks.labb1vg;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText number1, number2;
    private float n1, n2, res;
    private TextView result, count;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        number1 = findViewById(R.id.number1);
        number2 = findViewById(R.id.number2);
        count = findViewById(R.id.count);
        result = findViewById(R.id.result);

    }

    public void count(View view){
        //Gets the view thats passed and casts it as a button to get button text
        Button b = (Button)view;
        String buttonText = b.getText().toString();
        //Check so that none of the fields is empty
        if(!(number1.getText().toString().matches("") || number2.getText().toString().matches(""))){
            n1 = Float.valueOf(number1.getText().toString());
            n2 = Float.valueOf(number2.getText().toString());

            //Switch statement that dependig on what button was pressed calculates or clears
            switch(buttonText){
                case "+":
                    count.setText("+");
                    res = n1 + n2;

                    result.setText("Result: " + Float.toString(res));
                    break;
                case "-":
                    count.setText("-");
                    res = n1 - n2;

                    result.setText("Result: " + Float.toString(res));
                    break;

                case "*":
                    count.setText("*");
                    res = n1 * n2;

                    result.setText("Result: " + Float.toString(res));
                    break;
                case "/":
                    count.setText("/");
                    //Checks zero division and warns about it, just because.
                    if(n1 == 0|| n2 == 0){
                        Toast.makeText(this, "Dela inte med noll!", Toast.LENGTH_LONG).show();
                    }else{
                        res = n1 / n2;

                        result.setText("Result: " + Float.toString(res));
                    }

                    break;
                    //Clears all the text fields.
                case "Clear":
                    if(!count.getText().toString().matches("")) {
                        number1.setText("");
                        number2.setText("");
                        result.setText("Result: ");
                        count.setText("");
                    }

                    break;
            }
        }
    }
}