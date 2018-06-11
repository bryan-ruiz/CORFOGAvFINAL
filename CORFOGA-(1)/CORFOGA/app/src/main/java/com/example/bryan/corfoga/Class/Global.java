package com.example.bryan.corfoga.Class;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.bryan.corfoga.Database.DataBaseContract;
import com.example.bryan.corfoga.Database.DataBaseHelper;

import java.util.ArrayList;

/**
 * Created by Bryan on 30/04/2018.
 */

public class Global {

    private static Global instance = null;
    private ArrayList<Region> regionsList;
    private ArrayList<Inspection> inspectionsList;
    private ArrayList<Inspection> exportInspectionsList;
    private ArrayList<InspectionVisitComment> inspectionVisitCommentList;
    private ArrayList<Animal> animalsList;
    private ArrayList<Farm> farmsList;
    private Region region;
    private Farm farm;
    private Animal animal;
    private User user;
    private int visitNumber;
    private int inspectionId;

    public static Global getInstance() {
        if(instance == null) {
            instance = new Global();
        }
        return instance;
    }

    private Global() {
        this.inspectionsList = new ArrayList<Inspection>();
        this.exportInspectionsList = new ArrayList<Inspection>();
        this.inspectionVisitCommentList = new ArrayList<InspectionVisitComment>();
        this.animalsList = new ArrayList<Animal>();
        this.farmsList = new ArrayList<Farm>();
        this.regionsList = new ArrayList<Region>();
        this.visitNumber = 0;
        this.user = null;
        this.region = null;
        this.farm = null;
        this.animal = null;
        this.inspectionId = 0;
    }

    public int getInspectionId() {
        return inspectionId;
    }

    public void setInspectionId(int inspectionId) {
        this.inspectionId = inspectionId;
    }

    public int getVisitNumber() {
        return visitNumber;
    }

    public void setVisitNumber(int visitNumber) {
        this.visitNumber = visitNumber;
    }

    public void addInspection(Inspection inspection) {
        this.inspectionsList.add(inspection);
    }

    public void addAnimal(Animal animal) {
        this.animalsList.add(animal);
    }

    public void addFarm(Farm farm) {
        this.farmsList.add(farm);
    }

    public void addRegion(Region region) {
        this.regionsList.add(region);
    }

    public void resetRegionList() {
        this.regionsList = new ArrayList<Region>();
    }

    public void resetInspectionList() {
        this.inspectionsList = new ArrayList<Inspection>();
    }

    public void resetAnimalList() {
        this.animalsList = new ArrayList<Animal>();
    }

    public void resetFarmList() {
        this.farmsList = new ArrayList<Farm>();
    }

    public ArrayList<Inspection> getInspectionsList() {
        return inspectionsList;
    }

