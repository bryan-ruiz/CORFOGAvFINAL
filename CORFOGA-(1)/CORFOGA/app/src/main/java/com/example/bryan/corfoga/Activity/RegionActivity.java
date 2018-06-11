package com.example.bryan.corfoga.Activity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.bryan.corfoga.Class.Animal;
import com.example.bryan.corfoga.Class.Farm;
import com.example.bryan.corfoga.Class.Global;
import com.example.bryan.corfoga.Class.Inspection;
import com.example.bryan.corfoga.Class.InspectionVisitComment;
import com.example.bryan.corfoga.Class.Login;
import com.example.bryan.corfoga.Class.Region;
import com.example.bryan.corfoga.Database.DataBaseHelper;
import com.example.bryan.corfoga.InternetConection.Conection;
import com.example.bryan.corfoga.InternetConection.ServerConnection;
import com.example.bryan.corfoga.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegionActivity extends AppCompatActivity {
    private Button todas, pacificoCentral, central, huetarAtlantica, brunca, huetarNorte, chorotega;
    private Intent intent;
    private Global global;
    private ArrayList<InspectionVisitComment> inspectionVisitCommentsList;
    private ServerConnection serverConection;
    private Boolean isDatabaseEmpty;
    private ProgressDialog progressDialog;
    private Handler handler;
    private Timer timer;
    private Runnable runnable;
    private int progressPercentage;
    private Login loginDB;
    private AlertDialog.Builder alert;
    private Boolean sincError;
    private Boolean exportError;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regions);
        createRegions();
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
        global = Global.getInstance();
        serverConection = ServerConnection.getInstance();
        todas = findViewById(R.id.todas);
        pacificoCentral = findViewById(R.id.pacificoCentral);
        central = findViewById(R.id.central);
        huetarAtlantica = findViewById(R.id.huetarAtlantica);
        brunca = findViewById(R.id.brunca);
        huetarNorte = findViewById(R.id.huetarNorte);
        chorotega = findViewById(R.id.chorotega);
        intent = new Intent(getBaseContext(), FarmActivity.class);
        progressPercentage = 0;
        alert = new AlertDialog.Builder(this);
        loginDB = new Login();
    }
    /*
    * metodo que contiene el on click listener de los botones. Aqui es donde se verifica primero la base de datos
    * en caso de encontrar fincas para la region entonces se ingresa a esa finca. Caso contrario al anterior,
    * se muestra un error
    *  */
    private void setOnClicks() {
        todas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkDataBase(0);
                if (Global.getInstance().getFarmsList().isEmpty()) {
                    //Toast.makeText(getApplicationContext(),"¡No hay fincas disponibles!",Toast.LENGTH_LONG).show();
                    showAlert("No hay fincas disponibles", false);
                }
                else {
                    startActivity(intent);
                }

            }
        });
        pacificoCentral.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkDataBase(3);
                if (Global.getInstance().getFarmsList().isEmpty()) {
                    showAlert("No hay fincas disponibles", false);
                }
                else {
                    startActivity(intent);
                }

            }
        });
        central.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkDataBase(1);
                if (Global.getInstance().getFarmsList().isEmpty()) {
                    showAlert("No hay fincas disponibles", false);
                }
                else {
                    startActivity(intent);
                }

            }
        });
        huetarAtlantica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkDataBase(5);
                if (Global.getInstance().getFarmsList().isEmpty()) {
                    showAlert("No hay fincas disponibles", false);
                }
                else {
                    startActivity(intent);
                }
            }
        });
        brunca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkDataBase(4);
                if (Global.getInstance().getFarmsList().isEmpty()) {
                    showAlert("No hay fincas disponibles", false);
                }
                else {
                    startActivity(intent);
                }
            }
        });
        huetarNorte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkDataBase(6);
                if (Global.getInstance().getFarmsList().isEmpty()) {
                    showAlert("No hay fincas disponibles", false);
                }
                else {
                    startActivity(intent);
                }
            }
        });
        chorotega.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkDataBase(2);
                if (Global.getInstance().getFarmsList().isEmpty()) {
                    showAlert("No hay fincas disponibles", false);
                }
                else {
                    startActivity(intent);
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
    * Metodo que muestra la barra de porcentaje a la hora de importar o exportar datos
    * */
    private void showDataLoadBar(String title, final String type) {
        progressDialog = new ProgressDialog(this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setIndeterminate(false);
        progressDialog.setTitle(title);
        progressDialog.setMessage("Por favor espere mientras se realiza la transaccion.");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();
        progressDialog.setMax(100);
        runnable = new Runnable() {
            @Override
            public void run() {
                progressPercentage = progressPercentage+2;
                if (progressPercentage <= 100) {
                    progressDialog.setProgress(progressPercentage);
                }
                else {
                    timer.cancel();
                    progressDialog.dismiss();
                    progressPercentage = 0;
                    if (type.equals("exportar")) {
                        if (exportError) {
                            showAlert("Ha ocurrido un problema en la exportación, revise su conexión a internet y vuelva a intentar.", false);
                        }
                        else {
                            showAlert("Datos exportados correctamente", true);
                            resetAndImportData();
                            showDataLoadBarSinc("¡Sincronizado!");
                        }
                    }
                    else {
                        if (sincError) {
                            showAlert("Ha ocurrido un problema en la sincronización, revise su conexión a internet y vuelva a intentar.", false);
                        }
                        else {
                            showAlert("Aplicación sincronizada correctamente", true);
                        }
                    }
                }
            }
        };
        handler = new Handler();
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(runnable);
            }
        }, 5500, 200);
    }

    private void showDataLoadBarSinc(String title) {
        progressDialog = new ProgressDialog(this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setIndeterminate(false);
        progressDialog.setTitle(title);
        progressDialog.setMessage("Por favor espere mientras se sincroniza la aplicación.");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();
        progressDialog.setMax(100);
        runnable = new Runnable() {
            @Override
            public void run() {
                progressPercentage = progressPercentage+2;
                if (progressPercentage <= 100) {
                    progressDialog.setProgress(progressPercentage);
                }
                else {
                    timer.cancel();
                    progressDialog.dismiss();
                    progressPercentage = 0;
                    showAlert("Sincronización de datos lista", true);
                }
            }
        };
        handler = new Handler();
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(runnable);
            }
        }, 5500, 200);
    }

    private void resetAndImportData() {
        DataBaseHelper dataBaseHelper = new DataBaseHelper(getApplicationContext());
        dataBaseHelper.deleteDB(this);
        importInformationFromWebServer();
        loginDB.addUserDB(getApplicationContext(), global.getUser(), global.getUser().getUsername(), global.getUser().getPassword());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.importar:
                sincError = false;
                resetAndImportData();
                showDataLoadBar("¡Importando!", "importar");
                return true;
            case R.id.exportar:
                exportError = false;
                try {
                    for (Inspection inspection : Global.getInstance().getExportListOfInspections(getApplicationContext())) {
                        Call<String> result;
                        result = serverConection.getConection().addInspection(inspection);
                        result.enqueue(new Callback<String>() {
                            @Override
                            public void onResponse(Call<String> call, Response<String> response) {
                                exportError = false;
                            }

                            @Override
                            public void onFailure(Call<String> call, Throwable t) {
                                exportError = true;
                            }
                        });
                    }
                    showDataLoadBar("¡Exportando!", "exportar");
                }
                catch (Exception e) {
                    showAlert("No hay datos para exportar", false);
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_options, menu);
        return true;
    }

    private void createRegions() {
        Global.getInstance().resetRegionList();
        String[] regionsName = {
                "Pacífico Central",
                "Central",
                "Huetar Atlántica",
                "Brunca",
                "Huetar Norte",
                "Chorotega"
        };
        int position = 1;
        while (position <= regionsName.length) {
            Region region = new Region(position, regionsName[position-1]);
            Global.getInstance().addRegion(region);
            position ++;
        }
    }

    private void importInformationFromWebServer() {
        Call<List<Farm>> farmResult;
        for (final Region region: Global.getInstance().getRegionsList()) {
            farmResult = serverConection.getConection().getFarmsFromRegion(region.getId());
            farmResult.enqueue(new Callback<List<Farm>>() {
                @Override
                public void onResponse(Call<List<Farm>> call, Response<List<Farm>> response) {
                    if (!response.body().isEmpty()) {
                        for (final Farm farm: response.body()) {
                            region.addFarmDB(getApplicationContext(), farm);
                            getAnimalsCommentState(farm);
                            getAnimalsFromFarm(farm);
                        }
                    }
                }
                @Override
                public void onFailure(Call<List<Farm>> call, Throwable t) {
                    sincError = true;
                }
            });
        }
    }

    private String setEnableVisitNumber() {
        Calendar cal = Calendar.getInstance();
        int month = cal.get(Calendar.MONTH) + 1;
        if (month >= 1 && month <=3) {
            return "1";
        }
        else if (month >= 4 && month <=6) {
            return "2";
        }
        else if (month >= 7 && month <=9) {
            return "3";
        }
        else {
            return "4";
        }
    }

    private String getAnno() {
        Calendar cal = Calendar.getInstance();
        String anno = String.valueOf(cal.get(Calendar.YEAR));
        return anno;
    }

    private void getAnimalsCommentState(final Farm farm) {
        Call<List<InspectionVisitComment>> animalResult;
        String visitNumber = setEnableVisitNumber();
        String anno = getAnno();
        animalResult = serverConection.getConection().getAnimalsState(visitNumber, String.valueOf(farm.getAsocebuID()), anno);
        animalResult.enqueue(new Callback<List<InspectionVisitComment>>() {
            @Override
            public void onResponse(Call<List<InspectionVisitComment>> call, Response<List<InspectionVisitComment>> response) {
                if (!response.body().isEmpty()) {
                    for (final InspectionVisitComment inspectionVisitComment: response.body()) {
                        Log.d("error", "-+-+-+-+-+-+-+-++-+-+-+-+-+-+JAJAJAJAJAJAJAJAJAJAJAJAJAJAJ-+-+-+-+-+--+-+-+-+-+-+--++-++");
                        inspectionVisitComment.setFarmID(farm.getAsocebuID());
                        farm.addAnimalVisitNumberDB(getApplicationContext(), inspectionVisitComment);
                    }
                }
            }
            @Override
            public void onFailure(Call<List<InspectionVisitComment>> call, Throwable t) {
                sincError = true;
            }
        });
    }

    private void getAnimalsFromFarm(final Farm farm) {
        /*inspectionVisitCommentsList = new ArrayList<InspectionVisitComment>();
        try {
            inspectionVisitCommentsList = farm.getAnimalVisitCommentState(getApplicationContext());
        }
        catch (Exception e) {
            inspectionVisitCommentsList = new ArrayList<InspectionVisitComment>();
        }*/
        Call<List<Animal>> animalResult;
        animalResult = serverConection.getConection().getAnimalsFromFarm(farm.getAsocebuID());
        animalResult.enqueue(new Callback<List<Animal>>() {
            @Override
            public void onResponse(Call<List<Animal>> call, Response<List<Animal>> response) {
                if (!response.body().isEmpty()) {
                    for (final Animal animal: response.body()) {
                        animal.setState("Normal");
                        farm.addAnimalDB(getApplicationContext(), animal);
                    }
                }
            }
            @Override
            public void onFailure(Call<List<Animal>> call, Throwable t) {
                sincError = true;
            }
        });
    }

    private void checkDataBase(int id) {
        //Log.d("checkdb", "DBDBDBDBDBDB--------|11111111111111111111111111111111");
        try {
            if (id == 0) {
                Global.getInstance().getAllFarms(getApplication());
            } else {
                for (Region region : Global.getInstance().getRegionsList()) {
                    if (region.getId() == id) {
                        Global.getInstance().setFarmsList(region.getFarmListDB(getApplicationContext()));
                    }
                }
            }
        }
        catch (Exception e) {
            showAlert("Base de datos vacía parcial o completamente, sincronice la aplicación.", false);
        }

    }
}
