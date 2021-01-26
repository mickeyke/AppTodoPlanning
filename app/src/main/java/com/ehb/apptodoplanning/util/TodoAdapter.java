package com.ehb.apptodoplanning.util;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.ehb.apptodoplanning.R;
import com.ehb.apptodoplanning.model.TodoViewModel;
import com.ehb.apptodoplanning.model.entities.Todo;

import java.util.ArrayList;


public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.TodoViewHolder>{
    private FragmentActivity activity;

    class TodoViewHolder extends RecyclerView.ViewHolder {
        final TextView tvTitle,tvStartDate,tvEndDate;
        final TextView tvDescription;
        final Button btnDelete;

        //final Button btnDestails;

        public TodoViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.card_tx_title);
            tvDescription =  itemView.findViewById(R.id.card_tx_description);
            tvStartDate = itemView.findViewById( R.id.card_tx_startdate );
            tvEndDate = itemView.findViewById( R.id.card_tx_enddate );
            btnDelete = itemView.findViewById( R.id.bt_delete );
            btnDelete.setOnClickListener( deleteListener );
            //btnDestails =  itemView.findViewById(R.id.card_bt_details);
        }

        private View.OnClickListener deleteListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = getAdapterPosition();
                Todo toDelete = Allitems.get(pos);

                TodoViewModel model = ViewModelProviders.of(activity).get(TodoViewModel.class);
                model.deleteTodo(toDelete);
                notifyDataSetChanged();
            }
        };
    }

    private ArrayList<Todo> Allitems;

    public TodoAdapter(FragmentActivity activity){
        this.Allitems = new ArrayList<>();
        this.activity = activity;
    }

    public void addTodos(ArrayList<Todo> newTodo){
        Allitems = newTodo;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TodoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        //create view
        //in welke context komt alles ( activity )
        Context mContext = parent.getContext();
        //bepaal ik de inflater , haal deze uit de context
        LayoutInflater mLayoutInflater = LayoutInflater.from(mContext);
        //build card
        View card = mLayoutInflater.inflate(R.layout.todo_card,parent,false);

        return new TodoViewHolder(card);
    }

    @Override
    public void onBindViewHolder(@NonNull TodoViewHolder holder, int position) {
        Todo currentTask = Allitems.get(position);
        holder.tvTitle.setText(currentTask.getTitle());
        holder.tvDescription.setText(currentTask.getDescription());
        holder.tvStartDate.setText( currentTask.getStartDate() );

        //localdate geef problemen ik heb daarom alles in string geplaatst
        holder.tvEndDate.setText( currentTask.getDateCreated());
    }

    @Override
    public int getItemCount() {
        return Allitems.size();
    }
}
