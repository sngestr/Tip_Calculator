package com.example.tipcalculator.tipcalculator;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * Created by Esther Song on 3/28/17.
 */

public class SplitEvenActivity extends AppCompatActivity {
    private EditText bill_amount;
    private EditText number_of_people;
    private Spinner tip_percentage;
    private TextView display_result_area;
    private Button calculate_btn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_split_even);

        bill_amount = (EditText) findViewById(R.id.total_amount_input_field);
        number_of_people = (EditText) findViewById(R.id.number_of_people_input);
        tip_percentage = (Spinner) findViewById(R.id.tip_amount_spinner);
        display_result_area = (TextView) findViewById(R.id.result_txt);

        calculate_btn = (Button) findViewById(R.id.calculate_btn);
        calculate_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //close input method
                InputMethodManager input_manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                input_manager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

                //get cost for each person
                String cost_for_each = "$" + String.format("%.2f", get_total());
                display_result_area.setText(cost_for_each);
            }
        });
    }

    //calculate the cost for each person
    private Double get_total() {
        double result;

        String bill_total = bill_amount.getText().toString();
        String number_of_ppl = number_of_people.getText().toString();
        String percentage = tip_percentage.getSelectedItem().toString();

        //remove the % from the percentage
        percentage = percentage.substring(0, percentage.length() - 1);

        if (TextUtils.isEmpty(bill_total)) {
            bill_amount.setError("This item cannot be empty.");
            return 0.00;
        } else if (TextUtils.isEmpty(number_of_ppl)) {
            number_of_people.setError("This item cannot be empty.");
            return 0.00;
        } else {
            double tip_cost = Double.parseDouble(bill_total) * (Integer.parseInt(percentage) * .01);
            result = (Double.parseDouble(bill_total) + tip_cost) / Integer.parseInt(number_of_ppl);
            return result;
        }
    }

}
