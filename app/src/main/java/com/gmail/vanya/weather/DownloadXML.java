/*

package com.gmail.vanya.weather;

import android.app.ProgressDialog;
import android.os.AsyncTask;

import org.apache.commons.io.IOUtils;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

*
 * Created by Vanya on 07.02.2016.



public class DownloadXML extends AsyncTask<String, Integer, String>{

    public ProgressDialog progressDialog;
    String Url;
    public static ArrayList<String> cityWeatherTemp = new ArrayList<String>();
    public MainActivityFragment mainFragment;



    @Override
    protected void onPreExecute(){
        progressDialog = new ProgressDialog(mainFragment.getActivity());
        progressDialog.setCancelable(true);
        progressDialog.setMessage("downloading...");
        progressDialog.show();
    }

    @Override
    protected String doInBackground(String ... url){
        String content = null;
        Url= url[0];
        try{
            content = downloadData(url[0]);
        }catch (IOException e){}

        return content;
    }

    @Override
    public void onPostExecute(String result){

     if (Url.equals("https://pogoda.yandex.ru/static/cities.xml")) {
            parseCitiesXml(result);
            progressDialog.cancel();
            customExpandableListAdapter.notifyDataSetChanged();
        }
        else {

            try {
                parseForecastXml(result);
                progressDialog.cancel();
            }catch (IOException e) {}
            catch (XmlPullParserException e) {}

            cityWeatherTemp.clear();
            cityWeatherTemp.add(pickedCity);
            cityWeatherTemp.add(weather_type);
            cityWeatherTemp.add(temperature);
            SecondFragment secondFragment = new SecondFragment();
            MainActivity.fragmentManager.beginTransaction().replace(R.id.container, secondFragment).addToBackStack(null).commit();
        }


    }


    public String downloadData(String urlCity) throws IOException {
        InputStream is = null;
        try {
            URL url = new URL(urlCity);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setConnectTimeout(10000);
            httpURLConnection.connect();
            is = httpURLConnection.getInputStream();
            String content = IOUtils.toString(is, "UTF-8");

            return content;

        }finally {
            if (is !=null){
                is.close();
            }
        }
    }

    public  void parseCitiesXml(String content){
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

    public void parseForecastXml(String content) throws XmlPullParserException, IOException {

        XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
        XmlPullParser xmlPullParser = factory.newPullParser();
        xmlPullParser.setInput(new StringReader(content));

        while (xmlPullParser.getEventType()!= XmlPullParser.END_DOCUMENT){// &&( xmlPullParser.getName().equals("fact") &&xmlPullParser.getEventType() ==XmlPullParser.END_TAG )){

            if(xmlPullParser.getEventType()==XmlPullParser.START_TAG && xmlPullParser.getName().equals("temperature")){
                xmlPullParser.next();
                if (xmlPullParser.getEventType() == XmlPullParser.TEXT){
                    temperature =xmlPullParser.getText();
                }
            }

            if (xmlPullParser.getEventType()==XmlPullParser.START_TAG && xmlPullParser.getName().equals("weather_type")){
                xmlPullParser.next();
                if (xmlPullParser.getEventType() == XmlPullParser.TEXT) {
                    weather_type = xmlPullParser.getText();
                    break;
                }
            }
            xmlPullParser.next();
        }
    }

}
*/
