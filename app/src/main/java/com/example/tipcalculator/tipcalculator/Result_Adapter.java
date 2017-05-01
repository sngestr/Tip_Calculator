package com.example.tipcalculator.tipcalculator;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Esther Song on 4/24/17.
 */

public class Result_Adapter extends BaseAdapter {
    ArrayList<Person> people_list = new ArrayList<Person>();
    Context context;

    //constructor
    public Result_Adapter(Context context, ArrayList<Person> people_list){
        this.context = context;
        this.people_list = people_list;
    }

    public int getCount(){ return people_list.size(); }

    public Object getItem(int arg0) {
        return null;
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.result_row, viewGroup, false);

        TextView name = (TextView) row.findViewById(R.id.name_txt_v);
        TextView cost_w_tip = (TextView) row.findViewById(R.id.cost_w_tip_txt_v);

        name.setText(people_list.get(position).get_name());
        cost_w_tip.setText(String.format("%.2f", people_list.get(position).get_cost_with_tip()));

        return row;
    }

}
