package com.example.android.quakereporter;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class earthquakeAdapter extends ArrayAdapter<earthquake> {

    private static final String LOCATION_SEPARATOR = " of ";
    String primaryLocation;
    String offsetLocation;
    String magnitude;

    public earthquakeAdapter(Context context, ArrayList<earthquake> earthquakes) {
        super(context, 0, earthquakes);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.earthquake_list_item, parent, false);
        }

        earthquake currentEarthquake = getItem(position);
        String location = currentEarthquake.getLocation();
        if(location.contains(LOCATION_SEPARATOR)){
            String[] parts = location.split("(?<=of )");
            offsetLocation = parts[0];
            primaryLocation = parts[1];
        } else {
            offsetLocation = getContext().getString(R.string.near_the);
            primaryLocation = location;
        }

        Double mag = currentEarthquake.getMagnitude();
        DecimalFormat magnitudeFormat = new DecimalFormat("0.0");
        magnitude = magnitudeFormat.format(mag);

        TextView earthquakeMagnitude = (TextView) listItemView.findViewById(R.id.earthquake_mag);
        // Set the proper background color on the magnitude circle.
        // Fetch the background(circle shape) from the TextView, which is a GradientDrawable.
        GradientDrawable magnitudeCircle = (GradientDrawable) earthquakeMagnitude.getBackground();

        // Get the appropriate background color based on the current earthquake magnitude
        int magnitudeColor = getMagnitudeColor(currentEarthquake.getMagnitude());

        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);
        earthquakeMagnitude.setText(magnitude);

        TextView earthquakeLocationOffset = (TextView) listItemView.findViewById(R.id.earthquake_location_offset);
        earthquakeLocationOffset.setText(offsetLocation);

        TextView earthquakeLocation = (TextView) listItemView.findViewById(R.id.earthquake_location);
        earthquakeLocation.setText(primaryLocation);

        TextView eathquakeDate = (TextView) listItemView.findViewById(R.id.earthquake_date);
        eathquakeDate.setText(currentEarthquake.getDate());

        TextView earthquakeTime = (TextView) listItemView.findViewById(R.id.earthquake_time);
        earthquakeTime.setText(currentEarthquake.getTime());

        return listItemView;
    }

    private int getMagnitudeColor(double magnitude){
        int magnitudeColorResourceId;
        int magnitudeFloor = (int) Math.floor(magnitude);
        switch (magnitudeFloor) {
            case 0: // here we make magnitudeColorResourceId = magnitude1 because there is no break so it will go to case 1
            case 1:
                magnitudeColorResourceId = R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceId = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId = R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceId = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceId = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceId = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceId = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceId = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceId = R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceId = R.color.magnitude10plus;
                break;
        }
        return ContextCompat.getColor(getContext(), magnitudeColorResourceId);
    }

}
