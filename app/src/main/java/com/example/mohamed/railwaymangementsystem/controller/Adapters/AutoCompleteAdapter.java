package com.example.mohamed.railwaymangementsystem.controller.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import com.example.mohamed.railwaymangementsystem.Fragments.TrainSchudlingFragment;
import com.example.mohamed.railwaymangementsystem.R;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by mohamed on 4/11/2019.
 */

public class AutoCompleteAdapter  extends ArrayAdapter<String> {
    Context context ;
    ArrayList<String> mOriginalValues ;
    ArrayList<String> stations ;
    String text ="";
    private  ArrayFilter mFilter;
    public AutoCompleteAdapter(Context context, int resource, ArrayList<String> mOriginalValues) {
        super(context, resource);
        this.context=context;
        this.stations = mOriginalValues;
        this.mOriginalValues=mOriginalValues;
    }

    @Override
    public int getCount() {
        return stations.size();
    }

    @Nullable
    @Override
    public String getItem(int position) {
        return stations.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
         convertView= LayoutInflater.from(context).inflate(R.layout.auto_complete_item_layout,parent,false);
        TextView stationTextView = convertView.findViewById(R.id.auto_complete_station_item);
        convertView.setPadding(0,10,0,0);
        stationTextView.setText(highlight(text,stations.get(position)));
        return convertView;
    }
    @Override
    public Filter getFilter() {
        if (mFilter == null) {
            mFilter = new ArrayFilter();
        }
        return mFilter;
    }

    private class ArrayFilter extends Filter {
        private Object lock;

        @Override
        protected FilterResults performFiltering(CharSequence prefix) {
            FilterResults results = new FilterResults();
            if (prefix != null) {
                text = prefix.toString();
            }
            if (mOriginalValues == null) {
                synchronized (lock) {
                    mOriginalValues = new ArrayList<String>(stations);
                }
            }

            if (prefix == null || prefix.length() == 0) {
                synchronized (lock) {
                    ArrayList<String> list = new ArrayList<String>(
                            mOriginalValues);
                    results.values = list;
                    results.count = list.size();
                }
            } else {
                final String prefixString = prefix.toString().toLowerCase();
                ArrayList<String> values = mOriginalValues;
                int count = values.size();

                ArrayList<String> newValues = new ArrayList<String>(count);

                for (int i = 0; i < count; i++) {
                    String item = values.get(i);
                    if (item.toLowerCase().contains(prefixString)) {
                        newValues.add(item);
                    }

                }

                results.values = newValues;
                results.count = newValues.size();
            }

            return results;
        }

        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence constraint,  FilterResults results) {

            if (results.values != null) {
                stations = (ArrayList<String>) results.values;
            } else {
                stations = new ArrayList<String>();
            }
            if (results.count > 0) {
                notifyDataSetChanged();
            } else {
                notifyDataSetInvalidated();
            }
        }

    }

    public static CharSequence highlight(String search, String originalText) {
        // ignore case and accents
        // the same thing should have been done for the search text
        String normalizedText = Normalizer
                .normalize(originalText, Normalizer.Form.NFD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}+", "")
                .toLowerCase(Locale.ENGLISH);

        int start = normalizedText.indexOf(search.toLowerCase(Locale.ENGLISH));
        if (start < 0) {
            // not found, nothing to to
            return originalText;
        } else {
            // highlight each appearance in the original text
            // while searching in normalized text
            Spannable highlighted = new SpannableString(originalText);
            while (start >= 0) {
                int spanStart = Math.min(start, originalText.length());
                int spanEnd = Math.min(start + search.length(),
                        originalText.length());

                highlighted.setSpan(new ForegroundColorSpan(Color.BLUE),
                        spanStart, spanEnd, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

                start = normalizedText.indexOf(search, spanEnd);
            }

            return highlighted;
        }
    }
}
