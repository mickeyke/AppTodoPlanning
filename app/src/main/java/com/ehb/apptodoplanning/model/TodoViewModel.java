package com.ehb.apptodoplanning.model;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.ArrayList;

public class TodoViewModel extends ViewModel {
    private MutableLiveData<ArrayList<Todo>> todos;

    public TodoViewModel(){
    }

    // dit komt later vanuit een database ... .
    public MutableLiveData<ArrayList<Todo>> getTasks() {
        if(todos == null){
            todos = new MutableLiveData<>();
            ArrayList<Todo> tempTodos = new ArrayList<>();
            tempTodos.add(new Todo("Aanmaken Project","hier gaan we een project aamken voor andriod development"));
            tempTodos.add(new Todo("list opmaken","lijst maken van alle notties"));

            todos.setValue(tempTodos);
        }
        return todos;
    }
}
