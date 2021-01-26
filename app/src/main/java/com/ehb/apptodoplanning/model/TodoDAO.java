package com.ehb.apptodoplanning.model;
import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.ehb.apptodoplanning.model.entities.Todo;

import java.util.List;

@Dao
public interface TodoDAO {
    //bepalen wat het moet doen

    @Insert
    void insertTodo(Todo t);

    @Update
    void updateTodo(Todo t); //als primary key bestaat dat wordt de rij upgedate

    @Delete
    void deleteTodo(Todo t); //kijkt naar de key en dat wordt deze gedelete

    @Query( "SELECT * FROM Todo ORDER BY createdBy" )
    LiveData<List<Todo>> getAllTodo();

    @Query("SELECT * FROM Todo ORDER BY dateCreated ASC")
    LiveData<List<Todo>> getAllNotesChronological();


}
