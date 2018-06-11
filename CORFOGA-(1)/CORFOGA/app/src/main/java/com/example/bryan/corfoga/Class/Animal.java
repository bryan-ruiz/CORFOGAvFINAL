package com.example.bryan.corfoga.Class;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.bryan.corfoga.Database.DataBaseHelper;
import com.example.bryan.corfoga.Database.DataBaseContract;
import java.util.ArrayList;

/**
 * Created by Bryan on 26/02/2018.
 */

public class Animal {
    private int id;
    private int asocebuFarmID;
    private String register;
    private String code;
    private String sex;
    private String birthdate;
    private String fatherRegister;
    private String fatherCode;
    private String motherRegister;
    private String motherCode;
    private String state;
    private ArrayList<Inspection> inspectionsList;

    public Animal(int id, int asocebuFarmID, String register, String code, String sex, String birthdate, String fatherRegister, String fatherCode, String motherRegister, String motherCode) {
        this.id = id;
        this.asocebuFarmID = asocebuFarmID;
        this.register = register;
        this.code = code;
        this.sex = sex;
        this.birthdate = birthdate;
        this.fatherRegister = fatherRegister;
        this.fatherCode = fatherCode;
        this.motherRegister = motherRegister;
        this.motherCode = motherCode;
        this.state = "Normal";
        this.inspectionsList = new ArrayList<Inspection>();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAsocebuFarmID() {
        return asocebuFarmID;
    }

    public void setAsocebuFarmID(int asocebuFarmID) {
        this.asocebuFarmID = asocebuFarmID;
    }

    public String getRegister() {
        return register;
    }

    public void setRegister(String register) {
        this.register = register;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getFatherRegister() {
        return fatherRegister;
    }

    public void setFatherRegister(String fatherRegister) {
        this.fatherRegister = fatherRegister;
    }

    public String getFatherCode() {
        return fatherCode;
    }

    public void setFatherCode(String fatherCode) {
        this.fatherCode = fatherCode;
    }

    public String getMotherRegister() {
        return motherRegister;
    }

    public void setMotherRegister(String motherRegister) {
        this.motherRegister = motherRegister;
    }

    public String getMotherCode() {
        return motherCode;
    }

    public void setMotherCode(String motherCode) {
        this.motherCode = motherCode;
    }

    public ArrayList<Inspection> getInspectionsList() {
        return inspectionsList;
    }

    public void setInspectionsList(ArrayList<Inspection> inspectionsList) {
        this.inspectionsList = inspectionsList;
    }

    public long addInspectionDB(Context context, Inspection inspection) {
        // usar la clase DataBaseHelper para realizar la operacion de insertar
        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
        // Obtiene la base de datos en modo escritura
        SQLiteDatabase db = dataBaseHelper.getWritableDatabase();
        // Crear un mapa de valores donde las columnas son las llaves
        ContentValues values = new ContentValues();
        values.put(DataBaseContract.DataBaseEntry.COLUMN_NAME_INSPECTION_ID, inspection.getInspectionID());
        values.put(DataBaseContract.DataBaseEntry.COLUMN_NAME_INSPECTION_ASOCEBU_FARM_ID, inspection.getAsocebuFarmID());
        values.put(DataBaseContract.DataBaseEntry.COLUMN_NAME_INSPECTION_USER_ID, inspection.getUserID());
        values.put(DataBaseContract.DataBaseEntry.COLUMN_NAME_INSPECTION_DATETIME, inspection.getDatetime());
        values.put(DataBaseContract.DataBaseEntry.COLUMN_NAME_INSPECTION_VISIT_NUMBER, inspection.getVisitNumber());
        values.put(DataBaseContract.DataBaseEntry.COLUMN_NAME_INSPECTION_ANIMAL_ID, this.getId());
        values.put(DataBaseContract.DataBaseEntry.COLUMN_NAME_INSPECTION_FEED_SYSTEM, inspection.getFeedingMethodID());
        values.put(DataBaseContract.DataBaseEntry.COLUMN_NAME_INSPECTION_WEIGHT, inspection.getWeight());
        values.put(DataBaseContract.DataBaseEntry.COLUMN_NAME_INSPECTION_SCROTAL_CIRCUMFERENCE, inspection.getScrotalCircumference());
        values.put(DataBaseContract.DataBaseEntry.COLUMN_NAME_INSPECTION_COMMENT, inspection.getObservations());
        // Insertar la nueva fila
        return db.insert(DataBaseContract.DataBaseEntry.TABLE_NAME_INSPECTION, null, values);
    }

    public void update (Context context){
        this.inspectionsList = new ArrayList<Inspection>();
        String state, comment, scrotalCircumference, weight, datetime;
        int animalId, inspectionId, asocebuId, userId, visitNumber, feedSystem;
        // usar la clase DataBaseHelper para realizar la operacion de leer
        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
        // Obtiene la base de datos en modo lectura
        SQLiteDatabase db = dataBaseHelper.getReadableDatabase();
        // Define cuales columnas quiere solicitar // en este caso todas las de la clase
        String[] projection = {
                DataBaseContract.DataBaseEntry.COLUMN_NAME_ANIMAL_ID,
                DataBaseContract.DataBaseEntry.COLUMN_NAME_ANIMAL_ASOCEBU_FARM_ID,
                DataBaseContract.DataBaseEntry.COLUMN_NAME_ANIMAL_REGISTER,
                DataBaseContract.DataBaseEntry.COLUMN_NAME_ANIMAL_CODE,
                DataBaseContract.DataBaseEntry.COLUMN_NAME_ANIMAL_SEX,
                DataBaseContract.DataBaseEntry.COLUMN_NAME_ANIMAL_BIRTHDATE,
                DataBaseContract.DataBaseEntry.COLUMN_NAME_ANIMAL_FATHER_REGISTER,
                DataBaseContract.DataBaseEntry.COLUMN_NAME_ANIMAL_FATHER_CODE,
                DataBaseContract.DataBaseEntry.COLUMN_NAME_ANIMAL_MOTHER_REGISTER,
                DataBaseContract.DataBaseEntry.COLUMN_NAME_ANIMAL_MOTHER_CODE,
                DataBaseContract.DataBaseEntry.COLUMN_NAME_ANIMAL_STATE
        };
        // Resultados en el cursor
        Cursor cursor = db.query(
                DataBaseContract.DataBaseEntry.TABLE_NAME_ANIMAL, // tabla
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
            animalId = cursor.getInt(cursor.getColumnIndex(DataBaseContract.DataBaseEntry.COLUMN_NAME_ANIMAL_ID));
            asocebuId = cursor.getInt(cursor.getColumnIndex(DataBaseContract.DataBaseEntry.COLUMN_NAME_ANIMAL_ASOCEBU_FARM_ID));
            if (animalId == this.getId() && asocebuId == this.getAsocebuFarmID()) {
                ContentValues data=new ContentValues();
                data.put(DataBaseContract.DataBaseEntry.COLUMN_NAME_ANIMAL_ID,this.getId());
                data.put(DataBaseContract.DataBaseEntry.COLUMN_NAME_ANIMAL_ASOCEBU_FARM_ID,this.getAsocebuFarmID());
                data.put(DataBaseContract.DataBaseEntry.COLUMN_NAME_ANIMAL_REGISTER,this.getRegister());
                data.put(DataBaseContract.DataBaseEntry.COLUMN_NAME_ANIMAL_CODE,this.getCode());
                data.put(DataBaseContract.DataBaseEntry.COLUMN_NAME_ANIMAL_SEX,this.getSex());
                data.put(DataBaseContract.DataBaseEntry.COLUMN_NAME_ANIMAL_BIRTHDATE,this.getBirthdate());
                data.put(DataBaseContract.DataBaseEntry.COLUMN_NAME_ANIMAL_FATHER_REGISTER,this.getFatherRegister());
                data.put(DataBaseContract.DataBaseEntry.COLUMN_NAME_ANIMAL_FATHER_CODE,this.getFatherCode());
                data.put(DataBaseContract.DataBaseEntry.COLUMN_NAME_ANIMAL_MOTHER_REGISTER,this.getMotherRegister());
                data.put(DataBaseContract.DataBaseEntry.COLUMN_NAME_ANIMAL_MOTHER_CODE,this.getMotherCode());
                data.put(DataBaseContract.DataBaseEntry.COLUMN_NAME_ANIMAL_STATE,this.getState());
                db.update(DataBaseContract.DataBaseEntry.TABLE_NAME_ANIMAL, data, DataBaseContract.DataBaseEntry.COLUMN_NAME_ANIMAL_ID + "=" + this.getId() + " and " + DataBaseContract.DataBaseEntry.COLUMN_NAME_ANIMAL_ASOCEBU_FARM_ID + "=" + this.getAsocebuFarmID(), null);
                Log.d("error", "----------+++++++++++++++++++++++++---------------------+++++++++++++++++++++++++++---------------------------");
                return;
            }
        }
        while (cursor.moveToNext());
    }

}
