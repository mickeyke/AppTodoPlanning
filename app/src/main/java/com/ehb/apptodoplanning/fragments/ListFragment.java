package com.ehb.apptodoplanning.fragments;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.navigation.NavController;

import com.ehb.apptodoplanning.R;
import com.ehb.apptodoplanning.model.TodoViewModel;
import com.ehb.apptodoplanning.model.entities.Todo;
import com.ehb.apptodoplanning.util.TodoAdapter;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class ListFragment extends Fragment {

    TodoAdapter todoAdapter;
    private NavController mNavControler;

    public ListFragment() {
        // Required empty public constructor
    }

   public static ListFragment newInstance() {
        return new ListFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View rootView = inflater.inflate( R.layout.fragment_list, container, false );

       final RecyclerView rvTodos = rootView.findViewById( R.id.rv_todo );
       todoAdapter = new TodoAdapter(getActivity());
       rvTodos.setAdapter( todoAdapter );
        TodoViewModel model = ViewModelProviders.of(this).get( TodoViewModel.class );

        try {
            model.getTasks().observeForever( new Observer<List<Todo>>() {
                @Override
                public void onChanged(List<Todo> todos) {
                    todoAdapter.addTodos( (ArrayList<Todo>) todos );
                }
            });
        } catch (ParseException e) {
            e.printStackTrace(); // dit is er bij gekomen na dat er iin de constructor een parseExeption is toegevoegd
        }

        RecyclerView.LayoutManager manager = new LinearLayoutManager( getContext(),RecyclerView.VERTICAL,false );
       rvTodos.setLayoutManager( manager );

        return rootView;
    }

}