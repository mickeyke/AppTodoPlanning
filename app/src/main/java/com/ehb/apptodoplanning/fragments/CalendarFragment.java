package com.ehb.apptodoplanning.fragments;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.TextView;

import com.ehb.apptodoplanning.R;
import com.ehb.apptodoplanning.model.Todo;
import com.ehb.apptodoplanning.model.TodoViewModel;
import com.ehb.apptodoplanning.util.CalendarTodoAdapter;

import java.text.ParseException;
import java.util.ArrayList;

public class CalendarFragment extends Fragment {

    CalendarView cal;
    TextView myDate;
    String date;

    CalendarTodoAdapter calAdapter;

    /*private ArrayList<Todo> items;*/

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
        //view aanmaken
        View v = inflater.inflate(R.layout.fragment_calendar, container, false);
        //zoeken naar de id van de calander en text veld
        cal = (CalendarView) v.findViewById(R.id.viewCalendar );
        myDate = (TextView) v.findViewById( R.id.getDateText );

        //verwijzen naar de interface
        final RecyclerView rvCalTasks = v.findViewById( R.id.rv_calTodo );

        //aanmaken addapter
        calAdapter = new CalendarTodoAdapter();
        rvCalTasks.setAdapter( calAdapter );
        //opvragen data -> view model
        final TodoViewModel model = ViewModelProviders.of(this).get( TodoViewModel.class );

        //wanneer datum veranderd door op te klikken
        cal.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                int curentMonth = month + 1 ;
                date = dayOfMonth+"-"+curentMonth+"-"+year;

                    myDate.setText( dayOfMonth + "-" + curentMonth + "-" + year );
                /*kijken naar een array tasks en toon enkel degene die mogen gezien worden*/
                try {
                    model.getTasks().observeForever( new Observer<ArrayList<Todo>>() {
                        @Override
                        public void onChanged(ArrayList<Todo> todos) {

                            calAdapter.addTodos( todos );

                            //TODO eens checken : https://camposha.info/android-custom-filter-search-listview-with-images-text-baseadapter/
                           // calAdapter.filterOut( date );
                        }
                    });
                } catch (ParseException e) {
                    e.printStackTrace(); // dit is er bij gekomen na dat er iin de constructor een parseExeption is toegevoegd
                }

                //layout manager instellen voor te tonen goe je de card wilt te zien krijgen
                RecyclerView.LayoutManager manager = new LinearLayoutManager( getContext(),RecyclerView.VERTICAL,false );
                rvCalTasks.setLayoutManager( manager );
            }
        });


        // Inflate the layout for this fragment
        return v;
    }
}