    public void setInspectionsList(ArrayList<Inspection> inspectionsList) {
        this.inspectionsList = inspectionsList;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ArrayList<Animal> getAnimalsList() {
        return animalsList;
    }

    public void setAnimalsList(ArrayList<Animal> animalsList) {
        this.animalsList = animalsList;
    }

    public ArrayList<Farm> getFarmsList() {
        return farmsList;
    }

    public void setFarmsList(ArrayList<Farm> farmsList) {
        this.farmsList = farmsList;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public Farm getFarm() {
        return farm;
    }

    public void setFarm(Farm farm) {
        this.farm = farm;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public ArrayList<Region> getRegionsList() {
        return regionsList;
    }

    public void setRegionsList(ArrayList<Region> regionsList) {
        this.regionsList = regionsList;
    }

    public Boolean getAnimalVisitCommentState(Context context, int farmId, int animalId) {
        InspectionVisitComment inspectionVisitComment;
        String comment;
        // usar la clase DataBaseHelper para realizar la operacion de leer
        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
        // Obtiene la base de datos en modo lectura
        SQLiteDatabase db = dataBaseHelper.getReadableDatabase();
        // Define cuales columnas quiere solicitar // en este caso todas las de la clase
        String[] projection = {
                DataBaseContract.DataBaseEntry.COLUMN_NAME_ANIMAL_VISIT_ANIMAL_ID,
                DataBaseContract.DataBaseEntry.COLUMN_NAME_ANIMAL_VISIT_FARM_ID,
                DataBaseContract.DataBaseEntry.COLUMN_NAME_ANIMAL_VISIT_OBSERVATIONS
        };
        // Resultados en el cursor
        Cursor cursor = db.query(
                DataBaseContract.DataBaseEntry.TABLE_NAME_ANIMAL_VISIT, // tabla
                projection, // columnas
                null, // where
                null, // valores del where
                null, // agrupamiento
                null, // filtros por grupo
                null // orden
        );
        cursor.moveToFirst();
        do
        {
            int asocebuFarmId = cursor.getInt(cursor.getColumnIndex(DataBaseContract.DataBaseEntry.COLUMN_NAME_ANIMAL_VISIT_FARM_ID));
            int animal = cursor.getInt(cursor.getColumnIndex(DataBaseContract.DataBaseEntry.COLUMN_NAME_ANIMAL_VISIT_ANIMAL_ID));
            if (asocebuFarmId == farmId && animal == animalId) {
                return true;
            }
        }
        while (cursor.moveToNext());
        return false;
    }

    public ArrayList<Inspection> getExportListOfInspections(Context context) {
        this.exportInspectionsList = new ArrayList<Inspection>();
        Inspection inspection;
        String state, comment, scrotalCircumference, weight, datetime;
        int animalId, inspectionId, asocebuId, userId, visitNumber, feedSystem;
        // usar la clase DataBaseHelper para realizar la operacion de leer
        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
        // Obtiene la base de datos en modo lectura
        SQLiteDatabase db = dataBaseHelper.getReadableDatabase();
        // Define cuales columnas quiere solicitar // en este caso todas las de la clase
        String[] projection = {
                DataBaseContract.DataBaseEntry.COLUMN_NAME_INSPECTION_ID,
                DataBaseContract.DataBaseEntry.COLUMN_NAME_INSPECTION_ASOCEBU_FARM_ID,
                DataBaseContract.DataBaseEntry.COLUMN_NAME_INSPECTION_USER_ID,
                DataBaseContract.DataBaseEntry.COLUMN_NAME_INSPECTION_DATETIME,
                DataBaseContract.DataBaseEntry.COLUMN_NAME_INSPECTION_VISIT_NUMBER,
                DataBaseContract.DataBaseEntry.COLUMN_NAME_INSPECTION_ANIMAL_ID,
                DataBaseContract.DataBaseEntry.COLUMN_NAME_INSPECTION_FEED_SYSTEM,
                DataBaseContract.DataBaseEntry.COLUMN_NAME_INSPECTION_WEIGHT,
                DataBaseContract.DataBaseEntry.COLUMN_NAME_INSPECTION_SCROTAL_CIRCUMFERENCE,
                DataBaseContract.DataBaseEntry.COLUMN_NAME_INSPECTION_COMMENT
        };
        // Resultados en el cursor
        Cursor cursor = db.query(
                DataBaseContract.DataBaseEntry.TABLE_NAME_INSPECTION, // tabla
                projection, // columnas
                null, // where
                null, // valores del where
                null, // agrupamiento
                null, // filtros por grupo
                null // orden
        );
        cursor.moveToFirst();
        do
        {
            inspectionId = cursor.getInt(cursor.getColumnIndex(DataBaseContract.DataBaseEntry.COLUMN_NAME_INSPECTION_ID));
            animalId = cursor.getInt(cursor.getColumnIndex(DataBaseContract.DataBaseEntry.COLUMN_NAME_INSPECTION_ANIMAL_ID));
            asocebuId = cursor.getInt(cursor.getColumnIndex(DataBaseContract.DataBaseEntry.COLUMN_NAME_INSPECTION_ASOCEBU_FARM_ID));
            userId = cursor.getInt(cursor.getColumnIndex(DataBaseContract.DataBaseEntry.COLUMN_NAME_INSPECTION_USER_ID));
            datetime = cursor.getString(cursor.getColumnIndex(DataBaseContract.DataBaseEntry.COLUMN_NAME_INSPECTION_DATETIME));
            visitNumber = cursor.getInt(cursor.getColumnIndex(DataBaseContract.DataBaseEntry.COLUMN_NAME_INSPECTION_VISIT_NUMBER));
            feedSystem = cursor.getInt(cursor.getColumnIndex(DataBaseContract.DataBaseEntry.COLUMN_NAME_INSPECTION_FEED_SYSTEM));
            weight = cursor.getString(cursor.getColumnIndex(DataBaseContract.DataBaseEntry.COLUMN_NAME_INSPECTION_WEIGHT));
            scrotalCircumference = cursor.getString(cursor.getColumnIndex(DataBaseContract.DataBaseEntry.COLUMN_NAME_INSPECTION_SCROTAL_CIRCUMFERENCE));
            comment = cursor.getString(cursor.getColumnIndex(DataBaseContract.DataBaseEntry.COLUMN_NAME_INSPECTION_COMMENT));
            inspection = new Inspection(inspectionId, asocebuId, userId, datetime, visitNumber, animalId, feedSystem, weight, scrotalCircumference, comment);
            this.exportInspectionsList.add(inspection);
        }
        while (cursor.moveToNext());
        return this.exportInspectionsList;
    }

    public ArrayList<Farm> getAllFarms(Context context) {
        this.farmsList = new ArrayList<Farm>();
        Farm farm;
        String farmName, state, created, updated;
        int asocebuId, userId, region;
        // usar la clase DataBaseHelper para realizar la operacion de leer
        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
        // Obtiene la base de datos en modo lectura
        SQLiteDatabase db = dataBaseHelper.getReadableDatabase();
        // Define cuales columnas quiere solicitar // en este caso todas las de la clase
        String[] projection = {
                DataBaseContract.DataBaseEntry.COLUMN_NAME_FARM_ASOCEBU_ID,
                DataBaseContract.DataBaseEntry.COLUMN_NAME_FARM_USER_ID,
                DataBaseContract.DataBaseEntry.COLUMN_NAME_FARM_NAME,
                DataBaseContract.DataBaseEntry.COLUMN_NAME_FARM_STATE,
                DataBaseContract.DataBaseEntry.COLUMN_NAME_FARM_REGION,
                DataBaseContract.DataBaseEntry.COLUMN_NAME_FARM_CREATED_AT,
                DataBaseContract.DataBaseEntry.COLUMN_NAME_FARM_UPDATED_AT
        };
        // Resultados en el cursor
        Cursor cursor = db.query(
                DataBaseContract.DataBaseEntry.TABLE_NAME_FARM, // tabla
                projection, // columnas
                null, // where
                null, // valores del where
                null, // agrupamiento
                null, // filtros por grupo
                null // orden
        );
        cursor.moveToFirst();
        do {

            region = cursor.getInt(cursor.getColumnIndex(DataBaseContract.DataBaseEntry.COLUMN_NAME_FARM_REGION));
            asocebuId = cursor.getInt(cursor.getColumnIndex(DataBaseContract.DataBaseEntry.COLUMN_NAME_FARM_ASOCEBU_ID));
            userId = cursor.getInt(cursor.getColumnIndex(DataBaseContract.DataBaseEntry.COLUMN_NAME_FARM_USER_ID));
            farmName = cursor.getString(cursor.getColumnIndex(DataBaseContract.DataBaseEntry.COLUMN_NAME_FARM_NAME));
            state = cursor.getString(cursor.getColumnIndex(DataBaseContract.DataBaseEntry.COLUMN_NAME_FARM_STATE));
            created = cursor.getString(cursor.getColumnIndex(DataBaseContract.DataBaseEntry.COLUMN_NAME_FARM_CREATED_AT));
            updated = cursor.getString(cursor.getColumnIndex(DataBaseContract.DataBaseEntry.COLUMN_NAME_FARM_UPDATED_AT));
            farm = new Farm(asocebuId, userId, farmName, state, created, updated);
            this.farmsList.add(farm);

        }
        while (cursor.moveToNext());
        return this.farmsList;
    }

}

