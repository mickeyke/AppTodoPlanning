package com.ehb.apptodoplanning.model.entities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.os.Build;
import android.support.annotation.RequiresApi;

import org.threeten.bp.LocalDate;

import java.io.Serializable;

//import java.util.Date;

@Entity
public class Todo implements Serializable {
   @PrimaryKey(autoGenerate = true)
    private long id;
    private String title, description,createdBy,startDate,endDate,dateCreated;


    //localdate trouble waarschijnlijk omdat het een string array is.
    /*private LocalDate  dateCreated;
    private LocalDate endDate;*/


    //final LocalDate dt = dtf.parseLocalDate(yourinput);

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Ignore
    public Todo(String title, String description, String createdBy, String startDate, String endDate) {
        //this.id = id;
        this.title = title;
        this.description = description;
        this.createdBy = createdBy;
        //omzetten van string naar date ?
        //final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        this.startDate = startDate;
        this.endDate = endDate;
        //this.startDate = (LocalDate) dtf.parse(startDate);
        //this.endDate = (LocalDate) dtf.parse(endDate);

       this.dateCreated = LocalDate.now().toString();
    }

    //constructors
    public Todo() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }
    /*public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public LocalDate getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
    }*/
}
