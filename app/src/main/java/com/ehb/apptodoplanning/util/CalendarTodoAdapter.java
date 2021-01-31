package com.ehb.apptodoplanning.util;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.navigation.Navigation;

import com.ehb.apptodoplanning.R;
import com.ehb.apptodoplanning.model.entities.Todo;

import org.jetbrains.annotations.NotNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CalendarTodoAdapter extends RecyclerView.Adapter<CalendarTodoAdapter.CalenderViewHolder> implements Filterable {

    private FragmentActivity activity;


    public CalendarTodoAdapter(FragmentActivity activity) {
        this.tasks = new ArrayList<>();
        this.activity = activity;
        this.arrayListFiltered = tasks;
    }

    public void addCalTodos(ArrayList<Todo> todos) {
        tasks = todos;
        //arrayListFiltered = tasks;
        notifyDataSetChanged();
    }

    //verwijzen naar de cardlayout
    //innerclass maken voor een view holder - wordt enkel binnen de adapter gebruikt
    class CalenderViewHolder extends RecyclerView.ViewHolder {
        final TextView tvTitle;
        final TextView tvDescription;
        final ImageButton btnDestails;

        public CalenderViewHolder(@NonNull View itemView) {
            super( itemView );
            tvTitle = itemView.findViewById( R.id.cardCal_Title );
            tvDescription = itemView.findViewById( R.id.cardCal_description );
            btnDestails =  itemView.findViewById(R.id.bt_calmoreInfo);
            btnDestails.setOnClickListener(detailsListener);
        }

        private View.OnClickListener detailsListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = getAdapterPosition();
                Todo toPass = arrayListFiltered.get(pos);

                Bundle data = new Bundle();
                data.putSerializable("passedTodo", toPass);

                Navigation.findNavController(itemView).navigate(R.id.action_calendarFragment_to_addItemFragment, data);
            }
        };

    }

    //ergens data nodig een array list met tasks
    private ArrayList<Todo> tasks;
    private ArrayList<Todo> arrayListFiltered;


    //binden van viewholder, kunnen wijzigen naar de juiste layout
    @NonNull
    @Override
    public CalenderViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        //in welke context komt alles ( activity moet er gebruikt worden )
        Context mContext = viewGroup.getContext();
        //haal inflater uit context
        LayoutInflater mLayoutinflater = LayoutInflater.from( mContext );
        //build card
        View card = mLayoutinflater.inflate( R.layout.calendar_card, viewGroup, false );

        return new CalenderViewHolder( card );
    }

    @Override
    public void onBindViewHolder(@NonNull CalenderViewHolder calenderHolder, int i) {
        //invullen met items
        //filter van de datum bij elke klik
        //Todo currentTasks = tasks.get( i );
        Todo currentTasks =  arrayListFiltered.get( i );
        calenderHolder.tvTitle.setText( currentTasks.getTitle() );
        calenderHolder.tvDescription.setText( currentTasks.getDescription() );
    }

    @Override
    public int getItemCount() {
        //hoeveel items moeten er zichtbaar zetten
        //return tasks.size();
        return arrayListFiltered.size();
    }

    @Override
    public Filter getFilter() {
        return exampleFilter;
    }
    private Filter exampleFilter = new Filter() {

            @RequiresApi(api = Build.VERSION_CODES.O)
            @NotNull
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                //Zoeken naar oplossing : filter werkt niet meer na 2de klik ???
                List<Todo> filteredList = new ArrayList<>();
                if (constraint == null || constraint.length() == 0) {
                    filteredList.addAll(tasks);
                } else {

                    String stringFilterPattern = constraint.toString().toLowerCase().trim();
                    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                    for ( Todo item : tasks) {

                        Date minDate = null;
                        Date  maxDate = null;
                        Date filterPattern = null;
                        try {
                            minDate = formatter.parse(item.getStartDate()  );
                            maxDate = formatter.parse( item.getEndDate());
                            filterPattern = formatter.parse( (String) constraint );
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }

                        if (!filterPattern.before( minDate) && !filterPattern.after(maxDate)  ) {
                            filteredList.add(item);
                        }
                    }
                }
                FilterResults results = new FilterResults();
                results.values = filteredList;
                return results;

            }

            @Override
            protected void publishResults(CharSequence constraint, @NotNull FilterResults results) {

                arrayListFiltered.clear();
                arrayListFiltered.addAll((List) results.values);
                notifyDataSetChanged();

            }
        };
    }


