package com.example.bryan.corfoga.Database;
import android.provider.BaseColumns;

public class DataBaseContract {
    // Implementa la interfaz BaseColumns para heredar campos estandar del marco de Android _ID
    public static class DataBaseEntry implements BaseColumns {
        public static final String TABLE_NAME_ANIMAL_VISIT = "Visit";
        public static final String COLUMN_NAME_ANIMAL_VISIT_ANIMAL_ID = "animalID";
        public static final String COLUMN_NAME_ANIMAL_VISIT_FARM_ID = "farmID";
        public static final String COLUMN_NAME_ANIMAL_VISIT_OBSERVATIONS = "observations";

        public static final String TABLE_NAME_USER = "User";
        public static final String COLUMN_NAME_USER_ID = "id";
        public static final String COLUMN_NAME_USER_NAME = "username";
        public static final String COLUMN_NAME_USER_PASSWORD = "password";

        public static final String TABLE_NAME_FARM = "Farm";
        public static final String COLUMN_NAME_FARM_ASOCEBU_ID = "asocebuID";
        public static final String COLUMN_NAME_FARM_USER_ID = "userID";
        public static final String COLUMN_NAME_FARM_NAME = "name";
        public static final String COLUMN_NAME_FARM_STATE = "state";
        public static final String COLUMN_NAME_FARM_REGION = "region";
        public static final String COLUMN_NAME_FARM_CREATED_AT = "created_at";
        public static final String COLUMN_NAME_FARM_UPDATED_AT = "updated_at";

        public static final String TABLE_NAME_ANIMAL = "Animal";
        public static final String COLUMN_NAME_ANIMAL_ID = "id";
        public static final String COLUMN_NAME_ANIMAL_ASOCEBU_FARM_ID = "asocebuFarmID";
        public static final String COLUMN_NAME_ANIMAL_REGISTER = "register";
        public static final String COLUMN_NAME_ANIMAL_CODE = "code";
        public static final String COLUMN_NAME_ANIMAL_SEX = "sex";
        public static final String COLUMN_NAME_ANIMAL_BIRTHDATE = "birthdate";
        public static final String COLUMN_NAME_ANIMAL_FATHER_REGISTER = "fatherRegister";
        public static final String COLUMN_NAME_ANIMAL_FATHER_CODE = "fatherCode";
        public static final String COLUMN_NAME_ANIMAL_MOTHER_REGISTER = "motherRegister";
        public static final String COLUMN_NAME_ANIMAL_MOTHER_CODE = "motherCode";
        public static final String COLUMN_NAME_ANIMAL_STATE = "state";

        public static final String TABLE_NAME_INSPECTION = "Inspection";
        public static final String COLUMN_NAME_INSPECTION_ID = "id";
        public static final String COLUMN_NAME_INSPECTION_ASOCEBU_FARM_ID = "asocebuFarmID";
        public static final String COLUMN_NAME_INSPECTION_USER_ID = "userID";
        public static final String COLUMN_NAME_INSPECTION_DATETIME = "datetime";
        public static final String COLUMN_NAME_INSPECTION_VISIT_NUMBER = "visitNumber";
        public static final String COLUMN_NAME_INSPECTION_ANIMAL_ID = "animalID";
        public static final String COLUMN_NAME_INSPECTION_FEED_SYSTEM = "feedingMethodID";
        public static final String COLUMN_NAME_INSPECTION_WEIGHT = "weight";
        public static final String COLUMN_NAME_INSPECTION_SCROTAL_CIRCUMFERENCE = "scrotalCircumference";
        public static final String COLUMN_NAME_INSPECTION_COMMENT = "observations";
        public static final String COLUMN_NAME_INSPECTION_STATE = "state";
    }

    // Construir las tablas de la base de datos
    private static final String TEXT_TYPE = " TEXT";
    private static final String INTEGER_TYPE = " INTEGER";
    private static final String COMMA_SEP = ",";

