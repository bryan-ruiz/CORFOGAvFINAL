package com.example.bryan.corfoga.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bryan.corfoga.Class.Animal;
import com.example.bryan.corfoga.Class.Global;
import com.example.bryan.corfoga.Class.Inspection;
import com.example.bryan.corfoga.R;

import java.util.Calendar;

public class InspectionActivity extends AppCompatActivity {
    private Animal animal;
    private Spinner feedingSpinner, commentSpinner;
    private String spinnerItemSelected, animalGender, selectedItemText;
    private TextView register, code, gender, birthdate, comments, scrotalCircumferenceTitle, cm;
    private EditText weight, scrotalCircumference;
    private AlertDialog.Builder alert;
    private Boolean blocked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inspection);
        loadEnvironment();
        setTextInformationOnView();
        checkGender();
        eventListenerForSpinnerComment();
    }

    private void loadEnvironment() {
        blocked = false;
        alert = new AlertDialog.Builder(this);
        register = (TextView) findViewById(R.id.register);
        code = (TextView) findViewById(R.id.code);
        gender = (TextView) findViewById(R.id.gender);
        birthdate = (TextView) findViewById(R.id.birthdate);
        weight = (EditText) findViewById(R.id.txtPeso);
        scrotalCircumference = (EditText) findViewById(R.id.txtCE);
        cm = (TextView) findViewById(R.id.cm);
        comments = (MultiAutoCompleteTextView) findViewById(R.id.txtObservaciones);
        animal = Global.getInstance().getAnimal();
        animalGender = String.valueOf(animal.getSex());
        feedingSpinner = (Spinner) findViewById(R.id.spinFeeding);
        commentSpinner = (Spinner) findViewById(R.id.spinComment);
        scrotalCircumferenceTitle = (TextView) findViewById(R.id.sCt);
    }

    private void situationsOnCommon() {
        comments.setEnabled(false);
        weight.setEnabled(false);
        weight.setText("0");
        blocked = true;
        scrotalCircumference.setEnabled(false);
        scrotalCircumference.setText("0");
    }

    private void eventListenerForSpinnerComment() {
        commentSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedItemText = (String) parent.getItemAtPosition(position);
                switch (selectedItemText) {
                    case "1.Normal":
                        comments.setEnabled(true);
                        comments.setText("");
                        break;
                    case "2.Muerto":
                        situationsOnCommon();
                        comments.setText("Muerto");
                        break;
                    case "3.Comercializado":
                        situationsOnCommon();
                        comments.setText("Comercializado");
                        break;
                    case "4.Externo":
                        situationsOnCommon();
                        comments.setText("Externo");
                        break;
                    default:
                        comments.setEnabled(false);
                        comments.setText("");
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                selectedItemText = "1.Normal";
            }
        });
    }

    private void setTextInformationOnView() {
        register.setText(String.valueOf(animal.getRegister()));
        code.setText(String.valueOf(animal.getCode()));
        gender.setText(animalGender);
        birthdate.setText(String.valueOf(animal.getBirthdate()));
    }

    private void checkGender() {
        if (animalGender.equals("h") || animalGender.equals("H")) {
            scrotalCircumference.setVisibility(View.GONE);
            scrotalCircumferenceTitle.setVisibility(View.GONE);
            cm.setVisibility(View.GONE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_save, menu);
        return true;
    }

    private Integer getFeedingMethodFromSpinner() {
        if (spinnerItemSelected.equals("1.Pastoreo")) {
            return 1;
        } else if (spinnerItemSelected.equals("2.Semi Estabulación")) {
            return 2;
        } else if (spinnerItemSelected.equals("3.Estabulación")) {
            return 3;
        } else {
            return 4;
        }
    }

    private String getDatetime() {
        Calendar calendar = Calendar.getInstance();
        int seconds = calendar.get(Calendar.SECOND);
        int hour = calendar.get(Calendar.HOUR_OF_DAY); // IF YOU USE HOUR IT WILL GIVE 12 HOUR USE HOUR_OF_DAY TO GET 24 HOUR FORMAT
        int minutes = calendar.get(Calendar.MINUTE);
        int date = calendar.get(Calendar.DATE);
        int month = calendar.get(Calendar.MONTH) + 1; // in java month starts from 0 not from 1 so for december 11+1 = 12
        int year = calendar.get(Calendar.YEAR);
        String datetime = String.valueOf(year) + "-" + String.valueOf(month) + "-" + String.valueOf(date) + " " + String.valueOf(hour) + ":" + String.valueOf(minutes) + ":" + String.valueOf(seconds);
        return datetime;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.menu_item_save:
                String weightTotal = weight.getText().toString();
                String scrotalC;
                if (animalGender.equals("h") || animalGender.equals("H")) {
                    scrotalC = "0";
                }
                else {
                    scrotalC = scrotalCircumference.getText().toString();
                }
                String observations = comments.getText().toString();
                if (observations.equals("") || weightTotal.equals("") || scrotalC.equals("")) {
                    showAlert("No pueden haber espacios vacíos", false);
                }
                else {
                    if ((Integer.parseInt(weightTotal) >= 150 && Integer.parseInt(weightTotal) <= 999) || blocked) {
                        int id = Global.getInstance().getInspectionId() + 1;
                        Global.getInstance().setInspectionId(id);
                        int asocebuFarmID = animal.getAsocebuFarmID();
                        int userID = Global.getInstance().getUser().getIdUsuario();
                        String datetime = getDatetime();
                        int visitNumber = Global.getInstance().getVisitNumber();
                        int animalID = animal.getId();
                        spinnerItemSelected = feedingSpinner.getSelectedItem().toString();
                        int feedingMethodID = getFeedingMethodFromSpinner();
                        animal.setState(observations);
                        animal.update(getApplicationContext());
                        //int id, int asocebuFarmID, int userID, String datetime, int visitNumber, int animalID, int feedingMethodID, String weight, String scrotalCircumference, String observations) {
                        Inspection inspection = new Inspection(id, asocebuFarmID, userID, datetime, visitNumber, animalID, feedingMethodID, weightTotal, scrotalC, observations);
                        Global.getInstance().getAnimal().addInspectionDB(getApplicationContext(), inspection);
                        showAlert("Datos guardados correctamente", true);
                    } else {
                        showAlert("Peso no válido. Debe ser entre 150 y 999 kilogramos", false);
                        //Toast.makeText(getApplicationContext(), "¡Peso no válido!", Toast.LENGTH_LONG).show();
                    }
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    /*
    * metodo encargado de mostrar mensajes de alerta
    * */
    private void showAlert(final String message, boolean state) {
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
                if (!message.equals("No pueden haber espacios vacíos")) {
                    finish();
                }
            }
        });
        alert.show();
    }
}

