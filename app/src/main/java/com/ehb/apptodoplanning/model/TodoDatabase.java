package com.ehb.apptodoplanning.model;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

import com.ehb.apptodoplanning.model.entities.Todo;
import com.ehb.apptodoplanning.model.util.DateConverters;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database( version = 1, entities = {Todo.class})
@TypeConverters( {DateConverters.class} )
public abstract class TodoDatabase extends RoomDatabase {

    private static TodoDatabase instance;
    private static final String DB_NAME = "todoDatabase.db";
    //nodig voor threads
    static final ExecutorService databaseExecutor = Executors.newFixedThreadPool( 4 );

    public static TodoDatabase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context, TodoDatabase.class, DB_NAME).build();
        }
        return instance;
    }

    public abstract TodoDAO getTodoDAO();


}
