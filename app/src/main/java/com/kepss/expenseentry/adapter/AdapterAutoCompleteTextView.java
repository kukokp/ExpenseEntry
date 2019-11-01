package com.kepss.expenseentry.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.kepss.expenseentry.R;
import com.kepss.expenseentry.model.clsAutocompleteTextViewRes;

import java.util.ArrayList;
import java.util.List;

public class AdapterAutoCompleteTextView extends ArrayAdapter<clsAutocompleteTextViewRes> {
    private Context context;
    private int resource, textViewResourceId;
    private ArrayList<clsAutocompleteTextViewRes> items, itemsAll, suggestions;
    private int viewResourceId;

    //Typeface typeface = Typeface.createFromAsset(Config.globalContext.getAssets(), "fonts/" + Config.FONT_NAME);
    public AdapterAutoCompleteTextView(Context context, int viewResourceId, ArrayList<clsAutocompleteTextViewRes> items) {
        super(context, viewResourceId, items);
        this.items = (ArrayList<clsAutocompleteTextViewRes>) items;
        this.context = context;
        this.itemsAll = (ArrayList<clsAutocompleteTextViewRes>) items.clone();
        this.suggestions = new ArrayList<clsAutocompleteTextViewRes>();
        this.viewResourceId = viewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(viewResourceId, parent, false);
        }

        clsAutocompleteTextViewRes orderBookingCustomerList = items.get(position);
        TextView tvActRowName = (TextView) view.findViewById(R.id.tvActRowName);

        tvActRowName.setText(orderBookingCustomerList.name);

        return view;
//        return super.getView(position, convertView, parent);
    }

    @NonNull
    @Override
    public Filter getFilter() {
        return nameFilter;
    }

    /**
     * Custom Filter implementation for custom suggestions we provide.
     */
    private Filter nameFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            if (constraint != null) {

                FilterResults filterResults = null;
                try {
                    suggestions.clear();
                    for (clsAutocompleteTextViewRes people : itemsAll) {
                        if (people.name.toLowerCase().contains(constraint.toString().toLowerCase())) {
                            suggestions.add(people);
                        }
                    }
                    filterResults = new FilterResults();
                    filterResults.values = suggestions;
                    filterResults.count = suggestions.size();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return filterResults;
            } else {
                return new FilterResults();
            }
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            try {
                List<clsAutocompleteTextViewRes> filterList = (ArrayList<clsAutocompleteTextViewRes>) results.values;
                if (results != null && results.count > 0) {
                    clear();
                    for (clsAutocompleteTextViewRes people : filterList) {
                        add(people);
                        notifyDataSetChanged();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

}