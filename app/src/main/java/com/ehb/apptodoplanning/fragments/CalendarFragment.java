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

    CalendarView calendarView;
    TextView mydate;

    public CalendarFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        //met dit crasht hij :( :( :(
        calendarView = getView().findViewById( R.id.calendarView );
        mydate = getView().findViewById( R.id.getDateText );

        calendarView.setOnDateChangeListener( new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                //String dateText = toString(year);/*+"/"+month+"/"+dayOfMonth*/
                mydate.setText( "nieuwe datum" );
            }
        } );
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_calendar, container, false);
    }

    public void onClickCalendar(){

    }
}