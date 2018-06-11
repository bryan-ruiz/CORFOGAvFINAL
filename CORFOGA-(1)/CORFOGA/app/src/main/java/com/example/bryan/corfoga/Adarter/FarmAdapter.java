package com.example.bryan.corfoga.Adarter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.bryan.corfoga.Class.Farm;
import com.example.bryan.corfoga.R;

import java.util.ArrayList;

public class FarmAdapter extends BaseAdapter {
    private ArrayList<Farm> listItems;
    private Context context;

    public FarmAdapter(ArrayList<Farm> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }

    @Override
    public int getCount() {
        return this.listItems.size();
    }

    @Override
    public Farm getItem(int i) {
        return this.listItems.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Farm farm = getItem(i);
        view = LayoutInflater.from(this.context).inflate(R.layout.listview_item_row_farm, null);
        TextView textView = (TextView) view.findViewById(R.id.itemsFarm);
        textView.setText(farm.getName());
        return view;
    }
}
