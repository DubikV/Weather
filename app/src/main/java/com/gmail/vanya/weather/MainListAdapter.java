package com.gmail.vanya.weather;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Vanya on 07.02.2016.
 */
public class MainListAdapter extends BaseExpandableListAdapter {

    Map<String,List<String>> countriesCityes;
    private Context context;
    private List<String> countries;

    public MainListAdapter (Context context, ArrayList<String> countries, Map<String,List<String>> countriesCityes){
        this.context = context;
        this.countriesCityes = countriesCityes;
        this.countries = countries;
    }

    @Override
    public int getGroupCount() {
        return countries.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return countriesCityes.get(countries.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return countries.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {

        return countriesCityes.get(countries.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView,
                             ViewGroup parent) {

        String country = (String) getGroup(groupPosition);

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.country_view, null);
        }

       /* if (isExpanded){
            //Изменяем что-нибудь, если текущая Group раскрыта
        }
        else{
            //Изменяем что-нибудь, если текущая Group скрыта
        }*/

        TextView textCountry = (TextView) convertView.findViewById(R.id.textCountry);
        textCountry.setText(country);

        return convertView;

    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild,
                             View convertView, ViewGroup parent) {

        final String city = (String) getChild(groupPosition, childPosition);

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.city_view, null);
        }

        TextView textCity = (TextView) convertView.findViewById(R.id.textCity);
        textCity.setText(city);

        /*Button button = (Button)convertView.findViewById(R.id.buttonChild);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, "button is pressed", 5000).show();
            }
        });*/

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
