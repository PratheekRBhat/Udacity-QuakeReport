package com.example.android.quakereport;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.Flushable;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Earthquake_Adapter extends ArrayAdapter<Earthquake> {
    private static final String LOCATION_SEPARATOR = " of ";

    Earthquake_Adapter(Context context, List<Earthquake> earthquakes){
        super(context, 0, earthquakes);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View ListItemView = convertView;
        if(ListItemView == null){
            ListItemView = LayoutInflater.from(getContext()).inflate(R.layout.eartquake_list_item, parent, false);
        }

        Earthquake currentEarthquake = getItem(position);

        TextView magnitudeView = ListItemView.findViewById(R.id.quake_mag);
        String formattedMagnitude = formatMagnitude(currentEarthquake.getMagnitude());
        magnitudeView.setText(formattedMagnitude);

        GradientDrawable magnitudeCircle = (GradientDrawable) magnitudeView.getBackground();
        int magnitudeColor = getMagnitudeColor(currentEarthquake.getMagnitude());
        magnitudeCircle.setColor(magnitudeColor);

        String originalLocation = currentEarthquake.getLocation();

        String primaryLocation;
        String offsetLocation;

        if (originalLocation.contains(LOCATION_SEPARATOR)){
            String[] locationParts = originalLocation.split(LOCATION_SEPARATOR);
            offsetLocation = locationParts[0] + LOCATION_SEPARATOR;
            primaryLocation = locationParts[1];
        } else {
            offsetLocation = getContext().getString(R.string.near_the);
            primaryLocation = originalLocation;
        }

        TextView offsetLocationView = ListItemView.findViewById(R.id.offset_location);
        offsetLocationView.setText(offsetLocation);

        TextView primaryLocationView = ListItemView.findViewById(R.id.primary_location);
        primaryLocationView.setText(primaryLocation);

        Date dateObject = new Date(currentEarthquake.getTimeInMilliseconds());
        String formattedDate = formatDate(dateObject);

        TextView dateView =  ListItemView.findViewById(R.id.date);
        dateView.setText(formattedDate);

        String formattedTime = formatTime(dateObject);

        TextView timeView  = ListItemView.findViewById(R.id.time);
        timeView.setText(formattedTime);

        return ListItemView;
    }

    private int getMagnitudeColor(double magnitude) {
        int magnitudeColorResourceId;
        int magnitudeFloor = (int) Math.floor(magnitude);
        switch (magnitudeFloor) {
            case 0:
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

    private String formatMagnitude(double magnitude) {
        DecimalFormat magnitudeFormat = new DecimalFormat("0.0");
        return magnitudeFormat.format(magnitude);
    }

    private String formatTime(Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }

    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }
}
