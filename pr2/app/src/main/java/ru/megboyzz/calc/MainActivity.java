package ru.megboyzz.calc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.atomic.AtomicReference;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button xSquare = (Button)findViewById(R.id.XSquareButton);
        Button xCube = (Button)findViewById(R.id.XCubeButton);
        Button Exp = (Button)findViewById(R.id.exponentButton);

        Button xSquareRoot = (Button)findViewById(R.id.XSquareRootButton);
        Button xCubeRoot = (Button)findViewById(R.id.XCubeRootButton);
        Button xAndY = (Button)findViewById(R.id.ExponentYButton);

        EditText X = (EditText)findViewById(R.id.X);
        EditText Y = (EditText)findViewById(R.id.Y);

        TextView result = (TextView)findViewById(R.id.result);

        xSquare.setOnClickListener(v -> {

            double x = 0;

            try {
                String x_str = X.getText().toString();
                if(!x_str.isEmpty())
                    x = Double.parseDouble(X.getText().toString());
            }catch (NumberFormatException e){
                Toast.makeText(this, "lol", Toast.LENGTH_LONG).show();
            }

            result.setText(x * x +"");


        });

        xCube.setOnClickListener(v ->{
            double x = 0;
            try {
                String x_str = X.getText().toString();
                if(!x_str.isEmpty())
                    x = Double.parseDouble(X.getText().toString());
            }catch (NumberFormatException e){
                Toast.makeText(this, "lol", Toast.LENGTH_LONG).show();
            }

            result.setText(x * x * x +"");
        });

        Exp.setOnClickListener(v -> {
            double x = 0, y = 0;

            try {
                String x_str = X.getText().toString();
                String y_str = Y.getText().toString();
                if(!x_str.isEmpty())
                    x = Double.parseDouble(X.getText().toString());
                if(!y_str.isEmpty())
                    y = Double.parseDouble(Y.getText().toString());
            }catch (NumberFormatException e){
                Toast.makeText(this, "lol", Toast.LENGTH_LONG).show();
            }

            result.setText(Math.pow(x, y) +"");
        });

        xSquareRoot.setOnClickListener(v -> {
            double x = 0;
            try {
                String x_str = X.getText().toString();
                if(!x_str.isEmpty())
                    x = Double.parseDouble(X.getText().toString());
            }catch (NumberFormatException e){
                Toast.makeText(this, "lol", Toast.LENGTH_LONG).show();
            }

            if(x < 0){
                Toast.makeText(this, "x < 0", Toast.LENGTH_SHORT).show();
                return;
            }

            result.setText(Math.sqrt(x)+"");
        });

        xCubeRoot.setOnClickListener(v -> {
            double x = 0;
            try {
                String x_str = X.getText().toString();
                if(!x_str.isEmpty())
                    x = Double.parseDouble(X.getText().toString());
            }catch (NumberFormatException e){
                Toast.makeText(this, "lol", Toast.LENGTH_LONG).show();
            }

            result.setText(Math.cbrt(x)+"");
        });

        xAndY.setOnClickListener(v -> {
            double x = 0, y = 0;

            try {
                String x_str = X.getText().toString();
                String y_str = Y.getText().toString();
                if(!x_str.isEmpty())
                    x = Double.parseDouble(X.getText().toString());
                if(!y_str.isEmpty())
                    y = Double.parseDouble(Y.getText().toString());
            }catch (NumberFormatException e){
                Toast.makeText(this, "lol", Toast.LENGTH_LONG).show();
            }

            if(y == 0){
                Toast.makeText(this, "y не может быть равен нулю!", Toast.LENGTH_LONG).show();
            }

            result.setText(Math.pow(x, 1/y) +"");
        });

    }
}