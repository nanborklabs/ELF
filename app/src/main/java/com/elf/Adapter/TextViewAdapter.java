package com.elf.Adapter;

import android.widget.Filter;
import android.widget.Filterable;

import java.util.List;

/**
 * Created by nandhu on 22/8/16.
 */
public class TextViewAdapter implements Filterable {
    @Override
    public Filter getFilter() {

        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                List<String>
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {

            }
        }
        return null;
    }

}
