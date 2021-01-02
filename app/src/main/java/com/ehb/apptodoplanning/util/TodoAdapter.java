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


public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.TodoViewHolder>{

    class TodoViewHolder extends RecyclerView.ViewHolder {
        final TextView tvTitle;
        final TextView tvDescription;
        //final Button btnDestails;

        public TodoViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.card_tx_title);
            tvDescription =  itemView.findViewById(R.id.card_tx_description);
            //btnDestails =  itemView.findViewById(R.id.card_bt_details);
        }
    }

    private ArrayList<Todo> items;

    public TodoAdapter(){
        this.items = new ArrayList<>();
    }

    public void addTodos(ArrayList<Todo> newTodo){
        items = newTodo;
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
        Todo currentTask = items.get(position);
        holder.tvTitle.setText(currentTask.getTitle());
        holder.tvDescription.setText(currentTask.getDescription());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
