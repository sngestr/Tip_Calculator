package com.example.tipcalculator.tipcalculator;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;


class Person_Adapter extends BaseAdapter {

    ArrayList<Person> listItem = new ArrayList<Person>();

    Context mContext;

    //constructor
    public Person_Adapter(Context mContext, ArrayList<Person> listItem) {
        this.mContext = mContext;
        this.listItem = listItem;
    }

    public int getCount() {
        return listItem.size();
    }

    public Object getItem(int arg0) {
        return null;
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.people_list_row, viewGroup, false);

        TextView name_text = (TextView) row.findViewById(R.id.name_txt);
        TextView cost_text = (TextView) row.findViewById(R.id.cost_txt);

        name_text.setText(listItem.get(position).get_name());
        cost_text.setText(String.format("%.2f", listItem.get(position).get_cost()));

        //handle delete button and add onClickListeners
        final Button delete = (Button) row.findViewById(R.id.delete_btn);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listItem.remove(position);      //remove the selected person
                notifyDataSetChanged();         //apply changes

                //update the total cost in the activity
                if(mContext instanceof SplitByCostActivity){
                    ((SplitByCostActivity)mContext).update_total();
                }
            }
        });

        return row;
    }

    private double get_total(){
        double total = 0;
        for (int i = 0; i < listItem.size(); i++){
            total += listItem.get(i).get_cost();
        }
        return total;
    }
}