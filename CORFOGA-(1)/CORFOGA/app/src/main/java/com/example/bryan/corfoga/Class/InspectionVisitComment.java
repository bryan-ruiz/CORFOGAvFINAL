package com.example.bryan.corfoga.Class;

/**
 * Created by Bryan on 11/06/2018.
 */

public class InspectionVisitComment {
    private int animalID;
    private int farmID;
    private String observations;

    public InspectionVisitComment(int animalID, String observations) {
        this.animalID = animalID;
        this.farmID = 0;
        this.observations = observations;
    }

    public int getFarmID() {
        return farmID;
    }

    public void setFarmID(int farmID) {
        this.farmID = farmID;
    }

    public int getAnimalID() {
        return animalID;
    }

    public void setAnimalID(int animalID) {
        this.animalID = animalID;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }
}
