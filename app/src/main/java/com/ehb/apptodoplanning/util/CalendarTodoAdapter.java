package com.ehb.apptodoplanning.util;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ehb.apptodoplanning.R;
import com.ehb.apptodoplanning.model.Todo;

import java.util.ArrayList;

public class CalendarTodoAdapter extends RecyclerView.Adapter<CalendarTodoAdapter.CalenderViewHolder>{

    public void addTodos(ArrayList<Todo> newTodo){
        tasks = newTodo;
        notifyDataSetChanged();
    }
    //verwijzen naar de cardlayout
    //innerclass maken voor een view holder - wordt enkel binnen de adapter gebruikt
    class CalenderViewHolder extends RecyclerView.ViewHolder {
        final TextView tvTitle;
        final TextView tvDescription;
        final TextView tvStartdate;

        public CalenderViewHolder(@NonNull View itemView) {
            super( itemView );
            tvTitle = itemView.findViewById( R.id.cardCal_Title );
            tvDescription = itemView.findViewById( R.id.cardCal_description );
            tvStartdate = itemView.findViewById( R.id.cardCal_startDate );
        }
    }

    //ergens data nodig een array list met tasks
    private ArrayList<Todo> tasks;

    public void CalendarTodoAdapter(ArrayList<Todo> tasks) {
        this.tasks = tasks;
    }
    //binden van viewholder, kunnen wijzigen naar de juiste layout
    @NonNull
    @Override
    public CalenderViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //in welke context komt alles ( activity moet er gebruikt worden )
        Context mContext = viewGroup.getContext();
        //haal inflater uit context
        LayoutInflater mLayoutinflater = LayoutInflater.from( mContext );
        //build card
        View card = mLayoutinflater.inflate( R.layout.calendar_card,viewGroup,false );

        return new CalenderViewHolder( card );
    }

    @Override
    public void onBindViewHolder(@NonNull CalenderViewHolder calenderViewHolder, int i) {
        //invullen met items
        //filter van de datum bij elke klik
        Todo currentTasks = tasks.get( i );
        calenderViewHolder.tvTitle.setText(currentTasks.getTitle());
        calenderViewHolder.tvDescription.setText( currentTasks.getDescription() );
    }

    @Override
    public int getItemCount() {
        //hoeveel items moeten er zichtbaar zetten
        return tasks.size();
    }

    public void filterOut(String filter) {
        final int size = tasks.size();
        for(int i = size - 1; i>= 0; i--) {
            String taksDate = tasks.get(i).getStartDate().toString();
            if (taksDate == filter) {
                tasks.remove(i);
                notifyItemRemoved(i);
            }
        }
    }


}
