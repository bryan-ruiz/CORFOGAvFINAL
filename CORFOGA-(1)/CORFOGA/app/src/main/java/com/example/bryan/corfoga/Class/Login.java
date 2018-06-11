package com.example.bryan.corfoga.Class;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.bryan.corfoga.Database.DataBaseContract;
import com.example.bryan.corfoga.Database.DataBaseHelper;

/**
 * Created by Bryan on 30/04/2018.
 */

public class Login {
    public Login() {
    }

    public long addUserDB(Context context, User user, String username, String password) {
        // usar la clase DataBaseHelper para realizar la operacion de insertar
        Log.d("myTag", "DBDBDBDBDBDB");
        // usar la clase DataBaseHelper para realizar la operacion de insertar
        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
        // Obtiene la base de datos en modo escritura
        SQLiteDatabase db = dataBaseHelper.getWritableDatabase();
        // Crear un mapa de valores donde las columnas son las llaves
        ContentValues values = new ContentValues();
        values.put(DataBaseContract.DataBaseEntry.COLUMN_NAME_USER_ID, user.getIdUsuario());
        values.put(DataBaseContract.DataBaseEntry.COLUMN_NAME_USER_NAME, username);
        values.put(DataBaseContract.DataBaseEntry.COLUMN_NAME_USER_PASSWORD, password);
        // Insertar la nueva fila
        return db.insert(DataBaseContract.DataBaseEntry.TABLE_NAME_USER, null, values);

    }

    public User getUserFromDB (Context context, String username, String password){
        Log.d("myTag", "This is my message --------");
        try {
            String usernameDB, passwordDB;
            int idDB;
            // usar la clase DataBaseHelper para realizar la operacion de leer
            DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
            // Obtiene la base de datos en modo lectura
            SQLiteDatabase db = dataBaseHelper.getReadableDatabase();
            // Define cuales columnas quiere solicitar // en este caso todas las de la clase
            String[] projection = {
                    DataBaseContract.DataBaseEntry.COLUMN_NAME_USER_ID,
                    DataBaseContract.DataBaseEntry.COLUMN_NAME_USER_NAME,
                    DataBaseContract.DataBaseEntry.COLUMN_NAME_USER_PASSWORD
            };
            // Resultados en el cursor
            Cursor cursor = db.query(
                    DataBaseContract.DataBaseEntry.TABLE_NAME_USER, // tabla
                    projection, // columnas
                    null, // where
                    null, // valores del where
                    null, // agrupamiento
                    null, // filtros por grupo
                    null // orden
            );
            cursor.moveToFirst();
            do {
                usernameDB = cursor.getString(cursor.getColumnIndex(DataBaseContract.DataBaseEntry.COLUMN_NAME_USER_NAME));
                passwordDB = cursor.getString(cursor.getColumnIndex(DataBaseContract.DataBaseEntry.COLUMN_NAME_USER_PASSWORD));
                idDB = cursor.getInt(cursor.getColumnIndex(DataBaseContract.DataBaseEntry.COLUMN_NAME_USER_ID));
                if (usernameDB.equals(username) && passwordDB.equals(password)) {
                    Global global = Global.getInstance();
                    User user = new User(idDB);
                    global.setUser(user);
                    return user;
                }
            }
            while (cursor.moveToNext());
            return null;
        }
        catch (Exception e) {
            return null;
        }
    }
}