    // Creacion de tablas
    public static final String SQL_CREATE_ANIMAL_VISIT =
            "CREATE TABLE " + DataBaseEntry.TABLE_NAME_ANIMAL_VISIT + " (" +
                    DataBaseEntry.COLUMN_NAME_ANIMAL_VISIT_ANIMAL_ID + INTEGER_TYPE + "PRIMARY KEY" + COMMA_SEP +
                    DataBaseEntry.COLUMN_NAME_ANIMAL_VISIT_FARM_ID + INTEGER_TYPE + COMMA_SEP +
                    DataBaseEntry.COLUMN_NAME_ANIMAL_VISIT_OBSERVATIONS + TEXT_TYPE +")";

    public static final String SQL_DELETE_ANIMAL_VISIT =
            "DROP TABLE IF EXISTS " + DataBaseEntry.TABLE_NAME_ANIMAL_VISIT;

    public static final String SQL_CREATE_USER =
            "CREATE TABLE " + DataBaseEntry.TABLE_NAME_USER + " (" +
                    DataBaseEntry.COLUMN_NAME_USER_ID + INTEGER_TYPE + "PRIMARY KEY" + COMMA_SEP +
                    DataBaseEntry.COLUMN_NAME_USER_NAME + TEXT_TYPE  + COMMA_SEP +
                    DataBaseEntry.COLUMN_NAME_USER_PASSWORD + TEXT_TYPE +")";

    public static final String SQL_DELETE_USER =
            "DROP TABLE IF EXISTS " + DataBaseEntry.TABLE_NAME_USER;

    public static final String SQL_CREATE_FARM =
            "CREATE TABLE " + DataBaseEntry.TABLE_NAME_FARM + " (" +
                    DataBaseEntry.COLUMN_NAME_FARM_ASOCEBU_ID + INTEGER_TYPE + "PRIMARY KEY" + COMMA_SEP +
                    DataBaseEntry.COLUMN_NAME_FARM_USER_ID + INTEGER_TYPE  + COMMA_SEP +
                    DataBaseEntry.COLUMN_NAME_FARM_NAME + TEXT_TYPE  + COMMA_SEP +
                    DataBaseEntry.COLUMN_NAME_FARM_STATE + TEXT_TYPE  + COMMA_SEP +
                    DataBaseEntry.COLUMN_NAME_FARM_REGION + INTEGER_TYPE  + COMMA_SEP +
                    DataBaseEntry.COLUMN_NAME_FARM_CREATED_AT + TEXT_TYPE  + COMMA_SEP +
                    DataBaseEntry.COLUMN_NAME_FARM_UPDATED_AT + TEXT_TYPE +")";

    public static final String SQL_DELETE_FARM =
            "DROP TABLE IF EXISTS " + DataBaseEntry.TABLE_NAME_FARM;

    public static final String SQL_CREATE_ANIMAL =
            "CREATE TABLE " + DataBaseEntry.TABLE_NAME_ANIMAL + " (" +
                    DataBaseEntry.COLUMN_NAME_ANIMAL_ID + INTEGER_TYPE + "PRIMARY KEY" + COMMA_SEP +
                    DataBaseEntry.COLUMN_NAME_ANIMAL_ASOCEBU_FARM_ID + INTEGER_TYPE + COMMA_SEP +
                    DataBaseEntry.COLUMN_NAME_ANIMAL_REGISTER + TEXT_TYPE + COMMA_SEP +
                    DataBaseEntry.COLUMN_NAME_ANIMAL_CODE + TEXT_TYPE + COMMA_SEP +
                    DataBaseEntry.COLUMN_NAME_ANIMAL_SEX + TEXT_TYPE + COMMA_SEP +
                    DataBaseEntry.COLUMN_NAME_ANIMAL_BIRTHDATE + TEXT_TYPE + COMMA_SEP +
                    DataBaseEntry.COLUMN_NAME_ANIMAL_FATHER_REGISTER + TEXT_TYPE + COMMA_SEP +
                    DataBaseEntry.COLUMN_NAME_ANIMAL_FATHER_CODE + TEXT_TYPE + COMMA_SEP +
                    DataBaseEntry.COLUMN_NAME_ANIMAL_MOTHER_REGISTER + TEXT_TYPE + COMMA_SEP +
                    DataBaseEntry.COLUMN_NAME_ANIMAL_MOTHER_CODE + TEXT_TYPE + COMMA_SEP +
                    DataBaseEntry.COLUMN_NAME_ANIMAL_STATE + TEXT_TYPE + COMMA_SEP +
                    "FOREIGN KEY(" + DataBaseEntry.COLUMN_NAME_ANIMAL_ASOCEBU_FARM_ID + ") REFERENCES " +
                    DataBaseEntry.TABLE_NAME_FARM + "(" + DataBaseEntry.COLUMN_NAME_FARM_ASOCEBU_ID +
                    "))";
    public static final String SQL_DELETE_ANIMAL =
            "DROP TABLE IF EXISTS " + DataBaseEntry.TABLE_NAME_ANIMAL;

