package com.ehb.apptodoplanning.util;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.ehb.apptodoplanning.R;
import com.ehb.apptodoplanning.model.entities.Todo;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class CalendarTodoAdapter extends RecyclerView.Adapter<CalendarTodoAdapter.CalenderViewHolder> implements Filterable {

    private FragmentActivity activity;

    public CalendarTodoAdapter(FragmentActivity activity) {
        this.tasks = new ArrayList<>();
        this.activity = activity;
        this.arrayListFiltered = new ArrayList<>();
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
        //final TextView tvStartdate;

        public CalenderViewHolder(@NonNull View itemView) {
            super( itemView );
            tvTitle = itemView.findViewById( R.id.cardCal_Title );
            tvDescription = itemView.findViewById( R.id.cardCal_description );
            //tvStartdate = itemView.findViewById( R.id.cardCal_startDate );
        }
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
        Todo currentTasks = tasks.get( i );
        calenderHolder.tvTitle.setText( currentTasks.getTitle() );
        calenderHolder.tvDescription.setText( currentTasks.getDescription() );
    }

    @Override
    public int getItemCount() {
        //hoeveel items moeten er zichtbaar zetten
        return tasks.size();
    }

    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {
            @NotNull
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();

                ArrayList<Todo> arrayListFilter = new ArrayList<Todo>();
                SimpleDateFormat formatDate = new SimpleDateFormat("d-M-yyyy");
                if(constraint == null|| constraint.length() == 0) {
                    results.count = tasks.size();
                    results.values = tasks;
                } else {
                    for (Todo itemModel : tasks) {

                        String arrayDate = formatDate.format(itemModel.getStartDate());
                        if(arrayDate.toLowerCase().contains(constraint.toString().toLowerCase())) {
                            arrayListFilter.add(itemModel);
                        }
                    }
                    results.count = arrayListFilter.size();
                    results.values = arrayListFilter;

                }
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                arrayListFiltered = (ArrayList<Todo>) results.values;
                notifyDataSetChanged();

                if(arrayListFiltered.size() == 0) {
                    //Toast.makeText(context, "Not Found", Toast.LENGTH_LONG).show();
                }

            }
        };
        return filter;
    }
}


