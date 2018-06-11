package com.example.bryan.corfoga.Class;

import com.example.bryan.corfoga.Database.DataBaseHelper;
import com.example.bryan.corfoga.Database.DataBaseContract;

public class Inspection {
    private int inspectionID;
    private int asocebuFarmID;
    private int userID;
    private String datetime;
    private int visitNumber;
    private int animalID;
    private int feedingMethodID;
    private String weight;
    private String scrotalCircumference;
    private String observations;

    public Inspection(int inspectionID, int asocebuFarmID, int userID, String datetime, int visitNumber, int animalID, int feedingMethodID, String weight, String scrotalCircumference, String observations) {
        this.inspectionID = inspectionID;
        this.asocebuFarmID = asocebuFarmID;
        this.userID = userID;
        this.datetime = datetime;
        this.visitNumber = visitNumber;
        this.animalID = animalID;
        this.feedingMethodID = feedingMethodID;
        this.weight = weight;
        this.scrotalCircumference = scrotalCircumference;
        this.observations = observations;
    }

    public int getInspectionID() {
        return inspectionID;
    }

    public void setInspectionID(int id) {
        this.inspectionID = id;
    }

    public int getAsocebuFarmID() {
        return asocebuFarmID;
    }

    public void setAsocebuFarmID(int asocebuFarmID) {
        this.asocebuFarmID = asocebuFarmID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public int getVisitNumber() {
        return visitNumber;
    }

    public void setVisitNumber(int visitNumber) {
        this.visitNumber = visitNumber;
    }

    public int getAnimalID() {
        return animalID;
    }

    public void setAnimalID(int animalID) {
        this.animalID = animalID;
    }

    public int getFeedingMethodID() {
        return feedingMethodID;
    }

    public void setFeedingMethodID(int feedingMethodID) {
        this.feedingMethodID = feedingMethodID;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getScrotalCircumference() {
        return scrotalCircumference;
    }

    public void setScrotalCircumference(String scrotalCircumference) {
        this.scrotalCircumference = scrotalCircumference;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

}
