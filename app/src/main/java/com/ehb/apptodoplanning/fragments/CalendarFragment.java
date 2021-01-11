package com.ehb.apptodoplanning.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.TextView;

import com.ehb.apptodoplanning.R;

public class CalendarFragment extends Fragment {

    CalendarView cal;
    TextView myDate;
    Long date;

    public CalendarFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setHasOptionsMenu(true);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_calendar, container, false);

        cal = (CalendarView) v.findViewById(R.id.calendar);
        myDate = (TextView) v.findViewById( R.id.getDateText );


        cal.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                int curentMonth = month + 1 ;
                    myDate.setText( "Year=" + year + " Month=" + curentMonth + " Day=" + dayOfMonth );
                /*kijken naar een array tasks en toon enkel degene die mogen gezien worden*/

            }
        });

        // Inflate the layout for this fragment
        return v;
    }
}