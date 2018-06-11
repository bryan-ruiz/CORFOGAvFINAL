package com.example.bryan.corfoga.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.example.bryan.corfoga.Class.Global;
import com.example.bryan.corfoga.Class.Login;
import com.example.bryan.corfoga.Class.User;
import com.example.bryan.corfoga.InternetConection.ServerConnection;
import com.example.bryan.corfoga.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    //Variables con las que va a funcionar el activity
    private ProgressBar progressBar;
    private Button login;
    private String userName, password;
    private Login loginDB;
    private Global global;
    private AlertDialog.Builder alert;
    private Intent intent;
    private ServerConnection serverConection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loadEnvironment();
        setOnClicks();
    }
    /*
    * metodo encargado de cargar las variables que van a funcionar en el activity
    * */
    private void loadEnvironment() {
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.corfoga);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        login = (Button) findViewById(R.id.login);
        loginDB = new Login();
        progressBar.setVisibility(View.GONE);
        global = Global.getInstance();
        alert = new AlertDialog.Builder(this);
        intent = new Intent(LoginActivity.this, RegionActivity.class);
        serverConection = ServerConnection.getInstance();
    }
    /*
    * metodo que contiene el on click listener del boton para hacer login. Aqui es donde se verifica primero la base de datos
    * en caso de encontrar al usuario entonces se ingresa a la aplicacion. Caso contrario al anterior, se realiza una peticion del
    * usuario en el servidor mediante retrofit.
    *  */
    private void setOnClicks() {
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeViewTransactionState(false);
                userName = ((EditText) findViewById(R.id.userName)).getText().toString();
                password = ((EditText) findViewById(R.id.password)).getText().toString();
                //toRegionsView();
                try {
                    if (!userName.equals("") && !password.equals("")) {
                        if (checkDatabase()) {
                            changeViewTransactionState(true);
                            toRegionsView();
                        }
                        else {
                            Call<User> result = serverConection.getConection().getUser(userName, password);
                            result.enqueue(new Callback<User>() {
                                @Override
                                public void onResponse(Call<User> call, Response<User> response) {
                                    if (response.body() != null) {
                                        loginDB.addUserDB(getApplicationContext(), response.body(), userName, password);
                                        setLoggedUser( response.body().getIdUsuario());
                                        toRegionsView();
                                    }
                                    else {
                                        showAlert("¡Usuario o contraseña incorrectos, inténtelo más tarde!", false);
                                    }
                                    changeViewTransactionState(true);
                                }

                                @Override
                                public void onFailure(Call<User> call, Throwable t) {
                                    changeViewTransactionState(true);
                                    showAlert("¡Usuario o contraseña incorrectos, inténtelo más tarde!", false);
                                }
                            });
                        }
                    }
                    else {
                        changeViewTransactionState(true);
                        showAlert("¡No pueden haber espacios vacíos, inténtelo nuevamente!", false);
                    }
                }
                catch (Exception ex){
                    changeViewTransactionState(true);
                    showAlert("¡No se puede acceder al sistema, inténtelo más tarde!", false);
                }
            }
        });
    }
    /*
    * metodo encargado de mostrar mensajes de alerta
    * */
    private void showAlert(String message, boolean state) {
        if (state) {
            alert.setTitle("¡Listo!");
            alert.setIcon(R.drawable.success);
        }
        else {
            alert.setTitle("¡Error!");
            alert.setIcon(R.drawable.failed);
        }
        alert.setMessage(message);
        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        alert.show();
    }
    /*
    * metodo encargado de mostrar el cargando luego de presionar el boton de login.
    * tambien oculta el mensaje para cuando ya no es necesario.
    * */
    private void changeViewTransactionState(boolean state) {
        login.setEnabled(state);
        if (state) {
            progressBar.setVisibility(View.GONE);
        }
        else {
            progressBar.setVisibility(View.VISIBLE);
        }
    }

    private Boolean checkDatabase() {
        User existInBD = loginDB.getUserFromDB(getApplicationContext(), userName, password);
        if (existInBD != null) {
            setLoggedUser(existInBD.getIdUsuario());
            return true;
        }
        else {
            //showAlert("¡Usuario o Contraseña incorrectos. Tambien es posible que los datos no estén sincronizados, por favor, busca internet! ", false);
            return false;
        }
    }

    private void setLoggedUser(int id) {
        User user = new User(id);
        user.setUsername(userName);
        user.setPassword(password);
        global.setUser(user);
    }

    private void toRegionsView() {
        startActivity(intent);
    }
}