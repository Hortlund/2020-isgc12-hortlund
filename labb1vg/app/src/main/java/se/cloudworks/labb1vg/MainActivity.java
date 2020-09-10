package se.cloudworks.labb1vg;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View;
import android.widget.Button;

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
        Button b = (Button)view;
        String buttonText = b.getText().toString();
        //String resultText = result.getText().toString();
        //result.setText(resultText);
        if(!(number1.getText().toString().matches("") || number2.getText().toString().matches(""))){
            n1 = Float.valueOf(number1.getText().toString());
            n2 = Float.valueOf(number2.getText().toString());
        }
        else{
            n1 = 0;
            n2 = 0;
        }



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
                break;
            case "Clear":
                if(!count.getText().toString().matches("")){
                    number1.setText("");
                    number2.setText("");
                    result.setText("Result: ");
                    count.setText("");
                }else{
                    Log.d("wa", "else");
                }

                break;
        }

    }
}