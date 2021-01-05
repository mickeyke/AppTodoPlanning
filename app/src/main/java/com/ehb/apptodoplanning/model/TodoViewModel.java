package com.ehb.apptodoplanning.model;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.text.ParseException;
import java.util.ArrayList;

public class TodoViewModel extends ViewModel {
    private MutableLiveData<ArrayList<Todo>> todos;

    public TodoViewModel(){
    }

    // dit komt later vanuit een database ... .
    public MutableLiveData<ArrayList<Todo>> getTasks() throws ParseException {
        if(todos == null){
            todos = new MutableLiveData<>();
            ArrayList<Todo> tempTodos = new ArrayList<>();
            tempTodos.add(new Todo("Aanmaken Project","hier gaan we een project aamken voor andriod development","Michael Carels","2021-01-01","10-1-2021","28-12-2020"));
            tempTodos.add(new Todo("list opmaken","lijst maken van alle notties","Waldo","2021-01-03","15-01-2021","01-01-2021"));

            todos.setValue(tempTodos);
        }
        return todos;
    }
}
