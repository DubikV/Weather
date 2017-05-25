package com.gmail.vanya.weather;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * A placeholder fragment containing a simple view.
 */

public class MainActivityFragment extends Fragment {

    List<String> countries  = new ArrayList<String>();
    ArrayList<String> cities  = new ArrayList<String>();
    Map<String, String> CityId = new HashMap<String, String>();
    Map<String, List<String>> countriesCities = new LinkedHashMap<String, List<String>>();
    String containerCountry;


    /*String weather_type;
    String temperature;

    String pickedCity;
    String pickedId;
    public static  ArrayList<String> cityWeatherTemp = new ArrayList<String>();*/



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view  = inflater.inflate(R.layout.fragment_main, container, false);

        ExpandableListView explistView = (ExpandableListView) view.findViewById(R.id.expandableListView);

        MainListAdapter mainListAdapter = new MainListAdapter(getActivity(), (ArrayList<String>) countries, countriesCities);
        explistView.setAdapter(mainListAdapter);

        /*explistView.setOnClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                return true;

            }       public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {

                        pickedCity = countriesCities.get(countries.get(groupPosition)).get(childPosition);
                        pickedId = CityId.get(pickedCity);

                        DownloadXML downloadXML = new DownloadXML();
                        ConnectivityManager connectivityManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
                        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

                        if (networkInfo != null && networkInfo.isConnected()) {
                            downloadXML.execute("http://export.yandex.ru/weather-ng/forecasts/" + pickedId + ".xml");
                        } else {
                            Toast.makeText(getActivity(), "No network connection available.", Toast.LENGTH_SHORT).show();
                        }
                        return true;
                    }
                });*/

        if (countries.size() == 0 && countriesCities.size()==0 ){
//            ConnectivityManager connectivityManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
//            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

            //if (networkInfo != null && networkInfo.isConnected()) {
                DownloadData download = new DownloadData();
                download.setMainListAdapter(mainListAdapter);
                download.setMainFragment(this);
                download.execute("https://pogoda.yandex.ru/static/cities.xml");
                countries = download.getCountries();
                cities = download.getCities();
                CityId = download.getCityId();

            /*} else {
                Toast.makeText(getActivity(), "No network connection available.", Toast.LENGTH_SHORT).show();
            }*/
        }



        return view;

    }
}
