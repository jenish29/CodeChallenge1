package com.locationbasedcompany.pc.challenge;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pc on 5/25/18.
 */

public class ListViewRowAdapter extends ArrayAdapter {
    List list = new ArrayList<>();

    public ListViewRowAdapter(@NonNull Context context, int resource) {
        super(context, resource);
    }


    public void add( ListViewRow object) {
        super.add(object);
        list.add(object);
    }

    @Override
    public int getCount() {
        return list.size();

    }

    @Nullable
    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view;
        view = convertView;

        ListViewHolder listViewHolder;

        if(view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.list_view_row,parent, false);

            listViewHolder = new ListViewHolder();

            listViewHolder.name = view.findViewById(R.id.name);
            listViewHolder.domain = view.findViewById(R.id.domain);
            listViewHolder.breachDate = view.findViewById(R.id.breachDate);
            listViewHolder.description = view.findViewById(R.id.description);
            listViewHolder.dataClasses = view.findViewById(R.id.dataClasses);

            listViewHolder.description.setMovementMethod(new ScrollingMovementMethod());
            view.setTag(listViewHolder);

        }else{
            listViewHolder = (ListViewHolder)view.getTag();
        }

        ListViewRow row = (ListViewRow) this.getItem(position);

        listViewHolder.name.setText(row.getName());
        listViewHolder.domain.setText(row.getDomain());
        listViewHolder.breachDate.setText(row.getBreachDate());
        listViewHolder.description.setText(row.getDescription());
        listViewHolder.dataClasses.setText(row.getDataClasses());

        return view;
    }

    // this class is so we can reuse view making a smoother scroll in listview
    static class ListViewHolder {
        TextView name;
        TextView domain;
        TextView breachDate;
        TextView description;
        TextView dataClasses;

    }
}
