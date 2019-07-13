package com.example.mohamed.railwaymangementsystem.controller.Adapters;

import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mohamed.railwaymangementsystem.R;

import java.util.ArrayList;
import java.util.zip.Inflater;

/**
 * Created by mohamed on 7/4/2019.
 */

public class SpinnerAdapter implements android.widget.SpinnerAdapter {
    ArrayList<String> arrayList;
    Context context ;
    public  SpinnerAdapter(ArrayList<String> input , Context context)
    {
        super();
        arrayList = input;
        this.context=context;


    }
    @Override
    public View getDropDownView(int i, View view, ViewGroup viewGroup) {

        view =  View.inflate(context, R.layout.dropdown_layout, null);
        final TextView textView = (TextView) view.findViewById(R.id.dropdown);
        textView.setText(arrayList.get(i));
        return view;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver dataSetObserver) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {

    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
         view =  View.inflate(context, R.layout.spinner_text_view, null);
        TextView textView = (TextView) view.findViewById(R.id.train_id_tv);
        textView.setText(arrayList.get(i));
        return textView;
    }

    @Override
    public int getItemViewType(int i) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
