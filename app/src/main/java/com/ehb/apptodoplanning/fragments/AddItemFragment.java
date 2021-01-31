package com.ehb.apptodoplanning.fragments;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.navigation.Navigation;

import com.ehb.apptodoplanning.R;
import com.ehb.apptodoplanning.model.TodoViewModel;
import com.ehb.apptodoplanning.model.entities.Todo;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AddItemFragment extends Fragment {

    private EditText etTitle, etContent,etCreatedBy,etStartDate,etEndDate;
    private Todo selected;
    private FragmentActivity myContext;
    private EditText clickedEditText;

    private DatePickerDialog mDatePickerDialog;
    //private EditText edDate;

    public AddItemFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        myContext = (FragmentActivity) context;
    }

    public static AddItemFragment newInstance(){
        return new AddItemFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_add_item, container, false);

        setHasOptionsMenu(true);

        selected = (getArguments() != null)?(Todo) getArguments().getSerializable("passedTodo"):null;

        etTitle = rootView.findViewById(R.id.edit_title);
        etContent = rootView.findViewById(R.id.edit_description);
        etCreatedBy= rootView.findViewById(R.id.edit_createdBy);
        etStartDate= rootView.findViewById(R.id.editStartDate);
        etEndDate= rootView.findViewById(R.id.editEndDate);
        //etStartDate.setInputType( EditorInfo.TYPE_NULL);
        //etEndDate.setInputType( EditorInfo.TYPE_NULL);
        //hier nog verder aanvullen

        if(selected != null){
            //indien we iets willen update ....
            etTitle.setText(selected.getTitle());
            etContent.setText(selected.getDescription());
            etCreatedBy.setText( selected.getCreatedBy() );
            etEndDate.setText( selected.getEndDate());
            etStartDate.setText( selected.getStartDate());
            //hier nog verder aanvullen
        }

        //etStartDate = (EditText) rootView.findViewById(R.id.editStartDate);

        setDateTimeField();
        etStartDate.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                clickedEditText= (EditText) v;
                mDatePickerDialog.show();
                return false;
            }
        });

        etEndDate.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                clickedEditText = (EditText) v;
                mDatePickerDialog.show();
                return false;
            }
        });


        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated( view, savedInstanceState );
        TodoViewModel model = ViewModelProviders.of(this).get(TodoViewModel.class);
        Button btnAdd =  view.findViewById( R.id.bt_saveTasks );
        btnAdd.setOnClickListener( new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                //item toevoegen
                if(selected == null) {
                    Todo t = new Todo(etTitle.getText().toString(),etContent.getText().toString(),etCreatedBy.getText().toString(),etStartDate.getText().toString(),etEndDate.getText().toString());
                    model.insertTodo(t);
                }
                else {
                    selected.setTitle(etTitle.getText().toString());
                    selected.setDescription(etContent.getText().toString());
                    selected.setCreatedBy( etCreatedBy.getText().toString() );
                    selected.setStartDate( etStartDate.getText().toString());
                    selected.setEndDate( etEndDate.getText().toString());
                    model.updateTodo(selected);
                }
                Navigation.findNavController(getView()).navigateUp();
            }
        } );
    }

    private void setDateTimeField() {

        Calendar newCalendar = Calendar.getInstance();
        mDatePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                SimpleDateFormat sd = new SimpleDateFormat("dd-MM-yyyy");
                final Date startDate = newDate.getTime();
                String fdate = sd.format(startDate);
                clickedEditText.setText(fdate);

            }
        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
       // mDatePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis()+1000);
    }
}