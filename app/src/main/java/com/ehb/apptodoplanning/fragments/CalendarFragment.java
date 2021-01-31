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
import com.ehb.apptodoplanning.model.TodoViewModel;
import com.ehb.apptodoplanning.model.entities.Todo;
import com.ehb.apptodoplanning.util.CalendarTodoAdapter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class CalendarFragment extends Fragment {

    CalendarView cal;
    TextView myDate;
    String pickedDate;
    CalendarTodoAdapter calAdapter;
    SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");


    public CalendarFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate( R.layout.fragment_calendar, container, false );

        cal = (CalendarView) rootView.findViewById(R.id.viewCalendar );
        //verwijzen naar de interface
        final RecyclerView rvCalender = rootView.findViewById( R.id.rv_calTodo );

        calAdapter = new CalendarTodoAdapter(getActivity());
        rvCalender.setAdapter( calAdapter );
        //kaart vullen
        //data opvragen vanuit viewmodel
        TodoViewModel modelCal =  ViewModelProviders.of(this).get( TodoViewModel.class );
        try {
            modelCal.getTasks().observeForever( new Observer<List<Todo>>() {
                @Override
                public void onChanged(List<Todo> todos) {
                    calAdapter.addCalTodos( (ArrayList<Todo>) todos );

                    Date currentTime = Calendar.getInstance().getTime();

                    String formattedDate = df.format(currentTime);

                    calAdapter.getFilter().filter( formattedDate );
                }
            } );
        } catch (ParseException e) {
            e.printStackTrace();
        }

        //wanneer datum veranderd door op te klikken
        cal.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                int curentMonth = month + 1 ;
                pickedDate = dayOfMonth+"-"+curentMonth+"-"+year;
                //  myDate.setText( dayOfMonth + "-" + curentMonth + "-" + year );

                Date newDate= null;
                try {
                    newDate = df.parse(pickedDate);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                pickedDate = df.format( newDate );

                calAdapter.getFilter().filter( pickedDate );
                //waarde terug gebruiken
            }
        });

        RecyclerView.LayoutManager manager = new LinearLayoutManager( getContext(),RecyclerView.VERTICAL,false );
        rvCalender.setLayoutManager( manager );
        return rootView;
    }

   /* @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //view aanmaken
        View v = inflater.inflate(R.layout.fragment_calendar, container, false);
        //zoeken naar de id van de calander en text veld
        cal = (CalendarView) v.findViewById(R.id.viewCalendar );
        //myDate = (TextView) v.findViewById( R.id.getDateText );

        //verwijzen naar de interface
        final RecyclerView rvCalTasks = v.findViewById( R.id.rv_calTodo );

        //aanmaken addapter
         calAdapter = new CalendarTodoAdapter();
            rvCalTasks.setAdapter( calAdapter );
        //opvragen data -> view model
        TodoViewModel model = ViewModelProviders.of(this).get( TodoViewModel.class );
        //*kijken naar een array tasks en toon enkel degene die mogen gezien worden
        try {
            model.getTasks().observeForever( new Observer<List<Todo>>() {
                @Override
                public void onChanged(List<Todo> todos) {
                   // calAdapter.addTodos((ArrayList<Todo>) todos );//dit zorgt er voor dat het alles laat zien dat er is

                   //Filter de eerste view op get date today maar dit werkt niet
                    //pas na het klikken komt er een items te zien.
                   /* Date currentTime = Calendar.getInstance().getTime();
                    SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
                    String formattedDate = df.format(currentTime);

                    calAdapter.getFilter().filter( formattedDate );
                }
            });
        } catch (ParseException e) {
            e.printStackTrace(); // dit is er bij gekomen na dat er iin de constructor een parseExeption is toegevoegd
        }


        //wanneer datum veranderd door op te klikken
        cal.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                int curentMonth = month + 1 ;
                date = dayOfMonth+"-"+curentMonth+"-"+year;
              //  myDate.setText( dayOfMonth + "-" + curentMonth + "-" + year );
                calAdapter.getFilter().filter( date );
                //waarde terug gebruiken
            }
        });

        //layout manager instellen voor te tonen goe je de card wilt te zien krijgen
        RecyclerView.LayoutManager manager = new LinearLayoutManager( getContext(),RecyclerView.VERTICAL,false );
        rvCalTasks.setLayoutManager( manager );
        // Inflate the layout for this fragment
        return v;
    }
*/
}