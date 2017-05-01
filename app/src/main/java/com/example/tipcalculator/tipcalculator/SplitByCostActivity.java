package com.example.tipcalculator.tipcalculator;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Esther Song on 3/28/17.
 */

public class SplitByCostActivity extends AppCompatActivity{
    //private EditText total_amount;
    private Spinner tip_percentage;
    private EditText each_amount;
    private EditText person_name;
    private TextView total_cost;
    private TextView result_w_tip_v;

    private ListView cost_list;
    private ListView result_list;
    private ArrayList<Person> people_list;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_split_by_cost);

        //declaration
        tip_percentage = (Spinner) findViewById(R.id.tip_spinner);
        each_amount = (EditText) findViewById(R.id.person_cost_input);
        person_name = (EditText) findViewById(R.id.person_name);
        total_cost = (TextView) findViewById(R.id.total_cost_txt_v);
        final Button add_btn = (Button) findViewById(R.id.add_person_btn);
        result_w_tip_v = (TextView) findViewById(R.id.total_with_tip_txt_v);

        cost_list = (ListView) findViewById(R.id.people_list);
        result_list = (ListView) findViewById(R.id.result_list_v);

        people_list = new ArrayList<Person>();

        //set up the adapter
        cost_list.setAdapter(new Person_Adapter(this, people_list));
        //result_list.setAdapter(new Result_Adapter(this, people_list));

        //invoke this when the add button is clicked
        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                add_btn.setEnabled(false);
                //Add the information given by the user to the array list
                double cost;
                String cost_txt = String.format("%.2f", Double.parseDouble(each_amount.getText().toString()));     //get cost
                cost = Double.parseDouble(cost_txt);
                String name = person_name.getText().toString();         //get name

                add_person(name, cost);
                calculate_each_person_cost();       //calculate the amount with tip

                //display total cost with the added person
                total_cost.setText(String.format("%.2f", get_total()));

                result_w_tip_v.setText(Double.toString(get_total_with_tip()));

                add_btn.setEnabled(true);
            }
        });
    }

    //update the total cost
    public void update_total(){
        total_cost.setText(String.format("%.2f", get_total()));         //set the new total cost
        result_list.setAdapter(new Result_Adapter(this, people_list));  //update result list
    }

    //add a person obj to the list and display to list
    private void add_person(String name, double cost) {
        //create new Person obj
        Person person = new Person(name, cost);
        //add to array list
        people_list.add(person);
        //populate people list view
        cost_list.setAdapter(new Person_Adapter(this, people_list));
    }

    //calculate everyone's cost with the tip percentage
    private void calculate_each_person_cost(){
        String percentage = tip_percentage.getSelectedItem().toString();
        //remove the % from the percentage
        percentage = percentage.substring(0, percentage.length() - 1);
        double tip = Double.parseDouble(percentage); //get the tip percentage

        //calls the method that will calculate all the cost with tip for each person
        for(int i = 0; i < people_list.size(); i++){
            people_list.get(i).set_cost_with_tip(tip);
        }

        //populate the result list view
        result_list.setAdapter(new Result_Adapter(this, people_list));
    }

    //return the total cost with tip included
    private double get_total_with_tip(){
        double total = 0;
        for(int i = 0; i < people_list.size(); i++){
            total += people_list.get(i).get_cost_with_tip();
        }
        return total;
    }

    //return the total cost without tip
    private double get_total(){
        double total = 0;
        for (int i = 0; i < people_list.size(); i++){
            total += people_list.get(i).get_cost();
        }
        return total;
    }
}
