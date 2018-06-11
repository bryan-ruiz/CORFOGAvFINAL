package com.example.bryan.corfoga.Class;

import com.example.bryan.corfoga.Database.DataBaseHelper;
import com.example.bryan.corfoga.Database.DataBaseContract;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

public class Farm {
    private int asocebuID;
    private int userID;
    private String name;
    private String state;
    private String created_at;
    private String updated_at;
    private ArrayList<Animal> animalsList;

    public Farm(int asocebuID, int userID, String name, String state, String created_at, String updated_at) {
        this.asocebuID = asocebuID;
        this.userID = userID;
        this.name = name;
        this.state = state;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.animalsList = new ArrayList<Animal>();
    }

    public int getAsocebuID() {
        return asocebuID;
    }

    public void setAsocebuID(int asocebuID) {
        this.asocebuID = asocebuID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public ArrayList<Animal> getAnimalsList() {
        return animalsList;
    }

    public void setAnimalsList(ArrayList<Animal> animalsList) {
        this.animalsList = animalsList;
    }

    public long addAnimalDB(Context context, Animal animal) {
        // usar la clase DataBaseHelper para realizar la operacion de insertar
        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
        // Obtiene la base de datos en modo escritura
        SQLiteDatabase db = dataBaseHelper.getWritableDatabase();
        // Crear un mapa de valores donde las columnas son las llaves
        ContentValues values = new ContentValues();
        values.put(DataBaseContract.DataBaseEntry.COLUMN_NAME_ANIMAL_ID, animal.getId());
        values.put(DataBaseContract.DataBaseEntry.COLUMN_NAME_ANIMAL_ASOCEBU_FARM_ID, this.getAsocebuID());
        values.put(DataBaseContract.DataBaseEntry.COLUMN_NAME_ANIMAL_REGISTER, animal.getRegister());
        values.put(DataBaseContract.DataBaseEntry.COLUMN_NAME_ANIMAL_CODE, animal.getCode());
        values.put(DataBaseContract.DataBaseEntry.COLUMN_NAME_ANIMAL_SEX, animal.getSex());
        values.put(DataBaseContract.DataBaseEntry.COLUMN_NAME_ANIMAL_BIRTHDATE, animal.getBirthdate());
        values.put(DataBaseContract.DataBaseEntry.COLUMN_NAME_ANIMAL_FATHER_REGISTER, animal.getFatherRegister());
        values.put(DataBaseContract.DataBaseEntry.COLUMN_NAME_ANIMAL_FATHER_CODE, animal.getFatherCode());
        values.put(DataBaseContract.DataBaseEntry.COLUMN_NAME_ANIMAL_MOTHER_REGISTER, animal.getMotherRegister());
        values.put(DataBaseContract.DataBaseEntry.COLUMN_NAME_ANIMAL_MOTHER_CODE, animal.getMotherCode());
        values.put(DataBaseContract.DataBaseEntry.COLUMN_NAME_ANIMAL_STATE, animal.getState());
        // Insertar la nueva fila
        return db.insert(DataBaseContract.DataBaseEntry.TABLE_NAME_ANIMAL, null, values);
    }

    public long addAnimalVisitNumberDB(Context context, InspectionVisitComment inspectionVisitComment) {
        // usar la clase DataBaseHelper para realizar la operacion de insertar
        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
        // Obtiene la base de datos en modo escritura
        SQLiteDatabase db = dataBaseHelper.getWritableDatabase();
        // Crear un mapa de valores donde las columnas son las llaves
        ContentValues values = new ContentValues();
        Log.d("error", ";;;;;;;;;{{{{{{{{{{{}}}}}}}}}}}}}}}}}}}----{{{{{{{{{{{{{{{{}}}}}}};;;;;;;;;;;;;;");
        Log.d("error", String.valueOf(inspectionVisitComment.getAnimalID()));
        Log.d("error", String.valueOf(inspectionVisitComment.getFarmID()));
        Log.d("error", String.valueOf(inspectionVisitComment.getObservations()));
        Log.d("error", ";;;;;;;;;{{{{{{{{{{{}}}}}}}}}}}}}}}}}}}++++{{{{{{{{{{{{{{{{}}}}}}};;;;;;;;;;;;;;");

        values.put(DataBaseContract.DataBaseEntry.COLUMN_NAME_ANIMAL_VISIT_ANIMAL_ID, inspectionVisitComment.getAnimalID());
        values.put(DataBaseContract.DataBaseEntry.COLUMN_NAME_ANIMAL_VISIT_FARM_ID, inspectionVisitComment.getFarmID());
        values.put(DataBaseContract.DataBaseEntry.COLUMN_NAME_ANIMAL_VISIT_OBSERVATIONS, inspectionVisitComment.getObservations());
        // Insertar la nueva fila
        return db.insert(DataBaseContract.DataBaseEntry.TABLE_NAME_ANIMAL_VISIT, null, values);
    }

    public ArrayList<InspectionVisitComment> getAnimalVisitCommentState(Context context) {
        InspectionVisitComment inspectionVisitComment;
        Log.d("error", "/-7-7-7-7-7-=0=============================7-7-7-7-");
        ArrayList<InspectionVisitComment> list = new ArrayList<InspectionVisitComment>();
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
        Log.d("error", ";;;;;;;;;;;;;;;;;;;+++++++++++++inicio+++++++++++;;;;;;;;;;;;;;;;;;;;");
        Log.d("error", String.valueOf(cursor.getInt(cursor.getColumnIndex(DataBaseContract.DataBaseEntry.COLUMN_NAME_ANIMAL_VISIT_FARM_ID))));
        Log.d("error", String.valueOf(cursor.getInt(cursor.getColumnIndex(DataBaseContract.DataBaseEntry.COLUMN_NAME_ANIMAL_VISIT_ANIMAL_ID))));
        Log.d("error", ";;;;;;;;;;;;;;;;;;;++++++++++fin++++++++++++++;;;;;;;;;;;;;;;;;;;;");
        do
        {
            int asocebuFarmId = cursor.getInt(cursor.getColumnIndex(DataBaseContract.DataBaseEntry.COLUMN_NAME_ANIMAL_VISIT_FARM_ID));
            int animal = cursor.getInt(cursor.getColumnIndex(DataBaseContract.DataBaseEntry.COLUMN_NAME_ANIMAL_VISIT_ANIMAL_ID));
            Log.d("error", ";;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;");
            Log.d("error", String.valueOf(asocebuFarmId));
            if (asocebuFarmId == this.asocebuID) {
                Log.d("error", "/-7-7-7-7-7-7-7-7-7-7-7-7-7-7-7-7-7-7-7-7-7-7-7-7-7-7-7-7-7-7-");
                inspectionVisitComment = new InspectionVisitComment(animal, cursor.getString(cursor.getColumnIndex(DataBaseContract.DataBaseEntry.COLUMN_NAME_ANIMAL_VISIT_OBSERVATIONS)));
                inspectionVisitComment.setFarmID(asocebuFarmId);
                list.add(inspectionVisitComment);
            }
        }
        while (cursor.moveToNext());
        return list;
    }

    public ArrayList<Animal> getAnimalListDB (Context context){
        this.animalsList = new ArrayList<Animal>();
        Animal animal;
        String register, code, sex, birthdate, fatherRegister, fatherCode, motherRegister, motherCode, state;
        int asocebuFarmId, id;
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
            asocebuFarmId = cursor.getInt(cursor.getColumnIndex(DataBaseContract.DataBaseEntry.COLUMN_NAME_ANIMAL_ASOCEBU_FARM_ID));
            Log.d("farm-------------", String.valueOf(asocebuFarmId));
            Log.d("comparacion---------", String.valueOf(this.getAsocebuID()));
            if (asocebuFarmId == this.getAsocebuID()) {
                id = cursor.getInt(cursor.getColumnIndex(DataBaseContract.DataBaseEntry.COLUMN_NAME_ANIMAL_ID));
                register = cursor.getString(cursor.getColumnIndex(DataBaseContract.DataBaseEntry.COLUMN_NAME_ANIMAL_REGISTER));
                code = cursor.getString(cursor.getColumnIndex(DataBaseContract.DataBaseEntry.COLUMN_NAME_ANIMAL_CODE));
                sex = cursor.getString(cursor.getColumnIndex(DataBaseContract.DataBaseEntry.COLUMN_NAME_ANIMAL_SEX));
                birthdate = cursor.getString(cursor.getColumnIndex(DataBaseContract.DataBaseEntry.COLUMN_NAME_ANIMAL_BIRTHDATE));
                fatherRegister = cursor.getString(cursor.getColumnIndex(DataBaseContract.DataBaseEntry.COLUMN_NAME_ANIMAL_FATHER_REGISTER));
                fatherCode = cursor.getString(cursor.getColumnIndex(DataBaseContract.DataBaseEntry.COLUMN_NAME_ANIMAL_FATHER_CODE));
                motherRegister = cursor.getString(cursor.getColumnIndex(DataBaseContract.DataBaseEntry.COLUMN_NAME_ANIMAL_MOTHER_REGISTER));
                motherCode = cursor.getString(cursor.getColumnIndex(DataBaseContract.DataBaseEntry.COLUMN_NAME_ANIMAL_MOTHER_CODE));
                state = cursor.getString(cursor.getColumnIndex(DataBaseContract.DataBaseEntry.COLUMN_NAME_ANIMAL_STATE));
                animal = new Animal(id, asocebuFarmId, register, code, sex, birthdate, fatherRegister, fatherCode, motherRegister, motherCode);
                animal.setState(state);
                this.animalsList.add(animal);
            }
        }
        while (cursor.moveToNext());
        return this.animalsList;
    }
}
