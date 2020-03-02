package com.example.android.quakereport;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.Flushable;
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
            ListItemView = LayoutInflater.from(getContext()).inflate(R.layout.earthquake_list_item, parent, false);
        }

        Earthquake currentEarthquake = getItem(position);

        TextView magnitudeView = ListItemView.findViewById(R.id.quake_mag);
        magnitudeView.setText(currentEarthquake.getMagnitude());

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

    private String formatTime(Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }

    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }
}
