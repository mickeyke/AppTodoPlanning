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
            tempTodos.add(new Todo("Aanmaken Project","hier gaan we een project aamken voor andriod development","Michael Carels","1-1-2021","10-1-2021","28-12-2020"));
            tempTodos.add(new Todo("Aanmaken Project","hier gaan we een project aamken voor andriod development","Michael Carels","1-1-2021","10-1-2021","28-12-2020"));
            tempTodos.add(new Todo("list opmaken","lijst maken van alle notties","Waldo","03-01-2021","15-01-2021","01-01-2021"));
            tempTodos.add(new Todo("Nieuwe item toevoegen","het toevoegen van nieuwe todo aan de applicatie","Michael","13-01-2021","17-01-2021","13-01-2021"));
            todos.setValue(tempTodos);
        }
        return todos;
    }

}
