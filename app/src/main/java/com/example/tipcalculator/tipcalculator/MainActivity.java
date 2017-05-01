package com.example.tipcalculator.tipcalculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button split_even_btn = (Button) findViewById(R.id.split_even_btn);
        Button split_by_cost_btn = (Button) findViewById(R.id.split_by_cost_btn);

        //on click; go to split by cost activity
        split_by_cost_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SplitByCostActivity.class);
                startActivity(intent);
            }
        });

        //On click; go to split even activity
        split_even_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SplitEvenActivity.class);
                startActivity(intent);
            }
        });

    }
}
