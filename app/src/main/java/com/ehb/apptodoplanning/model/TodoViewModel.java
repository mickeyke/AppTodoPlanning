package com.ehb.apptodoplanning.model;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.ehb.apptodoplanning.model.entities.Todo;

import java.text.ParseException;
import java.util.List;

public class TodoViewModel extends AndroidViewModel {
    private TodoDatabase mTodoDatabase; //verwijzing naar de database
    private LiveData<List<Todo>> mTodos;
    private final Application mApplication;

    public TodoViewModel(Application application) {
        super(application);
        mApplication = application;
        mTodoDatabase = TodoDatabase.getInstance( application );
        mTodos = mTodoDatabase.getTodoDAO().getAllTodo();
    }

    // dit komt later vanuit een database ... .
    public LiveData<List<Todo>> getTasks() throws ParseException {
             //return mTodoDatabase.getTodoDAO().getAllTodo() ;
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(mApplication);
        String chosen = prefs.getString("pref_order", "Alfabetical");
        switch (chosen){
            case "Alfabetical": mTodos = mTodoDatabase.getTodoDAO().getAllTodo();
                break;
            case "Chronological": mTodos = mTodoDatabase.getTodoDAO().getAllNotesChronological();
                break;
        }

        return mTodos;
    }

    public void insertTodo(Todo t){
        TodoDatabase.databaseExecutor.execute(()->{
            mTodoDatabase.getTodoDAO().insertTodo(t);
        });
    }

    public void updateTodo(Todo t){
        TodoDatabase.databaseExecutor.execute(new Runnable() {
            @Override
            public void run() {
                mTodoDatabase.getTodoDAO().updateTodo(t);
            }
        });
    }

    public void deleteTodo(Todo t){
        TodoDatabase.databaseExecutor.execute(new Runnable() {
            @Override
            public void run() {
                mTodoDatabase.getTodoDAO().deleteTodo(t);
            }
        });
    }
}