    public static final String SQL_CREATE_INSPECTION =
            "CREATE TABLE " + DataBaseEntry.TABLE_NAME_INSPECTION + " (" +
                    DataBaseEntry.COLUMN_NAME_INSPECTION_ID + INTEGER_TYPE + "PRIMARY KEY" + COMMA_SEP +
                    DataBaseEntry.COLUMN_NAME_INSPECTION_ASOCEBU_FARM_ID + INTEGER_TYPE + COMMA_SEP +
                    DataBaseEntry.COLUMN_NAME_INSPECTION_USER_ID + INTEGER_TYPE + COMMA_SEP +
                    DataBaseEntry.COLUMN_NAME_INSPECTION_DATETIME + TEXT_TYPE + COMMA_SEP +
                    DataBaseEntry.COLUMN_NAME_INSPECTION_VISIT_NUMBER + INTEGER_TYPE + COMMA_SEP +
                    DataBaseEntry.COLUMN_NAME_INSPECTION_ANIMAL_ID  + INTEGER_TYPE + COMMA_SEP +
                    DataBaseEntry.COLUMN_NAME_INSPECTION_FEED_SYSTEM + INTEGER_TYPE + COMMA_SEP +
                    DataBaseEntry.COLUMN_NAME_INSPECTION_WEIGHT + TEXT_TYPE + COMMA_SEP +
                    DataBaseEntry.COLUMN_NAME_INSPECTION_SCROTAL_CIRCUMFERENCE + TEXT_TYPE + COMMA_SEP +
                    DataBaseEntry.COLUMN_NAME_INSPECTION_COMMENT + TEXT_TYPE + COMMA_SEP +
                    "FOREIGN KEY(" + DataBaseEntry.COLUMN_NAME_ANIMAL_ASOCEBU_FARM_ID + ") REFERENCES " +
                    DataBaseEntry.TABLE_NAME_FARM + "(" + DataBaseEntry.COLUMN_NAME_FARM_ASOCEBU_ID +
                    ")"+ COMMA_SEP +
                    "FOREIGN KEY(" + DataBaseEntry.COLUMN_NAME_INSPECTION_USER_ID + ") REFERENCES " +
                    DataBaseEntry.TABLE_NAME_USER + "(" + DataBaseEntry.COLUMN_NAME_USER_ID +
                    ")"+ COMMA_SEP +
                    "FOREIGN KEY(" + DataBaseEntry.COLUMN_NAME_INSPECTION_ANIMAL_ID + ") REFERENCES " +
                    DataBaseEntry.TABLE_NAME_ANIMAL + "(" + DataBaseEntry.COLUMN_NAME_ANIMAL_ID +
                    "))";

    public static final String SQL_DELETE_INSPECTION  =
            "DROP TABLE IF EXISTS " + DataBaseEntry.TABLE_NAME_INSPECTION;
}