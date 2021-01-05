package com.ehb.apptodoplanning.model;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Todo implements Serializable {
    private String title, description,createdBy;
    private Date startDate,endDate, dateCreated;

    public Todo(String title, String description, String createdBy, String startDate, String endDate, String dateCreated) throws ParseException {
        this.title = title;
        this.description = description;
        this.createdBy = createdBy;
        //string omzetten naar datum
        this.startDate = new SimpleDateFormat("yyyy-MM-dd").parse(startDate);
        this.endDate = new SimpleDateFormat("dd-MM-yyyy").parse(endDate);
        this.dateCreated = new SimpleDateFormat("dd-MM-yyyy").parse(dateCreated);
    }

    public Todo() {
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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }
}
