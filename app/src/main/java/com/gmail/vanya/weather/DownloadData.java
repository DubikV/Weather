package com.gmail.vanya.weather;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;

import org.apache.commons.io.IOUtils;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Vanya on 07.02.2016.
 */
public class DownloadData extends AsyncTask<String, Integer, String> {
    String Url;
    private MainActivityFragment mainFragment;
    private List<String> countries  = new ArrayList<String>();
    private ArrayList<String> cities  = new ArrayList<String>();
    private Map<String, String> CityId = new HashMap<String, String>();
    private Map<String, List<String>> countriesCities = new LinkedHashMap<String, List<String>>();
    public ProgressDialog progressDialog;
    private MainListAdapter mainListAdapter;

    public MainActivityFragment getMainFragment() {
        return mainFragment;
    }

    public void setMainFragment(MainActivityFragment mainFragment) {
        this.mainFragment = mainFragment;
    }

    public MainListAdapter getMainListAdapter() {
        return mainListAdapter;
    }

    public void setMainListAdapter(MainListAdapter mainListAdapter) {
        this.mainListAdapter = mainListAdapter;
    }

    public ArrayList<String> getCities() {
        return cities;
    }

    public void setCities(ArrayList<String> cities) {
        this.cities = cities;
    }

    public Map<String, String> getCityId() {
        return CityId;
    }

    public void setCityId(Map<String, String> cityId) {
        CityId = cityId;
    }

    public List<String> getCountries() {
        return countries;
    }

    public void setCountries(List<String> countries) {
        this.countries = countries;
    }

    public Map<String, List<String>> getCountriesCities() {
        return countriesCities;
    }

    public void setCountriesCities(Map<String, List<String>> countriesCities) {
        this.countriesCities = countriesCities;
    }

    @Override
    protected void onPreExecute(){

        progressDialog = new ProgressDialog(mainFragment.getActivity());
        progressDialog.setCancelable(true);
        progressDialog.setMessage("downloading...");
        progressDialog.show();
    }

    @Override
    protected String doInBackground(String... url) {
        String content = null;
        Url = url[0];
        try{
            content = downloadInfo(url[0]);
        }catch (IOException e){}

        return content;
    }

    @Override
    public void onPostExecute(String result){

        if (Url.equals("https://pogoda.yandex.ru/static/cities.xml")) {
            getListCountriesAndCyties(result);
            progressDialog.cancel();
            mainListAdapter.notifyDataSetChanged();
        }
        else {

           /* try {
                parseForecastXml(result);
                progressDialog.cancel();
            }catch (IOException e) {}
            catch (XmlPullParserException e) {}

            cityWeatherTemp.clear();
            cityWeatherTemp.add(pickedCity);
            cityWeatherTemp.add(weather_type);
            cityWeatherTemp.add(temperature);
            SecondFragment secondFragment = new SecondFragment();
            MainActivity.fragmentManager.beginTransaction().replace(R.id.container, secondFragment).addToBackStack(null).commit();*/
        }


    }


    public String downloadInfo(String urlCity) throws IOException {
        InputStream is = null;
        try {
            URL url = new URL(urlCity);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setConnectTimeout(10000);
            httpURLConnection.connect();
            Log.d("Tag", "We here///////||||||||||||||||||||||||||||||||||||||||||||||||||" + urlCity);
            is = httpURLConnection.getInputStream();
            String content = IOUtils.toString(is, "UTF-8");
            return content;

        }finally {
            if (is !=null){
                is.close();
            }
        }
    }

    public  void getListCountriesAndCyties(String content){
        String containerCountry = "";
        try{

            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = factory.newPullParser();
            parser.setInput(new StringReader(content));

            while (parser.getEventType()!= XmlPullParser.END_DOCUMENT){

                if (parser.getEventType()==XmlPullParser.START_TAG){
                    String name = parser.getName();
                    if (name.equals("country")){
                        countries.add(parser.getAttributeValue(0));
                        containerCountry = parser.getAttributeValue(0);
                    }
                    if (name.equals("city")){
                        String id = parser.getAttributeValue(0);
                        parser.next();
                        if (parser.getEventType() == XmlPullParser.TEXT){
                            cities.add(parser.getText());
                            CityId.put(parser.getText(), id);
                        }
                    }
                }
                if ( (parser.getEventType()==XmlPullParser.END_TAG) && (parser.getName().equals("country")) ){
                    countriesCities.put(containerCountry, cities);
                    cities  = new ArrayList<String>();
                }
                parser.next();
            }

        }catch (Exception e){}
    }
}
