package com.example.android.quakereport;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class EarthquakeDataAdapter extends ArrayAdapter<EarthquakeData> {

    private static final String LOCATION_SEPARATOR = " of ";

    private static final String LOG_TAG = EarthquakeData.class.getSimpleName();

    public EarthquakeDataAdapter(Activity context, ArrayList<EarthquakeData> earthquakeData) {
        super(context, 0, earthquakeData);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null)
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);


        EarthquakeData currentEarthquakeData = getItem(position);


        //Find the TextView with view ID magnitude
        TextView magniTextView = (TextView) listItemView.findViewById(R.id.magni);
        //Format he magnitude to show 1 decimal place
       String formattedMagnitude = formatMagnitude(currentEarthquakeData.getmMag());
//        magniTextView.setText(formattedMagnitude);
        magniTextView.setText(formattedMagnitude);
        //Set the proper background color on the magnitude circle.
        // Fetch the background from the TextView, which is a GradientDrawable.
        GradientDrawable magnitudeCircle = (GradientDrawable) magniTextView.getBackground();
        // Get the appropriate background color basae on the earthquake magnitude
        int magnitudeColor = getMagnitudeColor(currentEarthquakeData.getmMag());

        //Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);


        String originalLocation = currentEarthquakeData.getMplace();

        String primaryLocation;
        String locationOffset;

        //Check whether the original string contains the "of " text

        if(originalLocation.contains(LOCATION_SEPARATOR))  {
            //Split the string into different parts (as an array of Strings)
            //Based on the  "of" text.WE expect an array of 2 string , where
            //The first String will ne "5km  N" +  ------> "5Km N of"

            String[] parts = originalLocation.split(LOCATION_SEPARATOR);

            // Location offset should be "5km N " + " of " --> "5km N of"
            locationOffset = parts[0] + LOCATION_SEPARATOR;

            //primary location should be ""cairo, Egpty
            primaryLocation = parts[1];
        } else {
            //otherwise, there is no "of " text in the original string
            //Hence, set the default location offsset to say "Near the"
            locationOffset = getContext().getString(R.string.near_the);
            //The primary location will be the full location string "pacific-Antartica Ridge".
            primaryLocation = originalLocation;

        }

        TextView primaryLocationView = (TextView) listItemView.findViewById(R.id.place2);
        primaryLocationView.setText(primaryLocation);


        TextView locationOffsetView = (TextView) listItemView.findViewById(R.id.place);
        locationOffsetView.setText(locationOffset);




        Date dateObject = new Date(currentEarthquakeData.getTimeInMilliseconds());

        TextView date = (TextView) listItemView.findViewById(R.id.date);

        String formattedDate = formatData(dateObject);

        date.setText(formattedDate);

        TextView timeView = (TextView) listItemView.findViewById(R.id.time);

        String formattedTime = formatTime(dateObject);
        timeView.setText(formattedTime);




        return listItemView;
    }

    private int getMagnitudeColor(double getmMag) {
        int magnitudeColorResourceID;
        int magnitudeFloor = (int) Math.floor(getmMag);

        switch (magnitudeFloor) {
            case 0:
            case 1:
                magnitudeColorResourceID = R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceID = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceID = R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceID = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceID = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceID = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceID = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceID = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceID = R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceID = R.color.magnitude10plus;
                break;
        }
        return ContextCompat.getColor(getContext(),magnitudeColorResourceID);
    }



    private String formatMagnitude(double getmMag) {
        DecimalFormat magnitudeFormat = new DecimalFormat("0.0");
        return magnitudeFormat.format(getmMag);
    }

    private String formatData(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }

    private String formatTime(Date dateObject) {
        SimpleDateFormat timeformat = new SimpleDateFormat("h:mm a");
        return  timeformat.format(dateObject);
    }
}

